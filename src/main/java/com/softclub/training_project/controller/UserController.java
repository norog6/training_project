package com.softclub.training_project.controller;

import com.softclub.training_project.dto.UserDTO;
import com.softclub.training_project.dto.UserPersonalInfo;
import com.softclub.training_project.entity.User;
import com.softclub.training_project.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Работа с пользователем")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Обновление профиля"
    )
    @PutMapping("/{userId}/personal")
    public ResponseEntity<User> updateUserProfile(@PathVariable Long userId, @Valid @RequestBody UserPersonalInfo user) {
        return ResponseEntity.ok(userService.updateUserProfile(userId, user));
    }

    @Operation(
            summary = "Смена пароля"
    )
    @PutMapping("/{userId}/password")
    public ResponseEntity<User> changePassword(@PathVariable Long userId,@NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should be at least 6 characters") @RequestBody String newPassword) {
        return ResponseEntity.ok(userService.changePassword(userId, newPassword));
    }


    @Operation(
            summary = "Смена логина"
    )
    @PutMapping("/{userId}/login")
    public ResponseEntity<User> changeLogin(@PathVariable Long userId, @NotBlank(message = "Login is mandatory")
    @Size(min = 6, max = 20, message = "Login should be between 6 and 20 characters")@RequestBody String newLogin) {
        return ResponseEntity.ok(userService.changeLogin(userId, newLogin));
    }
    @Operation(summary = "Создание юзера")
    @PostMapping("reg")
    public void register(@Valid @RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
    }

    @Operation(summary = "ВСя инфа")
    @GetMapping("/{userId}/profile")
    public ResponseEntity<User> getUserProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }
}
