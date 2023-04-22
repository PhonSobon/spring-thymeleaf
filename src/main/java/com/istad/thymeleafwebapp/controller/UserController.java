package com.istad.thymeleafwebapp.controller;

import com.istad.thymeleafwebapp.models.Post;
import com.istad.thymeleafwebapp.models.User;
import com.istad.thymeleafwebapp.services.PostService;
import com.istad.thymeleafwebapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/authors")
public class UserController {
    private final UserService authorService;
    private final PostService articleService;

    @GetMapping
    String authors(Model model){
        model.addAttribute("authors",authorService.getAuthors());
        return "authors";
    }

    @GetMapping("/{id}")
    String authorProfile(@PathVariable Integer id,Model model){
        User author = authorService.getAuthorById(id);
        List<Post> articles = articleService.getArticleByAuthor(author);
        author.setArticles(articles);
        model.addAttribute("author",author);
        return "post-profile";
    }

}
