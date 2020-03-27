package com.robert.properLad.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.robert.properLad.models.User;
import com.robert.properLad.service.UserService;
import com.robert.properLad.validator.UserValidator;

@Controller
public class UserController {
	private final UserService userService;
	private final UserValidator userValidator;
	private final String[] states = {"","AK","AL","AR","AS","AZ","CA","CO","CT","DC","DE","FL","GA","GU","HI","IA","ID","IL","IN","KS","KY","LA","MA","MD","ME","MI","MN","MO","MS","MT","NC","ND","NE","NH","NJ","NM","NV","NY","OH","OK","OR","PA","PR","RI","SC","SD","TN","TX","UT","VA","VI","VT","WA","WI","WV","WY"};
; 
	
	public UserController(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}
	@GetMapping("/")
	public String index(@ModelAttribute("user") User user, Model model, HttpSession session) {
		if(session.getAttribute("id") != null) {
			Long userId = (Long) session.getAttribute("id");
			model.addAttribute("user", userService.findUserById(userId));
		}
		return "main.jsp";
	}
	@GetMapping("/login")
	public String login(@ModelAttribute("user") User user) {
		return "index.jsp";
	}
	@PostMapping(value="/login")
    public String loginUser(@ModelAttribute("user") User user, @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
    	boolean isAuth = userService.authenticateUser(email, password);
        // if the user is authenticated, save their user id in session
    	if(isAuth) {
    		User u = userService.findByEmail(email);
    		session.setAttribute("id", u.getId());
    		return "redirect:/dashboard/orders";
    	}
        // else, add error messages and return the login page
    	model.addAttribute("error", "Invalid login. Please try again.");
    	return "index.jsp";
    }
	@GetMapping("/register")
	public String getRegister(@ModelAttribute("user") User user, Model model) {
		model.addAttribute("states", states);
		return "register.jsp";
	}
	@PostMapping(value="/register_user")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model) {
		userValidator.validate(user, result); 
		if(result.hasErrors()){
			model.addAttribute("states", states);
			return "register.jsp";
		}
		User u = userService.registerUser(user);
		session.setAttribute("id", u.getId());
		return "redirect:/";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
	@GetMapping("/error")
	public String errorHandler() {
    	return "index.jsp";
    }
	
	
}
