package modele;

import java.util.ArrayList;
import java.util.List;

public class Responsable extends Utilisateur {
    private String nom;
    private String prenom;
    private List<Utilisateur> utilisateurs;
    private List<Rapport> rapports;
    
    public Responsable(int id, String username, String password, String nom, String prenom) {
        super(id, username, password);
        this.nom = nom;
        this.prenom = prenom;
        this.utilisateurs = new ArrayList<>();
        this.rapports = new ArrayList<>();
    }
    
    public Responsable() {
        super();
        this.utilisateurs = new ArrayList<>();
        this.rapports = new ArrayList<>();
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
    
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }
    
    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
    
    public List<Rapport> getRapports() {
        return rapports;
    }
    
    public void setRapports(List<Rapport> rapports) {
        this.rapports = rapports;
    }
    
    // Méthodes du diagramme UML
    public void gererUtilisateurs() {
        System.out.println("Le responsable " + prenom + " " + nom + " gère les utilisateurs");
    }
    
    public void genererRapports() {
        System.out.println("Le responsable " + prenom + " " + nom + " génère des rapports");
    }
    
    // Méthodes utilitaires
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
    }
    
    public void ajouterRapport(Rapport rapport) {
        rapports.add(rapport);
    }
    
    @Override
    public String toString() {
        return "Responsable [ID: " + id + ", Nom: " + nom + ", Prénom: " + prenom + "]"; 
    }
}