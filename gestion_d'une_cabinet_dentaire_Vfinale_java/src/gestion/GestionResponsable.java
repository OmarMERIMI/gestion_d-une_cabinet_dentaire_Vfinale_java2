package gestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modele.Responsable;

public class GestionResponsable {
    private List<Responsable> responsables;
    private Scanner scanner;
    
    public GestionResponsable() {
        this.responsables = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        
        // Ajouter un responsable pour les tests
        Responsable responsable1 = new Responsable(1, "resp1", "pass1", "Martin", "Jean");
        responsables.add(responsable1);
    }
    
    public void afficherMenu() {
        boolean retour = false;
        
        while (!retour) {
            System.out.println("\n======================================");
            System.out.println("GESTION DES RESPONSABLES");
            System.out.println("======================================");
            System.out.println("1. Afficher tous les responsables");
            System.out.println("2. Rechercher un responsable");
            System.out.println("3. Ajouter un responsable");
            System.out.println("4. Modifier un responsable");
            System.out.println("5. Supprimer un responsable");
            System.out.println("6. Retour au menu principal");
            System.out.println("======================================");
            
            System.out.print("Votre choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne
            
            switch (choix) {
                case 1:
                    afficherTousResponsables();
                    break;
                case 2:
                    rechercherResponsable();
                    break;
                case 3:
                    ajouterResponsable();
                    break;
                case 4:
                    modifierResponsable();
                    break;
                case 5:
                    supprimerResponsable();
                    break;
                case 6:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }
    
    private void afficherTousResponsables() {
        System.out.println("\n=== LISTE DES RESPONSABLES ===");
        
        if (responsables.isEmpty()) {
            System.out.println("Aucun responsable enregistré.");
            return;
        }
        
        for (Responsable responsable : responsables) {
            System.out.println(responsable);
            System.out.println("-----------------------------------");
        }
    }
    
    private void rechercherResponsable() {
        System.out.println("\n=== RECHERCHER UN RESPONSABLE ===");
        System.out.print("Entrez l'ID du responsable: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        Responsable responsable = trouverResponsableParId(id);
        
        if (responsable != null) {
            System.out.println("\nResponsable trouvé:");
            System.out.println(responsable);
        } else {
            System.out.println("Aucun responsable trouvé avec l'ID: " + id);
        }
    }
    
    private void ajouterResponsable() {
        System.out.println("\n=== AJOUTER UN RESPONSABLE ===");
        
        System.out.print("Entrez l'ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        // Vérifier si l'ID existe déjà
        if (trouverResponsableParId(id) != null) {
            System.out.println("Un responsable avec cet ID existe déjà.");
            return;
        }
        
        System.out.print("Entrez le nom d'utilisateur: ");
        String username = scanner.nextLine();
        
        System.out.print("Entrez le mot de passe: ");
        String password = scanner.nextLine();
        
        System.out.print("Entrez le nom: ");
        String nom = scanner.nextLine();
        
        System.out.print("Entrez le prénom: ");
        String prenom = scanner.nextLine();
        
        Responsable nouveauResponsable = new Responsable(id, username, password, nom, prenom);
        responsables.add(nouveauResponsable);
        
        System.out.println("Responsable ajouté avec succès.");
    }
    
    private Responsable trouverResponsableParId(int id) {
        for (Responsable responsable : responsables) {
            if (responsable.getId() == id) {
                return responsable;
            }
        }
        return null;
    }
    
    private void modifierResponsable() {
        System.out.println("\n=== MODIFIER UN RESPONSABLE ===");
        System.out.print("Entrez l'ID du responsable à modifier: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
    
        Responsable responsable = trouverResponsableParId(id);
    
        if (responsable != null) {
            System.out.println("\nResponsable trouvé: " + responsable);
    
            System.out.print("Nouveau nom d'utilisateur (actuel: " + responsable.getUsername() + "): ");
            String username = scanner.nextLine();
            if (!username.isEmpty()) {
                responsable.setUsername(username);
            }
    
            System.out.print("Nouveau mot de passe (laisser vide pour ne pas changer): ");
            String password = scanner.nextLine();
            if (!password.isEmpty()) {
                responsable.setPassword(password);
            }
    
            System.out.print("Nouveau nom (actuel: " + responsable.getNom() + "): ");
            String nom = scanner.nextLine();
            if (!nom.isEmpty()) {
                responsable.setNom(nom);
            }
    
            System.out.print("Nouveau prénom (actuel: " + responsable.getPrenom() + "): ");
            String prenom = scanner.nextLine();
            if (!prenom.isEmpty()) {
                responsable.setPrenom(prenom);
            }
    
            System.out.println("Responsable modifié avec succès.");
        } else {
            System.out.println("Aucun responsable trouvé avec l'ID: " + id);
        }
    }
    
    private void supprimerResponsable() {
        System.out.println("\n=== SUPPRIMER UN RESPONSABLE ===");
        System.out.print("Entrez l'ID du responsable à supprimer: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        Responsable responsable = trouverResponsableParId(id);
        responsables.remove(responsable);
        
        if (responsable != null) {
            System.out.println("\nResponsable trouvé: " + responsable);
            System.out.println("Responsable supprimé avec succès.");
        } else {
            System.out.println("Aucun responsable trouvé avec l'ID: " + id);
        }
    }
}