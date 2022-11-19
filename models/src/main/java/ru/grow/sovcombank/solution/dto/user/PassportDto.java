package ru.grow.sovcombank.solution.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.dto.base.TimeObject;
import ru.grow.sovcombank.solution.types.Gender;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassportDto extends TimeObject {
    /**
     * Имя
     */
    private String firstName;

    /**
     * Фамилия
     */
    private String lastName;

    /**
     * Отчество
     */
    private String middleName;

    /**
     * Гендер
     */
    private Gender gender;

    /**
     * День рождения
     */
    private Date dateOfBirth;

    /**
     * Город рождения
     */
    private String cityOfBirth;

    /**
     * Серийный номер паспорта
     */
    private String serialNumber;

    /**
     * Номер паспорта
     */
    private String passportNumber;
}

