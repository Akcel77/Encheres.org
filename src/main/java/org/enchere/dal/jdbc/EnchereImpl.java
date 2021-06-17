package org.enchere.dal.jdbc;


import org.enchere.bo.Enchere;
import org.enchere.dal.EnchereDAO;
import org.enchere.outils.BusinessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnchereImpl implements EnchereDAO {
        private static final String INSERT = "INSERT INTO encheres(date_enchere, montant_enchere, " +
                "no_article, no_utilisateur) VALUES(?,?,?,?)";
        private static final String UPDATE = "UPDATE encheres SET date_enchere=?, montant_enchere=?, no_article=?, " +
                "no_utilisateur=? WHERE no_enchere=?";
        private static final String DELETE = "DELETE FROM encheres WHERE no_enchere=?";
        private static final String FIND_ALL= "SELECT * FROM encheres";
        private static final String FIND_BY_ID = "SELECT * FROM encheres WHERE no_enchere=?";
        private static final String FIND_BY_USER_ID = "SELECT * FROM encheres WHERE no_article=?";

        /**
         * Insérer une nouvelle enchère
         * @param enchere
         */

    @Override
    public void create(Enchere enchere)throws BusinessException {
        if (enchere != null) {
            try {
                Connection cnx = ConectionProvider.getConnection();
                PreparedStatement stmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

                stmt.setString(1, enchere.getDate_enchere());
                stmt.setInt(2, enchere.getMontant_enchere());
                stmt.setInt(3, enchere.getNo_article());
                stmt.setInt(4, enchere.getNo_utilisateur());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    enchere.setNo_enchere(rs.getInt(1));
                }
                cnx.close();
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();

            }
        }
    }
    @Override
    public List<Enchere> findAllByArticleId(int id) {
        List<Enchere> listEnchere = new ArrayList<>();
        try {
            Connection cnx = ConectionProvider.getConnection();
            PreparedStatement stmt = cnx.prepareStatement(FIND_BY_USER_ID);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                listEnchere.add(new Enchere(
                        rs.getInt("no_enchere"),
                        rs.getString("date_enchere"),
                        rs.getInt("montant_enchere"),
                        rs.getInt("no_article"),
                        rs.getInt("no_utilisateur")
                ));
            }
            cnx.close();
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return listEnchere;
    }
}