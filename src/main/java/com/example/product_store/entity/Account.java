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
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(unique = true , nullable = false)
    private String username ;
    private String  password ;
    private String  displayName ;

    private String address ;
    private String email ;
    private String  phone ;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private  RoleAccount role ;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private  StatusAccount status ;

    @OneToMany(mappedBy = "account" , cascade = CascadeType.ALL)
    private  List<Order> orders ;

 }
