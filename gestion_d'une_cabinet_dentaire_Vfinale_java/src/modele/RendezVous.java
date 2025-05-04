package modele;

import java.util.Date;

public class RendezVous {
    private Date date;
    private String heure;
    private String statut; // "Confirmé", "En attente", "Annulé"
    private Patient patient;
    private Dentiste dentiste;
    private SalleDeTraitement salle;
    
    public RendezVous(Date date, String heure, String statut) {
        this.date = date;
        this.heure = heure;
        this.statut = statut;
    }
    
    public RendezVous() {
        // Constructeur par défaut
    }
    
    // Getters et Setters
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getHeure() {
        return heure;
    }
    
    public void setHeure(String heure) {
        this.heure = heure;
    }
    
    public String getStatut() {
        return statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    public Patient getPatient() {
        return patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public Dentiste getDentiste() {
        return dentiste;
    }
    
    public void setDentiste(Dentiste dentiste) {
        this.dentiste = dentiste;
    }
    
    public SalleDeTraitement getSalle() {
        return salle;
    }
    
    public void setSalle(SalleDeTraitement salle) {
        this.salle = salle;
    }
    
    // Méthodes du diagramme UML
    public void ajouterRappel() {
        System.out.println("Rappel ajouté pour le rendez-vous du " + date + " à " + heure);
    }
    
    public void confirmer() {
        System.out.println("Rendez-vous du " + date + " à " + heure + " confirmé");
        this.statut = "Confirmé";
    }
    
    @Override
    public String toString() {
        return "RendezVous [Date: " + date + ", Heure: " + heure + ", Statut: " + statut + "]";
    }
}