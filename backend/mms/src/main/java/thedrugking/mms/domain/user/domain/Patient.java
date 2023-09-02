package thedrugking.mms.domain.user.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thedrugking.mms.domain.medicine.domain.MedicineImage;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@DiscriminatorValue("P")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Patient extends User {

    @OneToMany(mappedBy = "patient")
    private List<Follow> followList = new ArrayList<>();

    @OneToMany(mappedBy = "patient")
    private List<MedicineImage> medicineImageList = new ArrayList<>();
}
