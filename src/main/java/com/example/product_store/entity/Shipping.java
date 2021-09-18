package com.example.product_store.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data


@ToString
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name ;
    private String address ;
    private String phone ;

    @OneToOne(mappedBy = "shipping" , cascade = CascadeType.ALL)
    private Order order;


}
