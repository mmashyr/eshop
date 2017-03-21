package com.mmashyr.controller.customer;

import com.mmashyr.entity.Category;
import com.mmashyr.entity.Product;
import com.mmashyr.service.CategoryService;
import com.mmashyr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/")
    public String showProduct(Model model) {
        model.addAttribute("numberOfPages", productService.findAll(new PageRequest(1, 3)).getTotalPages());
        return CUSTOMER_PAGES + "mainAjax";
    }

    @RequestMapping(value = "/maximumpages", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<Integer> getMaximumNumberOfPages() {
        Integer numberOfResults = productService.findAll(new PageRequest(0, 3)).getTotalPages();
        return new ResponseEntity<>(numberOfResults, HttpStatus.OK);
    }

    @RequestMapping(value = "/allproducts", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<List<Product>> getPage(@RequestParam Integer pageNumber) {
        Page<Product> productsToShow;
        productsToShow = productService.findAll(new PageRequest(pageNumber - 1, 3));
        return new ResponseEntity<>(productsToShow.getContent(), HttpStatus.OK);
    }

    @RequestMapping(value = "/product/{productId}")
    public String showProduct(Model model, @PathVariable Long productId) {
        Product product = productService.findOne(productId);
        if (product == null) {
            return "redirect:/";
        }
        model.addAttribute("product", product);
        return CUSTOMER_PAGES + "product";
    }

    @RequestMapping(value = "/numberofresultsbysearch", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<Integer> getNumberOfResultsFromSearch(@RequestParam String name) {
        Integer numberOfResults = 0;
        if (name != null && !name.isEmpty()) {
            numberOfResults = productService.findByName(name, new PageRequest(1, 3)).getTotalPages();
        }
        if (numberOfResults == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(numberOfResults, HttpStatus.OK);
    }

    @RequestMapping(value = "/numberofresultsbycategories", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<Integer> getNumberOfResultsByCategories(@RequestParam(value = "producer[]", required = false) List<String> producer) {
        Integer numberOfResults = 0;
        if (producer != null && !producer.isEmpty()) {
            numberOfResults = productService.findDistinctByCategoryNames(producer, new PageRequest(1, 3)).getTotalPages();
        }
        if (numberOfResults == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(numberOfResults, HttpStatus.OK);

    }

    @RequestMapping(value = "/searchajax", method = RequestMethod.POST)
    public ResponseEntity<List<Product>> getAjaxFromSearch(@RequestParam String name, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber) {
        Page<Product> productsToShow;
        if (name != null && !name.isEmpty()) {
            productsToShow = productService.findByName(name, new PageRequest(pageNumber - 1, 3));
            return new ResponseEntity<>(productsToShow.getContent(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping("/bycategories")
    public ResponseEntity<List<Product>> byCategoriesAjax(@RequestParam(required = false, defaultValue = "1") Integer pageNumber, @RequestParam(value = "producer[]", required = false) List<String> producer) {
        Page<Product> productsToShow;
        if (producer == null || producer.isEmpty()) {
            productsToShow = productService.findAll(new PageRequest(pageNumber - 1, 3));
            return new ResponseEntity<>(productsToShow.getContent(), HttpStatus.OK);
        }
        productsToShow = productService.findDistinctByCategoryNames(producer, new PageRequest(pageNumber - 1, 3));
        if (productsToShow == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productsToShow.getContent(), HttpStatus.OK);


    }
}
