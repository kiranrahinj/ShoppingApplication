package com.Controller;

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
import com.Enitity.User;
import com.Repository.CategoryRepository;
import com.Repository.ProductRepository;
import com.Repository.UserRepository;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class userController {
	@Autowired
	UserRepository userRepo;

	@Autowired
	CategoryRepository catRepo;

	@Autowired
	ProductRepository prodRepo;

	@GetMapping("/home")
	public String home(Model m, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		System.out.println(session);
		if (session != null) {
			List<Product> getProdById = (List<Product>) session.getAttribute("getProdById");
			m.addAttribute("getProdByIdRequest", true);
			m.addAttribute("getProdById", getProdById);
		} else {
			List<Product> product = prodRepo.findAll();
			m.addAttribute("getProdByIdRequest", false);
			m.addAttribute("prodList", product);
		}
		List<Category> category = catRepo.findAll();
		m.addAttribute("catList", category);
		return "index";
	}

	@GetMapping("/register")
	public String register() {
		return "registration";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/save_user")
	public String saveUser(@ModelAttribute User user, Model hs) {
		userRepo.save(user);
		hs.addAttribute("regMsg", true);
		return "redirect:/home";
	}

	@GetMapping("/admin")
	public String admin(HttpServletRequest req, Model m) {
		HttpSession session = req.getSession(false);
		User user = (User) session.getAttribute("admin");

		List<Category> categories = catRepo.findAll();

		if (session != null && user != null) {
			m.addAttribute("admin", true);
			m.addAttribute("adminName", user.getUserName());

			m.addAttribute("catList", categories);
			return "admin";
		} else
			return "redirect:/home";
	}

	@PostMapping("/loginUser")
	public String loginUser(@RequestParam("email") String email, @RequestParam("pass") String pass,
			HttpSession session) {
		User user = userRepo.findByUserEmail(email);

		if (user != null && user.getUserEmail().equals(email) && user.getUserPassword().equals(pass)
				&& user.getIsAdmin().equals("admin")) {
			session.setAttribute("admin", user);
			return "redirect:/admin";
		} else if (user != null && user.getUserEmail().equals(email) && user.getUserPassword().equals(pass)) {

			return "redirect:/login";
		} else {
			return "redirect:/home";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/home";
	}
}
