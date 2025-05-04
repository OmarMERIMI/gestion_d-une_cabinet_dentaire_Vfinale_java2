package main;

import java.util.Scanner;

import gestion.GestionAssistant;
import gestion.GestionDentiste;
import gestion.GestionDossierPatient;
import gestion.GestionFacture;
import gestion.GestionRapport;
import gestion.GestionRendezVous;
import gestion.GestionResponsable;
import gestion.GestionSalleDeTraitement;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    
    // Instances des gestionnaires
    private static GestionResponsable gestionResponsable = new GestionResponsable();
    private static GestionDentiste gestionDentiste = new GestionDentiste();
    private static GestionFacture gestionFacture = new GestionFacture();
    private static GestionDossierPatient gestionDossierPatient = new GestionDossierPatient();
    private static GestionRapport gestionRapport = new GestionRapport();
    private static GestionRendezVous gestionRendezVous = new GestionRendezVous();
    private static GestionSalleDeTraitement gestionSalleDeTraitement = new GestionSalleDeTraitement();
    private static GestionAssistant gestionAssistant = new GestionAssistant();
    
    public static void main(String[] args) {
        boolean continuer = true;
        
        while (continuer) {
            afficherMenu();
            int choix = saisirChoix();
            
            switch (choix) {
                case 1:
                    gestionResponsable.afficherMenu();
                    break;
                case 2:
                    gestionDentiste.afficherMenu();
                    break;
                case 3:
                    gestionFacture.afficherMenu();
                    break;
                case 4:
                    gestionDossierPatient.afficherMenu();
                    break;
                case 5:
                    gestionRapport.afficherMenu();
                    break;
                case 6:
                    gestionRendezVous.afficherMenu();
                    break;
                case 7:
                    gestionSalleDeTraitement.afficherMenu();
                    break;
                case 8:
                    gestionAssistant.afficherMenu();
                    break;
                case 9:
                    System.out.println("\nMerci d'avoir utilisé notre application. Au revoir!");
                    continuer = false;
                    break;
                default:
                    System.out.println("\nChoix invalide. Veuillez réessayer.");
            }
        }
        
        scanner.close();
    }
    
    private static void afficherMenu() {
        System.out.println("\n======================================");
        System.out.println("GESTION DE CABINET DENTAIRE");
        System.out.println("======================================");
        System.out.println("1. Gestion des responsables");
        System.out.println("2. Gestion des dentistes");
        System.out.println("3. Gestion des factures");
        System.out.println("4. Gestion des dossiers patients");
        System.out.println("5. Gestion des rapports");
        System.out.println("6. Gestion des rendez-vous");
        System.out.println("7. Gestion des salles de traitement");
        System.out.println("8. Gestion des assistants");
        System.out.println("9. Quitter");
        System.out.println("======================================");
    }
    
    private static int saisirChoix() {
        System.out.print("Votre choix: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Veuillez entrer un nombre.");
            System.out.print("Votre choix: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}