package com.group.libraryapp.domain.user;

import javax.persistence.*;

@Entity
public class User {

    @Id //primary key로 간주 한다
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id = null;

    @Column(nullable = false, length = 20)
    // @Column(nullable = false, length = 20, name = name)
    private String name;

    //@Column 별다른 옵션 없을 시 생략 가능
    private Integer age;

    //JPA 사용시 기본 생성자 반드시 필요함
    //protected User() {}

    public User(String name, Integer age) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));
        }

        this.name = name;
        this.age = age;
    }

}
