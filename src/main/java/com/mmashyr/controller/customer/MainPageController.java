package com.mmashyr.controller.customer;

import com.mmashyr.entity.Category;
import com.mmashyr.entity.Product;
import com.mmashyr.service.CategoryService;
import com.mmashyr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public String redirectToMainPage() {
        return "redirect:/page/1";
    }

    @RequestMapping("/page/{pageNumber}")
    public String populateProductsByChosenCategories(@PathVariable int pageNumber, Model model, @RequestParam(required = false) List<String> producer, @RequestParam(required = false) String name) {
        Page<Product> productsToShow;
        if (producer == null || producer.isEmpty()) {
            productsToShow = productService.findAll(new PageRequest(pageNumber - 1, 3));
            model.addAttribute("productsToShow", productsToShow);
        } else {
            productsToShow = productService.findDistinctByCategoryNames(producer, new PageRequest(pageNumber - 1, 3));
            model.addAttribute("productsToShow", productsToShow);
        }
        model.addAttribute("numberOfPages", productsToShow.getTotalPages());

        return CUSTOMER_PAGES + "main";
    }

    @RequestMapping(value = "search/page/{pageNumber}/")
    public String searchProductsByName(@PathVariable int pageNumber, Model model, @RequestParam String name) {
        Page<Product> productsToShow;
        if (name != null || !name.isEmpty()) {
            productsToShow = productService.findByName(name, new PageRequest(pageNumber - 1, 3));
            model.addAttribute("productsToShow", productsToShow);
        } else return "redirect:/";
        model.addAttribute("fromSearch", true);
        model.addAttribute("searchName", name);
        model.addAttribute("numberOfPages", productsToShow.getTotalPages());
        return CUSTOMER_PAGES + "main";
    }

    @RequestMapping(value = "/product/{productId}")
    public String showProduct(Model model, @PathVariable Long productId) {
        model.addAttribute("product", productService.findOne(productId));
        return CUSTOMER_PAGES + "product";
    }
}
