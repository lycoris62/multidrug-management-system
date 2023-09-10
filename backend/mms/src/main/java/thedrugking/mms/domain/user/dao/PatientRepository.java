package thedrugking.mms.domain.user.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import thedrugking.mms.domain.user.domain.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	Optional<Patient> findByLoginId(String loginId);

	boolean existsByLoginId(String loginId);
}
