package thedrugking.mms.domain.medicine.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thedrugking.mms.domain.model.BaseEntity;
import thedrugking.mms.domain.user.domain.Patient;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MedicineImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_image_id")
    private Long id;

    private String originalFileName;

    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

//    @OneToMany(mappedBy = "medicalPerson")
//    private List<Comment> commentList = new ArrayList<>();


}
