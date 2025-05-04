package gestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modele.Dentiste;

public class GestionDentiste {
    private List<Dentiste> dentistes;
    private Scanner scanner;
    
    public GestionDentiste() {
        this.dentistes = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        
        // Ajouter quelques dentistes pour les tests
        Dentiste dentiste1 = new Dentiste(1, "dent1", "pass1", "Leroy", "Orthodontie");
        Dentiste dentiste2 = new Dentiste(2, "dent2", "pass2", "Blanc", "Chirurgie dentaire");
        
        dentistes.add(dentiste1);
        dentistes.add(dentiste2);
    }
    
    public void afficherMenu() {
        boolean retour = false;
        
        while (!retour) {
            System.out.println("\n======================================");
            System.out.println("GESTION DES DENTISTES");
            System.out.println("======================================");
            System.out.println("1. Afficher tous les dentistes");
            System.out.println("2. Rechercher un dentiste");
            System.out.println("3. Ajouter un dentiste");
            System.out.println("4. Modifier un dentiste");
            System.out.println("5. Supprimer un dentiste");
            System.out.println("6. Retour au menu principal");
            System.out.println("======================================");
            
            System.out.print("Votre choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne
            
            switch (choix) {
                case 1:
                    afficherTousDentistes();
                    break;
                case 2:
                    rechercherDentiste();
                    break;
                case 3:
                    ajouterDentiste();
                    break;
                case 4:
                    modifierDentiste();
                    break;
                case 5:
                    supprimerDentiste();
                    break;
                case 6:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }
    
    private void afficherTousDentistes() {
        System.out.println("\n=== LISTE DES DENTISTES ===");
        
        if (dentistes.isEmpty()) {
            System.out.println("Aucun dentiste enregistré.");
            return;
        }
        
        for (Dentiste dentiste : dentistes) {
            System.out.println(dentiste);
            System.out.println("-----------------------------------");
        }
    }
    
    private void rechercherDentiste() {
        System.out.println("\n=== RECHERCHER UN DENTISTE ===");
        System.out.print("Entrez l'ID du dentiste: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        Dentiste dentiste = trouverDentisteParId(id);
        
        if (dentiste != null) {
            System.out.println("\nDentiste trouvé:");
            System.out.println(dentiste);
        } else {
            System.out.println("Aucun dentiste trouvé avec l'ID: " + id);
        }
    }
    
    private void ajouterDentiste() {
        System.out.println("\n=== AJOUTER UN DENTISTE ===");
        
        System.out.print("Entrez l'ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        // Vérifier si l'ID existe déjà
        if (trouverDentisteParId(id) != null) {
            System.out.println("Un dentiste avec cet ID existe déjà.");
            return;
        }
        
        System.out.print("Entrez le nom d'utilisateur: ");
        String username = scanner.nextLine();
        
        System.out.print("Entrez le mot de passe: ");
        String password = scanner.nextLine();
        
        System.out.print("Entrez le nom: ");
        String nom = scanner.nextLine();
        
        System.out.print("Entrez la spécialité: ");
        String specialite = scanner.nextLine();
        
        Dentiste nouveauDentiste = new Dentiste(id, username, password, nom, specialite);
        dentistes.add(nouveauDentiste);
        
        System.out.println("Dentiste ajouté avec succès.");
    }
    
    private void modifierDentiste() {
        System.out.println("\n=== MODIFIER UN DENTISTE ===");
        System.out.print("Entrez l'ID du dentiste à modifier: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        Dentiste dentiste = trouverDentisteParId(id);
        
        if (dentiste != null) {
            System.out.println("\nDentiste trouvé: " + dentiste);
            
            System.out.print("Nouveau nom d'utilisateur (actuel: " + dentiste.getUsername() + "): ");
            String username = scanner.nextLine();
            if (!username.isEmpty()) {
                dentiste.setUsername(username);
            }
            
            System.out.print("Nouveau mot de passe (laisser vide pour ne pas changer): ");
            String password = scanner.nextLine();
            if (!password.isEmpty()) {
                dentiste.setPassword(password);
            }
            
            System.out.print("Nouveau nom (actuel: " + dentiste.getNom() + "): ");
            String nom = scanner.nextLine();
            if (!nom.isEmpty()) {
                dentiste.setNom(nom);
            }
            
            System.out.print("Nouvelle spécialité (actuelle: " + dentiste.getSpecialite() + "): ");
            String specialite = scanner.nextLine();
            if (!specialite.isEmpty()) {
                dentiste.setSpecialite(specialite);
            }
            
            System.out.println("Dentiste modifié avec succès.");
        } else {
            System.out.println("Aucun dentiste trouvé avec l'ID: " + id);
        }
    }
    
    private void supprimerDentiste() {
        System.out.println("\n=== SUPPRIMER UN DENTISTE ===");
        System.out.print("Entrez l'ID du dentiste à supprimer: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
    
        Dentiste dentiste = trouverDentisteParId(id);
    
        if (dentiste != null) {
            dentistes.remove(dentiste);
            System.out.println("Dentiste supprimé avec succès.");
        } else {
            System.out.println("Aucun dentiste trouvé avec l'ID: " + id);
        }
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