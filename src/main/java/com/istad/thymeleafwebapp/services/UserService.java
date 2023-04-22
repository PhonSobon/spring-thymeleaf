package com.istad.thymeleafwebapp.services;

import com.istad.thymeleafwebapp.models.User;

import java.util.List;

public interface UserService {
    List<User> getAuthors();

    User getAuthorById(Integer id);
}
