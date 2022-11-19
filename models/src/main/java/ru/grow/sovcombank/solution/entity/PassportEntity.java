package ru.grow.sovcombank.solution.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.entity.base.TimeObjectEntity;
import ru.grow.sovcombank.solution.types.Gender;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "passport")
@Getter
@Setter
@NoArgsConstructor
public class PassportEntity extends TimeObjectEntity {
    @Id
    @Column(unique = true, insertable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passport_id_seq")
    @SequenceGenerator(name = "passport_id_seq", sequenceName = "passport_id_seq", allocationSize = 500)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String middleName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    private String cityOfBirth;

    @Column(nullable = false, length = 4)
    private String serialNumber;

    @Column(nullable = false, length = 6)
    private String passportNumber;
}
