package thedrugking.mms.domain.user.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thedrugking.mms.domain.model.BaseEntity;

@Getter
@Entity
@DiscriminatorColumn
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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

	public User(String loginId, String password, String name, String followId, LocalDate birthday) {
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.followId = followId;
		this.birthday = birthday;
	}
}
