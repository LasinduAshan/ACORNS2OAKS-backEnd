package lk.wixis360.website.service.impl;

import lk.wixis360.website.dto.StudentReviewDTO;
import lk.wixis360.website.entity.Cource;
import lk.wixis360.website.entity.FileUpload;
import lk.wixis360.website.entity.Student;
import lk.wixis360.website.entity.StudentReview;
import lk.wixis360.website.repo.CourseRepo;
import lk.wixis360.website.repo.FileUploadRepo;
import lk.wixis360.website.repo.StudentRepo;
import lk.wixis360.website.repo.StudentReviewRepo;
import lk.wixis360.website.service.FileUploadService;
import lk.wixis360.website.service.StudentReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentReviewServiceImpl implements StudentReviewService {


    @Autowired
    StudentReviewRepo repo;

    @Autowired
    CourseRepo crepo;

    @Autowired
    StudentRepo srepo;

    @Autowired
    ModelMapper mapper;


    @Override
    public void saveReview(StudentReviewDTO dto) {

        Cource one = crepo.getOne(dto.getCourseID());

        Student student = srepo.findStudentByEmailOrContact(dto.getStudentID(), dto.getStudentID());

        StudentReview review = new StudentReview(dto.getReview(),student.getId(),dto.getTutorID(),one);

        repo.save(review);
    }

    @Override
    public List<StudentReviewDTO> getAllReviewsByCourse(String courseId) {

        ArrayList<StudentReview> reviews = repo.findAllByReviewByCourseID(courseId);

        List<StudentReviewDTO> s = new ArrayList<>();

        for (StudentReview r : reviews) {
            Student one = srepo.getOne(r.getStudentID());
            s.add(new StudentReviewDTO(r.getId(),r.getReview(),r.getStudentID(),r.getCource().getCid(),one.getImgPath()));
        }

        return s;
    }
}


