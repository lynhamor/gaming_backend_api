package com.api.auth.service;

import com.api.auth.database.entity.User;
import com.api.auth.database.repository.UserRepository;
import com.api.auth.dto.ResponseDto;
import com.api.auth.dto.UserDto;
import com.api.auth.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseDto register(UserDto dto) throws Exception {

        User user = userRepository.findByUsername(dto.getUsername())
                .orElse(null);

        if (user != null)
            return ResponseDto.error("Username is already exist");

        String passwordIV = PasswordEncoder.generatePasswordIV();
        user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(PasswordEncoder.encodeUserPassword(dto.getPassword(), passwordIV));
        user.setPasswordIv(passwordIV);
        user.setCreatedBy("admin");
        user.setUpdatedBy("admin");

        user = userRepository.save(user);

        return ResponseDto.success("success", user);
    }

    public ResponseDto userList(){


        List<User> userList = userRepository.findAll();

        return ResponseDto.success("success", userList);
    }
}
