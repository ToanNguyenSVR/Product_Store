package com.example.product_store.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data




public class image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String imageUrl ;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private  Product product ;


}
