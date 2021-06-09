package org.enchere.dal.jdbc;

import org.enchere.bo.Articles;
import org.enchere.bo.Categorie;
import org.enchere.dal.ArticleDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleImpl implements ArticleDAO {

    private final String SELECTALL  = "SELECT * FROM articles_vendus";
    private final String SELECT = "SELECT * FROM articles_vendus WHERE no_article = ?";
    private final String UPDATE = "UPDATE articles_vendus SET nom_article=?, description=?, date_debut_encheres =?, date_fin_encheres=?, prix_initial=?,prix_vente=?,no_utilisateur=?,no_categorie=? WHERE no_article=?";
    private final String DELETE = "DELETE FROM articles_vendus WHERE no_article =?";
    private final String INSERT = "INSERT INTO articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES(?,?,?,?,?,?,?,?) ";
    private final String SELECT_BY_USER = "SELECT * FROM articles_vendus WHERE no_utilisateur=?";
    private final String DELETE_BY_USER = "DELETE FROM articles_vendus WHERE no_utilisateur=?";

    /**
     * Insere un article
     * @param article
     * @throws SQLException
     */
    @Override
    public void insert(Articles article) throws SQLException {
        Connection cnx = ConectionProvider.getConnection();
        PreparedStatement stmt = cnx.prepareStatement(INSERT);
        stmt.setString(1,article.getNomArticles());
        stmt.setString(2,article.getDescription());
        stmt.setString(3,article.getDateDebutEncheres());
        stmt.setString(4,article.getDateFinEncheres());
        stmt.setInt(5,article.getMiseAprix());
        stmt.setInt(6,article.getUtilisateur().getNoUtilisateur());
        stmt.setInt(7,article.getCaterogie().getNoCategorie());

        stmt.executeUpdate();
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
        Articles article = null;
        Connection cnx = ConectionProvider.getConnection();

        PreparedStatement stmt = cnx.prepareStatement(SELECT);
        stmt.setInt(1,id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){

        }

        return article;
    }

    /**
     * Retourne tout les articles pour un utlisateur données
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

}
