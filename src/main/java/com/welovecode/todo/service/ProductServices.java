package com.welovecode.todo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.welovecode.todo.exception.ProductException;
import com.welovecode.todo.model.Product;

public interface ProductServices {

	public Product saveProduct(Product product);
	
	public Product updateProduct(Long id, Product product) throws ProductException;
	
	public Page<Product> findAllProducts(int page, int size);
	
	public List<Product> findAllProducts();
	
	public Product findProdutById(long idProduct) throws ProductException;
	
	public Boolean deleteProdutById(Long idProduct);
	
	public List<Product> findByCategory(String category);
	
	public List<Product> findProdutByName(String search);
	
	//public Page<Product> findProdut(String search, int page, int size);
}
