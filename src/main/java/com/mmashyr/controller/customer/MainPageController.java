package com.mmashyr.controller.customer;

import com.mmashyr.entity.Category;
import com.mmashyr.entity.Product;
import com.mmashyr.service.CategoryService;
import com.mmashyr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Anton on 18.01.2017.
 */
@Controller
public class MainPageController {

    private final static String CUSTOMER_PAGES = "customerpages/";

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public void setProductService(com.mmashyr.service.ProductService productService) {
        this.productService = productService;
    }


    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ModelAttribute
    public void getAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("category", new Category());
        model.addAttribute("product", new Product());
    }

    @RequestMapping("/")
    public String populateProductsByChosenCategories(Model model, @RequestParam(required = false) List<Long> categoryIDs) {
        List<Product> productsToShow;

        if (categoryIDs == null || categoryIDs.isEmpty()) {
            productsToShow = productService.getAll();
        } else {
            productsToShow = productService.findDistinctByCategoryIds(categoryIDs);
        }
        model.addAttribute("productsToShow", productsToShow);
        return CUSTOMER_PAGES + "main";
    }

    @RequestMapping(value = "/product/{productId}")
    public String showProduct(Model model, @PathVariable Long productId) {
        model.addAttribute("product", productService.findOne(productId));
        return CUSTOMER_PAGES + "product";
    }
}
