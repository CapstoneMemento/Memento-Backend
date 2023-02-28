package start17.Memento.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import start17.Memento.model.dao.UserMapper;
import start17.Memento.domain.User;
import start17.Memento.model.dto.UserDto;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void createUser(User user) {
        encryptPassword(user);
        userMapper.createUser(user);
    }

    public User loginCheck(UserDto.LoginInfo userDto) {
        User user = userMapper.getUserById(userDto.getUser_id());
        if(passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            return user;
        }
        else {
            return null;
        }
    }

    public User findByUserId(String user_id) {
        User user = userMapper.getUserById(user_id);
        return user;
    }

    private void encryptPassword(User user) {
        String enPw = passwordEncoder.encode(user.getPassword());
        user.setPassword(enPw);
    }

//    @Override
//    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
//            return userRepository.findByUser_Id(user_id)
//                    .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
//    }
}
