package modele;

import java.util.ArrayList;
import java.util.List;

public class Dentiste extends Utilisateur {
    private String nom;
    private String specialite;
    private List<DossierPatient> dossierPatients;
    private List<Facture> factures;
    
    public Dentiste(int id, String username, String password, String nom, String specialite) {
        super(id, username, password);
        this.nom = nom;
        this.specialite = specialite;
        this.dossierPatients = new ArrayList<>();
        this.factures = new ArrayList<>();
    }
    
    public Dentiste() {
        super();
        this.dossierPatients = new ArrayList<>();
        this.factures = new ArrayList<>();
    }
    
    // Getters et Setters
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getSpecialite() {
        return specialite;
    }
    
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
    
    public List<DossierPatient> getDossierPatients() {
        return dossierPatients;
    }
    
    public void setDossierPatients(List<DossierPatient> dossierPatients) {
        this.dossierPatients = dossierPatients;
    }
    
    public List<Facture> getFactures() {
        return factures;
    }
    
    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }
    
    // Méthodes du diagramme UML
    public void consulterDossierPatient() {
        System.out.println("Le dentiste " + nom + " consulte un dossier patient");
    }
    
    public void ajouterNotes(String notes, DossierPatient dossier) {
        System.out.println("Le dentiste " + nom + " ajoute des notes au dossier");
        dossier.ajouterNotes(notes);
    }
    
    public void gererTraitement() {
        System.out.println("Le dentiste " + nom + " gère un traitement");
    }
    
    // Méthodes utilitaires
    public void ajouterDossierPatient(DossierPatient dossier) {
        dossierPatients.add(dossier);
    }
    
    public void ajouterFacture(Facture facture) {
        factures.add(facture);
    }
    
    @Override
    public String toString() {
        return "Dentiste [ID: " + id + ", Nom: " + nom + ", Spécialité: " + specialite + "]"; 
    }
}