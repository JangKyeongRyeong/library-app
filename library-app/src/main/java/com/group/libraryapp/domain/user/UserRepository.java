package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository 를 상속받아 @Repository 안써도됨
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);


}
