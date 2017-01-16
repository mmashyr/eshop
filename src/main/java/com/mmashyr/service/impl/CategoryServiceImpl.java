package com.mmashyr.service.impl;

import com.mmashyr.entity.Category;
import com.mmashyr.repository.CategoryRepository;
import com.mmashyr.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Anton on 13.01.2017.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        categoryRepository.delete(id);
    }

    @Override
    public Category findOne(Long id) {
        return null;
    }

    @Override
    public List<Category> getAll() {
        return null;
    }
}
