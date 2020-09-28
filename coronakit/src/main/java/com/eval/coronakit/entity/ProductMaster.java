package com.eval.coronakit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity(name="PRODUCTS")
public class ProductMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message="*name should not be empty")
	@Length(min=2,message="*should be minimun 2 char length")
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	
	@Min(value=10,message="*price should be atleast 10 units")
	private Integer cost;
	
	@NotEmpty(message="*description should not be empty")
	@Length(min=5,message="*should be minimun 5 char length")	
	private String productDescription;
	
	public ProductMaster() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductMaster(int id, String productName, Integer cost, String productDescription) {
		super();
		this.id = id;
		this.productName = productName;
		this.cost = cost;
		this.productDescription = productDescription;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	@Override
	public String toString() {
		return "ProductMaster [id=" + id + ", productName=" + productName + ", cost=" + cost + ", productDescription="
				+ productDescription + "]";
	}
	
	

}

	


