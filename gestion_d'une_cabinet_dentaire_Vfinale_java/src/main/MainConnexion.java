package main;

import gestion.GestionDentiste;
import gestion.GestionResponsable;
import java.util.Scanner;

public class MainConnexion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n======================================");
            System.out.println("CONNEXION UTILISATEUR");
            System.out.println("======================================");
            System.out.println("1. Se connecter en tant que Dentiste");
            System.out.println("2. Se connecter en tant que Responsable");
            System.out.println("3. Quitter");
            System.out.println("4. Gérer les dentistes");
            System.out.println("5. Gérer les assistants");
            System.out.println("6. Gérer les patients");
            System.out.println("7. Gérer les rapports");
            System.out.println("======================================");

            System.out.print("Votre choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    System.out.print("Entrez votre login: ");
                    String login = scanner.nextLine();
                    System.out.print("Entrez votre mot de passe: ");
                    String motDePasse = scanner.nextLine();

                    // Simuler une vérification de connexion
                    boolean connexionReussie = false;
                    boolean estResponsable = false;

                    if ("omarde".equals(login) && "omarde123".equals(motDePasse)) {
                        connexionReussie = true;
                    } else if ("responsable".equals(login) && "responsable123".equals(motDePasse)) {
                        connexionReussie = true;
                        estResponsable = true;
                    } else {
                        System.out.println("Login ou mot de passe incorrect.");
                        continue;
                    }

                    if (connexionReussie) {
                        if (estResponsable) {
                            GestionResponsable gestionResponsable = new GestionResponsable();
                            gestionResponsable.afficherMenu();
                            // Ajouter des options de gestion pour les responsables
                            System.out.println("1. Gérer les dentistes");
                            System.out.println("2. Gérer les assistants");
                            System.out.println("3. Gérer les patients");
                            System.out.println("4. Gérer les dossiers patients");
                            System.out.println("5. Gérer les factures");
                            System.out.println("6. Gérer les rendez-vous");
                            System.out.println("7. Gérer les salles de traitement");
                            System.out.println("8. Gérer les rapports");
                            System.out.println("9. Retour au menu principal");
                        } else {
                            GestionDentiste gestionDentiste = new GestionDentiste();
                            gestionDentiste.afficherMenu();
                            // Ajouter des options de gestion pour les dentistes
                            System.out.println("======================================");
                            System.out.println("GESTION DES DENTISTES");
                            System.out.println("======================================");
                            System.out.println("1. Afficher dossier patient");
                            System.out.println("2. Ajouter un dossier patient");
                            System.out.println("3. Rechercher un patient");
                            System.out.println("4. Gérer les traitements");
                            System.out.println("5. Retour au menu principal");
                            System.out.println("======================================");
                        }
                    }
                    break;
                case 2:
                    GestionResponsable gestionResponsable = new GestionResponsable();
                    gestionResponsable.afficherMenu();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }

        scanner.close();
    }
}