package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient extends Utilisateur {
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String historiqueMedical;
    private List<RendezVous> rendezVous;
    private DossierPatient dossierPatient;
    
    public Patient(int id, String username, String password, String nom, String prenom, 
                  Date dateNaissance, String historiqueMedical) {
        super(id, username, password);
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.historiqueMedical = historiqueMedical;
        this.rendezVous = new ArrayList<>();
    }
    
    public Patient() {
        super();
        this.rendezVous = new ArrayList<>();
    }
    
    // Getters et Setters
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
    
    public Date getDateNaissance() {
        return dateNaissance;
    }
    
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    public String getHistoriqueMedical() {
        return historiqueMedical;
    }
    
    public void setHistoriqueMedical(String historiqueMedical) {
        this.historiqueMedical = historiqueMedical;
    }
    
    public List<RendezVous> getRendezVous() {
        return rendezVous;
    }
    
    public void setRendezVous(List<RendezVous> rendezVous) {
        this.rendezVous = rendezVous;
    }
    
    public DossierPatient getDossierPatient() {
        return dossierPatient;
    }
    
    public void setDossierPatient(DossierPatient dossierPatient) {
        this.dossierPatient = dossierPatient;
    }
    
    // Méthodes du diagramme UML
    public void demanderRendezVous() {
        System.out.println("Demande de rendez-vous pour " + prenom + " " + nom);
    }
    
    public void confirmerRendezVous() {
        System.out.println("Confirmation de rendez-vous pour " + prenom + " " + nom);
    }
    
    public void payerFacture() {
        System.out.println("Paiement de facture pour " + prenom + " " + nom);
    }
    
    // Méthodes utilitaires
    public void ajouterRendezVous(RendezVous rdv) {
        rendezVous.add(rdv);
    }
    
    @Override
    public String toString() {
        return "Patient [ID: " + id + ", Nom: " + nom + ", Prénom: " + prenom + ", Date de naissance: " + dateNaissance + "]"; 
    }
}