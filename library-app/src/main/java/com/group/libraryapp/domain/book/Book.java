package com.group.libraryapp.domain.book;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id = null;

    @Column(nullable = false)
    private String name;

    // 기본 생성자
    public Book() {}

    public Book(String name) {

        if(name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;

    }
}
