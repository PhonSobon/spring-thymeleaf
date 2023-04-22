package com.istad.thymeleafwebapp.models;

import java.util.List;

public record CategoryPost(Category category, List<Post> articles) {
}
