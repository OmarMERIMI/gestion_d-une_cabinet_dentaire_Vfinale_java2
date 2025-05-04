package modele;

public abstract class Utilisateur {
    protected int id;
    protected String username;
    protected String password;
    
    public Utilisateur(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    
    public Utilisateur() {
        // Constructeur par défaut
    }
    
    // Getters et Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    // Méthodes du diagramme UML
    public void seConnecter() {
        System.out.println("Utilisateur " + username + " connecté");
    }
    
    public void seDeconnecter() {
        System.out.println("Utilisateur " + username + " déconnecté");
    }
    
    @Override
    public String toString() {
        return "ID: " + id + ", Username: " + username;
    }
}