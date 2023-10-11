package com.PIdevv.PI.Service;


import com.PIdevv.PI.Entities.EmailVerificationToken;

import java.util.List;

public interface IEmailVerificationTokenService {

    EmailVerificationToken createEmailVerificationToken(Object user);

    void ConfirmUserRegistration(String Token);

    List<EmailVerificationToken> getExpireToken();

    void deleteToken(Long id);
}
