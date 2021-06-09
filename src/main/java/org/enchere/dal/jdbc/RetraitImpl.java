package org.enchere.dal.jdbc;

import org.enchere.bo.Retrait;
import org.enchere.dal.RetraitDAO;
import org.enchere.outils.BusinessException;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RetraitImpl implements RetraitDAO {
    //REQUETE SQL INSERT
    private static final String INSERT = "INSERT INTO retraits (rue, code_postal, ville " +
            "VALUES (?,?,?)";
    //REQUETE SQL SELECT
    private static final String GET_BY_ID = "SELECT * " +
            "FROM retraits " +
            "WHERE no_retrait=?";
    private static final String ALL_RETRAIT = "SELECT * " +
            "FROM retraits";
    //REQUETE SQL UPDATE
    private static final String UPDATE = "UPDATE retraits " +
            "SET rue=?," +
            "code_postal=?," +
            "ville=? " +
            "WHERE no_retrait=?";
    //REQUETE SQL DELETE
    private static final String DELETE = "DELETE retraits " +
            "WHERE no_retrait=?";


    /**
     * Insert sql pour retrait
     * @param retrait
     * @return
     * @throws BusinessException
     */
    @Override
    public Retrait insert(Retrait retrait) throws BusinessException {
        if(retrait == null) {
            BusinessException businessException = new BusinessException();
            //TODO Code erreur businessException

            throw businessException;
        }
        try (Connection connection = ConectionProvider.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, retrait.getRue());
            stmt.setString(2, retrait.getCode_postal());
            stmt.setString(3, retrait.getVille());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()){
                retrait.setId(rs.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            //TODO : BusinessException CODE ERROR
            throw businessException;
        }
        return retrait;
    }


    /**
     * Recupere un retrait en fonction de son id et return ce retrait
     * @param id
     * @return
     * @throws BusinessException
     */
    @Override
    public Retrait getById(int id) throws BusinessException {
        Retrait retrait = null;

        try(Connection connection = ConectionProvider.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                retrait = new Retrait();
                retrait.setId(rs.getInt("no_retrait"));
                retrait.setRue(rs.getString("rue"));
                retrait.setCode_postal(rs.getString("code_postal"));
                retrait.setVille(rs.getString("ville"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            //TODO : BusinessException CODE ERROR

            throw businessException;
        }
        return retrait;
    }

    /**
     * Recupere la liste de tous les retraits et la retourne
     * @return
     * @throws BusinessException
     */
    @Override
    public List<Retrait> getAll() throws BusinessException {
        List<Retrait> retraitList = new ArrayList<>();
        try(Connection connection = ConectionProvider.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(ALL_RETRAIT);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Retrait retrait = new Retrait();
                retrait.setId(rs.getInt("no_retrait"));
                retrait.setRue(rs.getString("rue"));
                retrait.setCode_postal(rs.getString("code_postal"));
                retrait.setVille(rs.getString("ville"));
                retraitList.add(retrait);
            }
        }catch (SQLException e){
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            //TODO : BusinessException CODE ERROR
            throw businessException;
        }

        return retraitList;
    }

    /**
     * Update un article en fonction du no_retrait
     * @param retrait
     * @throws BusinessException
     */
    @Override
    public void update(Retrait retrait) throws BusinessException {

        try(Connection connection = ConectionProvider.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(UPDATE);
            stmt.setString(1, retrait.getRue());
            stmt.setString(2, retrait.getCode_postal());
            stmt.setString(3, retrait.getVille());
            stmt.setInt(4, retrait.getId());

            stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            //TODO : BusinessException CODE ERROR
            throw businessException;
        }


    }

    /**
     * Delete un retrait en fonction du no_retrait
     * @param id
     * @throws BusinessException
     */
    @Override
    public void delete(int id) throws BusinessException {
        try (Connection connection = ConectionProvider.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(DELETE);
            stmt.setInt(1, id);

            Retrait retrait = getById(id);
            //TODO : A FINIR QUAND article.DAO est ope et Article.bo aussi
                // Attribut bo Articles
            //              Utilisateur utilisateur
                //          Retrait lieuRetrait
            //LISTE <Article> for each loop

        }catch (SQLException e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            //TODO : BusinessException CODE ERROR
            throw businessException;
        }
    }
}
