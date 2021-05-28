package lk.wixis360.website.config;

import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(TwilioInitializer.class);

    private final TwilioConfig twilioConfig;

    @Autowired
    public TwilioInitializer(TwilioConfig twilioConfig){
        this.twilioConfig = twilioConfig;
        // mn set krpu eka
//        twilioConfig.setAccountSid("AC0e49ee644452b0404f5942ecf8f52e0c");
//        twilioConfig.setAuthToken("60a3254dc70004d12910bbc72cf7c603");
//        twilioConfig.setTrailNumber("17865902173");
        ///////////////////
        Twilio.init(
                twilioConfig.getAccountSid(),
                twilioConfig.getAuthToken()
        );
        LOGGER.info("Twilio initialized ... with account sid {} ", twilioConfig.getAccountSid());
    }

}
