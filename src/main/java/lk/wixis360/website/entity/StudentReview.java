package lk.wixis360.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String review;
    private String studentID;
    private String tutorID;


    @ManyToOne
    @JoinColumn(name = "courseID", referencedColumnName = "cid")
    private Cource cource;

    public StudentReview(String review, String studentID, Cource cource) {
        this.review = review;
        this.studentID = studentID;
        this.cource = cource;
    }

    public StudentReview(String review, String studentID, String tutorID, Cource cource) {
        this.review = review;
        this.studentID = studentID;
        this.tutorID = tutorID;
        this.cource = cource;
    }
}
