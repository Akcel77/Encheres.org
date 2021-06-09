package org.enchere.dal.jdbc;

import org.enchere.bo.Articles;
import org.enchere.bo.Categorie;
import org.enchere.dal.ArticleDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleImpl implements ArticleDAO {
    //TODO voir les requetes
    private final String SELECTALL ="" +
            "SELECT * " +
            "FROM articles_vendus";
    private final String SELECT ="" +
            "SELECT * " +
            "FROM articles " +
            "where id =?";
    private final String UPDATE ="" +
            "UPDATE articles " +
            "SET(x,x,x) VALUES(?,?,?,?,?)";
    private final String DELETE ="" +
            "DELETE FROM articles " +
            "WHERE id =?";


    @Override
    public void insert(Articles articles) {

    }

    @Override
    public void update(Articles article) {

    }

    @Override
    public void delete(Articles articles) {

    }

    @Override
    public List<Articles> findAll() throws SQLException {
        List<Articles> articlesList = new ArrayList<>();
        Connection cnx = null;
        cnx = ConectionProvider.getConnection();
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
