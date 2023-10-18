/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author zouag
 */
public class Coach {
    private int id,age;
    private String nom,prenom,email,specialite;

    public Coach(int id, int age, String nom, String prenom, String email, String specialite) {
        this.id = id;
        this.age = age;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.specialite = specialite;
    }

    public Coach(int age, String nom, String prenom, String email, String specialite) {
        this.age = age;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.specialite = specialite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Coach() {
    }

    @Override
    public String toString() {
        return "Coach{" + "id=" + id + ", age=" + age + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", specialite=" + specialite + '}';
    }
    
    
}
