package lk.wixis360.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rating {
    @Id
    private String ratingID;
    private int rateValue;

    @ManyToOne
    @JoinColumn(name = "studentID", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "courseID", referencedColumnName = "cid")
    private Cource cource;

}
