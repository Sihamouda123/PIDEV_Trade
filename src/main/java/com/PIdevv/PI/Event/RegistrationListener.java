package com.PIdevv.PI.Event;


import com.PIdevv.PI.Entities.EmailVerificationToken;
import com.PIdevv.PI.Entities.User;
import com.PIdevv.PI.Service.IEmailVerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final String FromAddress = "mohamed.hcini@esprit.tn";
    private final String SenderName = "FinanceMe Team";
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private IEmailVerificationTokenService emailVerificationTokenService;


    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        try {
            this.confirmRegistration(event);
        } catch (UnsupportedEncodingException | MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) throws UnsupportedEncodingException, MessagingException {
        String Fullname;
        User user = event.getUser();
        String recipientAddress;
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"http://localhost:4200/login\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";

        if (user != null) {
            recipientAddress = user.getEmail();
            Fullname = user.getFirstName() + " " + user.getLastName();
            EmailVerificationToken emailVerificationToken = emailVerificationTokenService.createEmailVerificationToken(user);
            content = content.replace("[[name]]", Fullname);
            String verifyURL = "http://localhost:4200/login" ;
            content = content.replace("[[URL]]", "http://localhost:4200/login");
        }

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(FromAddress, SenderName);
        helper.setTo(user.getEmail());
        helper.setSubject("Email Confirmation");

        helper.setText(content, true);

        javaMailSender.send(message);


    }
}
