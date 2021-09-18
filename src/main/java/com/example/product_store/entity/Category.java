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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
   private String categoryName ;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusCategory status ;
    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
    private List<SubCategory> subCategories ;



}
