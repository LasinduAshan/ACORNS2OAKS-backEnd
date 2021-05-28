package lk.wixis360.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TutorDesignation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String designation;


    @ManyToOne
    @JoinColumn(name = "tutorID", referencedColumnName = "tid")
    private Tutor tutor;

    public TutorDesignation(String designation, Tutor tutor) {
        this.designation = designation;
        this.tutor = tutor;
    }
//    @OneToOne
//    @JoinColumn(name = "studentID", referencedColumnName = "id")
//    private Student student;

//    @OneToOne
//    @JoinColumn(name = "userID", referencedColumnName = "tid")
//    private Tutor tutor;
}
