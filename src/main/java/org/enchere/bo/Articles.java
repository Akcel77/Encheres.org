package org.enchere.bo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestions des articles
 */
public class Articles {
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private int id;
    private String nomArticles;
    private String description;
    private String dateDebutEncheres;
    private String dateFinEncheres;
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

    public Articles(String nomArticles, String description, String dateDebutEncheres, String dateFinEncheres, int miseAprix, Categorie caterogie, Utilisateur utilisateur) {
        this.nomArticles = nomArticles;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
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
     * Ajoute une enchere dans la liste des encheres
     * @param enchere
     */
    public void addEnchere(Enchere enchere){
        if (encheres == null){
            encheres = new ArrayList<>();
        }
        this.encheres.add(enchere);
    }

    /**
     * Retourne une instance d'enchere
     * @param id
     * @return une instance d'enchere qui as pour as pour id l'id passé en parametre si elle existe sinon null
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
            System.out.println("enchere n'est pas null" + encheres.toString());
            return encheres.get(encheres.size()-1);
        }else{
            return null;
        }
    }

    // Getter & setter
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

    public String getEtatDeVente() {
        return etatDeVente;
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
