package gestion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import modele.DossierPatient;
import modele.Patient;

public class GestionDossierPatient {
    private List<DossierPatient> dossiers;
    private List<Patient> patients;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;
    
    public GestionDossierPatient() {
        this.dossiers = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        // Ajouter quelques patients et dossiers pour les tests
        try {
            Patient patient1 = new Patient(1, "pat1", "pass1", "Durand", "Marie", dateFormat.parse("15/05/1980"), "Allergie au latex");
            Patient patient2 = new Patient(2, "pat2", "pass2", "Petit", "Pierre", dateFormat.parse("22/11/1975"), "Diabétique");
            
            patients.add(patient1);
            patients.add(patient2);
            
            DossierPatient dossier1 = new DossierPatient(1, "Traitement orthodontique en cours");
            dossier1.setPatient(patient1);
            dossier1.ajouterNotes("Première consultation le 10/01/2023");
            
            DossierPatient dossier2 = new DossierPatient(2, "Extraction de dent de sagesse prévue");
            dossier2.setPatient(patient2);
            dossier2.ajouterNotes("Radiographie effectuée le 05/02/2023");
            
            dossiers.add(dossier1);
            dossiers.add(dossier2);
            
        } catch (ParseException e) {
            System.out.println("Erreur lors de la création des données de test: " + e.getMessage());
        }
    }
    
    public void afficherMenu() {
        boolean retour = false;
        
        while (!retour) {
            System.out.println("\n======================================");
            System.out.println("GESTION DES DOSSIERS PATIENTS");
            System.out.println("======================================");
            System.out.println("1. Afficher tous les dossiers patients");
            System.out.println("2. Rechercher un dossier patient");
            System.out.println("3. Ajouter un dossier patient");
            System.out.println("4. Modifier un dossier patient");
            System.out.println("5. Ajouter des notes à un dossier");
            System.out.println("6. Consulter un dossier");
            System.out.println("7. Retour au menu principal");
            System.out.println("======================================");
            
            System.out.print("Votre choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne
            
            switch (choix) {
                case 1:
                    afficherTousDossiers();
                    break;
                case 2:
                    rechercherDossier();
                    break;
                case 3:
                    ajouterDossier();
                    break;
                case 4:
                    modifierDossier();
                    break;
                case 5:
                    ajouterNotes();
                    break;
                case 6:
                    consulterDossier();
                    break;
                case 7:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }
    
    private void afficherTousDossiers() {
        System.out.println("\n=== LISTE DES DOSSIERS PATIENTS ===");
        
        if (dossiers.isEmpty()) {
            System.out.println("Aucun dossier patient enregistré.");
            return;
        }
        
        for (DossierPatient dossier : dossiers) {
            System.out.println(dossier);
            if (dossier.getPatient() != null) {
                System.out.println("Patient: " + dossier.getPatient().getNom() + " " + dossier.getPatient().getPrenom());
            }
            System.out.println("-----------------------------------");
        }
    }
    
    private void rechercherDossier() {
        System.out.println("\n=== RECHERCHER UN DOSSIER PATIENT ===");
        System.out.print("Entrez l'ID du patient: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        DossierPatient dossier = trouverDossierParIdPatient(id);
        
        if (dossier != null) {
            System.out.println("\nDossier trouvé:");
            System.out.println(dossier);
            if (dossier.getPatient() != null) {
                System.out.println("Patient: " + dossier.getPatient().getNom() + " " + dossier.getPatient().getPrenom());
            }
        } else {
            System.out.println("Aucun dossier trouvé pour le patient avec l'ID: " + id);
        }
    }
    
    private void ajouterDossier() {
        System.out.println("\n=== AJOUTER UN DOSSIER PATIENT ===");
        
        System.out.print("Entrez l'ID du patient: ");
        int idPatient = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        // Vérifier si un dossier existe déjà pour ce patient
        if (trouverDossierParIdPatient(idPatient) != null) {
            System.out.println("Un dossier pour ce patient existe déjà.");
            return;
        }
        
        // Vérifier si le patient existe
        Patient patient = trouverPatientParId(idPatient);
        if (patient == null) {
            System.out.println("Aucun patient trouvé avec l'ID: " + idPatient);
            System.out.println("Voulez-vous créer un nouveau patient? (O/N): ");
            String reponse = scanner.nextLine();
            
            if (reponse.equalsIgnoreCase("O")) {
                patient = creerNouveauPatient(idPatient);
            } else {
                return;
            }
        }
        
        System.out.print("Entrez le dossier médical: ");
        String dossierMedical = scanner.nextLine();
        
        DossierPatient nouveauDossier = new DossierPatient(idPatient, dossierMedical);
        nouveauDossier.setPatient(patient);
        
        System.out.print("Ajouter des notes initiales? (O/N): ");
        String reponse = scanner.nextLine();
        
        if (reponse.equalsIgnoreCase("O")) {
            System.out.print("Entrez les notes: ");
            String notes = scanner.nextLine();
            nouveauDossier.ajouterNotes(notes);
        }
        
        dossiers.add(nouveauDossier);
        System.out.println("Dossier patient ajouté avec succès.");
    }
    
    private void modifierDossier() {
        System.out.println("\n=== MODIFIER UN DOSSIER PATIENT ===");
        System.out.print("Entrez l'ID du patient: ");
        int idPatient = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        DossierPatient dossier = trouverDossierParIdPatient(idPatient);
        
        if (dossier != null) {
            System.out.println("\nDossier trouvé: " + dossier);
            
            System.out.print("Nouveau dossier médical (actuel: " + dossier.getDossierMedical() + "): ");
            String dossierMedical = scanner.nextLine();
            if (!dossierMedical.isEmpty()) {
                dossier.setDossierMedical(dossierMedical);
            }
            
            System.out.println("Dossier patient modifié avec succès.");
        } else {
            System.out.println("Aucun dossier trouvé pour le patient avec l'ID: " + idPatient);
        }
    }
    
    private void ajouterNotes() {
        System.out.println("\n=== AJOUTER DES NOTES À UN DOSSIER ===");
        System.out.print("Entrez l'ID du patient: ");
        int idPatient = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        DossierPatient dossier = trouverDossierParIdPatient(idPatient);
        
        if (dossier != null) {
            System.out.println("\nDossier trouvé: " + dossier);
            
            System.out.print("Entrez les nouvelles notes: ");
            String notes = scanner.nextLine();
            dossier.ajouterNotes(notes);
            
            System.out.println("Notes ajoutées avec succès.");
        } else {
            System.out.println("Aucun dossier trouvé pour le patient avec l'ID: " + idPatient);
        }
    }
    
    private void consulterDossier() {
        System.out.println("\n=== CONSULTER UN DOSSIER ===");
        System.out.print("Entrez l'ID du patient: ");
        int idPatient = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        DossierPatient dossier = trouverDossierParIdPatient(idPatient);
        
        if (dossier != null) {
            dossier.consulterDossier();
            if (dossier.getPatient() != null) {
                System.out.println("Patient: " + dossier.getPatient().getNom() + " " + dossier.getPatient().getPrenom());
                System.out.println("Historique médical: " + dossier.getPatient().getHistoriqueMedical());
            }
        } else {
            System.out.println("Aucun dossier trouvé pour le patient avec l'ID: " + idPatient);
        }
    }
    
    private DossierPatient trouverDossierParIdPatient(int idPatient) {
        for (DossierPatient dossier : dossiers) {
            if (dossier.getIdPatient() == idPatient) {
                return dossier;
            }
        }
        return null;
    }
    
    private Patient trouverPatientParId(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }
    
    private Patient creerNouveauPatient(int id) {
        System.out.println("\n=== CRÉER UN NOUVEAU PATIENT ===");
        
        System.out.print("Entrez le nom d'utilisateur: ");
        String username = scanner.nextLine();
        
        System.out.print("Entrez le mot de passe: ");
        String password = scanner.nextLine();
        
        System.out.print("Entrez le nom: ");
        String nom = scanner.nextLine();
        
        System.out.print("Entrez le prénom: ");
        String prenom = scanner.nextLine();
        
        System.out.print("Entrez la date de naissance (jj/mm/aaaa): ");
        String dateStr = scanner.nextLine();
        
        System.out.print("Entrez l'historique médical: ");
        String historiqueMedical = scanner.nextLine();
        
        Date dateNaissance = null;
        try {
            dateNaissance = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Format de date invalide. Utilisation de la date actuelle.");
            dateNaissance = new Date();
        }
        
        Patient nouveauPatient = new Patient(id, username, password, nom, prenom, dateNaissance, historiqueMedical);
        patients.add(nouveauPatient);
        
        System.out.println("Patient créé avec succès.");
        return nouveauPatient;
    }
}