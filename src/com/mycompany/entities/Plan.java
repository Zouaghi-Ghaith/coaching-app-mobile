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
public class Plan {
    private int id,nbParticipant,nombre_de_seances,coachId;
    String nom,description;

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }
    

    public Plan(int id, int nbParticipant, int nombre_de_seances, String nom, String description) {
        this.id = id;
        this.nbParticipant = nbParticipant;
        this.nombre_de_seances = nombre_de_seances;
        this.nom = nom;
        this.description = description;
    }

    public Plan(int nbParticipant, int nombre_de_seances, String nom, String description) {
        this.nbParticipant = nbParticipant;
        this.nombre_de_seances = nombre_de_seances;
        this.nom = nom;
        this.description = description;
    }

    public Plan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbParticipant() {
        return nbParticipant;
    }

    public void setNbParticipant(int nbParticipant) {
        this.nbParticipant = nbParticipant;
    }

    public int getNombre_de_seances() {
        return nombre_de_seances;
    }

    public void setNombre_de_seances(int nombre_de_seances) {
        this.nombre_de_seances = nombre_de_seances;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Plan{" + "id=" + id + ", nbParticipant=" + nbParticipant + ", nombre_de_seances=" + nombre_de_seances + ", nom=" + nom + ", description=" + description + '}';
    }
    
}
