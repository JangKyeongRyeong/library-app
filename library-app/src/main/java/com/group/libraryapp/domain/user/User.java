package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanHistory.UserLoanHistory;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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
    protected User() {}

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    public User(String name, Integer age) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));
        }

        this.name = name;
        this.age = age;
    }

    public void updateName(String name) {
        this.name = name;
    }

    /*
    *  userLoanHistories 의 cascade 옵션 덕분에 함수를 만들어서 그 함수 안에 userLoanHistory.add() 하면
    *  UserLoanHistory 엔티티 (테이블)에 자동으로 insert 된다.
    *
    *  이렇게 '대출'이라는 행위를 User와 UserLoanHistory 이 2개의 객체가 서로 협력해서 이루어내도록 변경하였다.
    *  이를 '도메인 계층에 비즈니스 로직이 들어갔다' 라고 표현한다.
    */
    public void loanBook(String bookName) {
        userLoanHistories.add(new UserLoanHistory(this, bookName));
    }

    // 위의 loanBook() 과 같은 원리
    public void returnBook(String bookName) {
        UserLoanHistory targetHistory = userLoanHistories.stream().
                filter(history -> history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doRetrun();
    }

}
