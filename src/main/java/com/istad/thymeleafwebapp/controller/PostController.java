package com.istad.thymeleafwebapp.controller;

import com.istad.thymeleafwebapp.models.Post;
import com.istad.thymeleafwebapp.models.User;
import com.istad.thymeleafwebapp.models.Category;
import com.istad.thymeleafwebapp.services.PostService;
import com.istad.thymeleafwebapp.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class PostController {
    private final PostService articleService;
    private final CategoryService categoryService;

    @GetMapping
    String articlePage(Model model){
        List<Post> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "sections/article/Post";
    }

    @GetMapping("/{uuid}")
    String singleArticle(@PathVariable String uuid, Model model){
        Post article = articleService.singleArticle(uuid);
        model.addAttribute("article",article);
        return "single-article";
    }

    @GetMapping("/new")
    String newArticle(Post article, Model model){
        model.addAttribute("article",article);
        model.addAttribute("users",articleService.auths());
        model.addAttribute("categories",categoryService.getCategories());
        return "sections/article/post-new";
    }

    @PostMapping(value = "/new")
    String doSaveArticle(@ModelAttribute @Valid Post article,
                         BindingResult result,
                         @RequestParam(value = "author_id",required = false) Integer author_id,
                         @RequestParam(value = "category_ids",required = false) List<Integer> category_ids,
                         @RequestParam(value = "thumbnailFile",required = false) MultipartFile file,
                         Model model){

        User author =  articleService.auths().stream()
                .filter(a -> a.getId().equals(author_id))
                .findFirst()
                .orElse(null);

        System.out.println(category_ids);
        List<Category> categories = categoryService.getCategories()
                .stream().filter(category -> category_ids.contains(category.getId()))
                .toList();

        article.setAuthor(author);

        article.setCategories(categories);

        if (result.hasErrors()){
            System.out.println(result.getFieldErrors());
            model.addAttribute("article",article);
            return "sections/article/post-new";
        }
        articleService.save(article,file);
        return "redirect:/article/new";
    }
}
