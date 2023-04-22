package com.istad.thymeleafwebapp.services;

import com.istad.thymeleafwebapp.models.Category;
import com.istad.thymeleafwebapp.models.CategoryPost;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    CategoryPost getCategoryById(Integer id);
}
