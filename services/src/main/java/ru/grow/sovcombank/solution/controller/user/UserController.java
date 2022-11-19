package ru.grow.sovcombank.solution.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.grow.sovcombank.solution.dto.user.UserAddDto;
import ru.grow.sovcombank.solution.dto.user.UserDto;
import ru.grow.sovcombank.solution.dto.user.UserUpdateDto;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    /**
     * Создание пользователя
     *
     * @param userAdd трансферный объект создания пользователя
     * @return сохранённого пользователя
     */
    @PostMapping
    public ResponseEntity<UserDto> add(@RequestBody UserAddDto userAdd) {
        return null;
    }

    /**
     * Удаление пользователя по идентификатору
     *
     * @param id идентификатор пользователя
     * @return удалённого пользователя
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable Long id) {
        return null;
    }

    /**
     * Изменение данных пользователя
     *
     * @param userDto трансферный объект обновленных данных пользователя
     * @return измененный пользователь
     */
    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody UserUpdateDto userDto) {
        return null;
    }
}
