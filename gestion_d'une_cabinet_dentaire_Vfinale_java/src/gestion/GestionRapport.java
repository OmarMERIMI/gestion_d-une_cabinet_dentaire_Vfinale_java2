package gestion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import modele.Rapport;
import modele.Responsable;

public class GestionRapport {
    private List<Rapport> rapports;
    private List<Responsable> responsables;
    private Scanner scanner;
    private SimpleDateFormat dateFormat;
    
    public GestionRapport() {
        this.rapports = new ArrayList<>();
        this.responsables = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        // Ajouter quelques rapports pour les tests
        try {
            Responsable responsable1 = new Responsable(1, "resp1", "pass1", "Martin", "Jean");
            responsables.add(responsable1);
            
            Rapport rapport1 = new Rapport(1, "Financier", dateFormat.parse("15/01/2023"), "Rapport financier du premier trimestre");
            rapport1.setResponsable(responsable1);
            
            Rapport rapport2 = new Rapport(2, "Activité", dateFormat.parse("20/02/2023"), "Rapport d'activité mensuel");
            rapport2.setResponsable(responsable1);
            
            rapports.add(rapport1);
            rapports.add(rapport2);
            
        } catch (ParseException e) {
            System.out.println("Erreur lors de la création des données de test: " + e.getMessage());
        }
    }
    
    public void afficherMenu() {
        boolean retour = false;
        
        while (!retour) {
            System.out.println("\n======================================");
            System.out.println("GESTION DES RAPPORTS");
            System.out.println("======================================");
            System.out.println("1. Afficher tous les rapports");
            System.out.println("2. Rechercher un rapport");
            System.out.println("3. Ajouter un rapport");
            System.out.println("4. Modifier un rapport");
            System.out.println("5. Générer un rapport");
            System.out.println("6. Retour au menu principal");
            System.out.println("======================================");
            
            System.out.print("Votre choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne
            
            switch (choix) {
                case 1:
                    afficherTousRapports();
                    break;
                case 2:
                    rechercherRapport();
                    break;
                case 3:
                    ajouterRapport();
                    break;
                case 4:
                    modifierRapport();
                    break;
                case 5:
                    genererRapport();
                    break;
                case 6:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }
    
    private void afficherTousRapports() {
        System.out.println("\n=== LISTE DES RAPPORTS ===");
        
        if (rapports.isEmpty()) {
            System.out.println("Aucun rapport enregistré.");
            return;
        }
        
        for (Rapport rapport : rapports) {
            System.out.println(rapport);
            if (rapport.getResponsable() != null) {
                System.out.println("Responsable: " + rapport.getResponsable().getNom() + " " + rapport.getResponsable().getPrenom());
            }
            System.out.println("-----------------------------------");
        }
    }
    
    private void rechercherRapport() {
        System.out.println("\n=== RECHERCHER UN RAPPORT ===");
        System.out.print("Entrez l'ID du rapport: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        Rapport rapport = trouverRapportParId(id);
        
        if (rapport != null) {
            System.out.println("\nRapport trouvé:");
            System.out.println(rapport);
            System.out.println("Contenu: " + rapport.getContenu());
            if (rapport.getResponsable() != null) {
                System.out.println("Responsable: " + rapport.getResponsable().getNom() + " " + rapport.getResponsable().getPrenom());
            }
        } else {
            System.out.println("Aucun rapport trouvé avec l'ID: " + id);
        }
    }
    
    private void ajouterRapport() {
        System.out.println("\n=== AJOUTER UN RAPPORT ===");
        
        System.out.print("Entrez l'ID du rapport: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        // Vérifier si l'ID existe déjà
        if (trouverRapportParId(id) != null) {
            System.out.println("Un rapport avec cet ID existe déjà.");
            return;
        }
        
        System.out.print("Entrez l'ID du responsable: ");
        int idResponsable = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        Responsable responsable = trouverResponsableParId(idResponsable);
        if (responsable == null) {
            System.out.println("Aucun responsable trouvé avec l'ID: " + idResponsable);
            return;
        }
        
        System.out.print("Entrez le type de rapport: ");
        String type = scanner.nextLine();
        
        System.out.print("Entrez la date (jj/mm/aaaa): ");
        String dateStr = scanner.nextLine();
        
        System.out.print("Entrez le contenu du rapport: ");
        String contenu = scanner.nextLine();
        
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Format de date invalide. Utilisation de la date actuelle.");
            date = new Date();
        }
        
        Rapport nouveauRapport = new Rapport(id, type, date, contenu);
        nouveauRapport.setResponsable(responsable);
        
        rapports.add(nouveauRapport);
        responsable.ajouterRapport(nouveauRapport);
        System.out.println("Rapport ajouté avec succès.");
    }
    
    private void modifierRapport() {
        System.out.println("\n=== MODIFIER UN RAPPORT ===");
        System.out.print("Entrez l'ID du rapport: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        Rapport rapport = trouverRapportParId(id);
        
        if (rapport != null) {
            System.out.println("\nRapport trouvé: " + rapport);
            
            System.out.print("Nouveau type (actuel: " + rapport.getType() + "): ");
            String type = scanner.nextLine();
            if (!type.isEmpty()) {
                rapport.setType(type);
            }
            
            System.out.print("Nouveau contenu (actuel: " + rapport.getContenu() + "): ");
            String contenu = scanner.nextLine();
            if (!contenu.isEmpty()) {
                rapport.setContenu(contenu);
            }
            
            System.out.println("Rapport modifié avec succès.");
        } else {
            System.out.println("Aucun rapport trouvé avec l'ID: " + id);
        }
    }
    
    private void genererRapport() {
        System.out.println("\n=== GÉNÉRER UN RAPPORT ===");
        System.out.print("Entrez l'ID du rapport à générer: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        Rapport rapport = trouverRapportParId(id);
        
        if (rapport != null) {
            rapport.genererRapport();
            System.out.println("Contenu du rapport: " + rapport.getContenu());
            System.out.println("Rapport généré avec succès.");
        } else {
            System.out.println("Aucun rapport trouvé avec l'ID: " + id);
        }
    }
    
    private Rapport trouverRapportParId(int id) {
        for (Rapport rapport : rapports) {
            if (rapport.getId() == id) {
                return rapport;
            }
        }
        return null;
    }
    
    private Responsable trouverResponsableParId(int id) {
        for (Responsable responsable : responsables) {
            if (responsable.getId() == id) {
                return responsable;
            }
        }
        return null;
    }
}