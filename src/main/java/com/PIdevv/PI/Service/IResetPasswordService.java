package com.PIdevv.PI.Service;


import com.PIdevv.PI.Entities.PasswordResetToken;
import com.PIdevv.PI.Exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public interface IResetPasswordService {
      //  void resetPassword(String email, String newPassword) throws UsernameNotFoundException;
    PasswordResetToken CreatePasswordToken(String email) throws UserNotFoundException, UnsupportedEncodingException, MessagingException;
    boolean VerifyExpiration(String token);
    void ConfirmPasswordReset(String token,String password);

    List<PasswordResetToken> getExpireToken();

    void deleteToken(Long id);
    }

