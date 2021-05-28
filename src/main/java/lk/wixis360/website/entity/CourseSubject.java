package lk.wixis360.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CourseSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int csID;

    @ManyToOne
    @JoinColumn(name = "courseID", referencedColumnName = "cid")
    private Cource cource;

    @ManyToOne
    @JoinColumn(name = "subjectID", referencedColumnName = "id")
    private Subject subject;
}
