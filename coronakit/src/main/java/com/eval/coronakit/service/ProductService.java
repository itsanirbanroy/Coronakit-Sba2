package com.eval.coronakit.service;

import java.util.List;

import com.eval.coronakit.entity.ProductMaster;

public interface ProductService {

	public ProductMaster addNewProduct(ProductMaster product);
	public List<ProductMaster> getAllProducts();
	public void deleteProduct(int productId);
	public ProductMaster getProductById(int productId);
}
