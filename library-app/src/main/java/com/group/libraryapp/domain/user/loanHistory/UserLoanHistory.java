package com.group.libraryapp.domain.user.loanHistory;

import com.group.libraryapp.domain.user.User;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne
    private User user;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "is_return")
    private boolean isReturn;

    public UserLoanHistory() {}

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void doRetrun() {
        this.isReturn = true;
    }
}
