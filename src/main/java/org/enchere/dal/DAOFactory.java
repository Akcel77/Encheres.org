package org.enchere.dal;

import org.enchere.dal.jdbc.*;

public class DAOFactory {

    public static UtilisateurDAO getUtilisateurDAO() {
        return new UtilisateurImpl();
    }
    public static ArticleDAO getArticleDAO() {
        return new ArticleImpl();
    }
    public static EnchereDAO getEnchereDAO () {
        return new EnchereImpl();
    }
    public static CategorieDAO getCategorieDAO() { return new CategorieImpl(); }
    public static RetraitDAO getRetraitDAO() { return new RetraitImpl(); }
    }

