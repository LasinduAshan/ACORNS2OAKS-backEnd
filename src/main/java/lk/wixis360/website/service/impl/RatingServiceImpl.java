package lk.wixis360.website.service.impl;

import lk.wixis360.website.dto.RatingDTO;
import lk.wixis360.website.dto.StudentReviewDTO;
import lk.wixis360.website.entity.Cource;
import lk.wixis360.website.entity.Rating;
import lk.wixis360.website.entity.Student;
import lk.wixis360.website.entity.StudentReview;
import lk.wixis360.website.repo.CourseRepo;
import lk.wixis360.website.repo.RatingRepo;
import lk.wixis360.website.repo.StudentRepo;
import lk.wixis360.website.repo.StudentReviewRepo;
import lk.wixis360.website.service.RatingService;
import lk.wixis360.website.service.StudentReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {


    @Autowired
    RatingRepo repo;

    @Autowired
    CourseRepo crepo;

    @Autowired
    StudentRepo srepo;

    @Autowired
    ModelMapper mapper;


    @Override
    public void saveRating(RatingDTO dto) {

        Student student = srepo.findStudentByEmailOrContact(dto.getStudentID(), dto.getStudentID());
        Cource cource = crepo.getOne(dto.getCourseID());

        Rating rating = repo.findRatingByCourseIDAndStudentID(cource.getCid(), student.getId());

        if (rating == null){
            Rating r = new Rating(UUID.randomUUID().toString(),dto.getRateValue(),student,cource);
            repo.save(r);
        }else {
           rating.setRateValue(dto.getRateValue());
           repo.save(rating);
        }

        int sum = repo.findRatingSumByCourseID(dto.getCourseID());
        System.out.println("Rating sum for courseID         ....."+sum);

        int count = repo.findRatingCountByCourseID(dto.getCourseID());
        System.out.println("Rating Count for courseID         ....."+count);

        //int total = 0;

        int total = sum/count;
        System.out.println("avg.........        "  +total);



    }

    @Override
    public List<RatingDTO> getAllReviewsByCourse(String courseId) {
        return null;
    }
}


