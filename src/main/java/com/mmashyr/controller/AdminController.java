package com.mmashyr.controller;

import com.mmashyr.entity.Product;
import com.mmashyr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Anton on 16.01.2017.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String getAllProducts(Model model) {
        model.addAttribute("productList", productService.getAll());
        return "adminpages/productlist";
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public String removeProduct(@PathVariable("id") long id) {
        if (productService.findOne(id) == null) {
            return "redirect:/product";
        }
        productService.delete(id);

        return "redirect:/product";
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.GET)
    public String showNewProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "adminpages/newproductform";
    }

    @RequestMapping(value = "/product//add", method = RequestMethod.POST)
    public String addWorker(@Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "adminpages/newproductform";
        }
        productService.save(product);

        return "redirect:/product";
    }
}
