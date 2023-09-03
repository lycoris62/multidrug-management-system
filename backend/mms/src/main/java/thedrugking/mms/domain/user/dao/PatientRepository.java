package thedrugking.mms.domain.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import thedrugking.mms.domain.user.domain.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
