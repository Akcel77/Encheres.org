package org.enchere.bll;

import org.enchere.bo.Articles;
import org.enchere.dal.ArticleDAO;
import org.enchere.dal.DAOFactory;

import java.sql.SQLException;
import java.util.List;

public class ArticleManager{

    private ArticleDAO articleDAO;

    public ArticleManager() {
        this.articleDAO = DAOFactory.getArticleDAO();
    }

    /**
     * Insere un articles
     * @param articles
     */
    public void insert(Articles articles) {
        articleDAO.insert(articles);
    }

    /**
     * Met à jour un article
     * @param article
     */
    public void update(Articles article) {
        articleDAO.update(article);
    }

    /**
     * Suprimme un article
     * @param articles
     */
    public void delete(Articles articles) {
        articleDAO.delete(articles);
    }

    /**
     * Retourne la liste de tout les articles
     * @return
     */
    public List<Articles> findAll() throws SQLException {
        return articleDAO.findAll();
    }

    /**
     * Retourne un article par l'id
     * @param id
     * @return
     */
    public Articles find(int id) throws SQLException {
        return articleDAO.find(id);
    }

}