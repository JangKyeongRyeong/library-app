package com.group.libraryapp.repository.book;

import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookMemoryRepository implements BookRepository {

    @Override
    public void saveBook() {
        System.out.printf("MemoryRepository");
    }
}
