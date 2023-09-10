package thedrugking.mms.domain.user.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import thedrugking.mms.domain.user.application.UserAuthService;
import thedrugking.mms.domain.user.dto.LoginRequestDto;
import thedrugking.mms.domain.user.dto.LoginResponseDto;
import thedrugking.mms.domain.user.dto.SignUpRequestDto;
import thedrugking.mms.global.common.response.SuccessResponseDto;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthApi {

	private final UserAuthService userAuthService;

	@PostMapping("/api/signup")
	public ResponseEntity<SuccessResponseDto> signup(@Valid SignUpRequestDto request) {

		log.info("req = {}", request.getName());

		SuccessResponseDto success = userAuthService.signup(request);
		return ResponseEntity.ok(success);
	}

	@PostMapping("/api/login")
	public ResponseEntity<LoginResponseDto> login(@Valid LoginRequestDto request) {

		LoginResponseDto response = userAuthService.login(request);
		return ResponseEntity.ok(response);
	}
}
