package lk.wixis360.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FileUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String videoPath;

    public FileUpload(String videoPath, Cource cource) {
        this.videoPath = videoPath;
        this.cource = cource;
    }

    @ManyToOne
    @JoinColumn(name = "courseID", referencedColumnName = "cid")
    private Cource cource;

//    @OneToOne
//    @JoinColumn(name = "studentID", referencedColumnName = "id")
//    private Student student;

//    @OneToOne
//    @JoinColumn(name = "userID", referencedColumnName = "tid")
//    private Tutor tutor;
}
