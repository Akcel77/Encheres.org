package org.enchere.bo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Gestions des articles
 */
public class Articles {
    private DateFormat dateFormatDayUS = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat dateFormatDayFR = new SimpleDateFormat("dd-MM-yyyy");
    private int id;
    private String nomArticles;
    private String description;
    private String dateDebutEncheres;
    private String dateFinEncheres;
    private String heureDebut;
    private String heureFin;
    private int miseAprix;
    private String etatDeVente;
    private Categorie caterogie;
    private Utilisateur utilisateur;
    private Retrait retrait;
    private List<Enchere> encheres;

    public Articles(){
    }

    public Articles(String nomArticles, String description, String dateDebutEncheres, String dateFinEncheres, int miseAprix, Categorie caterogie, Utilisateur utilisateur, Retrait retrait) {
        this.nomArticles = nomArticles;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.miseAprix = miseAprix;
        this.caterogie = caterogie;
        this.utilisateur = utilisateur;
        this.retrait = retrait;
    }

    public Articles(String nomArticles, String description, String dateDebutEncheres, String dateFinEncheres, String heureDebut, String heureFin, int miseAprix, Categorie caterogie, Utilisateur utilisateur) {
        this.nomArticles = nomArticles;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.miseAprix = miseAprix;
        this.caterogie = caterogie;
        this.utilisateur = utilisateur;
    }

    public Articles(int id, String nomArticles, String description, String dateDebutEncheres, String dateFinEncheres, int miseAprix, String etatDeVente, Categorie caterogie, List<Enchere> encheres) {
        this.id = id;
        this.nomArticles = nomArticles;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.miseAprix = miseAprix;
        this.etatDeVente = etatDeVente;
        this.caterogie = caterogie;
        this.encheres = encheres;
    }

    public Articles(int id, String nomArticles, String description, String dateDebutEncheres, String dateFinEncheres, int miseAprix, String etatDeVente, Categorie caterogie) {
        this.id = id;
        this.nomArticles = nomArticles;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.miseAprix = miseAprix;
        this.etatDeVente = etatDeVente;
        this.caterogie = caterogie;
    }

    public Articles(String nomArticles, String description, String dateDebutEncheres, String dateFinEncheres, int miseAprix) {
        this.nomArticles = nomArticles;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.miseAprix = miseAprix;
    }

    // Méthodes
    /**
     * Ajoute une enchère dans la liste des enchères
     * @param enchere
     */
    public void addEnchere(Enchere enchere){
        if (encheres == null){
            encheres = new ArrayList<>();
        }
        this.encheres.add(enchere);
    }

    /**
     * Retourne une instance d'enchère
     * @param id
     * @return une instance d'enchere qui a pour id l'id passé en paramètre si elle existe, sinon null
     */
    public Enchere getEnchereByID(int id){
        Enchere enchere = null;
        for (Enchere ecr: encheres) {
            if (ecr.getNo_enchere() == id){
                enchere = ecr;
            }
        }
        return enchere;
    }

    public Enchere getLastEncheres() {
        if (encheres.size() != 0){
            return encheres.get(encheres.size()-1);
        }else{
            return null;
        }
    }

    public Categorie getCaterogie() {
        return caterogie;
    }

    public void setCaterogie(Categorie caterogie) {
        this.caterogie = caterogie;
    }

    public List<Enchere> getEncheres() {
        return encheres;
    }

    public void setEncheres(List<Enchere> encheres) {
        this.encheres = encheres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomArticles() {
        return nomArticles;
    }

    public void setNomArticles(String nomArticles) {
        this.nomArticles = nomArticles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    public void setDateDebutEncheres(String dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }

    public String getDateFinEncheres() {
        return dateFinEncheres;
    }

    public void setDateFinEncheres(String dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }

    public int getMiseAprix() {
        return miseAprix;
    }

    public void setMiseAprix(int miseAprix) {
        this.miseAprix = miseAprix;
    }

    public void setEtatDeVente(String etatDeVente) {
        this.etatDeVente = etatDeVente;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Retrait getRetrait() { return retrait; }

    public void setRetrait(Retrait retrait) { this.retrait = retrait; }

    public String convertToFRDAte(String date) throws ParseException {
        Date date1 = dateFormatDayUS.parse(date);
        return dateFormatDayFR.format(date1);
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "id=" + id +
                ", nomArticles='" + nomArticles + '\'' +
                ", description='" + description + '\'' +
                ", dateDebutEncheres='" + dateDebutEncheres + '\'' +
                ", dateFinEncheres='" + dateFinEncheres + '\'' +
                ", miseAprix=" + miseAprix +
                ", etatDeVente='" + etatDeVente + '\'' +
                ", caterogie=" + caterogie +
                ", utilisateur=" + utilisateur +
                ", encheres=" + encheres +
                '}';
    }
}
