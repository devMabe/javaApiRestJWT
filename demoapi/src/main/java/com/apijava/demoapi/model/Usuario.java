package com.apijava.demoapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@EqualsAndHashCode @ToString
public class Usuario {
    @Id
    @Getter @Setter @Column(name = "Id")
    private int id;
    
    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "user")
    private String user;

    @Getter @Setter @Column(name = "password")
    private String password;

 

}
