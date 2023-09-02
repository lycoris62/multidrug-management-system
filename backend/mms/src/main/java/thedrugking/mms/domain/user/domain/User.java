package thedrugking.mms.domain.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import thedrugking.mms.domain.model.BaseEntity;

import java.time.LocalDate;

@Getter
@Entity
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "users")
public abstract class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "login_id", nullable = false, unique = true, length = 20)
    private String loginId;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @Column(name = "follow_id")
    private String followId;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;
}
