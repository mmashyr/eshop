package com.mmashyr.controller.admin;

import com.mmashyr.entity.Category;
import com.mmashyr.service.CategoryService;
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
 * Created by Anton on 17.01.2017.
 */
@Controller
@RequestMapping("/admin")
public class CategoryController {

    private final static String CATEGORY_PAGES = "adminpages/categorypages/";
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return CATEGORY_PAGES + "categorylist";
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public String getCategoryPage(@PathVariable("id") long id, Model model) {
        Category category = categoryService.findOne(id);
        if (category == null) {
            return "redirect:/admin/category";
        }
        model.addAttribute("category", category);

        return CATEGORY_PAGES + "category";
    }

    @RequestMapping(value = "/category/add", method = RequestMethod.GET)
    public String showNewCategoryForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);

        return CATEGORY_PAGES + "newcategoryform";
    }

    @RequestMapping(value = "/category/add", method = RequestMethod.POST)
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) {
            return CATEGORY_PAGES + "newcategoryform";
        }
        categoryService.save(category);

        return "redirect:/admin/category";
    }
}
