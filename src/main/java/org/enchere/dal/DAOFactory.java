package org.enchere.dal;

import org.enchere.dal.jdbc.ArticleImpl;
import org.enchere.dal.jdbc.EnchereImpl;
import org.enchere.dal.jdbc.UtilisateurImpl;

public class DAOFactory {

    public static UtilisateurDAO getUtilisateurDAO() {
        return new UtilisateurImpl();
    }

    /**
     * Retourne une instance de ArticleDAO
     */
    public static ArticleDAO getArticleDAO() {
        return new ArticleImpl();
    }

    public static EnchereDAO createEnchereDAO () {
        return new EnchereImpl();
    }

}

