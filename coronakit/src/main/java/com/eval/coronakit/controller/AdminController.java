package com.eval.coronakit.controller;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eval.coronakit.dao.ProductMasterRepository;
import com.eval.coronakit.entity.ProductMaster;
import com.eval.coronakit.service.ProductService;
import com.eval.coronakit.service.ProductServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	ProductMasterRepository productMasterRepository;
	
	@Autowired
	ProductServiceImpl productServiceImpl;

	@Autowired
	ProductService productService;
	
	@GetMapping("/home")
	public String home() {
		return "admin-home";
	}
	
	@GetMapping("/product-entry")
	public String productEntry(ProductMaster product) {
		
		return "add-new-item";
	}
	
	@PostMapping("/product-save")
	public String productSave(@ModelAttribute ProductMaster product, BindingResult result ) {
		//System.out.println("Product datas"+product.toString());
		if(result.hasErrors())
		{
			return "add-new-item";
		}
		productService.addNewProduct(product);
		return "redirect:/admin/product-list";
	}
	

	@GetMapping("/product-list")
	public String productList(ModelMap model) {
		model.put("products", productServiceImpl.getAllProducts());		
		return "show-all-item-admin";
	}
	
	@GetMapping("/product-delete/{productId}")
	public String productDelete(@PathVariable("productId") int productId) {
		productService.deleteProduct(productId);
		return "redirect:/admin/product-list";
	}
	
}
