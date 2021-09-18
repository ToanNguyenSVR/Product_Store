package com.example.product_store.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
//@Builder
@ToString
public class Cart {
    private Long productId;
    private String productCode;
    private String productName;
    private int productQuantity;
    private double productPrice;
    private String productDescription;
    private String productImageUrl;

    private int quantity;


}
