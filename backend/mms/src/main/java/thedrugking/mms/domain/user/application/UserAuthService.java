package thedrugking.mms.domain.user.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import thedrugking.mms.domain.user.dao.UserRepository;
import thedrugking.mms.domain.user.domain.MedicalPerson;
import thedrugking.mms.domain.user.domain.Patient;
import thedrugking.mms.domain.user.domain.User;
import thedrugking.mms.domain.user.dto.SignUpRequestDto;
import thedrugking.mms.domain.user.dto.UserType;
import thedrugking.mms.global.common.response.SuccessResponseDto;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserAuthService {

	private final UserRepository userRepository;

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
}
