package lk.wixis360.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subject {
    @Id
    private String id;
    private String name;

    @OneToMany(mappedBy = "subject")
    private List<CourseSubject> courseSubjects = new ArrayList<>();
}
