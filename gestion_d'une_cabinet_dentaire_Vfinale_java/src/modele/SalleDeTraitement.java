package modele;

import java.util.ArrayList;
import java.util.List;

public class SalleDeTraitement {
    private int id;
    private String nom;
    private String equipements;
    private String statut; // "Disponible", "Occupée", "En maintenance"
    private Assistant assistant;
    private List<RendezVous> rendezVous;
    
    public SalleDeTraitement(int id, String nom, String equipements, String statut) {
        this.id = id;
        this.nom = nom;
        this.equipements = equipements;
        this.statut = statut;
        this.rendezVous = new ArrayList<>();
    }
    
    public SalleDeTraitement() {
        this.rendezVous = new ArrayList<>();
    }
    
    // Getters et Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getEquipements() {
        return equipements;
    }
    
    public void setEquipements(String equipements) {
        this.equipements = equipements;
    }
    
    public String getStatut() {
        return statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    public Assistant getAssistant() {
        return assistant;
    }
    
    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }
    
    public List<RendezVous> getRendezVous() {
        return rendezVous;
    }
    
    public void setRendezVous(List<RendezVous> rendezVous) {
        this.rendezVous = rendezVous;
    }
    
    // Méthodes du diagramme UML
    public void preparer() {
        System.out.println("Préparation de la salle " + nom);
        this.statut = "Disponible";
    }
    
    public void verifierEquipement() {
        System.out.println("Vérification des équipements de la salle " + nom);
    }
    
    // Méthodes utilitaires
    public void ajouterRendezVous(RendezVous rdv) {
        rendezVous.add(rdv);
    }
    
    @Override
    public String toString() {
        return "SalleDeTraitement [ID: " + id + ", Nom: " + nom + ", Statut: " + statut + "]";
    }
}