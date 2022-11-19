package ru.grow.sovcombank.solution.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.grow.sovcombank.solution.entity.base.TimeObjectEntity;
import ru.grow.sovcombank.solution.types.Role;

import javax.persistence.*;

@Entity
@Table(name = "security_user")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public class SecurityUserEntity extends TimeObjectEntity {
    @Id
    @Column(unique = true, insertable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 10000)
    private Long id;

    @Column(unique = true, nullable = false, length = 30)
    private String login;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
