package thedrugking.mms.domain.user.domain;

import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@DiscriminatorValue("M")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MedicalPerson extends User {

	//    @OneToMany(mappedBy = "medicalPerson")
	//    private List<Follow> followList = new ArrayList<>();
	//
	//    @OneToMany(mappedBy = "medicalPerson")
	//    private List<Comment> commentList = new ArrayList<>();

	@Builder
	public MedicalPerson(String loginId, String password, String name, String followId, LocalDate birthday) {
		super(loginId, password, name, followId, birthday);
	}
}
