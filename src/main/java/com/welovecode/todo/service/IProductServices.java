package com.welovecode.todo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.welovecode.todo.exception.ProductException;
import com.welovecode.todo.model.Product;
import com.welovecode.todo.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class IProductServices implements ProductServices {

	private final ProductRepository repository;
	
	@Override
	public Product saveProduct(Product product) {
		// TODO Auto-generated method stub
		log.info("Save product");
		return repository.save(product);
	}

	@Override
	public Product updateProduct(Long id, Product product) throws ProductException {
		// TODO Auto-generated method stub
		log.info("Update product");
		Product p = findProdutById(id);
		if(p != null && p.getId() == id) {
			return repository.save(product);
		} else {
			throw new ProductException("Product doesn't exist in the database");
		}
	}

	@Override
	public Page<Product> findAllProducts(int page, int size) {
		// TODO Auto-generated method stub
		log.info("Fetching date from page {} of size {}", page, size);
		return repository.findAll(PageRequest.of(page, size));
	}
	
	@Override
	public List<Product> findAllProducts() {
		// TODO Auto-generated method stub
		log.info("Fetching All Products");
		return repository.findAll();
	}

	@Override
	public Product findProdutById(long idProduct) throws ProductException {
		// TODO Auto-generated method stub
		log.info("Fetching Product with id {}", idProduct);
		return repository.findProductById(idProduct);
	}

	@Override
	public Boolean deleteProdutById(Long idProduct) {
		// TODO Auto-generated method stub
		log.info("Delete Product with id {}", idProduct);
		repository.deleteById(idProduct);
		return true;
	}

	@Override
	public List<Product> findByCategory(String category) {
		// TODO Auto-generated method stub
		log.info("Fetching Product with category {}", category);
		return repository.findProductByCategory(category);
	}

	@Override
	public List<Product> findProdutByName(String search) {
		// TODO Auto-generated method stub
		log.info("Fetching Product with search Term {}", search);
		if(search == null  || search == "") {
			repository.findAll();
		}
		return repository.findProductByName(search);
	}

	/*
	 * @Override public Page<Product> findProdutBySearch(String search, int page,
	 * int size) { // TODO Auto-generated method stub
	 * log.info("Fetching product from page {} of size {}",page,size); return
	 * repository.findProduct(search, PageRequest.of(page, size)); }
	 */

}
