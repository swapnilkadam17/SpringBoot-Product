package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	
@Autowired
private ProductServices service;

@RequestMapping("/")
public String viewHomePage(Model model)
{
	List<Product> listProduct =service.listAll();
	model.addAttribute("listProduct",listProduct);
	return "index";
}
@RequestMapping("/new")
public String showNewProductForm(Model model) {
	Product product = new Product();
	model.addAttribute("product",product);
	return "new_product";
}
@RequestMapping(value="/save", method = RequestMethod.POST)
public String saveProduct(@ModelAttribute("product")Product product)
{
	service.save(product);
	return "redirect:/";
}
@RequestMapping(value="/edit/{id}")
public ModelAndView showEdit(@PathVariable(name="id")Long id)
{
	ModelAndView mv = new ModelAndView("edit_product");
	Product product = service.get(id);
	mv.addObject("product",product);
	return mv;
}
@RequestMapping("/delete/{id}")
public String deleteProduct(@PathVariable(name="id")Long id)
{
	service.delete(id);
	return "redirect:/";
}
}

