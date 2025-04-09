package com.group.libraryapp.dto.user.request;

import lombok.Getter;

public class UserCreateRequest {

    @Getter
    private String name;
    @Getter
    private Integer age; // Integer : null을 표현할수있는 자료형 (int는 null 표현 불가)
}
