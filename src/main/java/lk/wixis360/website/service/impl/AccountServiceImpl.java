package lk.wixis360.website.service.impl;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lk.wixis360.website.config.TwilioConfig;
import lk.wixis360.website.dto.AccountDTO;
import lk.wixis360.website.dto.SmsRequestDTO;
import lk.wixis360.website.entity.Account;
import lk.wixis360.website.repo.AccountRepo;
import lk.wixis360.website.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    static String otpCode;

    private final TwilioConfig twilioConfig;

    @Autowired
    AccountRepo repo;

    @Autowired
    ModelMapper mapper;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    public AccountServiceImpl(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }


    @Override
    public void saveAccount(AccountDTO dto) {
        //if (!repo.existsById(dto.getEmail())){
            //Account c = mapper.map(dto, Account.class);

            Account c = new Account(UUID.randomUUID().toString(),dto.getEmail(),dto.getContact(),dto.getPassword(),dto.getRole());
            repo.save(c);
//        }else {
//            throw new RuntimeException("Account All ready exist");
//        }
    }

    @Override
    public String searchLoginUserRole(String email, String password) {
       // Account loginDetails = repo.findLoginByEmailAndAndPassword(email, password);
       // Account loginDetails = repo.findLoginByEmailOrContactAndPassword(email, email, password);
        Account loginDetails = repo.getAccountByContactOrEmailAndPassword(email, email, password);
        if (loginDetails != null ){
            return loginDetails.getRole();
        }else {
            throw new RuntimeException("Wrong Email or password");
        }
    }

    @Override
    public boolean searchAccount(String value) {
        Account account = repo.findAccountByEmailOrContact(value,value);
        if (account == null){
            return false;
        }else {
             return true;
        }
    }

    @Override
    public String searchEmail(String value) {
        Account account = repo.findAccountByEmailOrContact(value,value);
        return account.getEmail();
    }

    @Override
    public String searchContactNo(String value) {
        Account account = repo.findAccountByEmailOrContact(value,value);
        return account.getContact();
    }

    @Override
    public Boolean emailOtpCodeChecking(String otp) {

        //System.out.println(AccountServiceImpl.otpCode);
        int a = Integer.parseInt(AccountServiceImpl.otpCode);
        int b = Integer.parseInt(otp);
        System.out.println(a);
        System.out.println(b);

        if (a == b){
            return true;
        }else {
            return false;
        }

    }

    public void sendSimpleMessage(String to, String subject, String text) {

        Random random = new Random();
        int otp = 100000 + random.nextInt(90000);

        AccountServiceImpl.otpCode = otp+"";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(otp+"");
        emailSender.send(message);
    }

    @Override
    public void sendSms(SmsRequestDTO smsRequestDTO) {
        PhoneNumber to = new PhoneNumber(smsRequestDTO.getPhoneNumber());
        PhoneNumber from = new PhoneNumber(twilioConfig.getTrailNumber());
        String message = smsRequestDTO.getMessage();
        MessageCreator creator = Message.creator(to, from, message );
        creator.create();
    }

    @Override
    public void updateAccountPassword(AccountDTO dto) {
        Account account = repo.findAccountByEmailOrContact(dto.getEmailOrPhoneNumber(),dto.getEmailOrPhoneNumber());
        if (account != null) {
            Account c = repo.findAccountByEmail(account.getEmail());
            c.setPassword(dto.getPassword());
            repo.save(c);
        } else {
            throw new RuntimeException("No such Password for update..!");
        }
    }

}
