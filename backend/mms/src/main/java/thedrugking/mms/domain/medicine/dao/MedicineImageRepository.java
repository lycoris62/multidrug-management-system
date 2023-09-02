package thedrugking.mms.domain.medicine.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import thedrugking.mms.domain.medicine.domain.MedicineImage;

public interface MedicineImageRepository extends JpaRepository<MedicineImage, Long> {
}
