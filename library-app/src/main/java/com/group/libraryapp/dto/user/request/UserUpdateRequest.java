package com.group.libraryapp.dto.user.request;

import lombok.Getter;
import lombok.Setter;

public class UserUpdateRequest {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String name;
}
