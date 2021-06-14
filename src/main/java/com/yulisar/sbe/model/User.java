/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yulisar.sbe.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Yulizar
 */
@Entity
@Table(name = "tb_master_user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
//    @SequenceGenerator(name="seq_id_user",
//                       sequenceName="seq_id_user",
//                       allocationSize=1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//                    generator="seq_id_user")
    @Column(name = "ID")
    private Integer idUser;

    @Column(name = "NAMA")
    private String nama;

    @Column(name = "JENIS_KELAMIN")
    private char jenisKelamin;

    @Column(name = "TANGGAL_LAHIR")
    @Temporal(value=TemporalType.DATE)
    private Date tanggalLahir;

    @Column(name = "ALAMAT")
    private String alamat;

    @Column(name = "EMAIL")
    private String email;

//    @ManyToOne
//    @Column(name = "ROLE_ID")
//    private UserRole userRole;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", insertable = true, updatable = true)
    private UserRole userRole;
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "post_id")
//    private Post post;
    
    

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public char getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(char jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    
    

}
