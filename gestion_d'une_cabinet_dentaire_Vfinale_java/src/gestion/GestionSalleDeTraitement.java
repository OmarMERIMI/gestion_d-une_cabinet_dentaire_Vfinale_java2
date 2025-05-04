package gestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modele.Assistant;
import modele.SalleDeTraitement;

public class GestionSalleDeTraitement {
    private List<SalleDeTraitement> salles;
    private List<Assistant> assistants;
    private Scanner scanner;
    
    public GestionSalleDeTraitement() {
        this.salles = new ArrayList<>();
        this.assistants = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        
        // Ajouter quelques salles pour les tests
        SalleDeTraitement salle1 = new SalleDeTraitement(1, "Salle A", "Équipement orthodontique", "Disponible");
        SalleDeTraitement salle2 = new SalleDeTraitement(2, "Salle B", "Équipement chirurgical", "Disponible");
        
        Assistant assistant1 = new Assistant(1, "assist1", "pass1", "Dubois", "Sophie");
        Assistant assistant2 = new Assistant(2, "assist2", "pass2", "Moreau", "Thomas");
        
        salle1.setAssistant(assistant1);
        salle2.setAssistant(assistant2);
        assistant1.ajouterSalle(salle1);
        assistant2.ajouterSalle(salle2);
        
        salles.add(salle1);
        salles.add(salle2);
        assistants.add(assistant1);
        assistants.add(assistant2);
    }
    
    public void afficherMenu() {
        boolean retour = false;
        
        while (!retour) {
            System.out.println("\n======================================");
            System.out.println("GESTION DES SALLES DE TRAITEMENT");
            System.out.println("======================================");
            System.out.println("1. Afficher toutes les salles");
            System.out.println("2. Rechercher une salle");
            System.out.println("3. Ajouter une salle");
            System.out.println("4. Modifier une salle");
            System.out.println("5. Préparer une salle");
            System.out.println("6. Vérifier l'équipement d'une salle");
            System.out.println("7. Retour au menu principal");
            System.out.println("======================================");
            
            System.out.print("Votre choix: ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne
            
            switch (choix) {
                case 1:
                    afficherToutesSalles();
                    break;
                case 2:
                    rechercherSalle();
                    break;
                case 3:
                    ajouterSalle();
                    break;
                case 4:
                    modifierSalle();
                    break;
                case 5:
                    preparerSalle();
                    break;
                case 6:
                    verifierEquipement();
                    break;
                case 7:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }
    
    private void afficherToutesSalles() {
        System.out.println("\n=== LISTE DES SALLES DE TRAITEMENT ===");
        
        if (salles.isEmpty()) {
            System.out.println("Aucune salle enregistrée.");
            return;
        }
        
        for (SalleDeTraitement salle : salles) {
            System.out.println(salle);
            if (salle.getAssistant() != null) {
                System.out.println("Assistant: " + salle.getAssistant().getNom() + " " + salle.getAssistant().getPrenom());
            }
            System.out.println("-----------------------------------");
        }
    }
    
    private void rechercherSalle() {
        System.out.println("\n=== RECHERCHER UNE SALLE ===");
        System.out.print("Entrez l'ID de la salle: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        SalleDeTraitement salle = trouverSalleParId(id);
        
        if (salle != null) {
            System.out.println("\nSalle trouvée:");
            System.out.println(salle);
            System.out.println("Équipements: " + salle.getEquipements());
            if (salle.getAssistant() != null) {
                System.out.println("Assistant: " + salle.getAssistant().getNom() + " " + salle.getAssistant().getPrenom());
            }
        } else {
            System.out.println("Aucune salle trouvée avec l'ID: " + id);
        }
    }
    
    private void ajouterSalle() {
        System.out.println("\n=== AJOUTER UNE SALLE ===");
        
        System.out.print("Entrez l'ID de la salle: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        // Vérifier si l'ID existe déjà
        if (trouverSalleParId(id) != null) {
            System.out.println("Une salle avec cet ID existe déjà.");
            return;
        }
        
        System.out.print("Entrez le nom de la salle: ");
        String nom = scanner.nextLine();
        
        System.out.print("Entrez les équipements de la salle: ");
        String equipements = scanner.nextLine();
        
        System.out.print("Entrez le statut de la salle (Disponible, Occupée, En maintenance): ");
        String statut = scanner.nextLine();
        
        SalleDeTraitement nouvelleSalle = new SalleDeTraitement(id, nom, equipements, statut);
        
        System.out.print("Voulez-vous assigner un assistant à cette salle? (O/N): ");
        String reponse = scanner.nextLine();
        
        if (reponse.equalsIgnoreCase("O")) {
            System.out.print("Entrez l'ID de l'assistant: ");
            int idAssistant = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne
            
            Assistant assistant = trouverAssistantParId(idAssistant);
            if (assistant != null) {
                nouvelleSalle.setAssistant(assistant);
                assistant.ajouterSalle(nouvelleSalle);
                System.out.println("Assistant assigné avec succès.");
            } else {
                System.out.println("Aucun assistant trouvé avec l'ID: " + idAssistant);
            }
        }
        
        salles.add(nouvelleSalle);
        System.out.println("Salle ajoutée avec succès.");
    }
    
    private void modifierSalle() {
        System.out.println("\n=== MODIFIER UNE SALLE ===");
        System.out.print("Entrez l'ID de la salle: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        SalleDeTraitement salle = trouverSalleParId(id);
        
        if (salle != null) {
            System.out.println("\nSalle trouvée: " + salle);
            
            System.out.print("Nouveau nom (actuel: " + salle.getNom() + "): ");
            String nom = scanner.nextLine();
            if (!nom.isEmpty()) {
                salle.setNom(nom);
            }
            
            System.out.print("Nouveaux équipements (actuels: " + salle.getEquipements() + "): ");
            String equipements = scanner.nextLine();
            if (!equipements.isEmpty()) {
                salle.setEquipements(equipements);
            }
            
            System.out.print("Nouveau statut (actuel: " + salle.getStatut() + "): ");
            String statut = scanner.nextLine();
            if (!statut.isEmpty()) {
                salle.setStatut(statut);
            }
            
            System.out.print("Voulez-vous modifier l'assistant assigné? (O/N): ");
            String reponse = scanner.nextLine();
            
            if (reponse.equalsIgnoreCase("O")) {
                System.out.print("Entrez l'ID du nouvel assistant: ");
                int idAssistant = scanner.nextInt();
                scanner.nextLine(); // Consommer la nouvelle ligne
                
                Assistant assistant = trouverAssistantParId(idAssistant);
                if (assistant != null) {
                    salle.setAssistant(assistant);
                    assistant.ajouterSalle(salle);
                    System.out.println("Assistant modifié avec succès.");
                } else {
                    System.out.println("Aucun assistant trouvé avec l'ID: " + idAssistant);
                }
            }
            
            System.out.println("Salle modifiée avec succès.");
        } else {
            System.out.println("Aucune salle trouvée avec l'ID: " + id);
        }
    }
    
    private void preparerSalle() {
        System.out.println("\n=== PRÉPARER UNE SALLE ===");
        System.out.print("Entrez l'ID de la salle: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        SalleDeTraitement salle = trouverSalleParId(id);
        
        if (salle != null) {
            salle.preparer();
            System.out.println("La salle " + salle.getNom() + " a été préparée et est maintenant disponible.");
        } else {
            System.out.println("Aucune salle trouvée avec l'ID: " + id);
        }
    }
    
    private void verifierEquipement() {
        System.out.println("\n=== VÉRIFIER L'ÉQUIPEMENT D'UNE SALLE ===");
        System.out.print("Entrez l'ID de la salle: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        
        SalleDeTraitement salle = trouverSalleParId(id);
        
        if (salle != null) {
            salle.verifierEquipement();
            System.out.println("Équipements de la salle " + salle.getNom() + ": " + salle.getEquipements());
        } else {
            System.out.println("Aucune salle trouvée avec l'ID: " + id);
        }
    }
    
    private SalleDeTraitement trouverSalleParId(int id) {
        for (SalleDeTraitement salle : salles) {
            if (salle.getId() == id) {
                return salle;
            }
        }
        return null;
    }
    
    private Assistant trouverAssistantParId(int id) {
        for (Assistant assistant : assistants) {
            if (assistant.getId() == id) {
                return assistant;
            }
        }
        return null;
    }
}