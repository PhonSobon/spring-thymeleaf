package com.istad.thymeleafwebapp.services;

import com.istad.thymeleafwebapp.models.Post;
import com.istad.thymeleafwebapp.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    // find all article
    // POJO
    List<Post> findAll();

    List<User> auths();
    Post singleArticle(String uuid);

    boolean save(Post article, MultipartFile file);

    List<Post> getArticleByAuthor(User author);

}
