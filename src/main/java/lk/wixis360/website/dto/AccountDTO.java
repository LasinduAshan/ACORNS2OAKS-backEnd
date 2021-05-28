package lk.wixis360.website.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String email;
    private String contact;
    private String password;
    private String role;

    private String confirmPassword;
    private String emailOrPhoneNumber;

    public AccountDTO(String email, String contact, String password, String role) {
        this.email = email;
        this.contact = contact;
        this.password = password;
        this.role = role;
    }
}
