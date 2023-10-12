package com.PIdevv.PI.Scheduled;


import com.PIdevv.PI.Entities.EmailVerificationToken;
import com.PIdevv.PI.Entities.PasswordResetToken;
import com.PIdevv.PI.Entities.User;
import com.PIdevv.PI.Service.EmailVerificationTokenService;
import com.PIdevv.PI.Service.IResetPasswordService;
import com.PIdevv.PI.Service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
public class SceduledService {

    private static final Logger logger = LogManager.getLogger(SceduledService.class);

    @Autowired
    UserService userService;

    @Autowired
    private IResetPasswordService resetPasswordService;

    @Autowired
    private EmailVerificationTokenService emailVerificationTokenService;


    @Scheduled(cron = "*/1 * * * * *")
    public void DisableUserBan() {
        logger.info("From Scheduled Disable bean");

        for (User user : userService.getBannedUser()) {
            if (user.getBannedPeriode().after(new Date()) || user.getBannedPeriode().compareTo(new Date()) == 0) {
                user.setBanned(false);

                userService.UpdateUser(user);
                logger.info("Disable ban for agent " + user.getUsername());
            }
            else {
                logger.info("nothing to delete");

            }
        }

    }

    @Scheduled(cron = "*/50 * * * * *")
    public void ClearExpireToken() {
        for (EmailVerificationToken emailVerificationToken : emailVerificationTokenService.getExpireToken()) {
            emailVerificationTokenService.deleteToken(emailVerificationToken.getIdEmailToken());
            logger.info("Email Token deleted");


            for (PasswordResetToken passwordResetToken : resetPasswordService.getExpireToken()) {
                resetPasswordService.deleteToken(passwordResetToken.getIdPasswordToken());
                logger.info("Password Token deleted");
            }

        }


    }
}

