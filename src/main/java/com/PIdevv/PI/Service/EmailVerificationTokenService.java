package com.PIdevv.PI.Service;


import com.PIdevv.PI.Entities.EmailVerificationToken;
import com.PIdevv.PI.Entities.User;
import com.PIdevv.PI.Exception.TokenEmailException;
import com.PIdevv.PI.Repository.EmailVerificationTokenRepository;
import com.PIdevv.PI.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class EmailVerificationTokenService implements IEmailVerificationTokenService {

    @Value("${email.emailTokenDurationMs}")
    private Long emailTokenDurationMs;
    @Autowired
    EmailVerificationTokenRepository EMVRep;
    @Autowired
    UserRepository userRepository;


    @Override
    public EmailVerificationToken createEmailVerificationToken(Object user) {
        EmailVerificationToken EMV=new EmailVerificationToken();
        EMV.setToken(UUID.randomUUID().toString());
        EMV.setExpireDate(Instant.now().plusSeconds(emailTokenDurationMs));
        if(user instanceof User)
            EMV.setEmailUser(userRepository.findByEmail(((User) user).getEmail()));

        EMVRep.save(EMV);
        return EMV;
    }


    private boolean VerifyExpiration(EmailVerificationToken token) {
        if(token.getExpireDate().isBefore(Instant.now())) {
            EMVRep.delete(token);
            throw new TokenEmailException(token.getToken(), "this token was expired");
        }

        return true;
    }

    @Override
    public void ConfirmUserRegistration(String Token) {
        EmailVerificationToken emailVerificationToken=EMVRep.findByToken(Token);
        System.out.println(Token);
        User user =emailVerificationToken.getEmailUser();
        this.VerifyExpiration(emailVerificationToken);
        if(user != null) {
            user.setActived(true);
            userRepository.save(user);

        }
    }


    @Override
    public List<EmailVerificationToken> getExpireToken() {

        return EMVRep.findExpireToken(Instant.now());
    }


    @Override
    public void deleteToken(Long id) {
        EMVRep.deleteById(id);

    }

}

