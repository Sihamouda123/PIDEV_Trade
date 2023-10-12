package com.PIdevv.PI.Service;


import com.PIdevv.PI.Entities.PasswordResetToken;
import com.PIdevv.PI.Entities.User;
import com.PIdevv.PI.Exception.TokenEmailException;
import com.PIdevv.PI.Exception.UserNotFoundException;
import com.PIdevv.PI.Repository.ResetPasswordRepository;
import com.PIdevv.PI.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ResetPasswordService implements IResetPasswordService  {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResetPasswordRepository resetPasswordRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private UserService userService;
/*


    private static final String SUBJECT = "Reset your password";
    private static final String TEXT = "<h1>Welcome to FINANCEME</h1> To reset your password, click on this link:  ";
    @Override
    public void resetPassword(String email, String newPassword) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found for email: " + email);
         }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        String resetUrl = "http://localhost:8080/reset-password/email " ;
        sendEmail(email, SUBJECT, TEXT+resetUrl);

    }

    private void sendEmail(String to, String subject, String htmlContent) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(htmlContent, true);


        };


        emailSender.send(messagePreparator);
    }*/


    @Value("${pass.PassTokenDurationMs}")
    private long passTokenDurationMs;
    private final String FromAddress="mohamed.hcini@esprit.tn";
    private final String SenderName="FinanceMe Team";




    @Override
    public PasswordResetToken CreatePasswordToken(String email) throws UserNotFoundException, UnsupportedEncodingException, MessagingException {

        PasswordResetToken passwordResetToken=new PasswordResetToken();
        User user=userRepository.findByEmail(email);
System.out.println(user.getEmail());
        passwordResetToken.setExpireDate(Instant.now().plusSeconds(passTokenDurationMs));
        passwordResetToken.setToken(UUID.randomUUID().toString());
        String toAddress;
        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"[[URL]]\" >Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

         if(user != null) {
            passwordResetToken.setUsertPass(user);
             resetPasswordRepository.save(passwordResetToken);
            toAddress=user.getEmail();
            String verifyURL = "http://localhost:8080/api/user/resetPassword/" + passwordResetToken.getToken();
            content = content.replace("[[URL]]", verifyURL);

        }
        else
            throw new UserNotFoundException("Could not find any user with the email"+ email);

        resetPasswordRepository.save(passwordResetToken);
        System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(FromAddress,SenderName);
        helper.setTo(toAddress);
        helper.setSubject("Password Reset");
        helper.setText(content, true);

        emailSender.send(message);

        return passwordResetToken;

    }

    @Override
    public boolean VerifyExpiration(String token) {
        PasswordResetToken passwordResetToken=resetPasswordRepository.findByToken(token);
        if(passwordResetToken !=null) {
            if(passwordResetToken.getExpireDate().isBefore(Instant.now())){
                resetPasswordRepository.delete(passwordResetToken);

                throw new TokenEmailException(token, "this token was expired");
            }
            return true;
        }
        else
            throw new TokenEmailException(token, "this token is not in the database");
    }

    @Override
    public void ConfirmPasswordReset(String token, String password) {
        PasswordResetToken passwordResetToken=resetPasswordRepository.findByToken(token);
        System.out.print(passwordResetToken);
        VerifyExpiration(token);
        User user=passwordResetToken.getUsertPass();

        if(user != null) {
            userService.UpdatePassword(user, password);
        }


    }

    @Override
    public List<PasswordResetToken> getExpireToken() {

        return resetPasswordRepository.findExpireToken(Instant.now());
    }

    @Override
    public void deleteToken(Long id) {
        resetPasswordRepository.deleteById(id);

    }


}






