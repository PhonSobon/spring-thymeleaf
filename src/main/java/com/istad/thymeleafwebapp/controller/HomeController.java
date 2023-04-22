package com.istad.thymeleafwebapp.controller;

import com.istad.thymeleafwebapp.models.Post;
import com.istad.thymeleafwebapp.models.Category;
import com.istad.thymeleafwebapp.services.PostService;
import com.istad.thymeleafwebapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService articleService;
    private final CategoryService categoryService;

    @GetMapping("/")
    String homePage(Model model){
        List<Post> articles = articleService.findAll();
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("articles", articles);
        model.addAttribute("categories", categories);
        return "index";
    }
}
