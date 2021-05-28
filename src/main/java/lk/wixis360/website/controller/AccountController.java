package lk.wixis360.website.controller;

import lk.wixis360.website.dto.AccountDTO;
import lk.wixis360.website.dto.SmsRequestDTO;
import lk.wixis360.website.service.AccountService;
import lk.wixis360.website.service.impl.AccountServiceImpl;
import lk.wixis360.website.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@CrossOrigin
public class AccountController {

    @Autowired
    AccountService accountService;


    @GetMapping(path = "/{email}/{password}")
    public ResponseEntity searchLoginUserRole(@PathVariable String email, @PathVariable String password){
        String userRole = accountService.searchLoginUserRole(email, password);
        StandardResponse success = new StandardResponse(200,"Success",userRole);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @GetMapping(path = "/{value}")
    public ResponseEntity searchAccount(@PathVariable String value){
        boolean b = accountService.searchAccount(value);
        StandardResponse success = new StandardResponse(200,"Success",b);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @GetMapping(path = "/test/{emailOrPhoneNumber}")
    public ResponseEntity sendOTPCodeFromEmail(@PathVariable String emailOrPhoneNumber) {
        String email = accountService.searchEmail(emailOrPhoneNumber);
        System.out.println(email);
        //sahanmaduranga310123@gmail.com
        String s = "test";
        accountService.sendSimpleMessage(email, "otp code", s);

        StandardResponse success = new StandardResponse(200,"Success","no");
        return new ResponseEntity(success, HttpStatus.OK);
        //return "mm";
    }

    @GetMapping(path = "/test1/{emailOrPhoneNumber}")
    public ResponseEntity testMessageSend(@PathVariable String emailOrPhoneNumber) {
        String contactNo = accountService.searchContactNo(emailOrPhoneNumber);
        //System.out.println("+94"+contactNo);
        String s = "message";//"+94763527293"
        accountService.sendSms(new SmsRequestDTO("+94763527293","aaaaaaaaaaaa"));

        StandardResponse success = new StandardResponse(200,"Success","no");
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @GetMapping(path = "/test2/{otp}")
    public ResponseEntity emailOtpCodeChecking(@PathVariable String otp) {

        //System.out.println("reqest eke ywana otp eka        "       +  otp);

        Boolean isCheck = accountService.emailOtpCodeChecking(otp);

        System.out.println(isCheck);

        StandardResponse success = new StandardResponse(200,"Success",isCheck);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @PutMapping
   // @PutMapping(path = "/{emailOrPhoneNumber}/{password}")
    //public ResponseEntity UpdateAccountPassword(@PathVariable String emailOrPhoneNumber, @PathVariable String password){
    public ResponseEntity UpdateAccountPassword(@RequestBody AccountDTO dto){
        //System.out.println(dto.toString());
        accountService.updateAccountPassword(dto);
        StandardResponse success = new StandardResponse(200,"Success",null);
        return new ResponseEntity(success, HttpStatus.OK);
    }

}
