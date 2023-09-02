package thedrugking.mms.domain.comments.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thedrugking.mms.domain.medicine.domain.MedicineImage;
import thedrugking.mms.domain.user.domain.MedicalPerson;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_person_id")
    private MedicalPerson medicalPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_image_id")
    private MedicineImage medicineImage;
}
