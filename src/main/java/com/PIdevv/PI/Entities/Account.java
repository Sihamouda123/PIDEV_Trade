package com.PIdevv.PI.Entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table( name = "Account")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAccount")
    private Integer IdAccount;
    private Long Balance;
    private Date DateOpened;
    @ManyToOne
    User user;

    @Override
    public String toString() {
        return "Account{" +
                "IdAccount=" + IdAccount +
                ", Balance=" + Balance +
                ", DateOpened=" + DateOpened +
                '}';
    }

    public Integer getIdAccount() {
        return IdAccount;
    }

    public void setIdAccount(Integer idAccount) {
        IdAccount = idAccount;
    }

    public Long getBalance() {
        return Balance;
    }

    public void setBalance(Long balance) {
        Balance = balance;
    }

    public Date getDateOpened() {
        return DateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        DateOpened = dateOpened;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

