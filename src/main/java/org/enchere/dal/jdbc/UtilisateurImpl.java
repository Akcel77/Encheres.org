package org.enchere.dal.jdbc;

import org.enchere.bo.Articles;
import org.enchere.bo.Utilisateur;
import org.enchere.dal.CodeErreurDAL;
import org.enchere.dal.UtilisateurDAO;
import org.enchere.outils.BusinessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurImpl implements UtilisateurDAO {
//test

    // REQUETE SQL INSERT
    private static final String INSERT = "INSERT INTO utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe,credit, administrateur) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

    // REQUETE SQL UPDATE
    private static final String UPDATE ="UPDATE utilisateurs " +
            "SET nom=?, " +
            "prenom=?, " +
            "email=?, " +
            "telephone=?, " +
            "rue=?, " +
            "code_postal=?, " +
            "ville=?, " +
            "mot_de_passe=?, " +
            "credit=?, " +
            "administrateur=? " +
            "WHERE no_utilisateur=?";
    //TODO : UPDATE PASSWORD
    private static final String UPDATE_PSW = "UPDATE utilisateurs " +
            "SET mot_de_passe=? " +
            "WHERE email=?";
    //TODO : UPDATE CREDIT
    private static final String UPDATE_CREDIT = "UPDATE utilisateurs " +
            "SET credit=? " +
            "WHERE no_utilisateur =?";
    // REQUETE SQL SELECT
    private static final String ALL_PSEUDO = "SELECT pseudo " +
            "FROM utilisateurs ";

    private static final String ALL_ARTICLE = "SELECT * " +
            "FROM articles_vendus " +
            "WHERE no_utilisateur=?";
    private static final String ALL_USER = "SELECT * " +
            "FROM utilisateurs";
    private static final String SEARCH_PSEUDO = "SELECT * " +
            "FROM utilisateurs " +
            "WHERE pseudo=?";
    private static final String SEARCH_ID = "SELECT * " +
            "FROM utilisateurs " +
            "WHERE no_utilisateur=?";
    private static final String SEARCH_MAIL = "SELECT * " +
            "FROM utilisateurs " +
            "WHERE email=?";
    // REQUETE SQL DELETE
    private static final String DELETE = "DELETE " +
            "FROM utilisateurs " +
            "WHERE no_utilisateur=?";




    private static ConectionProvider connection = new ConectionProvider();
    private static Utilisateur utilisateur = new Utilisateur();

    /**
     * Creation d'un nouvel utilisateur
     * @param utilisateur
     * @throws BusinessException
     */
    @Override
    public Utilisateur insert(Utilisateur utilisateur) throws BusinessException{
        if(utilisateur == null){
            BusinessException businessException = new BusinessException();
            throw  businessException;
        }
        try(Connection connection = ConectionProvider.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, utilisateur.getPseudo());
            stmt.setString(2, utilisateur.getNom());
            stmt.setString(3, utilisateur.getPrenom());
            stmt.setString(4, utilisateur.getEmail());
            stmt.setString(5, utilisateur.getTelephone());
            stmt.setString(6, utilisateur.getRue());
            stmt.setString(7, utilisateur.getCodePostal());
            stmt.setString(8, utilisateur.getVille());
            stmt.setString(9, utilisateur.getMotDePasse());
            stmt.setInt(10, utilisateur.getCredit());
            stmt.setBoolean(11, utilisateur.isAdministrateur());

            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();

            if (resultSet.next()){
                utilisateur.setNoUtilisateur(resultSet.getInt(1));
            }
            System.out.println("DAL " + utilisateur);
            stmt.close();


        }catch (SQLException e){
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodeErreurDAL.ERREUR_UTILISATEUR_INSERT);

           throw businessException;
        }

        return utilisateur;

    }

    /**
     * Mise a jour d'un nouvel utilisateur
     * @param utilisateur
     * @throws BusinessException
     */
    @Override
    public void update (Utilisateur utilisateur) throws BusinessException{
        try(Connection connection = ConectionProvider.getConnection()){
            PreparedStatement  stmt = connection.prepareStatement(UPDATE);

            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getTelephone());
            stmt.setString(5, utilisateur.getRue());
            stmt.setString(6, utilisateur.getCodePostal());
            stmt.setString(7, utilisateur.getVille());
            stmt.setString(8, utilisateur.getMotDePasse());
            stmt.setInt(9, utilisateur.getCredit());
            stmt.setBoolean(10, utilisateur.isAdministrateur());
            stmt.setInt(11, utilisateur.getNoUtilisateur());

            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
//            BusinessException businessException = new BusinessException(e.getMessage(), e);
//            //TODO : ERREUR LOG
//
//            throw businessException;
        }
    }

    /**
     * Suppression d'un utilisateur par son id (no)
     * @param no
     * @throws BusinessException
     */
    @Override
    public void delete (int no) throws BusinessException{
        try(Connection connection = ConectionProvider.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(DELETE);
            stmt.setInt(1, no);
            //TODO : Suppression des articles utilisateur

            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
//            BusinessException businessException = new BusinessException(e.getMessage(), e);
//            //TODO : ERREUR LOG
//
//            throw businessException;
        }
    }

    /**
     * Recuperation d'un Utilisateur par son pseudo
     * @param pseudo
     * @return
     * @throws BusinessException
     */
    @Override
    public Utilisateur getByPseudo(String pseudo) throws BusinessException{
        Utilisateur utilisateur = null;
        try(Connection connection = ConectionProvider.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(SEARCH_PSEUDO);

            stmt.setString(1, pseudo);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                utilisateur = userBuilder(rs);

            }
        }catch (SQLException e){
            e.printStackTrace();
//            BusinessException businessException = new BusinessException(e.getMessage(), e);
//            //TODO : ERREUR LOG
//
//            throw businessException;
        }
        return utilisateur;
    }

    /**
     * Recherche d'un utilisateur par son email
     * @param email
     * @return
     * @throws BusinessException
     */
    @Override
    public Utilisateur getByMail(String email) throws BusinessException{
        Utilisateur utilisateur = null;
        try(Connection connection = ConectionProvider.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(SEARCH_MAIL);

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                utilisateur = userBuilder(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
//            BusinessException businessException = new BusinessException(e.getMessage(), e);
//            //TODO : ERREUR LOG
//
//            throw businessException;
        }
        return utilisateur;
    }


    /**
     * Renvoie la liste de tous les utlisateurs
     * @return
     * @throws BusinessException
     */
    @Override
    public List<Utilisateur> getAllUser()throws BusinessException{
        List<Utilisateur> utilisateurList = new ArrayList<>();
        Utilisateur utilisateur = null;

        try(Connection connection = ConectionProvider.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(ALL_USER);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                utilisateur = userBuilder(rs);
                utilisateurList.add(utilisateur);
            }
        }catch (SQLException e){
            e.printStackTrace();
//            BusinessException businessException = new BusinessException(e.getMessage(), e);
//            //TODO : ERREUR LOG
//
//            throw businessException;
        }
        return utilisateurList;
    }

    /**
     * Recuperation de tout les pseudo (pour lors de la creation verification si un pseudo est deja present ou non)
     * @return
     * @throws BusinessException
     */
    @Override
    public List<String> getAllPseudo() throws BusinessException {
        List<String> listPseudo = new ArrayList<>();

        try (Connection connection = ConectionProvider.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(ALL_PSEUDO);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                listPseudo.add(rs.getString("pseudo"));

            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return listPseudo;
    }

    /**
     * Cherche un id utilisateur
     * @param id
     * @return
     * @throws BusinessException
     */
    @Override
    public Utilisateur searchByID(int id) throws BusinessException {
        Utilisateur utilisateur = new Utilisateur();
        try (Connection connection = ConectionProvider.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(SEARCH_ID);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                userBuilder(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodeErreurDAL.ERREUR_SEARCH_BY_ID);
            throw businessException;
        }
        return utilisateur;
    }


    public List<Articles> getAllArticle(Utilisateur utilisateur) throws BusinessException {
        List<Articles> articlesList = new ArrayList<>();
        try(Connection connection = ConectionProvider.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(ALL_USER);
            //TODO
//            stmt.setInt(1, utilisateur.getNoUtilisateur());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Articles articles = new Articles();
                articles.setId(rs.getInt("no_article"));
                articles.setNomArticles(rs.getString("description"));
                articles.setDateDebutEncheres(rs.getString("date_debut_encheres"));
                articles.setDateFinEncheres(rs.getString("date_fin_encheres"));
                articles.setMiseAprix(rs.getInt("prix_initial"));
                articles.setEtatDeVente(rs.getString("prix_vente"));
                // Private Utilisateur utilisateur
                //
                // Private Retrait lieuRetrait

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return articlesList;

    }

    /**
     * Function pour eviter la redondance
     * @param rs
     * @return
     * @throws SQLException
     */
    public Utilisateur userBuilder(ResultSet rs ) throws SQLException{
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNoUtilisateur( rs.getInt("no_utilisateur"));
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

        return utilisateur;
    }
}
