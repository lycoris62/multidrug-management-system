package thedrugking.mms.domain.medicine.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import thedrugking.mms.domain.medicine.application.MedicineService;
import thedrugking.mms.global.common.response.SuccessResponseDto;

@RestController
@RequiredArgsConstructor
public class MedicineImageApi {

    private final MedicineService medicineService;

    @PostMapping("/api/patient/upload")
    public ResponseEntity<SuccessResponseDto> upload(@RequestParam MultipartFile file) {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("파일이 존재하지 않음");
        }

        SuccessResponseDto success = medicineService.upload(file);
        return ResponseEntity.ok(success);
    }
}
