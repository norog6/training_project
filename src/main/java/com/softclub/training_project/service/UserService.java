package com.softclub.training_project.service;

import com.softclub.training_project.dto.UserDTO;
import com.softclub.training_project.dto.UserPersonalInfo;
import com.softclub.training_project.entity.User;
import com.softclub.training_project.mapper.UserMapper;
import com.softclub.training_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public void createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        userRepository.save(user);
    }
    public User updateUserProfile(Long userId, UserPersonalInfo userPersonalInfo) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(userPersonalInfo.getFirstName());
        user.setLastName(userPersonalInfo.getLastName());
        user.setEmail(userPersonalInfo.getEmail());
        user.setPhoneNumber(userPersonalInfo.getPhoneNumber());
        return userRepository.save(user);
    }

    public User changePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(newPassword);
        return userRepository.save(user);
    }
    public User changeLogin(Long userId, String newLogin) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(newLogin);
        return userRepository.save(user);
    }

    public User getUserProfile(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
