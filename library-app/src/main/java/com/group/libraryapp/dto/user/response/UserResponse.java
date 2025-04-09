package com.group.libraryapp.dto.user.response;

import com.group.libraryapp.domain.user.User;
import lombok.Getter;

public class UserResponse {

    @Getter
    private long id;

    @Getter
    private String name;

    @Getter
    private Integer age;

    public UserResponse(long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public UserResponse(long id, User user) {
        this.id = id;
        this.name = user.getName();
        this.age = user.getAge();
    }
}
