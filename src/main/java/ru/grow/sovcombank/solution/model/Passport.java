package ru.grow.sovcombank.solution.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Passport {
    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private Date dateOfBirthDay;

    private String number;

    private String serial;
}
