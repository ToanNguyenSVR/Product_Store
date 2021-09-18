package com.example.product_store.reponsitory;


import com.example.product_store.entity.Product;
import com.example.product_store.entity.image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<image,Integer> {
    public List<image> findByProduct (Product product);


}
