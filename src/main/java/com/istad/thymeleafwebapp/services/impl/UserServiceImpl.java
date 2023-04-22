package com.istad.thymeleafwebapp.services.impl;

import com.istad.thymeleafwebapp.models.User;
import com.istad.thymeleafwebapp.repositories.StaticRepository;
import com.istad.thymeleafwebapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final StaticRepository staticRepository;
    @Override
    public List<User> getAuthors() {
        return staticRepository.getAuthors();
    }

    @Override
    public User getAuthorById(Integer id) {
        return staticRepository.getAuthors().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
