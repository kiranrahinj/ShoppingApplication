package com.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Enitity.Category;
import com.Enitity.Product;
import com.Repository.CategoryRepository;
import com.Repository.ProductRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CategoryController {

	@Autowired
	ProductRepository prodRepo;
	@Autowired
	CategoryRepository catRepo;
	
	@PostMapping("/saveCategory")
	public String saveProd(@ModelAttribute Category cat,Model m) {
		catRepo.save(cat); 
		return "redirect:/admin";
	}
	
	@GetMapping("/getProduct")
	public String getProdById(@RequestParam int id,HttpSession session,HttpServletRequest request,Model m) {
//		System.out.println(id);
		List<Product>products=new ArrayList<>();
		
		Helper help=new Helper();
		m.addAttribute("myFunc",help);
		
		if(id==100000000) {
			products=prodRepo.findAll();
		}
		else {
			Category cat=catRepo.findById(id).get();
			products=prodRepo.findByCategory(cat);
		}	
		session.setAttribute("getProdById",products);
		
		return "redirect:/home";
	}
}
