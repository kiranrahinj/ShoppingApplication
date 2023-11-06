package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Enitity.Category;
import com.Enitity.Product;


public interface ProductRepository extends JpaRepository<Product,Integer>{
		List<Product> findByCategory(Category category);
}
