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
public class Student {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    @Column(unique = true)
    private String contact;
    private String dob;
    @Column(unique = true)
    private String email;
    private String imgPath;

    @OneToMany(mappedBy = "student",cascade= CascadeType.ALL,orphanRemoval=true)
    private List<Registration> registrations = new ArrayList<>();

    @OneToMany(mappedBy = "student",cascade= CascadeType.ALL,orphanRemoval=true)
    private List<StudentInterest> studentInterests = new ArrayList<>();

    @OneToMany(mappedBy = "student",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<Rating> ratings = new ArrayList<>();

//    @OneToOne(mappedBy = "student")
//    private Account account;

}
