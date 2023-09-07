package thedrugking.mms.domain.user.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thedrugking.mms.domain.medicine.domain.MedicineImage;

@Getter
@Entity
@DiscriminatorValue("P")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Patient extends User {

	@OneToMany(mappedBy = "patient")
	private final List<Follow> followList = new ArrayList<>();

	@OneToMany(mappedBy = "patient")
	private final List<MedicineImage> medicineImageList = new ArrayList<>();

	@Builder
	public Patient(String loginId, String password, String name, String followId, LocalDate birthday) {
		super(loginId, password, name, followId, birthday);
	}
}
