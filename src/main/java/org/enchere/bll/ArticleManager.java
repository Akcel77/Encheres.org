package org.enchere.bll;

import org.enchere.bo.Articles;
import org.enchere.dal.ArticleDAO;
import org.enchere.dal.DAOFactory;
import org.enchere.dal.jdbc.ArticleImpl;
import org.enchere.outils.BusinessException;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ArticleManager{

    private static ArticleDAO articleDAO;

    static{
        ArticleManager.articleDAO = DAOFactory.getArticleDAO();
    }

    /**
     * Insère un article
     * @param article
     */
    public static int insert(Articles article) throws SQLException {
        return articleDAO.insert(article);
    }

    /**
     * Met à jour un article
     * @param article
     */
    public static void update(Articles article) throws SQLException {
        articleDAO.update(article);
    }

    /**
     * Supprime un article
     * @param id
     */
    public static void delete(int id) throws SQLException {
        articleDAO.delete(id);
    }

    /**
     * Retourne la liste de tous les articles
     * @return
     */
    public static List<Articles> findAll() throws SQLException, BusinessException, ParseException {
        return articleDAO.findAll();
    }

    /**
     * Retourne un article par l'id
     * @param id
     * @return
     */
    public static Articles find(int id) throws SQLException, BusinessException, ParseException {
        return articleDAO.find(id);
    }


    /**
     *  Retourne tous les articles publiés par un utilisateur
     * @param id
     * @return
     * @throws SQLException
     */
    public static List<Articles> findAllByUser(int id) throws SQLException{
        return articleDAO.findAllByUser(id);
    }
}