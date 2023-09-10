package thedrugking.mms.domain.user.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDto {
	private String name;
	private UserType userType;
	private LocalDate birthday;
	private String accessToken;
}
