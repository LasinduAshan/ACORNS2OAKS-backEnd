package lk.wixis360.website.service.impl;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lk.wixis360.website.config.TwilioConfig;
import lk.wixis360.website.dto.SmsRequestDTO;
import lk.wixis360.website.service.SmsSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SmsSenderServiceImpl implements SmsSenderService {

    private final TwilioConfig twilioConfig;

    @Autowired
    public SmsSenderServiceImpl(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    @Override
    public void sendSms(SmsRequestDTO smsRequestDTO) {
        PhoneNumber to = new PhoneNumber(smsRequestDTO.getPhoneNumber());
        PhoneNumber from = new PhoneNumber(twilioConfig.getTrailNumber());
        String message = smsRequestDTO.getMessage();
        MessageCreator creator = Message.creator(to, from, message );
        creator.create();
    }
}
