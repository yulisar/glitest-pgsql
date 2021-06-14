/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yulisar.sbe.model;

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
import javax.persistence.Table;

/**
 *
 * @author Yulizar
 */
@Entity
@Table(name = "tb_master_user_role")
public class UserRole {
     
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private Integer idRole;
    
    @Column(name = "NAMA_ROLE")
    private String namaRole;
    

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getNamaRole() {
        return namaRole;
    }

    public void setNamaRole(String namaRole) {
        this.namaRole = namaRole;
    }
    
    

}
