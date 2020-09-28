package com.eval.coronakit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eval.coronakit.dao.ProductMasterRepository;
import com.eval.coronakit.entity.ProductMaster;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMasterRepository repository;
	
	@Override
	public ProductMaster addNewProduct(ProductMaster product) {
		// TODO Auto-generated method stub
		
		return repository.save(product);
	}

	@Override
	public List<ProductMaster> getAllProducts() {
		// TODO Auto-generated method stub
		//System.out.println("Repo Size = "+repository.findAll().size());
		//List<ProductMaster> products = new ArrayList<ProductMaster>();
		//for (ProductMaster product: repository.findAll())
			//products.add(product);
		return repository.findAll();
	}

	@Override
	public void deleteProduct(int productId) {
		// TODO Auto-generated method stub
		repository.deleteById(productId);
	}

	@Override
	public ProductMaster getProductById(int productId) {
		// TODO Auto-generated method stub
		return repository.findById(productId).get();
	}

}
