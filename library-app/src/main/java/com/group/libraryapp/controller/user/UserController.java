package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.fruit.FruitService;
import com.group.libraryapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final FruitService fruitService;

    //스프링 빈 주입 받는 방법
    // 첫번째 방법 : 생성자 사용 (@Autowired 생략 가능) - 권장
    public UserController(UserService userService, @Qualifier("main") FruitService fruitService) {
        this.userService = userService;
        this.fruitService = fruitService;
    }

    /*

    두번째 방법 : setter 사용 - 누군가 setter 사용 시 오작동 발생 가능성 있음
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    세번째 방법 : 필드에 바로 사용 - 테스트 어려움
    @Autowired
    private UserService userService;

    */


    @PostMapping("/user")
    public void saveUsers(@RequestBody UserCreateRequest request) {
        userService.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        userService.deleteUser(name);
    }

    @GetMapping("/user/error-test")
    public void errorTest() {
        throw new IllegalArgumentException();
    }



}
