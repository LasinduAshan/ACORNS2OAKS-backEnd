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
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private Date date;
    private double amount;

    /*@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "regID", referencedColumnName = "rid")
    private Registration registration;*/

   /* public Payment(Date date, double amount, Registration registration) {
        this.date = date;
        this.amount = amount;
        this.registration = registration;
    }*/
}
