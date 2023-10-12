package com.PIdevv.PI.Entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Inheritance
public class Trading implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double montant;
    private Date date;
    private Double tauxInteret;
    private Integer dureeValidate;
    private String status;
    private String typeOrdre;
    private Double fraisTrade;


}
