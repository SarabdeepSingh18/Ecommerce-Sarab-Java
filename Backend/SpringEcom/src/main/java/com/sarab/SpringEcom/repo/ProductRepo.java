package com.sarab.SpringEcom.repo;

import com.sarab.SpringEcom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sarab.SpringEcom.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

}
