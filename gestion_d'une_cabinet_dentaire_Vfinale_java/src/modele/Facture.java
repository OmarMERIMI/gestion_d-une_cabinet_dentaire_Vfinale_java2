package modele;

import java.util.Date;

public class Facture {
    private int id;
    private Date dateEmission;
    private float montant;
    private String statut; // "Payée", "En attente", "Annulée"
    private Dentiste dentiste;
    private Patient patient;
    
    public Facture(int id, Date dateEmission, float montant, String statut) {
        this.id = id;
        this.dateEmission = dateEmission;
        this.montant = montant;
        this.statut = statut;
    }
    
    public Facture() {
        // Constructeur par défaut
    }
    
    // Getters et Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Date getDateEmission() {
        return dateEmission;
    }
    
    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
    }
    
    public float getMontant() {
        return montant;
    }
    
    public void setMontant(float montant) {
        this.montant = montant;
    }
    
    public String getStatut() {
        return statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    public Dentiste getDentiste() {
        return dentiste;
    }
    
    public void setDentiste(Dentiste dentiste) {
        this.dentiste = dentiste;
    }
    
    public Patient getPatient() {
        return patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    // Méthodes du diagramme UML
    public float calculerMontant() {
        System.out.println("Calcul du montant de la facture ID: " + id);
        return montant;
    }
    
    public void payer() {
        System.out.println("Paiement de la facture ID: " + id);
        this.statut = "Payée";
    }
    
    @Override
    public String toString() {
        return "Facture [ID: " + id + ", Date d'émission: " + dateEmission + ", Montant: " + montant + ", Statut: " + statut + "]";
    }
}