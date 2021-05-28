package lk.wixis360.website.service;


import lk.wixis360.website.dto.AccountDTO;
import lk.wixis360.website.dto.SmsRequestDTO;

public interface AccountService {
    void saveAccount(AccountDTO dto);

    String searchLoginUserRole(String email, String password);

    boolean searchAccount(String value);

    String searchEmail(String value);

    String searchContactNo(String value);

    Boolean emailOtpCodeChecking(String otp);

    void sendSimpleMessage(String to, String subject, String text);

    void sendSms(SmsRequestDTO smsRequestDTO);

    public void updateAccountPassword(AccountDTO dto);
}
