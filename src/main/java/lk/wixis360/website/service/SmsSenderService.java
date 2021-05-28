package lk.wixis360.website.service;


import lk.wixis360.website.dto.AccountDTO;
import lk.wixis360.website.dto.SmsRequestDTO;

public interface SmsSenderService {
    void sendSms(SmsRequestDTO smsRequestDTO);
}
