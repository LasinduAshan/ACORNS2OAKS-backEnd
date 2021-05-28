package lk.wixis360.website.service;


import lk.wixis360.website.dto.StudentReviewDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentReviewService {
    public void saveReview(StudentReviewDTO dto);
    public List<StudentReviewDTO> getAllReviewsByCourse(String courseId);
}
