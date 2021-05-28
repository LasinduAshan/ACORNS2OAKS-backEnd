package lk.wixis360.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


    @ManyToOne
    @JoinColumn(name = "studentID", referencedColumnName = "id")
    private Student student;

    public StudentInterest(String name, Student student) {
        this.name = name;
        this.student = student;
    }
//    @OneToOne
//    @JoinColumn(name = "studentID", referencedColumnName = "id")
//    private Student student;

//    @OneToOne
//    @JoinColumn(name = "userID", referencedColumnName = "tid")
//    private Tutor tutor;
}
