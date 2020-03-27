package com.robert.properLad.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.robert.properLad.models.Cart;
import com.robert.properLad.models.Category;
import com.robert.properLad.models.Image;
import com.robert.properLad.models.Order;
import com.robert.properLad.models.Product;
import com.robert.properLad.models.ProductCategory;
import com.robert.properLad.models.User;
import com.robert.properLad.s3Services.UploadFile;
import com.robert.properLad.service.CategoryService;
import com.robert.properLad.service.ImageService;
import com.robert.properLad.service.OrderService;
import com.robert.properLad.service.ProductCategoryService;
import com.robert.properLad.service.ProductService;
import com.robert.properLad.service.UserService;
import com.robert.properLad.validator.UserValidator;

@Controller
public class AdminController implements HandlerExceptionResolver {
	private final UserService userService;
	private final CategoryService categoryService;
	private final ImageService imageService;
	private final ProductService productService;
	private final OrderService orderService;
	private final ProductCategoryService productCategoryService;
	private final UserValidator userValidator;
	
	public AdminController(UserService userService, CategoryService categoryService, ProductService productService, ProductCategoryService productCategoryService, ImageService imageService, OrderService orderService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.categoryService = categoryService;
		this.productService = productService;
		this.productCategoryService = productCategoryService;
		this.imageService = imageService;
		this.orderService = orderService;
	}
	
	@GetMapping("/admin")
	public String adminIndex(@ModelAttribute("user") User user) {
		return "admin/adminIndex.jsp";
	}
	
	// Dashboard -> Orders
	@GetMapping("/dashboard/orders")
	public String allOrders(@ModelAttribute("order") Order order, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("id");
		if (userId == null) {
			return "redirect:/";
		}
		User u = userService.findUserById(userId);
		model.addAttribute("user", u);
		model.addAttribute("orders", orderService.getAllOrders());
		return "admin/allOrders.jsp";
	}
	
	// Dashboard -> Products
	@GetMapping("/dashboard/products")
	public String allProducts(@ModelAttribute("product") Product product, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("id");
		if (userId == null) {
			return "redirect:/";
		}
		User u = userService.findUserById(userId);
		model.addAttribute("user", u);
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("products", productService.getAllProducts());
		return "admin/allProducts.jsp";
	}
	@SuppressWarnings("null")
	@PostMapping(value="/create_product")
//	@ResponseBody
	public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, @ModelAttribute("image") Image image, @ModelAttribute("productCategory") ProductCategory productCategory, @RequestParam(required = false) Category existingCategory, @RequestParam(required = false) String newCategory, @RequestParam("imageUrl") MultipartFile[] files) {
		System.out.println("Sorry");
		if(result.hasErrors()) {
			System.out.println("Sorry sorry");
			return "redirect:/dashboard/products";
		}
		//Create Product
		Product p = productService.createProduct(product);
		Long productId = p.getId();
		// Return categories list to compare new category with existing to reduce duplicates
		List<Category> catList = categoryService.getAllCategories();
		// Create string array to check if category name already exists
//		List<String> catStrings = new ArrayList<String>();
		for (Category cat : catList) {
			String catString = cat.getCategory_name();
			if (catString == newCategory) {
				return "redirect:/dashboard/products";
			}
		}
		List<Category> categories = new ArrayList<>();
		//If existing category is selected,then
		if (existingCategory != null) {
			System.out.println("----");
			categories.add(existingCategory);
		}
		System.out.println("!!!!!!!!");
		@SuppressWarnings("unused")
		Category newCat = null;
		String keyNamePrefix = p.getName() + "/";
		if (newCategory != null) {
			System.out.println(newCategory);
			Category newC = new Category();
			newC.setCategory_name(newCategory);
			categoryService.createCategory(newC);
			categories.add(newC);
		}
		for (Category cat : categories) {
			ProductCategory pC = new ProductCategory();
			pC.setProduct(p);
			pC.setCategory(cat);
			productCategoryService.createProdCat(pC);
		}
		System.out.println("---URL");
		UploadFile s3Uploader = new UploadFile();
		try {
			for(MultipartFile file : files) {
				System.out.println("Out here trying");
				Image img = new Image();
				ObjectMetadata metaData = new ObjectMetadata();
				metaData.setContentType("image/png");
				String imgUrl = s3Uploader.Upload("topladimages", keyNamePrefix + file.getOriginalFilename(), file.getInputStream(), metaData);
				System.out.println(imgUrl);
				img.setUrl(imgUrl);
				img.setProduct(p);
				imageService.createImg(img);
//				if(img.getIsMain() == true) {
//					p.setMainImageUrl(imgUrl);
//				} *** Change this after you make it possible to select main image ***
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return "redirect:/products/" + p.getId();
	}
	@GetMapping("/products/{id}/edit")
	public String toEditProduct(@PathVariable("id") Long id, Model model) {
		Product product = productService.findProductById(id);
		List<Image> pImages = imageService.findImagesByProductId(product.getId());
		model.addAttribute("product", product);
		model.addAttribute("pImage", pImages);
		return "admin/editProduct.jsp";
	}
	
	@RequestMapping(value="/products/{id}/delete")
	public String deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
		return "redirect:/dashboard/products";
	}
	
	@GetMapping("/test")
	public String test(@ModelAttribute("image") Image image) {
		return "test.jsp";
	}
	
	// Test route for file upload
//	@PostMapping("/testupload")
//	public String testS3Upload(@Valid @ModelAttribute("image") Image image, BindingResult result, @RequestParam("imageUrl") MultipartFile[] files) {
//		if(result.hasErrors()) {
//			System.out.println("Hi");
//			System.out.println(result);
//			return "redirect:/test";
//		}
//		System.out.println("---URL");
//		UploadFile s3Uploader = new UploadFile();
//		try {
//			for(MultipartFile file: files) {
//				System.out.println("Out here trying");
//				
//				ObjectMetadata metaData = new ObjectMetadata();
//				metaData.setContentType("image/png");
//				String imgUrl = s3Uploader.Upload("topladimages", file.getOriginalFilename(), file.getInputStream(), metaData);
//				System.out.println(imgUrl);
//				image.setUrl(imgUrl);
//				imageService.createImg(image);
//			}
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//		return "redirect:/dashboard/products";
//	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exc) {
		ModelAndView modelAndView = new ModelAndView("file");
		if (exc instanceof MaxUploadSizeExceededException) {
			modelAndView.getModel()
				.put("message", "File size exceeds limit!");
		}
		return modelAndView;
	}
}
