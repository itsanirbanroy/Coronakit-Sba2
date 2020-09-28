package com.eval.coronakit.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eval.coronakit.entity.ProductMaster;
import com.eval.coronakit.entity.CoronaKit;
import com.eval.coronakit.entity.KitDetail;
import com.eval.coronakit.service.CoronaKitService;
import com.eval.coronakit.service.KitDetailService;
import com.eval.coronakit.service.ProductService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CoronaKitService coronaKitService;
	
	@Autowired
	KitDetailService kitDetailService;
	
	ProductMaster product;
	
	List<KitDetail> kitProducts = new ArrayList<KitDetail>();
	
	List<ProductMaster> products;
	
	
	@RequestMapping("/home")
	public String home() {
		return "user-home";
	}
	
	@RequestMapping("/show-kit")
	public String showKit(Model model) {
		return "show-cart";
	}

	@RequestMapping("/show-list")
	public String showList(ModelMap model) {
		model.put("products", productService.getAllProducts());
		return "show-all-item-user";
	}
	
	@RequestMapping("/add-to-cart/{productId}")
	public String showKit(@PathVariable("productId") int productId, Model model, HttpSession session) {
		KitDetail kitDetail = new KitDetail();
		product = productService.getProductById(productId);
		products = productService.getAllProducts();
		Map<Integer, String> productMap = productService.getAllProducts().stream()
				.collect(Collectors.toMap(x -> x.getId(), x -> x.getProductName()));

		if (session.getAttribute("cart") == null)  {
			kitDetail.setAmount(product.getCost());
			kitDetail.setProductId(productId);
			kitDetail.setQuantity(1);
			kitDetail.setId(10);
			kitProducts.add(kitDetail);
			//kitDetailService.addKitItem(kitDetail);
			session.setAttribute("cart", kitProducts);
		} else {	
			//fetching Kit details
			kitProducts = (List<KitDetail>)session.getAttribute("cart");
			
			int index = exists(productId, kitProducts);
			if (index == -1) {
				kitDetail.setAmount(product.getCost());
				kitDetail.setProductId(productId);
				kitDetail.setQuantity(1);
				kitDetail.setId(kitProducts.size()+1);
				kitProducts.add(kitDetail);
				//kitDetailService.addKitItem(kitDetail);
			} else {
				int quantity = kitProducts.get(index).getQuantity() + 1;
				kitProducts.get(index).setQuantity(quantity);
				session.setAttribute("cart", kitProducts);
				session.setAttribute("productMap", productMap);
				return "show-cart";
			}
			
		}
		session.setAttribute("cart", kitProducts);
		session.setAttribute("productMap", productMap);
		return "show-cart";
	}
	
	@RequestMapping("/checkout")
	public String checkout(Model model, HttpSession session) {
		List<KitDetail> kitProducts = (List<KitDetail>)session.getAttribute("cart");
		
		int total = 0;
		for (KitDetail kitDetail : kitProducts) {
			int amount = kitDetail.getAmount();
			int qty = kitDetail.getQuantity();
			total = total+qty*amount;
		}
		
		CoronaKit coronaKit =new CoronaKit();
		coronaKit.setOrderDate(LocalDate.now().toString());
		coronaKit.setTotalAmount(total);
		
		model.addAttribute("coronaKit", coronaKit);
		session.setAttribute("coronaKit", coronaKit);
		return "checkout-address";
	}
	
	@RequestMapping(value="/finalize", method=RequestMethod.POST)
	public String finalizeOrder(@ModelAttribute CoronaKit coronakit, Model model, HttpSession session) {
		List<KitDetail> kitProducts = (List<KitDetail>)session.getAttribute("cart");
		
		CoronaKit cKit = (CoronaKit) session.getAttribute("coronaKit");
		cKit.setDeliveryAddress(coronakit.getDeliveryAddress());
		model.addAttribute("coronaKit", cKit);
		coronaKitService.saveKit(cKit);
		session.setAttribute("coronaKit", cKit);
		return "show-summary";
	}
	
	@RequestMapping("/delete/{itemId}")
	public String deleteItem(@PathVariable("itemId") int itemId, HttpSession session) {
		kitProducts = (List<KitDetail>)session.getAttribute("cart");
		int index = exists(itemId, kitProducts);
		if(kitProducts.get(index).getQuantity()>=2) {
			int quantity = kitProducts.get(index).getQuantity() - 1;
			kitProducts.get(index).setQuantity(quantity);
		}
		else
			kitProducts.remove(index);
		session.setAttribute("cart", kitProducts);
		
		return "show-cart";
	}
	
	private int exists(int productId, List<KitDetail> kit) {
		for (int i = 0; i < kit.size(); i++) {
			if (kit.get(i).getProductId()==productId) {
				return i;
			}
		}
		return -1;
	}

}
