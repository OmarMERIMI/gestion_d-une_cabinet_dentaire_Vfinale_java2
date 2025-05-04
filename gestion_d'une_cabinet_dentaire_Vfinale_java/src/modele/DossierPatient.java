package modele;

import java.util.ArrayList;
import java.util.List;

public class DossierPatient {
    private int idPatient;
    private String dossierMedical;
    private String notes;
    private Patient patient;
    private List<RendezVous> rendezVous;
    
    public DossierPatient(int idPatient, String dossierMedical) {
        this.idPatient = idPatient;
        this.dossierMedical = dossierMedical;
        this.notes = "";
        this.rendezVous = new ArrayList<>();
    }
    
    public DossierPatient() {
        this.notes = "";
        this.rendezVous = new ArrayList<>();
    }
    
    // Getters et Setters
    public int getIdPatient() {
        return idPatient;
    }
    
    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }
    
    public String getDossierMedical() {
        return dossierMedical;
    }
    
    public void setDossierMedical(String dossierMedical) {
        this.dossierMedical = dossierMedical;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public Patient getPatient() {
        return patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
        if (patient != null) {
            patient.setDossierPatient(this);
        }
    }
    
    public List<RendezVous> getRendezVous() {
        return rendezVous;
    }
    
    public void setRendezVous(List<RendezVous> rendezVous) {
        this.rendezVous = rendezVous;
    }
    
    // Méthodes du diagramme UML
    public void ajouterNotes(String nouvelleNote) {
        if (this.notes.isEmpty()) {
            this.notes = nouvelleNote;
        } else {
            this.notes += "\n" + nouvelleNote;
        }
        System.out.println("Note ajoutée au dossier du patient ID: " + idPatient);
    }
    
    public void consulterDossier() {
        System.out.println("Consultation du dossier du patient ID: " + idPatient);
        System.out.println("Dossier médical: " + dossierMedical);
        System.out.println("Notes: " + notes);
    }
    
    // Méthodes utilitaires
    public void ajouterRendezVous(RendezVous rdv) {
        rendezVous.add(rdv);
    }
    
    @Override
    public String toString() {
        return "DossierPatient [ID Patient: " + idPatient + ", Dossier médical: " + dossierMedical + "]"; 
    }
}