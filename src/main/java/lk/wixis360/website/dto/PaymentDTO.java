package lk.wixis360.website.dto;

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
public class PaymentDTO {
    private int pid;
    private String date;
    private double amount;
    private int rid;
    private String cid;
    private String email;

    public PaymentDTO(String date, double amount, int rid) {
        this.date = date;
        this.amount = amount;
        this.rid = rid;
    }

    public PaymentDTO(double amount, String cid, String email) {
        this.amount = amount;
        this.cid = cid;
        this.email = email;
    }
}
