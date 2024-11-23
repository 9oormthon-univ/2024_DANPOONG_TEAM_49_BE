package com.goormthon3.team49.domain.awsS3;

import com.goormthon3.team49.domain.awsS3.application.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class AmazonS3Controller {

    private final AwsS3Service awsS3Service;

    /**
     * 파일 업로드를 처리하는 엔드포인트
     * @param multipartFiles 업로드할 파일 목록
     * @return 업로드된 파일 이름 목록
     */
    @PostMapping
    public ResponseEntity<List<String>> uploadFile(@RequestParam List<MultipartFile> multipartFiles) {
        List<String> uploadedFiles = awsS3Service.uploadFile(multipartFiles);
        return ResponseEntity.ok(uploadedFiles);
    }

    /**
     * 파일 삭제를 처리하는 엔드포인트
     * @param fileName 삭제할 파일의 이름
     * @return 삭제된 파일 이름
     */
    @DeleteMapping
    public ResponseEntity<String> deleteFile(@RequestParam String fileName) {
        awsS3Service.deleteFile(fileName);
        return ResponseEntity.ok(fileName);
    }
}
