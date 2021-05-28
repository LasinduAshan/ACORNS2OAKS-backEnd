package lk.wixis360.website.dto;

import lk.wixis360.website.entity.CourseSubject;
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
public class SubjectDTO {
    private String id;
    private String name;
}
