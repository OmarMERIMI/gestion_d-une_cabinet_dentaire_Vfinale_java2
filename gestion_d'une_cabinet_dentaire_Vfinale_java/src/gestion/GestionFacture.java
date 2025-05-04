package gestion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import modele.Dentiste;
import modele.Facture;
import modele.Patient;

public class GestionFacture {
    private List<Facture> factures;
    private List<Patient> patients;
    private List<Dentiste> dentistes;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;
    
    public GestionFacture() {
        this.factures = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.dentistes = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        // Ajouter quelques factures pour les tests
        try {
            Patient patient1 = new Patient(1, "pat1", "pass1", "Durand", "Marie", dateFormat.parse("15/05/1980"), "Allergie au latex");
            Patient patient2 = new Patient(2, "pat2", "pass2", "Petit", "Pierre", dateFormat.parse("22/11/1975"), "Diabétique");
            
            Dentiste dentiste1 = new Dentiste(1, "dent1", "pass1", "Leroy", "Orthodontie");
            Dentiste dentiste2 = new Dentiste(2, "dent2", "pass2", "Blanc", "Chirurgie dentaire");
            
            patients.add(patient1);
            patients.add(patient2);
            dentistes.add(dentiste1);
            dentistes.add(dentiste2);
            
            Facture facture1 = new Facture(1, dateFormat.parse("10/01/2023"), 150.0f, "En attente");
            facture1.setPatient(patient1);
            facture1.setDentiste(dentiste1);
            
            Facture facture2 = new Facture(2, dateFormat.parse("05/02/2023"), 200.0f, "Payée");
            facture2.setPatient(patient2);
            facture2.setDentiste(dentiste2);
            
            factures.add(facture1);
            factures.add(facture2);
            
        } catch (ParseException e) {
            System.out.println("Erreur lors de la création des données de test: " + e.getMessage());
        }
    }
    
    public void afficherMenu() {
        boolean retour = false;
        
        while (!retour) {
            System.out.println("\n======================================");
            System.out.println("GESTION DES FACTURES");
            System.out.println("======================================");
            System.out.println("1. Afficher toutes les factures");
            System.out.println("2. Rechercher une facture");
            System.out.println("3. Ajouter une facture");
            System.out.println("4. Modifier une facture");
            System.out.println("5. Payer une facture");
            System.out.println("6. Retour au menu principal");
            System.out.println("======================================");
            
            System.out.print("Votre choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne
            
            switch (choix) {
                case 1:
                    afficherToutesFactures();
                    break;
                case 2:
                    rechercherFacture();
                    break;
                case 3:
                    ajouterFacture();
                    break;
                case 4:
                    modifierFacture();
                    break;
                case 5:
                    payerFacture();
                    break;
                case 6:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }
    
    private void afficherToutesFactures() {
        System.out.println("\n=== LISTE DES FACTURES ===");
        
        if (factures.isEmpty()) {
            System.out.println("Aucune facture enregistrée.");
            return;
        }
        
        for (Facture facture : factures) {
            System.out.println(facture);
            if (facture.getPatient() != null) {
                System.out.println("Patient: " + facture.getPatient().getNom() + " " + facture.getPatient().getPrenom());
            }
            if (facture.getDentiste() != null) {
                System.out.println("Dentiste: " + facture.getDentiste().getNom());
            }
            System.out.println("-----------------------------------");
        }
    }
    
    private void rechercherFacture() {
        System.out.println("\n=== RECHERCHER UNE FACTURE ===");
        System.out.print("Entrez l'ID de la facture: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        Facture facture = trouverFactureParId(id);
        
        if (facture != null) {
            System.out.println("\nFacture trouvée:");
            System.out.println(facture);
            if (facture.getPatient() != null) {
                System.out.println("Patient: " + facture.getPatient().getNom() + " " + facture.getPatient().getPrenom());
            }
            if (facture.getDentiste() != null) {
                System.out.println("Dentiste: " + facture.getDentiste().getNom());
            }
        } else {
            System.out.println("Aucune facture trouvée avec l'ID: " + id);
        }
    }
    
    private void ajouterFacture() {
        System.out.println("\n=== AJOUTER UNE FACTURE ===");
        
        System.out.print("Entrez l'ID de la facture: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        // Vérifier si l'ID existe déjà
        if (trouverFactureParId(id) != null) {
            System.out.println("Une facture avec cet ID existe déjà.");
            return;
        }
        
        System.out.print("Entrez l'ID du patient: ");
        int idPatient = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        Patient patient = trouverPatientParId(idPatient);
        if (patient == null) {
            System.out.println("Aucun patient trouvé avec l'ID: " + idPatient);
            return;
        }
        
        System.out.print("Entrez l'ID du dentiste: ");
        int idDentiste = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        Dentiste dentiste = trouverDentisteParId(idDentiste);
        if (dentiste == null) {
            System.out.println("Aucun dentiste trouvé avec l'ID: " + idDentiste);
            return;
        }
        
        System.out.print("Entrez la date d'émission (jj/mm/aaaa): ");
        String dateStr = scanner.nextLine();
        
        System.out.print("Entrez le montant: ");
        float montant = scanner.nextFloat();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        Date dateEmission = null;
        try {
            dateEmission = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Format de date invalide. Utilisation de la date actuelle.");
            dateEmission = new Date();
        }
        
        Facture nouvelleFacture = new Facture(id, dateEmission, montant, "En attente");
        nouvelleFacture.setPatient(patient);
        nouvelleFacture.setDentiste(dentiste);
        
        factures.add(nouvelleFacture);
        System.out.println("Facture ajoutée avec succès.");
    }
    
    private void modifierFacture() {
        System.out.println("\n=== MODIFIER UNE FACTURE ===");
        System.out.print("Entrez l'ID de la facture: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        Facture facture = trouverFactureParId(id);
        
        if (facture != null) {
            System.out.println("\nFacture trouvée: " + facture);
            
            System.out.print("Nouveau montant (actuel: " + facture.getMontant() + "): ");
            String montantStr = scanner.nextLine();
            if (!montantStr.isEmpty()) {
                try {
                    float montant = Float.parseFloat(montantStr);
                    facture.setMontant(montant);
                } catch (NumberFormatException e) {
                    System.out.println("Format de montant invalide. Montant non modifié.");
                }
            }
            
            System.out.print("Nouveau statut (actuel: " + facture.getStatut() + "): ");
            String statut = scanner.nextLine();
            if (!statut.isEmpty()) {
                facture.setStatut(statut);
            }
            
            System.out.println("Facture modifiée avec succès.");
        } else {
            System.out.println("Aucune facture trouvée avec l'ID: " + id);
        }
    }
    
    private void payerFacture() {
        System.out.println("\n=== PAYER UNE FACTURE ===");
        System.out.print("Entrez l'ID de la facture: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        Facture facture = trouverFactureParId(id);
        
        if (facture != null) {
            System.out.println("\nFacture trouvée: " + facture);
            
            if (facture.getStatut().equals("Payée")) {
                System.out.println("Cette facture est déjà payée.");
                return;
            }
            
            facture.payer();
            System.out.println("Facture payée avec succès.");
        } else {
            System.out.println("Aucune facture trouvée avec l'ID: " + id);
        }
    }
    
    private Facture trouverFactureParId(int id) {
        for (Facture facture : factures) {
            if (facture.getId() == id) {
                return facture;
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
    
    private Dentiste trouverDentisteParId(int id) {
        for (Dentiste dentiste : dentistes) {
            if (dentiste.getId() == id) {
                return dentiste;
            }
        }
        return null;
    }
}