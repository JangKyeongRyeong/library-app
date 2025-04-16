package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
     * @Transactional : 함수가 시작될 때 start transaction; 을 해준다
     *                  함수가 예외 없이 잘 끝나면 commit;
     *                  문제 발생 시 rollback;
     *                  다만 IOException 과 같은 Checked Exception 은 롤백이 일어나지 않는다.
     */
    @Transactional
    public void saveUser(UserCreateRequest request) {
        userRepository.save(new User(request.getName(), request.getAge()));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        //List<User> users = userRepository.findAll();
        //return userRepository.findAll().stream().map(user -> new UserResponse(user.getId(), user.getName(), user.getAge())).collect(Collectors.toList());
        return userRepository.findAll().stream().map(UserResponse::new).collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        //영속성 컨텍스트 시작
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        user.updateName(request.getName());
        //영속성 컨텍스트가 수정됨을 알고(변경 감지) 자동 save 해준다.
        //userRepository.save(user);
        //영속성 컨텍스트 끝
    }

    @Transactional
    public void deleteUser(String name) {
        User user = userRepository.findByName(name);

        if(user == null){
            throw new IllegalArgumentException();
        }

        userRepository.delete(user);
    }

}
