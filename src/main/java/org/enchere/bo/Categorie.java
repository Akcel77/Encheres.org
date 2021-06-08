package org.enchere.bo;

public class Categorie<String> {

    private int noCategorie;
    private String libelle;

    //Constructor

    public Categorie(int noCategorie, String libelle) {
        this.noCategorie = noCategorie;
        this.libelle = libelle;
    }

    public Categorie() {

    }


    // Getters & Setters

    public int getNoCategorie() {
        return noCategorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
