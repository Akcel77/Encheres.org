package org.enchere.dal.jdbc;

import com.sun.org.glassfish.gmbal.ManagedAttribute;
import org.enchere.bll.CategorieManager;
import org.enchere.bll.EnchereManager;
import org.enchere.bll.RetraitManager;
import org.enchere.bll.UtilisateurManager;
import org.enchere.bo.Articles;
import org.enchere.bo.Categorie;
import org.enchere.bo.Enchere;
import org.enchere.bo.Utilisateur;
import org.enchere.dal.ArticleDAO;
import org.enchere.outils.BusinessException;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ArticleImpl implements ArticleDAO {

    private final String SELECTALL  = "SELECT * FROM articles_vendus";
    private final String SELECT = "SELECT * FROM articles_vendus WHERE no_article = ?";
    private final String UPDATE = "UPDATE articles_vendus SET nom_article=?, description=?, date_debut_encheres =?, date_fin_encheres=?, prix_initial=?,no_categorie=? WHERE no_article=?";
    private final String DELETE = "DELETE FROM articles_vendus WHERE no_article =?";
    private final String INSERT = "INSERT INTO articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES(?,?,?,?,?,?,?) ";
    private final String SELECT_BY_USER = "SELECT * FROM articles_vendus WHERE no_utilisateur=?";
    private DateFormat dateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat dateFormatHour = new SimpleDateFormat("HH:mm:ss");

    /**
     * Insère un article
     * @param article
     * @throws SQLException
     */
    @Override
    public int insert(Articles article) throws SQLException {
        Connection cnx = ConectionProvider.getConnection();
        PreparedStatement stmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1,article.getNomArticles());
        stmt.setString(2,article.getDescription());
        stmt.setString(3,article.getDateDebutEncheres() + " " + article.getHeureDebut() + ":00");
        stmt.setString(4,article.getDateFinEncheres() + " " + article.getHeureFin() + ":00");
        stmt.setInt(5,article.getMiseAprix());
        stmt.setInt(6,article.getUtilisateur().getNoUtilisateur());
        stmt.setInt(7,article.getCaterogie().getNoCategorie());
        stmt.executeUpdate();

        // retourne le numéro généré par l'auto-incrémentation
        int noArticle = 0;
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()){
            noArticle =  rs.getInt(1);
        }
        cnx.close();
        stmt.close();
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
        stmt.setString(3,article.getDateDebutEncheres() + " " + article.getHeureDebut());
        stmt.setString(4,article.getDateFinEncheres() + " " + article.getHeureFin());
        stmt.setInt(5,article.getMiseAprix());
        stmt.setInt(6,article.getCaterogie().getNoCategorie());
        stmt.setInt(7,article.getId());
        stmt.executeUpdate();
        cnx.close();
        stmt.close();
    }

    /**
     * Supprime un article par son ID
     * @param id
     * @throws SQLException
     */
    @Override
    public void delete(int id) throws SQLException {
        Connection cnx = ConectionProvider.getConnection();
        PreparedStatement stmt = cnx.prepareStatement(DELETE);
        stmt.setInt(1,id);
        stmt.executeUpdate();
        cnx.close();
        stmt.close();
    }

    /**
     * Retourne une liste de tous les articles
     * @return
     * @throws SQLException
     */
    @Override
    public List<Articles> findAll() throws SQLException, BusinessException {
        int idUser = 0;
        int idCategorie = 0;
        int idArticle = 0;
        List<Articles> articles = new ArrayList<>();
        Connection cnx = ConectionProvider.getConnection();
        PreparedStatement stmt = cnx.prepareStatement(SELECTALL);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            Articles article = new Articles();
            article.setId(rs.getInt("no_article"));
            article.setNomArticles(rs.getString("nom_article"));
            article.setDescription(rs.getString("description"));
            article.setDateDebutEncheres(dateFormatDay.format(java.sql.Timestamp.valueOf(rs.getString("date_debut_encheres"))));
            article.setDateFinEncheres(dateFormatDay.format(java.sql.Timestamp.valueOf(rs.getString("date_fin_encheres"))));
            article.setHeureDebut(dateFormatHour.format(java.sql.Timestamp.valueOf(rs.getString("date_debut_encheres"))));
            article.setHeureFin(dateFormatHour.format(java.sql.Timestamp.valueOf(rs.getString("date_fin_encheres"))));
            article.setMiseAprix(rs.getInt("prix_initial"));
            idUser = rs.getInt("no_utilisateur");
            idCategorie = rs.getInt("no_categorie");
            idArticle = rs.getInt("no_article");
            article.setUtilisateur(UtilisateurManager.selectUserByID(idUser));
            article.setCaterogie(CategorieManager.selectById(idCategorie));
            article.setEncheres(EnchereManager.findAllByArticleId(idArticle));
            article.setRetrait(RetraitManager.selectRetraitByArticleId(idArticle));
            articles.add(article);
        }
        cnx.close();
        stmt.close();
        return articles;
    }

    /**
     * Retourne un article par son id
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Articles find(int id) throws SQLException, BusinessException{
        int idUser = 0;
        int idCategorie = 0;
        int idArticle = 0;
        Articles article = new Articles();
        Connection cnx = ConectionProvider.getConnection();
        PreparedStatement stmt = cnx.prepareStatement(SELECT);
        stmt.setInt(1,id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            article.setId(rs.getInt("no_article"));
            article.setNomArticles(rs.getString("nom_article"));
            article.setDescription(rs.getString("description"));
            article.setDateDebutEncheres(dateFormatDay.format(java.sql.Timestamp.valueOf(rs.getString("date_debut_encheres"))));
            article.setDateFinEncheres(dateFormatDay.format(java.sql.Timestamp.valueOf(rs.getString("date_fin_encheres"))));
            article.setHeureDebut(dateFormatHour.format(java.sql.Timestamp.valueOf(rs.getString("date_debut_encheres"))));
            article.setHeureFin(dateFormatHour.format(java.sql.Timestamp.valueOf(rs.getString("date_fin_encheres"))));
            article.setMiseAprix(rs.getInt("prix_initial"));
            idUser = rs.getInt("no_utilisateur");
            idCategorie = rs.getInt("no_categorie");
            idArticle = rs.getInt("no_article");
        }
        article.setUtilisateur(UtilisateurManager.selectUserByID(idUser));
        article.setCaterogie(CategorieManager.selectById(idCategorie));
        article.setEncheres(EnchereManager.findAllByArticleId(idArticle));
        article.setRetrait(RetraitManager.selectRetraitByArticleId(idArticle));
        cnx.close();
        stmt.close();
        return article;
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
        cnx.close();
        stmt.close();
        return articlesList;
    }
}
