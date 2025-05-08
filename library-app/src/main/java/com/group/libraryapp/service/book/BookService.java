package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanHistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanHistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository,
                       UserLoanHistoryRepository userLoanHistoryRepository,
                       UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {

        // 1. 책 정보를 가져온다
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        // 2. 대출 기록 정보를 확인하여 대출중인지 확인한다.
        // 3. 만약 대출중이면 예외 발생하도록 처리
        boolean exists = userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false);
        System.out.println("조회 결과: " + exists);

        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException("이미 대출중인 책입니다.");
        }

        // 4. 유저 정보를 가져온다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);


        // 5. 유저 정보와 책 정보를 기반으로 UserLoanHistory 를 저장
        //userLoanHistoryRepository.save(new UserLoanHistory(user, book.getName()));

        // 5번처럼 UserLoanHistory 객체를 직접 사용하지 않고 user.loanBook()을 통해 저장.
        user.loanBook(book.getName());

    }

    @Transactional
    public void returnBook(BookReturnRequest request) {
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalAccessError::new);

        /*
        UserLoanHistory history = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        history.doRetrun();
        */

        /*
            @Transactional의 영속성 컨텍스트의 변경 감지 기능 덕분에 save 별도로 안해도됨
            userLoanHistoryRepository.save(history);   -> 불필요
        */

        user.returnBook(request.getBookName());

    }
}
