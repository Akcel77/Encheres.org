package org.enchere.dal.jdbc;

import org.enchere.bo.Articles;
import org.enchere.bo.Categorie;
import org.enchere.bo.Enchere;
import org.enchere.bo.Utilisateur;
import org.enchere.dal.ArticleDAO;
import org.enchere.outils.BusinessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleImpl implements ArticleDAO {

    private final String SELECTALL  = "SELECT * FROM articles_vendus";
    private final String SELECT = "SELECT * FROM articles_vendus WHERE no_article = ?";
    private final String UPDATE = "UPDATE articles_vendus SET nom_article=?, description=?, date_debut_encheres =?, date_fin_encheres=?, prix_initial=?,prix_vente=?,no_utilisateur=?,no_categorie=? WHERE no_article=?";
    private final String DELETE = "DELETE FROM articles_vendus WHERE no_article =?";
    private final String INSERT = "INSERT INTO articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES(?,?,?,?,?,?,?) ";
    private final String SELECT_BY_USER = "SELECT * FROM articles_vendus WHERE no_utilisateur=?";
    private final String DELETE_BY_USER = "DELETE FROM articles_vendus WHERE no_utilisateur=?";

    private final String SELECT_BY_CATEGORIE = "SELECT * FROM articles_vendus WHERE no_categorie=?";
    private final String SELECT_BY_NOM = "SELECT * FROM articles_vendus WHERE nom_article LIKE ? ";


    private final String SEARCH = "SELECT u.no_utilisateur, u.pseudo, u.nom, u.prenom, u.email, " +
            "cat.no_categorie, cat.libelle, " +
            "a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente," +
            "FROM articles a " +
            "JOIN categories cat ON a.no_article = cat.no_categorie " +
            "JOIN utilisateurs u ON a.no_utilisateur = u.no_utilisateur " +
            "WHERE a.nom_article LIKE ? ";

    /**
     *
     * @param article
     * @throws SQLException
     */
    @Override
    public int insert(Articles article) throws SQLException {
        Connection cnx = ConectionProvider.getConnection();
        PreparedStatement stmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1,article.getNomArticles());
        stmt.setString(2,article.getDescription());
        stmt.setString(3,article.getDateDebutEncheres());
        stmt.setString(4,article.getDateFinEncheres());
        stmt.setInt(5,article.getMiseAprix());
        stmt.setInt(6,article.getUtilisateur().getNoUtilisateur());
        stmt.setInt(7,article.getCaterogie().getNoCategorie());

        stmt.executeUpdate();

        // retourne le numéro géréré par l'auto-incrémentation
        int noArticle = 0;
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()){
             noArticle =  rs.getInt(1);
        }
        return noArticle;
    }

    /**
     * Met à jour un article
     * @param article
     * @throws SQLException
     */
    @Override
    public void update(Articles article) throws SQLException {
        Connection cnx = ConectionProvider.getConnection();
        PreparedStatement stmt = cnx.prepareStatement(UPDATE);
        stmt.setString(1,article.getNomArticles());
        stmt.setString(2,article.getDescription());
        stmt.setString(3,article.getDateDebutEncheres());
        stmt.setString(4,article.getDateFinEncheres());
        stmt.setInt(5,article.getMiseAprix());
        stmt.setInt(6,article.getCaterogie().getNoCategorie());

        stmt.executeUpdate();
    }

    /**
     * Suprime un article par son ID
     * @param id
     * @throws SQLException
     */
    @Override
    public void delete(int id) throws SQLException {
        Connection cnx = ConectionProvider.getConnection();
        PreparedStatement stmt = cnx.prepareStatement(DELETE);
        stmt.setInt(1,id);
        stmt.executeUpdate();
    }

    /**
     * Retourne tout les articles
     * @return
     * @throws SQLException
     */
    @Override
    public List<Articles> findAll() throws SQLException {
        List<Articles> articlesList = new ArrayList<>();
        Connection cnx = ConectionProvider.getConnection();
        Statement stmt = cnx.createStatement();
        ResultSet rs = stmt.executeQuery(SELECTALL);
        while (rs.next()){
            articlesList.add(new Articles(
                    rs.getInt("no_article"),
                    rs.getString("nom_article"),
                    rs.getString("description"),
                    rs.getString("date_debut_encheres"),
                    rs.getString("date_fin_encheres"),
                    rs.getInt("prix_initial"),
                    "en cours",
                    new Categorie()
            ));
        }
        return articlesList;
    }

    /**
     * Retourne un article par son id
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Articles find(int id) throws SQLException {
        Articles article = new Articles();
        Connection cnx = ConectionProvider.getConnection();

        PreparedStatement stmt = cnx.prepareStatement(SELECT);
        stmt.setInt(1,id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            //TODO finir la recup avec utilisateur/ enchere / categorie
            article.setId(rs.getInt("no_article"));
            article.setNomArticles(rs.getString("nom_article"));
            article.setDescription(rs.getString("description"));
            article.setDateDebutEncheres(rs.getString("date_debut_encheres"));
            article.setDateFinEncheres(rs.getString("date_fin_encheres"));
            article.setMiseAprix(rs.getInt("prix_initial"));
            // recuperer l'utilisateur
            // recuperer la derneire enchere
            // recuperer la categries
        }

        return article;
    }

    @Override
    public ArrayList<Articles> findByCategorie(int noCat) throws BusinessException{
        ArrayList<Articles> articlesArrayList = new ArrayList<>();
        try(Connection connection = ConectionProvider.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(SELECT_BY_CATEGORIE);
            stmt.setInt(1, noCat);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Articles articles = new Articles();
                articles.setId(rs.getInt("no_article"));
                articles.setNomArticles(rs.getString("nom_article"));
                articles.setDescription(rs.getString("description"));
                articles.setDateDebutEncheres(rs.getString("date_debut_encheres"));
                articles.setDateFinEncheres(rs.getString("date_fin_encheres"));
                articles.setMiseAprix(rs.getInt("prix_initial"));
                articles.setCaterogie(new Categorie(rs.getInt("no_categorie"), rs.getString("libelle")));
                articlesArrayList.add(articles);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return articlesArrayList;
    }

    @Override
    public ArrayList<Articles> findByNomArticle(String nomArticle) throws BusinessException{
        ArrayList<Articles> articlesArrayList = new ArrayList<>();
        try(Connection connection = ConectionProvider.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(SELECT_BY_NOM);
            stmt.setString(1, "%"+nomArticle+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Articles articles = new Articles();
                articles.setId(rs.getInt("no_article"));
                articles.setNomArticles(rs.getString("nom_article"));
                articles.setDescription(rs.getString("description"));
                articles.setDateDebutEncheres(rs.getString("date_debut_encheres"));
                articles.setDateFinEncheres(rs.getString("date_fin_encheres"));
                articles.setMiseAprix(rs.getInt("prix_initial"));
                articles.setCaterogie(new Categorie(rs.getInt("no_categorie"), rs.getString("libelle")));
                articlesArrayList.add(articles);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return articlesArrayList;
    }

    @Override
    public ArrayList<Articles> findWithCond (String nom , int noCategorie, String condition) throws BusinessException{
        ArrayList<Articles> articles = new ArrayList<>();
        String sqlRequete = "";

        try (Connection connection = ConectionProvider.getConnection()){
            if (noCategorie!= -1){
                sqlRequete = SEARCH + "AND c.no_categorie=" + noCategorie + " " + condition;
            }else {
                sqlRequete = SEARCH + condition;
            }
            PreparedStatement stmt = connection.prepareStatement(sqlRequete);
            stmt.setString(1, "%" + nom + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                articles.add(createArticleEnchere(rs));
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }return articles;
    }


    /**
     * Retourne tous les articles pour un utilisateur donné
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public List<Articles> findAllByUser(int id) throws SQLException {
        List<Articles> articlesList = new ArrayList<>();
        Connection cnx = ConectionProvider.getConnection();
        PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_USER);
        stmt.setInt(1,id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            articlesList.add(new Articles(
                    rs.getInt("no_article"),
                    rs.getString("nom_article"),
                    rs.getString("description"),
                    rs.getString("date_debut_encheres"),
                    rs.getString("date_fin_encheres"),
                    rs.getInt("prix_initial"),
                    "en cours",
                    new Categorie()
            ));
        }
        return articlesList;
    }

    /**
     * Supprime
     * @param id
     * @throws SQLException
     */
    @Override
    public void deleteAllByID(int id) throws SQLException {
        Connection cnx = ConectionProvider.getConnection();
        PreparedStatement stmt = cnx.prepareStatement(DELETE_BY_USER);
        stmt.setInt(1,id);
        stmt.executeUpdate();
    }

    @Override
    public List<Articles> search() throws SQLException {
        List<Articles> articlesSearch = new ArrayList<>();
        Connection cnx = ConectionProvider.getConnection();
        PreparedStatement stmt = cnx.prepareStatement(SEARCH);

        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            articlesSearch.add(createArticleEnchere(rs));
        }
        return articlesSearch;
    }

    private Articles createArticleEnchere(ResultSet rs) throws SQLException {
        Articles articles = new Articles();
        Categorie categorie = new Categorie();
        Utilisateur utilisateur = new Utilisateur();
        Enchere enchere = new Enchere();

        utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
        utilisateur.setPseudo(rs.getString("pseudo"));
        utilisateur.setNom(rs.getString("nom"));
        utilisateur.setPrenom(rs.getString("prenom"));
        utilisateur.setEmail(rs.getString("email"));
        utilisateur.setTelephone(rs.getString("telephone"));
        utilisateur.setRue(rs.getString("rue"));
        utilisateur.setCodePostal(rs.getString("code_postal"));
        utilisateur.setVille(rs.getString("ville"));
        utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
        utilisateur.setCredit(rs.getInt("credit"));
        utilisateur.setAdministrateur(rs.getBoolean("administrateur"));

        categorie.setNoCategorie(rs.getInt("no_categorie"));
        categorie.setLibelle(rs.getString("libelle"));

        articles.setNomArticles(rs.getString("nom_article"));
        articles.setDescription(rs.getString("description"));
        articles.setDateDebutEncheres(rs.getString("date_debut_encheres"));
        articles.setDateFinEncheres(rs.getString("date_fin_encheres"));
        articles.setMiseAprix(rs.getInt("prix_initial"));

        articles.setId(rs.getInt("no_article"));
        articles.setUtilisateur(utilisateur);
        articles.setCaterogie(categorie);
        return articles;
    }
}
