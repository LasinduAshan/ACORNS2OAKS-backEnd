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
public class Tutor {
    @Id
    private String tid;
    private String firstName;
    private String lastName;
    private String userName;
    @Column(unique = true)
    private String contact;
    private String dob;
    private String experience;
    @Column(unique = true)
    private String email;

    private String nameOfInstitution;
    private String country;
    private String subject;
    private String experienceInstitution;
    private String designation;
    private String imgPath;
    private String about;

    public Tutor(String tid, String firstName, String lastName, String userName, String contact, String dob, String experience, String email, String nameOfInstitution, String country, String subject, String experienceInstitution, String designation, String imgPath, List<Cource> cources, List<TutorDesignation> tutorDesignations) {
        this.tid = tid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.contact = contact;
        this.dob = dob;
        this.experience = experience;
        this.email = email;
        this.nameOfInstitution = nameOfInstitution;
        this.country = country;
        this.subject = subject;
        this.experienceInstitution = experienceInstitution;
        this.designation = designation;
        this.imgPath = imgPath;
        this.cources = cources;
        this.tutorDesignations = tutorDesignations;
    }

    public Tutor(String tid, String firstName, String lastName, String userName, String contact, String dob, String experience, String email, String nameOfInstitution, String country, String subject, String experienceInstitution, List<Cource> cources, List<TutorDesignation> tutorDesignations) {
        this.tid = tid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.contact = contact;
        this.dob = dob;
        this.experience = experience;
        this.email = email;
        this.nameOfInstitution = nameOfInstitution;
        this.country = country;
        this.subject = subject;
        this.experienceInstitution = experienceInstitution;
        this.cources = cources;
        this.tutorDesignations = tutorDesignations;
    }
/* @ManyToOne
    @JoinColumn(name = "courseID", referencedColumnName = "cid")
    private Cource cource;*/

    @OneToMany(mappedBy = "tutor",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<Cource> cources = new ArrayList<>();

    @OneToMany(mappedBy = "tutor",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<TutorDesignation> tutorDesignations = new ArrayList<>();

//    @OneToOne(mappedBy = "tutor")
//    private Account account;
}
