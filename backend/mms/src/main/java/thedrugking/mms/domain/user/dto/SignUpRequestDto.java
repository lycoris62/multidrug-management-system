package thedrugking.mms.domain.user.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpRequestDto {

	@NotBlank(message = "아이디는 필수입니다.")
	private String loginId;

	@NotBlank(message = "비밀번호는 필수입니다.")
	private String password;

	@NotBlank(message = "비밀번호 확인은 필수입니다.")
	private String confirmPassword;

	@NotBlank(message = "이름은 필수입니다.")
	private String name;

	@NotNull(message = "유저 구분은 필수입니다.")
	private UserType userType;

	@NotNull(message = "생일은 필수입니다.")
	@DateTimeFormat(pattern = "yyyyMMdd")
	private LocalDate birthday;
}
