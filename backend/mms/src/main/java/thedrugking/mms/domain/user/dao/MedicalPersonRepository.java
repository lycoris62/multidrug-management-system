package thedrugking.mms.domain.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import thedrugking.mms.domain.user.domain.MedicalPerson;

public interface MedicalPersonRepository extends JpaRepository<MedicalPerson, Long> {
}
