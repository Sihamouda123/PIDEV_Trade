package com.PIdevv.PI.Repository;

import com.PIdevv.PI.Entities.EmailVerificationToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface EmailVerificationTokenRepository extends CrudRepository<EmailVerificationToken, Long> {

    EmailVerificationToken findByToken(String token);

    @Query("Select T From EmailVerificationToken T where T.expireDate < ?1")
    List<EmailVerificationToken> findExpireToken(Instant date);


}
