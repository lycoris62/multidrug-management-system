package thedrugking.mms.domain.user.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import thedrugking.mms.domain.user.dao.PatientRepository;
import thedrugking.mms.domain.user.dao.UserRepository;
import thedrugking.mms.domain.user.domain.MedicalPerson;
import thedrugking.mms.domain.user.domain.Patient;
import thedrugking.mms.domain.user.domain.User;
import thedrugking.mms.domain.user.dto.LoginRequestDto;
import thedrugking.mms.domain.user.dto.LoginResponseDto;
import thedrugking.mms.domain.user.dto.SignUpRequestDto;
import thedrugking.mms.domain.user.dto.UserType;
import thedrugking.mms.global.common.response.SuccessResponseDto;
import thedrugking.mms.global.config.security.jwt.JwtProvider;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserAuthService {

	private final UserRepository userRepository;
	private final PatientRepository patientRepository;
	private final JwtProvider jwtProvider;

	@Transactional
	public SuccessResponseDto signup(SignUpRequestDto request) {

		if (!request.getPassword().equals(request.getConfirmPassword())) {
			throw new IllegalArgumentException("비밀번호와 확인용 비밀번호가 다릅니다.");
		}

		User user = createUser(request);
		userRepository.save(user);

		return new SuccessResponseDto();
	}

	private User createUser(SignUpRequestDto request) {

		if (request.getUserType() == UserType.PATIENT) {
			return createPatientEntity(request);
		}
		if (request.getUserType() == UserType.MEDICAL_PERSON) {
			return createMedicalPersonEntity(request);
		}

		throw new IllegalArgumentException("잘못된 유저 타입");
	}

	private Patient createPatientEntity(SignUpRequestDto request) {
		return Patient.builder()
			.loginId(request.getLoginId())
			.password(request.getPassword())
			.birthday(request.getBirthday())
			.name(request.getName())
			.followId(UUID.randomUUID().toString())
			.build();
	}

	private MedicalPerson createMedicalPersonEntity(SignUpRequestDto request) {
		return MedicalPerson.builder()
			.loginId(request.getLoginId())
			.password(request.getPassword())
			.birthday(request.getBirthday())
			.name(request.getName())
			.followId(UUID.randomUUID().toString())
			.build();
	}

	public LoginResponseDto login(LoginRequestDto request) {

		User findUser = userRepository.findByLoginId(request.getLoginId())
			.orElseThrow(() -> new IllegalArgumentException("잘못된 아아디 또는 비밀번호 입력"));

		if (!findUser.getPassword().equals(request.getPassword())) {
			throw new IllegalArgumentException("잘못된 아아디 또는 비밀번호 입력");
		}

		return createResponseDto(findUser);
	}

	private LoginResponseDto createResponseDto(User findUser) {

		String accessToken = jwtProvider.createToken(findUser.getLoginId());
		UserType userType = patientRepository.existsByLoginId(findUser.getLoginId())
			? UserType.PATIENT
			: UserType.MEDICAL_PERSON;

		return new LoginResponseDto(findUser.getName(), userType, findUser.getBirthday(), accessToken);
	}
}
