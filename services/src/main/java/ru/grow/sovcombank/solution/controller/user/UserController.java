package ru.grow.sovcombank.solution.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.grow.sovcombank.solution.dto.user.UserAddDto;
import ru.grow.sovcombank.solution.dto.user.UserDto;
import ru.grow.sovcombank.solution.dto.user.UserInfoUpdateDto;
import ru.grow.sovcombank.solution.dto.user.UserPasswordUpdateDto;
import ru.grow.sovcombank.solution.service.UserService;

// TODO: 19.11.2022 Дописать изменение пароля отдельным методом

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Создание пользователя
     *
     * @param userAdd трансферный объект создания пользователя
     * @return сохранённого пользователя
     */
    @PostMapping
    public ResponseEntity<UserDto> add(@RequestBody UserAddDto userAdd) {
        return ResponseEntity.ok(userService.add(userAdd));
    }

    /**
     * Удаление пользователя по идентификатору
     *
     * @return удалённого пользователя
     */
    @DeleteMapping("/")
    public ResponseEntity<UserDto> delete() {
        return ResponseEntity.ok(userService.delete());
    }

    /**
     * Изменение данных пользователя
     *
     * @param userDto трансферный объект обновленных данных пользователя
     * @return измененный пользователь
     */
    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody UserInfoUpdateDto userDto) {
        return ResponseEntity.ok(userService.update(userDto));
    }

    @PutMapping("/name")
    public ResponseEntity<UserDto> updatePassword(@RequestBody UserPasswordUpdateDto userDto) {
        return ResponseEntity.ok(userService.updatePassword(userDto));
    }

}
