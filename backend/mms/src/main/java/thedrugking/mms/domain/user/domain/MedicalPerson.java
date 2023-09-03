package thedrugking.mms.domain.user.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
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
}
