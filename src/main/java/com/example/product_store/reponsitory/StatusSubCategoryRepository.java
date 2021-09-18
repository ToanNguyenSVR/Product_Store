package com.example.product_store.reponsitory;


import com.example.product_store.entity.StatusSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusSubCategoryRepository extends JpaRepository<StatusSubCategory,Integer> {
}
