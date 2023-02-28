package start17.Memento.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import start17.Memento.entity.Role;
import start17.Memento.entity.UserEntity;
import start17.Memento.exception.CustomException;
import start17.Memento.jwt.JwtTokenProvider;
import start17.Memento.model.dto.UserDto;
import start17.Memento.repository.UserRepository;
import start17.Memento.service.UserService;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public UserEntity createUser(UserDto userDto) {
        UserEntity findUser = userRepository.findByUserid(userDto.getUserid());
        if (findUser != null) {
            throw new CustomException("이미 존재하는 아이디입니다.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUserid(userDto.getUserid());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setNickname(userDto.getNickname());
        userEntity.setRoles(Collections.singletonList(Role.ROLE_USER)); //수정할 것
        return userRepository.save(userEntity);
    }

}
