package modele;

import java.util.Date;

public class Rapport {
    private int id;
    private String type;
    private Date date;
    private String contenu;
    private Responsable responsable;
    
    public Rapport(int id, String type, Date date, String contenu) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.contenu = contenu;
    }
    
    public Rapport() {
        // Constructeur par défaut
    }
    
    // Getters et Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getContenu() {
        return contenu;
    }
    
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    public Responsable getResponsable() {
        return responsable;
    }
    
    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }
    
    // Méthodes du diagramme UML
    public void genererRapport() {
        System.out.println("Génération du rapport ID: " + id + " de type: " + type);
    }
    
    @Override
    public String toString() {
        return "Rapport [ID: " + id + ", Type: " + type + ", Date: " + date + "]";
    }
}