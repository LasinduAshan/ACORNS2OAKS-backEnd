package lk.wixis360.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cource {
    @Id
    private String cid;
    private String title;
    private String courseIncludes;
    private String deteails;
    private String whatWillLearn;
    private String imgPath;
   /* private String cname;
    private String duration;
    private double price;
    //private int price;

    private String description;
    private String requirements;
    private String review;
    //private String tutorName;
    private String imgPath;
    private String videoPath;
    private String category;*/

    public Cource(String cid, String title, String courseIncludes, String deteails, String whatWillLearn, String imgPath, Tutor tutor) {
        this.cid = cid;
        this.title = title;
        this.courseIncludes = courseIncludes;
        this.deteails = deteails;
        this.whatWillLearn = whatWillLearn;
        this.imgPath = imgPath;
        this.tutor = tutor;
    }

    @OneToMany(mappedBy = "cource",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<Registration> registrations = new ArrayList<>();

   /* @OneToMany(mappedBy = "cource")
    private List<Tutor> tutors = new ArrayList<>();*/
   @ManyToOne
   @JoinColumn(name = "tutorID", referencedColumnName = "tid")
   private Tutor tutor;

    @OneToMany(mappedBy = "cource",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<CourseSubject> courseSubjects = new ArrayList<>();

    @OneToMany(mappedBy = "cource",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<FileUpload> fileUploads = new ArrayList<>();

    @OneToMany(mappedBy = "cource",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<StudentReview> studentReviews = new ArrayList<>();

    @OneToMany(mappedBy = "cource",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<Rating> ratings = new ArrayList<>();

   /* public Cource(String cid, String cname, String duration, double price) {
        this.cid = cid;
        this.cname = cname;
        this.duration = duration;
        //this.price = price;
    }*/
}
