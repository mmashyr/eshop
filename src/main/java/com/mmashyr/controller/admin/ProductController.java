package com.mmashyr.controller.admin;

import com.mmashyr.entity.Category;
import com.mmashyr.entity.Product;
import com.mmashyr.service.CategoryService;
import com.mmashyr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Anton on 16.01.2017.
 */

@Controller
@RequestMapping("/admin")
public class ProductController {

    private final static String PRODUCT_PAGES = "adminpages/productpages/";
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String getAllProducts(Model model) {
        model.addAttribute("productList", productService.getAll());
        return PRODUCT_PAGES + "productlist";
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public String getProductPage(@PathVariable("id") long id, Model model) {
        Product product = productService.findOne(id);
        product.getCategories().size();
        if (product == null) {
            return "redirect:/admin/product";
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("category", new Category());
        return PRODUCT_PAGES + "product";
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public String removeProduct(@PathVariable("id") long id) {
        if (productService.findOne(id) == null) {
            return "redirect:/admin/product";
        }
        productService.delete(id);

        return "redirect:/admin/product";
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.GET)
    public String showNewProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return PRODUCT_PAGES + "newproductform";
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return PRODUCT_PAGES + "newproductform";
        }
        productService.save(product);

        return "redirect:/admin/product";
    }

    @RequestMapping(value = "/product/{productId}/category/add", method = RequestMethod.POST)
    public String addCategoryToProduct(@PathVariable("productId") Long productId,
                                       @RequestParam("id") Long categoryID) {
        Product product = productService.findOne(productId);
        if (product == null) {
            return "redirect:/admin/product";
        }
        Category categoryToAdd = categoryService.findOne(categoryID);
        product.getCategories().add(categoryToAdd);
        productService.save(product);

        return "redirect:/admin/product/{productId}";
    }

    @RequestMapping(value = "/product/{productId}/category/{categoryId}", method = RequestMethod.DELETE)
    public String removeCategoryFromProduct(@PathVariable Long productId, @PathVariable Long categoryId) {
        Product product = productService.findOne(productId);
        Category category = categoryService.findOne(categoryId);
        product.getCategories().remove(category);
        productService.save(product);

        return "redirect:/admin/product/{productId}";
    }
}
