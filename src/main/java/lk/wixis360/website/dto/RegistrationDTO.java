package lk.wixis360.website.dto;

import lk.wixis360.website.entity.Cource;
import lk.wixis360.website.entity.Payment;
import lk.wixis360.website.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    private int rid;
    private String regDate;
    private String sid;
    private String cid;
    private String amount;
    private String email;
    private String tutorID;

    public RegistrationDTO(String cid, String amount, String email) {
        this.cid = cid;
        this.amount = amount;
        this.email = email;
    }

    public RegistrationDTO(String cid, String email) {
        this.cid = cid;
        this.email = email;
    }
}
