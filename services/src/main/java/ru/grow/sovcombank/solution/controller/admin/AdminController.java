package ru.grow.sovcombank.solution.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.grow.sovcombank.solution.dto.user.UserPreviewDto;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    // TODO: 19.11.2022 Заменить на пагинацию!
    /**
     * @return всех пользователей
     */
    @GetMapping
    public ResponseEntity<List<UserPreviewDto>> getAllUsers(Principal principal) {
        return null;
    }

    /**
     * @param status статус валидации
     * @return всех пользователей по статусу валидации
     */
    @GetMapping("/valid")
    public ResponseEntity<List<UserPreviewDto>> getUsersByValidStatus(@RequestParam Boolean status) {
        return null;
    }

    /**
     * @param status статус блокировки
     * @return всех пользователей по статусу блокировки
     */
    @GetMapping("/block")
    public ResponseEntity<List<UserPreviewDto>> getUsersByBlockStatus(@RequestParam Boolean status) {
        return null;
    }

    /**
     * Изменяет статус блокировки пользователя по идентификатору
     *
     * @param userId идентификатор пользователя
     * @param status статус блокировки
     * @return обновленный пользователь
     */
    @PutMapping("/{userId}/block")
    public ResponseEntity<UserPreviewDto> changeAccountBlockStatus(@PathVariable Long userId, @RequestBody Boolean status) {
        return null;
    }

    /**
     * Изменяет статус валидации пользователя по идентификатору
     *
     * @param userId идентификатор пользователя
     * @param status статус валидации
     * @return обновленный пользователь
     */
    @PutMapping("/{userId}/validation")
    public ResponseEntity<UserPreviewDto> changeAccountValidStatus(@PathVariable Long userId, @RequestBody Boolean status) {
        return null;
    }
}
