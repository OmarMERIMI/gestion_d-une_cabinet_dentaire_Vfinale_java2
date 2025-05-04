package modele;

import java.util.ArrayList;
import java.util.List;

public class Assistant extends Utilisateur {
    private String nom;
    private String prenom;
    private List<SalleDeTraitement> salles;
    
    public Assistant(int id, String username, String password, String nom, String prenom) {
        super(id, username, password);
        this.nom = nom;
        this.prenom = prenom;
        this.salles = new ArrayList<>();
    }
    
    public Assistant() {
        super();
        this.salles = new ArrayList<>();
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
    
    public List<SalleDeTraitement> getSalles() {
        return salles;
    }
    
    public void setSalles(List<SalleDeTraitement> salles) {
        this.salles = salles;
    }
    
    // Méthodes du diagramme UML
    public void preparerSalle() {
        System.out.println("L'assistant " + prenom + " " + nom + " prépare une salle");
    }
    
    public void accueillirPatient() {
        System.out.println("L'assistant " + prenom + " " + nom + " accueille un patient");
    }
    
    public void envoyerRappel() {
        System.out.println("L'assistant " + prenom + " " + nom + " envoie un rappel");
    }
    
    // Méthodes utilitaires
    public void ajouterSalle(SalleDeTraitement salle) {
        salles.add(salle);
    }
    
    @Override
    public String toString() {
        return "Assistant [ID: " + id + ", Nom: " + nom + ", Prénom: " + prenom + "]"; 
    }
}