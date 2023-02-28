package start17.Memento.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import start17.Memento.domain.Role;
import start17.Memento.model.dao.UserMapper;
import start17.Memento.domain.User;
import start17.Memento.model.dto.UserDto;
import start17.Memento.model.dto.UserRegisterDto;
import start17.Memento.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
//
//    @Autowired
//    UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserRegisterDto userRegisterDto) {
        //아이디 중복 확인
        if(userRepository.findByUserId(userRegisterDto.getUser_id()) != null) {
            return null;
        }

        //가입 성공한 유저에겐 "USER" 권한 부여
        User user = userRepository.save(User.builder()
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .user_id(userRegisterDto.getUser_id())
                .role(Role.USER)
                .build());

        return UserDto.builder()
                .user_id(user.getUser_id())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

//    public void createUser(User user) {
//        encryptPassword(user);
//        userMapper.createUser(user);
//    }

//    public User loginCheck(UserDto.LoginInfo userDto) {
//        User user = userMapper.getUserById(userDto.getUser_id());
//        if(passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
//            return user;
//        }
//        else {
//            return null;
//        }
//    }

//    private void encryptPassword(User user) {
//        String enPw = passwordEncoder.encode(user.getPassword());
//        user.setPassword(enPw);
//    }
}
