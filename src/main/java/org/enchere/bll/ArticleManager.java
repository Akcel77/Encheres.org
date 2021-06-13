package org.enchere.bll;

import org.enchere.bo.Articles;
import org.enchere.dal.ArticleDAO;
import org.enchere.dal.DAOFactory;
import org.enchere.dal.jdbc.ArticleImpl;
import org.enchere.outils.BusinessException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleManager{

    private static ArticleDAO articleDAO;

    static{
        ArticleManager.articleDAO = DAOFactory.getArticleDAO();
    }

    /**
     * Insere un articles
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
     * Suprimme un article
     * @param id
     */
    public static void delete(int id) throws SQLException {
        articleDAO.delete(id);
    }

    /**
     * Retourne la liste de tous les articles
     * @return
     */
    public static List<Articles> findAll() throws SQLException, BusinessException {
        return articleDAO.findAll();
    }

    /**
     * Retourne un article par l'id
     * @param id
     * @return
     */
    public static Articles find(int id) throws SQLException, BusinessException {
        return articleDAO.find(id);
    }

    public static List<Articles> search() throws SQLException{
        return articleDAO.search();
    }

    public static ArrayList<Articles> findByCategorie(int noCat) throws BusinessException{
        return articleDAO.findByCategorie(noCat);
    }
    public static ArrayList<Articles> findByNomArticle(String nomArticle) throws BusinessException{
        return articleDAO.findByNomArticle(nomArticle);
    }
    public static ArrayList<Articles> findWithCond (String nom , int noCategorie, String condition) throws BusinessException{
        System.out.println(nom + " " + noCategorie + " "  + condition);
        return articleDAO.findWithCond(nom, noCategorie, condition);
    }

    public static List<Articles> findAllByUser(int id) throws SQLException{
        return articleDAO.findAllByUser(id);
    }
}