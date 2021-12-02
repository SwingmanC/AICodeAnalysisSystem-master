package org.nju.demo.service.impl;

import org.nju.demo.dao.CategoryMapper;
import org.nju.demo.entity.Category;
import org.nju.demo.entity.CategoryExample;
import org.nju.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategories() {
        CategoryExample categoryExample = new CategoryExample();
        return categoryMapper.selectByExample(categoryExample);
    }

    @Override
    public Category getCategory(int id) {
        return categoryMapper.selectByPrimaryKey(id);
    }
}
