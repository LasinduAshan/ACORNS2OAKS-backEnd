package lk.wixis360.website.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentReviewDTO {
    private int id;
    private String review;
    private String studentID;
    private String courseID;
    private String imgPath;
    private String tutorID;

    public StudentReviewDTO(int id, String review, String studentID, String courseID, String imgPath) {
        this.id = id;
        this.review = review;
        this.studentID = studentID;
        this.courseID = courseID;
        this.imgPath = imgPath;
    }
}
