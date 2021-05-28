package lk.wixis360.website.dto;

import lk.wixis360.website.entity.CourseSubject;
import lk.wixis360.website.entity.Registration;
import lk.wixis360.website.entity.Tutor;
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
public class CourceDTO {
    private String cid;
    private String cname;
    private String duration;
    private double price;
    private String email;

    private String description;
    private String requirements;
    private String review;
    private String tutorName;
    private String imgPath;
    private String [] videoPath;
    private String category;
    private String tutorID;

    private String title;
    private String courseIncludes;
    private String deteails;
    private String whatWillLearn;
    private String tutorEamilOrContact;

    private int rateValue;

    // get myCourse details constructor
    public CourceDTO(String cid, String cname, String duration, String description) {
        this.cid = cid;
        this.cname = cname;
        this.duration = duration;
        this.description = description;
    }

    public CourceDTO(String cid, String cname, String duration, double price, String email, String description, String requirements, String review, String tutorName, String imgPath, String [] videoPath, String category, String tutorID) {
        this.cid = cid;
        this.cname = cname;
        this.duration = duration;
        this.price = price;
        this.email = email;
        this.description = description;
        this.requirements = requirements;
        this.review = review;
        this.tutorName = tutorName;
        this.imgPath = imgPath;
        this.videoPath = videoPath;
        this.category = category;
        this.tutorID = tutorID;
    }

    public CourceDTO(String cid, String cname, String duration, double price, String description, String requirements, String review, String imgPath, String [] videoPath, String category, String tutorID) {
        this.cid = cid;
        this.cname = cname;
        this.duration = duration;
        this.price = price;
        this.description = description;
        this.requirements = requirements;
        this.review = review;
        this.imgPath = imgPath;
        this.videoPath = videoPath;
        this.category = category;
        this.tutorID = tutorID;
    }

    public CourceDTO(String cid, String cname, String duration, double price, String description, String requirements, String review, String tutorName, String imgPath, String [] videoPath, String category, String tutorID) {
        this.cid = cid;
        this.cname = cname;
        this.duration = duration;
        this.price = price;
        this.description = description;
        this.requirements = requirements;
        this.review = review;
        this.tutorName = tutorName;
        this.imgPath = imgPath;
        this.videoPath = videoPath;
        this.category = category;
        this.tutorID = tutorID;
    }

    public CourceDTO(String cid, String cname, String duration, double price) {
        this.cid = cid;
        this.cname = cname;
        this.duration = duration;
        this.price = price;
    }

    public CourceDTO(String cid, String cname, String duration, String description, String requirements, String review, String tutorName, String imgPath, String [] videoPath) {
        this.cid = cid;
        this.cname = cname;
        this.duration = duration;
        this.description = description;
        this.requirements = requirements;
        this.review = review;
        this.tutorName = tutorName;
        this.imgPath = imgPath;
        this.videoPath = videoPath;
    }

    public CourceDTO(String cid, String title, String deteails, String tutorName, String imgPath, String tutorID) {
        this.cid = cid;
        this.title = title;
        this.deteails = deteails;
        this.tutorName = tutorName;
        this.imgPath = imgPath;
        this.tutorID = tutorID;
    }

    public CourceDTO(String cid, String title, String deteails, String courseIncludes, String[] paths, String imgPath) {
        this.cid = cid;
        this.title = title;
        this.deteails = deteails;
        this.courseIncludes = courseIncludes;
        this.videoPath = paths;
        this.imgPath = imgPath;
    }

    public CourceDTO(String cid, String title, String deteails, String tid, String imgPath, int rateValue) {
        this.cid = cid;
        this.title = title;
        this.deteails = deteails;
        this.tutorID = tid;
        this.imgPath = imgPath;
        this.rateValue = rateValue;
    }

    public CourceDTO(String cid, String title, String deteails, String tutorName, String imgPath, String tid, int rate) {
        this.cid = cid;
        this.title = title;
        this.deteails = deteails;
        this.tutorName = tutorName;
        this.imgPath = imgPath;
        this.tutorID = tid;
        this.rateValue = rate;
    }

    public CourceDTO(String title, String courseIncludes, String deteails, String whatWillLearn, String tutorEamilOrContact) {
        this.title = title;
        this.courseIncludes = courseIncludes;
        this.deteails = deteails;
        this.whatWillLearn = whatWillLearn;
        this.tutorEamilOrContact = tutorEamilOrContact;
    }

    public CourceDTO(String cid, String title, String deteails, String courseIncludes, String[] paths, String imgPath, int rate) {
        this.cid = cid;
        this.title = title;
        this.deteails = deteails;
        this.courseIncludes = courseIncludes;
        this.videoPath = paths;
        this.imgPath = imgPath;
        this.rateValue = rate;
    }
}

