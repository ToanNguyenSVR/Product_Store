package com.example.product_store.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data


@ToString
public class StatusAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String status ;

    @OneToMany(mappedBy = "status" , cascade = CascadeType.ALL)
    private List<Account> accountList ;

}
