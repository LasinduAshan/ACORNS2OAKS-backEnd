package lk.wixis360.website.dto;

import lk.wixis360.website.entity.Cource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TutorDTO {
    private String tid;
    private String firstName;
    private String lastName;
    private String userName;
    private String contact;
    private String dob;
    private String experience;
    private String email;
    private String password;

    private String nameOfInstitution;
    private String country;
    private String subject;
    private String experienceInstitution;
    //private String [] model1;
    private String model1;
    private String imagePath;
    private String about;

    private int reviewValue;
    private int RagisteedCourseValue;


    public TutorDTO(String tid, String firstName, String lastName, String userName, String contact, String dob, String experience, String email, String password, String nameOfInstitution, String country, String subject, String experienceInstitution, String model1, String imagePath, String about) {
        this.tid = tid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.contact = contact;
        this.dob = dob;
        this.experience = experience;
        this.email = email;
        this.password = password;
        this.nameOfInstitution = nameOfInstitution;
        this.country = country;
        this.subject = subject;
        this.experienceInstitution = experienceInstitution;
        this.model1 = model1;
        this.imagePath = imagePath;
        this.about = about;
    }

    public TutorDTO(String tid, String firstName, String lastName, String imagePath) {
        this.tid = tid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imagePath = imagePath;
    }

    public TutorDTO(String tid, String firstName, String lastName, String userName, String contact, String dob, String experience, String email, String password, String nameOfInstitution, String country, String subject, String experienceInstitution, String model1, String imagePath) {
        this.tid = tid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.contact = contact;
        this.dob = dob;
        this.experience = experience;
        this.email = email;
        this.password = password;
        this.nameOfInstitution = nameOfInstitution;
        this.country = country;
        this.subject = subject;
        this.experienceInstitution = experienceInstitution;
        this.model1 = model1;
        this.imagePath = imagePath;
    }

    public TutorDTO(String tid, String firstName, String lastName, String imgPath, String nameOfInstitution, String experience) {
        this.tid = tid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imagePath = imgPath;
        this.nameOfInstitution = nameOfInstitution;
        this.experience = experience;
    }
}
