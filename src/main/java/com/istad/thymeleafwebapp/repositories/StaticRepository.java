package com.istad.thymeleafwebapp.repositories;

import com.istad.thymeleafwebapp.models.Post;
import com.istad.thymeleafwebapp.models.User;
import com.istad.thymeleafwebapp.models.Category;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Getter
public class StaticRepository {

    private List<Post> articles;
    private List<User> authors;
    private List<Category> categories;
    private Faker faker;

    @Autowired
    public void setFaker(Faker faker) {
        this.faker = faker;
    }

    @PostConstruct
    void init(){
        User author = new User(
                1, "socheat",
                "socheat".toLowerCase(), "Female",
                "socheat01@gmail.com",
                "Kompong Chhang",
                "/resources/img/profile/1.webp",
                "/resources/img/profile/1.webp"
        );

        authors = new ArrayList<>(){{
            add(author);
            add(new User(
                    2, "sovan",
                    "sovan".toLowerCase(), "Female",
                    "sovan@gimal.com",
                    "Kompong Cham",
                    "/resources/img/profile/3.webp",
                    "/resources/img/profile/3.webp"
            ));

            add(new User(
                    3, "chany",
                    "chany".toLowerCase(), "male",
                    "chany0234@gmail.com",
                    "Seam Reap",
                    "/resources/img/profile/2.webp",
                    "/resources/img/default/5.webp"
            ));

            add(new User(
                    4, "Sokna",
                    "sokna".toLowerCase(), "female",
                    "sokna0234@gmail.com",
                    "Battambng",
                    "/resources/img/profile/4.webp",
                    "/resources/img/profile/4.webp"
            ));
        }};

        categories = new ArrayList<>(){{
            for (int i = 1; i < 11; i++){
                add(new Category(i,faker.pokemon().name(),faker.color().hex()));
            }
        }};
        Random random = new Random();
        articles = new ArrayList<>() {{

            for (int i = 1; i < 5; i++) {
                if (i % 2 == 0) {
                    add(new Post(UUID.randomUUID(), faker.book().title(), "/resources/img/default/5.webp",authors.get(random.nextInt(authors.size())),faker.lorem().paragraphs(10).toString(),categories.stream().filter(cat->cat.getId().equals(random.nextInt(categories.size())))
                            .collect(Collectors.toList())));
                } else {
                    add(new Post(UUID.randomUUID(), faker.book().title(), "/resources/img/default/6.webp",authors.get(random.nextInt(authors.size())),faker.lorem().paragraphs(10).toString(),categories.stream().filter(cat->cat.getId().equals(random.nextInt(categories.size())))
                            .collect(Collectors.toList())));
                }
            }
        }};

    }
}
