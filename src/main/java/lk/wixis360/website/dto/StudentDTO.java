package lk.wixis360.website.dto;

import lk.wixis360.website.entity.Registration;
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
public class StudentDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String contact;
    private String dob;
    private String password;
    private String [] model1;
    private String imgPath;
}
