package lk.wixis360.website.repo;

import lk.wixis360.website.entity.Rating;
import lk.wixis360.website.entity.StudentReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface RatingRepo extends JpaRepository<Rating, String> {



//    @Query(value = "select * from student_review where courseid=?1", nativeQuery = true)
//    ArrayList<StudentReview> findAllByReviewByCourseID(String cid);

    //Account findLoginByEmailAndAndPassword(String email, String password);
    //Account findLoginByEmailOrContactOrPassword(String email, String contact, String password);
//    Account findLoginByEmailOrContactAndPassword(String email, String contact, String password);
//
//   /* @Query(value = "select * from account where contact=?1 or email=?2 and password=?3", nativeQuery = true)
//    Account getAccountByContactOrEmailAndPassword(String contact, String email, String password);*/
//
//    @Query(value = "select * from account where contact=?1 and password=?3 or email=?2 and password=?3", nativeQuery = true)
//    Account getAccountByContactOrEmailAndPassword(String contact, String email, String password);
//    @Query(value = "select * from file_upload where courseid=?1", nativeQuery = true)
//    ArrayList<FileUpload> findAllFileUploadByCourseID(String id);

    @Query(value = "select * from rating where courseid=?1 and studentid=?2", nativeQuery = true)
    Rating findRatingByCourseIDAndStudentID(String cid,String sid);

    @Query(value = "select sum(rate_value) from rating where courseid=?1", nativeQuery = true)
    int findRatingSumByCourseID(String cid);

    @Query(value = "select count(rate_value) from rating where courseid=?1", nativeQuery = true)
    int findRatingCountByCourseID(String cid);

    @Query(value = "select * from rating where courseid=?1", nativeQuery = true)
    ArrayList<Rating> findRatingByCourseID(String cid);

}
