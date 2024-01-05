package com.welovecode.todo.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.welovecode.todo.dto.ProductDTO;
import com.welovecode.todo.model.Product;
import com.welovecode.todo.service.IProductServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ProductController {

	private final IProductServices service;

	private ModelMapper mapper;

	@PostMapping("/product")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Product createProduct(@RequestBody Product product) {
		return service.saveProduct(product);
	}

	@PutMapping("/product/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ProductDTO updateProduct(@PathVariable(value = "id") Long id, @RequestBody Product product)
			throws Exception {
		return mapper.map(service.updateProduct(id, product), ProductDTO.class);
	}

	@GetMapping("/products/pagination")
	@ResponseStatus(code = HttpStatus.OK)
	private Page<Product> getAllProducts(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return service.findAllProducts(page, size);

	}

	@GetMapping("/products")
	@ResponseStatus(code = HttpStatus.OK)
	private List<ProductDTO> getAllProducts() {
		List<Product> products = service.findAllProducts();
		List<ProductDTO> productsDTO = new ArrayList<>();
		for (Product p : products) {
			ProductDTO prodDTO = mapper.map(p, ProductDTO.class);
			productsDTO.add(prodDTO);
		}
		return productsDTO;
	}

	@GetMapping("/product/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ProductDTO getProductById(@PathVariable(value = "id") long id) throws Exception {
		return mapper.map(service.findProdutById(id), ProductDTO.class);
	}

	@DeleteMapping("/product/{id}") // @ResponseStatus(code = HttpStatus.NO_CONTENT) if we change Boolean by void
	@ResponseStatus(code = HttpStatus.OK)
	public Boolean deleteProduct(@PathVariable(value = "id") Long id) {
		return service.deleteProdutById(id);
	}

	@GetMapping("/product/category/{category}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProductDTO> getProductByCategory(@PathVariable(value = "category") String category) {
		List<Product> products = service.findByCategory(category);
		List<ProductDTO> productsDTO = new ArrayList<>();
		for (Product p : products) {
			ProductDTO prodDTO = mapper.map(p, ProductDTO.class);
			productsDTO.add(prodDTO);
		}
		return productsDTO;
	}

	@GetMapping("/product/search/{search}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProductDTO> searchProductByName(@PathVariable(value = "search") String search) {
		List<Product> products = service.findProdutByName(search);
		List<ProductDTO> productsDTO = new ArrayList<>();
		for (Product p : products) {
			// ProductDTO prodDTO = mapper.map(p, ProductDTO.class);
			// productsDTO.add(prodDTO);
			p.setName("search");

		}
		return productsDTO;
	}

	/*
	 * @GetMapping("/product/search2/{search}") public ResponseEntity<HttpResponse>
	 * searchProduct(@RequestParam(value="search") String search,
	 * 
	 * @RequestParam(value="page") int page,
	 * 
	 * @RequestParam(value="size") int size) { //return
	 * service.findProdutBySearch(search, page, size); return
	 * ResponseEntity.ok().body( HttpResponse.builder()
	 * .timeStamp(LocalDate.now().toString())
	 * .message("Product Retrieve successfully") .status(HttpStatus.OK)
	 * .statusCode(HttpStatus.OK.value()) .data(Map.of("data",
	 * service.findProdutBySearch(search, page, size))) .build()); }
	 */
}
