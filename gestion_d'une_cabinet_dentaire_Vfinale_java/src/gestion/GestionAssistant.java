package gestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modele.Assistant;
import modele.SalleDeTraitement;

public class GestionAssistant {
    private List<Assistant> assistants;
    private List<SalleDeTraitement> salles;
    private Scanner scanner;
    
    public GestionAssistant() {
        this.assistants = new ArrayList<>();
        this.salles = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        
        // Ajouter quelques assistants pour les tests
        Assistant assistant1 = new Assistant(1, "assist1", "pass1", "Dubois", "Sophie");
        Assistant assistant2 = new Assistant(2, "assist2", "pass2", "Moreau", "Thomas");
        
        SalleDeTraitement salle1 = new SalleDeTraitement(1, "Salle A", "Équipement orthodontique", "Disponible");
        SalleDeTraitement salle2 = new SalleDeTraitement(2, "Salle B", "Équipement chirurgical", "Disponible");
        
        salle1.setAssistant(assistant1);
        salle2.setAssistant(assistant2);
        assistant1.ajouterSalle(salle1);
        assistant2.ajouterSalle(salle2);
        
        assistants.add(assistant1);
        assistants.add(assistant2);
        salles.add(salle1);
        salles.add(salle2);
    }
    
    public void afficherMenu() {
        boolean retour = false;
        
        while (!retour) {
            System.out.println("\n======================================");
            System.out.println("GESTION DES ASSISTANTS");
            System.out.println("======================================");
            System.out.println("1. Afficher tous les assistants");
            System.out.println("2. Rechercher un assistant");
            System.out.println("3. Ajouter un assistant");
            System.out.println("4. Modifier un assistant");
            System.out.println("5. Supprimer un assistant");
            System.out.println("6. Assigner une salle à un assistant");
            System.out.println("7. Retour au menu principal");
            System.out.println("======================================");
            
            System.out.print("Votre choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne
            
            switch (choix) {
                case 1:
                    afficherTousAssistants();
                    break;
                case 2:
                    rechercherAssistant();
                    break;
                case 3:
                    ajouterAssistant();
                    break;
                case 4:
                    modifierAssistant();
                    break;
                case 5:
                    supprimerAssistant();
                    break;
                case 6:
                    assignerSalle();
                    break;
                case 7:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }
    
    private void afficherTousAssistants() {
        System.out.println("\n=== LISTE DES ASSISTANTS ===");
        
        if (assistants.isEmpty()) {
            System.out.println("Aucun assistant enregistré.");
            return;
        }
        
        for (Assistant assistant : assistants) {
            System.out.println(assistant);
            System.out.println("Salles assignées: ");
            if (assistant.getSalles().isEmpty()) {
                System.out.println("  Aucune salle assignée");
            } else {
                for (SalleDeTraitement salle : assistant.getSalles()) {
                    System.out.println("  - " + salle.getNom());
                }
            }
            System.out.println("-----------------------------------");
        }
    }
    
    private void rechercherAssistant() {
        System.out.println("\n=== RECHERCHER UN ASSISTANT ===");
        System.out.print("Entrez l'ID de l'assistant: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        Assistant assistant = trouverAssistantParId(id);
        
        if (assistant != null) {
            System.out.println("\nAssistant trouvé:");
            System.out.println(assistant);
            System.out.println("Salles assignées: ");
            if (assistant.getSalles().isEmpty()) {
                System.out.println("  Aucune salle assignée");
            } else {
                for (SalleDeTraitement salle : assistant.getSalles()) {
                    System.out.println("  - " + salle.getNom());
                }
            }
        } else {
            System.out.println("Aucun assistant trouvé avec l'ID: " + id);
        }
    }
    
    private void ajouterAssistant() {
        System.out.println("\n=== AJOUTER UN ASSISTANT ===");
        
        System.out.print("Entrez l'ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        // Vérifier si l'ID existe déjà
        if (trouverAssistantParId(id) != null) {
            System.out.println("Un assistant avec cet ID existe déjà.");
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
        
        Assistant nouveauAssistant = new Assistant(id, username, password, nom, prenom);
        assistants.add(nouveauAssistant);
        
        System.out.println("Assistant ajouté avec succès.");
    }
    
    private void modifierAssistant() {
        System.out.println("\n=== MODIFIER UN ASSISTANT ===");
        System.out.print("Entrez l'ID de l'assistant à modifier: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        Assistant assistant = trouverAssistantParId(id);
        
        if (assistant != null) {
            System.out.println("\nAssistant trouvé: " + assistant);
            
            System.out.print("Nouveau nom d'utilisateur (actuel: " + assistant.getUsername() + "): ");
            String username = scanner.nextLine();
            if (!username.isEmpty()) {
                assistant.setUsername(username);
            }
            
            System.out.print("Nouveau mot de passe (laisser vide pour ne pas changer): ");
            String password = scanner.nextLine();
            if (!password.isEmpty()) {
                assistant.setPassword(password);
            }
            
            System.out.print("Nouveau nom (actuel: " + assistant.getNom() + "): ");
            String nom = scanner.nextLine();
            if (!nom.isEmpty()) {
                assistant.setNom(nom);
            }
            
            System.out.print("Nouveau prénom (actuel: " + assistant.getPrenom() + "): ");
            String prenom = scanner.nextLine();
            if (!prenom.isEmpty()) {
                assistant.setPrenom(prenom);
            }
            
            System.out.println("Assistant modifié avec succès.");
        } else {
            System.out.println("Aucun assistant trouvé avec l'ID: " + id);
        }
    }
    
    private void supprimerAssistant() {
        System.out.println("\n=== SUPPRIMER UN ASSISTANT ===");
        System.out.print("Entrez l'ID de l'assistant à supprimer: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        Assistant assistant = trouverAssistantParId(id);
        
        if (assistant != null) {
            System.out.println("\nAssistant trouvé: " + assistant);
            System.out.print("Voulez-vous vraiment supprimer cet assistant? (O/N): ");
            String confirmation = scanner.nextLine();
            
            if (confirmation.equalsIgnoreCase("O")) {
                // Retirer l'assistant des salles assignées
                for (SalleDeTraitement salle : assistant.getSalles()) {
                    salle.setAssistant(null);
                }
                
                assistants.remove(assistant);
                System.out.println("Assistant supprimé avec succès.");
            } else {
                System.out.println("Suppression annulée.");
            }
        } else {
            System.out.println("Aucun assistant trouvé avec l'ID: " + id);
        }
    }
    
    private void assignerSalle() {
        System.out.println("\n=== ASSIGNER UNE SALLE À UN ASSISTANT ===");
        System.out.print("Entrez l'ID de l'assistant: ");
        int idAssistant = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        Assistant assistant = trouverAssistantParId(idAssistant);
        
        if (assistant == null) {
            System.out.println("Aucun assistant trouvé avec l'ID: " + idAssistant);
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
        
        // Vérifier si la salle est déjà assignée à un autre assistant
        if (salle.getAssistant() != null && salle.getAssistant().getId() != assistant.getId()) {
            System.out.println("Cette salle est déjà assignée à l'assistant: " + 
                               salle.getAssistant().getNom() + " " + salle.getAssistant().getPrenom());
            System.out.print("Voulez-vous réassigner cette salle? (O/N): ");
            String confirmation = scanner.nextLine();
            
            if (!confirmation.equalsIgnoreCase("O")) {
                System.out.println("Assignation annulée.");
                return;
            }
            
            // Retirer la salle de l'assistant précédent
            salle.getAssistant().getSalles().remove(salle);
        }
        
        salle.setAssistant(assistant);
        assistant.ajouterSalle(salle);
        
        System.out.println("Salle " + salle.getNom() + " assignée avec succès à l'assistant " + 
                           assistant.getNom() + " " + assistant.getPrenom());
    }
    
    private Assistant trouverAssistantParId(int id) {
        for (Assistant assistant : assistants) {
            if (assistant.getId() == id) {
                return assistant;
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