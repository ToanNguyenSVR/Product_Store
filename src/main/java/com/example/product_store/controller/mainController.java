package com.example.product_store.controller;

import com.example.product_store.entity.Cart;
import com.example.product_store.entity.Category;
import com.example.product_store.entity.Product;
import com.example.product_store.entity.image;
import com.example.product_store.reponsitory.CategoryRepository;
import com.example.product_store.reponsitory.ImageRepository;
import com.example.product_store.reponsitory.ProductRepository;
import com.example.product_store.util.MathFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class mainController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ImageRepository imageRepository;


    @GetMapping("/")
    public String index() {
        return "/home";
    }

    @GetMapping("/products")
    public String getListProduct(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page
            , @RequestParam(name = "categoryId", defaultValue = "-1") int categoryId
            , @RequestParam(name = "subCategoryId", defaultValue = "-1") int subCategoryId) {


        final int PAGE_SIZE = 20;
        Page<Product> products;
        if (subCategoryId != -1) {
            products = productRepository.findBySubCategoryId(subCategoryId, PageRequest.of(page - 1, PAGE_SIZE));
            if (products.getTotalElements() == 0) {
                products = productRepository.findAll(PageRequest.of(page - 1, PAGE_SIZE));
            }
        } else if (categoryId != -1) {
            products = productRepository.findByCategoryId(categoryId, PageRequest.of(page - 1, PAGE_SIZE));
            if (products.getTotalElements() == 0) {
                products = productRepository.findAll(PageRequest.of(page - 1, PAGE_SIZE));
            }
        } else {
            products = productRepository.findAll(PageRequest.of(page - 1, PAGE_SIZE));

        }

        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", products.getTotalPages());

        return "/listProduct";
    }

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam(name = "productId") Long productId) {
        List<image> images = null;
        Product product = productRepository.findProductById(productId);
        if (product != null) {
            images = imageRepository.findByProduct(product);
            image img = new image();
            img.setImageUrl(product.getImageUrl());
            images.add(0, img);
        }
        product.setImageList(images);
        model.addAttribute("product", product);

        return "/detail";
    }

    @GetMapping("/add-to-cart")
    public String addToCart(Model model, HttpSession session, @RequestParam("productId") Long productId) {
        Product product = productRepository.findProductById(productId);
        Cart cart = new Cart();
        cart.setProductId(product.getId());
        cart.setProductCode(product.getCode());
        cart.setProductName(product.getName());
        cart.setProductQuantity(product.getQuantity());
        cart.setProductPrice(product.getPrice());
        cart.setProductDescription(product.getDescription());
        cart.setProductImageUrl(product.getImageUrl());
        cart.setQuantity(1);
        //List<Cart> carts = (List<Cart>) session.getAttribute("CARTS");

        List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
        if (listCart == null) {
            listCart = new ArrayList<>();
            listCart.add(cart);
        } else {
            boolean isExist = true;
            for (Cart c : listCart) {
                if (productId == c.getProductId()) {
                    isExist = false;
                    c.setQuantity(c.getQuantity() + 1);
                }

            }
            if (isExist) {
                listCart.add(cart);
            }
        }

        session.setAttribute("listCart", listCart);
        return "redirect:/carts";


    }

    @GetMapping("/carts")
    public String getListCart(Model model, HttpSession session) {
        List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
        if (listCart == null || listCart.size() == 0) {
            return "/emptyCart";
        }
        //tính tổng tiền
        double totalMoney = 0;
        for (Cart c : listCart) {
            totalMoney += c.getProductPrice() * c.getQuantity();
        }
        model.addAttribute("listCart", listCart);
        model.addAttribute("totalMoney", MathFunction.getMoney(totalMoney));
        return "/listCart";

    }

    @GetMapping("/delete-cart")
    public String deleteCart(Model model, HttpSession session, @RequestParam("productId") Long productId) {
        List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
        if (listCart == null || listCart.size() == 0) {
            return "/emptyCart";
        }
        boolean isError = true;
        for (Cart c : listCart) {
            if (c.getProductId() == productId) {
                listCart.remove(c);
                isError = false;
                break;
            }
        }
        if (isError) {
            return "/404";
        }
        session.setAttribute("listCart", listCart);
        return "/listCart";

    }

    @PostMapping("/update-cart")
    public String updateCart(HttpServletRequest req , HttpSession session ) {
        List<Cart> listCart = (List<Cart>) session.getAttribute("listCart") ;
        for (int i = 0 ; i < listCart.size()  ; i ++) {
            listCart.get(i).setQuantity(Integer.parseInt(req.getParameter("quantity" + i)));
        }
        session.setAttribute("listCart" , listCart);
        return"redirect:/carts";
    }

    @GetMapping("/checkout")
    public String chekout(Model model, HttpSession session) {
        List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
        if (listCart == null || listCart.size() == 0) {
            return "/emptyCart";
        }
        //tính tổng tiền
        double totalMoney = 0;
        for (Cart c : listCart) {
            totalMoney += c.getProductPrice() * c.getQuantity();
        }
        model.addAttribute("listCart", listCart);
        model.addAttribute("totalMoney", MathFunction.getMoney(totalMoney));

        return "/checkout";
    }

    @GetMapping("/prepare-shipping")
    public String prepareShipping() {



        // tự làm
        return "/prepareShipping";
    }
    @GetMapping("/search")
    public String search(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
                         @RequestParam(value = "keyword", defaultValue = "") String keyword) {

        final int PAGE_SIZE = 20;
        Page<Product> products = productRepository.search("%"+keyword+"%",PageRequest.of(page-1,PAGE_SIZE));
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", products.getTotalPages());
        return "/listProduct";
    }


}
