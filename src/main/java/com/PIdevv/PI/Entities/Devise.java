package com.PIdevv.PI.Entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Devise extends Trading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double paireDevises;
    private Double tauxChange;
    private Double montantDeviseBase;
    private Double montantDeviseContrePartie;
    private  Double montant;
    private Double spread;

}
