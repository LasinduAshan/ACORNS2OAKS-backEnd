package lk.wixis360.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
   // private int loginID;
    private String loginID;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String contact;
    private String password;
    private String role;

    public Account(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Account(String email, String contact, String password, String role) {
        this.email = email;
        this.contact = contact;
        this.password = password;
        this.role = role;
    }
//    @OneToOne
//    @JoinColumn(name = "studentID", referencedColumnName = "id")
//    private Student student;

//    @OneToOne
//    @JoinColumn(name = "userID", referencedColumnName = "tid")
//    private Tutor tutor;
}
