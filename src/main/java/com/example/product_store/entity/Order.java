package com.example.product_store.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity(name = "Order_tbl")
@Data


@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String note ;
    private double totalPrice ;
    private Date createDate ;


    @OneToOne
    @JoinColumn(name = "shopping_id")
    private Shipping shipping ;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private  Account account ;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusOrder status ;

    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

 }
