package org.enchere.bo;

import java.util.List;

public class Categorie<String> {

    private int noCategorie;
    private String libelle;

    //categorieArticles - Liste des articles par catégorie (0 ou plusieurs articles)
    List<Articles> articles;

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

    public void setNoCategorie(int noCategorie) {
        this.noCategorie = noCategorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }



    @Override
    public java.lang.String toString() {
        return "Categorie{" +
                "noCategorie=" + noCategorie +
                ", libelle=" + libelle +
                '}';
    }
}
