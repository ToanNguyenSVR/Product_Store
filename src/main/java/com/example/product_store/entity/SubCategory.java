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
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String subCategoryName ;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusSubCategory status ;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category ;

    @OneToMany(mappedBy = "subCategory" , cascade = CascadeType.ALL)
    private List<Product> products ;





}
