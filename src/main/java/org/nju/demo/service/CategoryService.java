package org.nju.demo.service;

import org.nju.demo.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getCategories();

    Category getCategory(int id);

}
