package com.mmashyr.controller.customer.service;

import com.mmashyr.entity.Product;
import com.mmashyr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by Mark on 26.04.2017.
 */
@RestController
@RequestMapping(value = "/service/product/")
public class ServiceProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(com.mmashyr.service.ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAll();
        if (products == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.FOUND);
    }

    @GetMapping(value = "/page/{pageNumber}")
    public ResponseEntity<List<Product>> getProductPage(@PathVariable int pageNumber) {
        if (pageNumber <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Page<Product> page;
        page = productService.findAllByPage(new PageRequest(pageNumber - 1, 3));
        List<Product> products = page.getContent();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping(value = "/{productId}")
    public ResponseEntity<Product> showProduct(@PathVariable Long productId) {
        Product product = productService.findOne(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product, HttpStatus.FOUND);
    }

    @GetMapping(value = "/maximumpages")
    public ResponseEntity<Integer> getMaximumNumberOfPages() {
        Integer numberOfResults = productService.findAllByPage(new PageRequest(0, 3)).getTotalPages();
        if (numberOfResults == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(numberOfResults, HttpStatus.OK);
    }

    @GetMapping(value = "/search/result/")
    public ResponseEntity<Integer> getNumberOfResultsFromSearch(@RequestParam String name) {
        Integer numberOfResults;
        if (name == null || name.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        numberOfResults = productService.findByName(name, new PageRequest(0, 3)).getTotalPages();
        if (numberOfResults == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(numberOfResults, HttpStatus.OK);
    }

    @GetMapping(value = "/search/result/page/{pageNumber}")
    public ResponseEntity<List<Product>> search(@RequestParam String name,
                                                @PathVariable(value = "pageNumber") int pageNumber) {
        Page<Product> productsToShow;
        if (pageNumber <= 0 || name == null || name.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        productsToShow = productService.findByName(name, new PageRequest(pageNumber - 1, 3));
        if (!productsToShow.hasContent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productsToShow.getContent(), HttpStatus.OK);
    }

    @GetMapping(value = "/categories/result")
    public ResponseEntity<Integer> getNumberOfResultsByCategories(
            @RequestParam(value = "producer[]", required = false) List<String> producer) {
        Integer numberOfResults;
        if (producer == null || producer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        numberOfResults = productService.findDistinctByCategoryNames(
                producer, new PageRequest(0, 3)).getTotalPages();
        if (numberOfResults == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(numberOfResults, HttpStatus.OK);
    }

    @GetMapping("/categories/result/page/{pageNumber}")
    public ResponseEntity<List<Product>> byCategoriesAjax(
            @PathVariable(required = false) Integer pageNumber,
            @RequestParam(value = "producer[]", required = false) List<String> producer) {
        Page<Product> productsToShow;
        if (producer == null || producer.isEmpty()) {
            productsToShow = productService.findAllByPage(new PageRequest(pageNumber - 1, 3));
            return new ResponseEntity<>(productsToShow.getContent(), HttpStatus.OK);
        }
        productsToShow = productService.findDistinctByCategoryNames(
                producer, new PageRequest(pageNumber - 1, 3));
        if (!productsToShow.hasContent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productsToShow.getContent(), HttpStatus.OK);
    }
}
