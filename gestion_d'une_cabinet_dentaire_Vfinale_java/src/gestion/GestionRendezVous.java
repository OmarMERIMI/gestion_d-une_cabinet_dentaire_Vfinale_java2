package gestion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import modele.Dentiste;
import modele.Patient;
import modele.RendezVous;
import modele.SalleDeTraitement;

public class GestionRendezVous {
    private List<RendezVous> rendezVous;
    private List<Patient> patients;
    private List<Dentiste> dentistes;
    private List<SalleDeTraitement> salles;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;
    
    public GestionRendezVous() {
        this.rendezVous = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.dentistes = new ArrayList<>();
        this.salles = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        // Ajouter quelques données pour les tests
        try {
            Patient patient1 = new Patient(1, "pat1", "pass1", "Durand", "Marie", dateFormat.parse("15/05/1980"), "Allergie au latex");
            Patient patient2 = new Patient(2, "pat2", "pass2", "Petit", "Pierre", dateFormat.parse("22/11/1975"), "Diabétique");
            
            Dentiste dentiste1 = new Dentiste(1, "dent1", "pass1", "Leroy", "Orthodontie");
            Dentiste dentiste2 = new Dentiste(2, "dent2", "pass2", "Blanc", "Chirurgie dentaire");
            
            SalleDeTraitement salle1 = new SalleDeTraitement(1, "Salle A", "Équipement orthodontique", "Disponible");
            SalleDeTraitement salle2 = new SalleDeTraitement(2, "Salle B", "Équipement chirurgical", "Disponible");
            
            patients.add(patient1);
            patients.add(patient2);
            dentistes.add(dentiste1);
            dentistes.add(dentiste2);
            salles.add(salle1);
            salles.add(salle2);
            
            RendezVous rdv1 = new RendezVous(dateFormat.parse("15/03/2023"), "10:00", "Confirmé");
            rdv1.setPatient(patient1);
            rdv1.setDentiste(dentiste1);
            rdv1.setSalle(salle1);
            
            RendezVous rdv2 = new RendezVous(dateFormat.parse("20/03/2023"), "14:30", "En attente");
            rdv2.setPatient(patient2);
            rdv2.setDentiste(dentiste2);
            rdv2.setSalle(salle2);
            
            rendezVous.add(rdv1);
            rendezVous.add(rdv2);
            
        } catch (ParseException e) {
            System.out.println("Erreur lors de la création des données de test: " + e.getMessage());
        }
    }
    
    public void afficherMenu() {
        boolean retour = false;
        
        while (!retour) {
            System.out.println("\n======================================");
            System.out.println("GESTION DES RENDEZ-VOUS");
            System.out.println("======================================");
            System.out.println("1. Afficher tous les rendez-vous");
            System.out.println("2. Rechercher un rendez-vous");
            System.out.println("3. Ajouter un rendez-vous");
            System.out.println("4. Modifier un rendez-vous");
            System.out.println("5. Confirmer un rendez-vous");
            System.out.println("6. Annuler un rendez-vous");
            System.out.println("7. Retour au menu principal");
            System.out.println("======================================");
            
            System.out.print("Votre choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne
            
            switch (choix) {
                case 1:
                    afficherTousRendezVous();
                    break;
                case 2:
                    rechercherRendezVous();
                    break;
                case 3:
                    ajouterRendezVous();
                    break;
                case 4:
                    modifierRendezVous();
                    break;
                case 5:
                    confirmerRendezVous();
                    break;
                case 6:
                    annulerRendezVous();
                    break;
                case 7:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }
    
    private void afficherTousRendezVous() {
        System.out.println("\n=== LISTE DES RENDEZ-VOUS ===");
        
        if (rendezVous.isEmpty()) {
            System.out.println("Aucun rendez-vous enregistré.");
            return;
        }
        
        for (RendezVous rdv : rendezVous) {
            System.out.println(rdv);
            if (rdv.getPatient() != null) {
                System.out.println("Patient: " + rdv.getPatient().getNom() + " " + rdv.getPatient().getPrenom());
            }
            if (rdv.getDentiste() != null) {
                System.out.println("Dentiste: " + rdv.getDentiste().getNom());
            }
            if (rdv.getSalle() != null) {
                System.out.println("Salle: " + rdv.getSalle().getNom());
            }
            System.out.println("-----------------------------------");
        }
    }
    
    private void rechercherRendezVous() {
        System.out.println("\n=== RECHERCHER UN RENDEZ-VOUS ===");
        System.out.println("1. Rechercher par date");
        System.out.println("2. Rechercher par patient");
        System.out.println("3. Rechercher par dentiste");
        System.out.print("Votre choix: ");
        int choix = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        switch (choix) {
            case 1:
                rechercherParDate();
                break;
            case 2:
                rechercherParPatient();
                break;
            case 3:
                rechercherParDentiste();
                break;
            default:
                System.out.println("Choix invalide.");
        }
    }
    
    private void rechercherParDate() {
        System.out.print("Entrez la date (jj/mm/aaaa): ");
        String dateStr = scanner.nextLine();
        
        try {
            Date date = dateFormat.parse(dateStr);
            boolean trouve = false;
            
            for (RendezVous rdv : rendezVous) {
                if (dateFormat.format(rdv.getDate()).equals(dateFormat.format(date))) {
                    System.out.println(rdv);
                    if (rdv.getPatient() != null) {
                        System.out.println("Patient: " + rdv.getPatient().getNom() + " " + rdv.getPatient().getPrenom());
                    }
                    if (rdv.getDentiste() != null) {
                        System.out.println("Dentiste: " + rdv.getDentiste().getNom());
                    }
                    System.out.println("-----------------------------------");
                    trouve = true;
                }
            }
            
            if (!trouve) {
                System.out.println("Aucun rendez-vous trouvé pour cette date.");
            }
            
        } catch (ParseException e) {
            System.out.println("Format de date invalide.");
        }
    }
    
    private void rechercherParPatient() {
        System.out.print("Entrez l'ID du patient: ");
        int idPatient = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        boolean trouve = false;
        
        for (RendezVous rdv : rendezVous) {
            if (rdv.getPatient() != null && rdv.getPatient().getId() == idPatient) {
                System.out.println(rdv);
                System.out.println("Dentiste: " + (rdv.getDentiste() != null ? rdv.getDentiste().getNom() : "Non assigné"));
                System.out.println("-----------------------------------");
                trouve = true;
            }
        }
        
        if (!trouve) {
            System.out.println("Aucun rendez-vous trouvé pour ce patient.");
        }
    }
    
    private void rechercherParDentiste() {
        System.out.print("Entrez l'ID du dentiste: ");
        int idDentiste = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        boolean trouve = false;
        
        for (RendezVous rdv : rendezVous) {
            if (rdv.getDentiste() != null && rdv.getDentiste().getId() == idDentiste) {
                System.out.println(rdv);
                System.out.println("Patient: " + (rdv.getPatient() != null ? rdv.getPatient().getNom() + " " + rdv.getPatient().getPrenom() : "Non assigné"));
                System.out.println("-----------------------------------");
                trouve = true;
            }
        }
        
        if (!trouve) {
            System.out.println("Aucun rendez-vous trouvé pour ce dentiste.");
        }
    }
    
    private void ajouterRendezVous() {
        System.out.println("\n=== AJOUTER UN RENDEZ-VOUS ===");
        
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
        
        System.out.print("Entrez l'ID de la salle: ");
        int idSalle = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        SalleDeTraitement salle = trouverSalleParId(idSalle);
        if (salle == null) {
            System.out.println("Aucune salle trouvée avec l'ID: " + idSalle);
            return;
        }
        
        System.out.print("Entrez la date (jj/mm/aaaa): ");
        String dateStr = scanner.nextLine();
        
        System.out.print("Entrez l'heure (hh:mm): ");
        String heure = scanner.nextLine();
        
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Format de date invalide. Utilisation de la date actuelle.");
            date = new Date();
        }
        
        RendezVous nouveauRdv = new RendezVous(date, heure, "En attente");
        nouveauRdv.setPatient(patient);
        nouveauRdv.setDentiste(dentiste);
        nouveauRdv.setSalle(salle);
        
        rendezVous.add(nouveauRdv);
        patient.ajouterRendezVous(nouveauRdv);
        System.out.println("Rendez-vous ajouté avec succès.");
    }
    
    private void modifierRendezVous() {
        System.out.println("\n=== MODIFIER UN RENDEZ-VOUS ===");
        System.out.print("Entrez l'ID du patient: ");
        int idPatient = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        System.out.print("Entrez la date du rendez-vous (jj/mm/aaaa): ");
        String dateStr = scanner.nextLine();
        
        try {
            Date date = dateFormat.parse(dateStr);
            RendezVous rdvAModifier = null;
            
            for (RendezVous rdv : rendezVous) {
                if (rdv.getPatient() != null && rdv.getPatient().getId() == idPatient && 
                    dateFormat.format(rdv.getDate()).equals(dateFormat.format(date))) {
                    rdvAModifier = rdv;
                    break;
                }
            }
            
            if (rdvAModifier != null) {
                System.out.println("\nRendez-vous trouvé: " + rdvAModifier);
                
                System.out.print("Nouvelle date (jj/mm/aaaa) (actuelle: " + dateFormat.format(rdvAModifier.getDate()) + "): ");
                String nouvelleDateStr = scanner.nextLine();
                if (!nouvelleDateStr.isEmpty()) {
                    try {
                        Date nouvelleDate = dateFormat.parse(nouvelleDateStr);
                        rdvAModifier.setDate(nouvelleDate);
                    } catch (ParseException e) {
                        System.out.println("Format de date invalide. Date non modifiée.");
                    }
                }
                
                System.out.print("Nouvelle heure (actuelle: " + rdvAModifier.getHeure() + "): ");
                String nouvelleHeure = scanner.nextLine();
                if (!nouvelleHeure.isEmpty()) {
                    rdvAModifier.setHeure(nouvelleHeure);
                }
                
                System.out.println("Rendez-vous modifié avec succès.");
            } else {
                System.out.println("Aucun rendez-vous trouvé pour ce patient à cette date.");
            }
            
        } catch (ParseException e) {
            System.out.println("Format de date invalide.");
        }
    }
    
    private void confirmerRendezVous() {
        System.out.println("\n=== CONFIRMER UN RENDEZ-VOUS ===");
        System.out.print("Entrez l'ID du patient: ");
        int idPatient = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        System.out.print("Entrez la date du rendez-vous (jj/mm/aaaa): ");
        String dateStr = scanner.nextLine();
        
        try {
            Date date = dateFormat.parse(dateStr);
            RendezVous rdvAConfirmer = null;
            
            for (RendezVous rdv : rendezVous) {
                if (rdv.getPatient() != null && rdv.getPatient().getId() == idPatient && 
                    dateFormat.format(rdv.getDate()).equals(dateFormat.format(date))) {
                    rdvAConfirmer = rdv;
                    break;
                }
            }
            
            if (rdvAConfirmer != null) {
                rdvAConfirmer.confirmer();
                System.out.println("Rendez-vous confirmé avec succès.");
            } else {
                System.out.println("Aucun rendez-vous trouvé pour ce patient à cette date.");
            }
            
        } catch (ParseException e) {
            System.out.println("Format de date invalide.");
        }
    }
    
    private void annulerRendezVous() {
        System.out.println("\n=== ANNULER UN RENDEZ-VOUS ===");
        System.out.print("Entrez l'ID du patient: ");
        int idPatient = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        System.out.print("Entrez la date du rendez-vous (jj/mm/aaaa): ");
        String dateStr = scanner.nextLine();
        
        try {
            Date date = dateFormat.parse(dateStr);
            RendezVous rdvAAnnuler = null;
            int indexRdv = -1;
            
            for (int i = 0; i < rendezVous.size(); i++) {
                RendezVous rdv = rendezVous.get(i);
                if (rdv.getPatient() != null && rdv.getPatient().getId() == idPatient && 
                    dateFormat.format(rdv.getDate()).equals(dateFormat.format(date))) {
                    rdvAAnnuler = rdv;
                    indexRdv = i;
                    break;
                }
            }
            
            if (rdvAAnnuler != null) {
                System.out.println("\nRendez-vous trouvé: " + rdvAAnnuler);
                System.out.print("Voulez-vous vraiment annuler ce rendez-vous? (O/N): ");
                String confirmation = scanner.nextLine();
                
                if (confirmation.equalsIgnoreCase("O")) {
                    rdvAAnnuler.setStatut("Annulé");
                    System.out.println("Rendez-vous annulé avec succès.");
                } else {
                    System.out.println("Annulation abandonnée.");
                }
            } else {
                System.out.println("Aucun rendez-vous trouvé pour ce patient à cette date.");
            }
            
        } catch (ParseException e) {
            System.out.println("Format de date invalide.");
        }
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
    
    private SalleDeTraitement trouverSalleParId(int id) {
        for (SalleDeTraitement salle : salles) {
            if (salle.getId() == id) {
                return salle;
            }
        }
        return null;
    }
}