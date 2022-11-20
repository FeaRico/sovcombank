package ru.grow.sovcombank.solution.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordUpdateDto {
    private String oldPassword;
    private String newPassword;
}
