package com.welovecode.todo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.welovecode.todo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select p from Product p where p.id = :id")
	public Product findProductById(@Param(value = "id") long id);
	
	@Query("select p from Product p where p.category = :category")
	public List<Product> findProductByCategory(@Param(value="category") String category);
	
	@Query("select p from Product p where p.name = :search")
	public List<Product> findProductByName(@Param(value="search") String search);
	
	//@Query("select p from Product p where p.name = :search or p.category =search")
	//public Page<Product> findProduct(@Param(value="search") String search, Pageable pageable);
}
