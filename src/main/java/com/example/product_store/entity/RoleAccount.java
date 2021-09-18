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
public class RoleAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String role ;
    @OneToMany(mappedBy = "role" , cascade = CascadeType.ALL)
    private List<Account> accountList ;


}
