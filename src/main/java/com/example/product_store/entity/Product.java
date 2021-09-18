package com.example.product_store.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Data


@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String code ;
    private String name ;
    private int quantity ;
    private double price ;
    private String description ;
    private String imageUrl ;
    private Date createDate ;


    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    private List<image> imageList ;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusProduct status ;
    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails ;
    @ManyToOne
    @JoinColumn(name = "subCategory_id")
    private SubCategory subCategory ;


 }
