package Models;

import java.util.Date;

public class Etudiant {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String datenaissance;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDatenaissance() {
        return datenaissance;
    }

    public Etudiant(int id, String nom, String prenom, String email, String datenaissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.datenaissance = datenaissance;
    }

    public Etudiant(String nom, String email, String prenom, String datenaissance) {
        this.nom = nom;
        this.email = email;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
    }

    public void setDatenaissance(String datenaissance) {
        this.datenaissance = datenaissance;
    }

}
