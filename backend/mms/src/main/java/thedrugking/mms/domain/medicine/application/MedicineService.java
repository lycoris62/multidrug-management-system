package thedrugking.mms.domain.medicine.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import thedrugking.mms.response.SuccessResponseDto;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MedicineService {

    @Transactional
    public SuccessResponseDto upload(MultipartFile file) {
        String ext = getExtension(file);
        File filePath = createFilePath(ext);
        saveFile(file, filePath);

        return new SuccessResponseDto();
    }

    private String getExtension(MultipartFile file) {
        if (file.getOriginalFilename() == null) {
            throw new IllegalStateException("파일의 이름 없음");
        }

        String filename = file.getOriginalFilename();
        return filename.substring(filename.lastIndexOf("."));
    }

    private File createFilePath(String ext) {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        String uniqueName = uuidString.split("-")[0];
        String rootPath = System.getProperty("user.dir");
        return new File("%s/src/main/resources/static/image/%s%s".formatted(rootPath, uniqueName, ext));
    }

    private void saveFile(MultipartFile file, File filePath) {
        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
