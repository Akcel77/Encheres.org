package org.enchere.bll;

import org.enchere.bo.Articles;
import org.enchere.dal.ArticleDAO;
import org.enchere.dal.DAOFactory;
import org.enchere.dal.jdbc.ArticleImpl;

import java.sql.SQLException;
import java.util.List;

public class ArticleManager{

    private static ArticleDAO articleDAO;

    public ArticleManager() {
        ArticleManager.articleDAO = DAOFactory.getArticleDAO();
    }

    /**
     * Insere un articles
     * @param article
     */
    public static void insert(Articles article) throws SQLException {
        System.out.println("test2");
        System.out.println(article);
        articleDAO.insert(article);
        System.out.println("test2.5");
    }

    /**
     * Met à jour un article
     * @param article
     */
    public static void update(Articles article) throws SQLException {
        articleDAO.update(article);
    }


    /**
     * Suprimme un article
     * @param id
     */
    public static void delete(int id) throws SQLException {
        articleDAO.delete(id);
    }

    /**
     * Retourne la liste de tout les articles
     * @return
     */
    public static List<Articles> findAll() throws SQLException {
        return articleDAO.findAll();
    }

    /**
     * Retourne un article par l'id
     * @param id
     * @return
     */
    public static Articles find(int id) throws SQLException {
        return articleDAO.find(id);
    }

}