package org.nju.demo.service.impl;

import org.nju.demo.dao.CategoryMapper;
import org.nju.demo.entity.Category;
import org.nju.demo.entity.CategoryExample;
import org.nju.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategories() {
        CategoryExample categoryExample = new CategoryExample();
        return categoryMapper.selectByExample(categoryExample);
    }
}
