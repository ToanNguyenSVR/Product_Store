package com.example.product_store.reponsitory;


import com.example.product_store.entity.StatusAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusAccountRepository extends JpaRepository<StatusAccount,Integer> {
}
