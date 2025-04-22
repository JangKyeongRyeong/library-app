package com.group.libraryapp.domain.user.loanHistory;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "is_return")
    private boolean isReturn;

    public UserLoanHistory() {}

    public UserLoanHistory(long userId, String bookName) {
        this.userId = userId;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void doRetrun() {
        this.isReturn = true;
    }
}
