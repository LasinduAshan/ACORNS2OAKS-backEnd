package lk.wixis360.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Registration {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private int rid;
    private String rid;
    private Date regDate;
    private String tutorID;

    @ManyToOne
    @JoinColumn(name = "studentID", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "courseID", referencedColumnName = "cid")
    private Cource cource;

   /* @OneToMany(mappedBy = "registration",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Payment> payments = new ArrayList<>();*/

    public Registration(String rid, Date regDate, Student student, Cource cource) {
        this.rid = rid;
        this.regDate = regDate;
        this.student = student;
        this.cource = cource;
    }

    public Registration(Date regDate, Cource cource, Student student) {
        this.regDate = regDate;
        this.cource = cource;
        this.student = student;
    }

    public Registration(Date regDate, String tutorID, Student student, Cource cource) {
        this.regDate = regDate;
        this.tutorID = tutorID;
        this.student = student;
        this.cource = cource;
    }
}
