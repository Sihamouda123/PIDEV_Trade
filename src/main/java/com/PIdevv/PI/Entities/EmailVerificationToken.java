package com.PIdevv.PI.Entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="emailToken")
public class EmailVerificationToken implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long IdEmailToken;
    @Column(name="token")
    private String token;
    @Column(name="expireDate")
    private Instant expireDate;


    @OneToOne
    private User emailUser;


}
