package org.enchere.dal.jdbc;


import org.enchere.bo.Enchere;
import org.enchere.dal.EnchereDAO;
import org.enchere.outils.BusinessException;

import java.sql.*;

public class EnchereImpl implements EnchereDAO {
        private static final String INSERT = "INSERT INTO ENCHERES(date_enchere, montant_enchere, " +
                "no_article, no_utilisateur) VALUES(?,?,?,?);";
        private static final String UPDATE = "UPDATE ENCHERES SET date_enchere=?, montant_enchere=?, \" +\n" +
                "                \"no_article=?, no_utilisateur=? WHERE no_enchere=?";

        private static final String DELETE = "SELECT * FROM enchere WHERE no_enchere=?";

        /**
         * Insérer une nouvelle enchère
         * @param enchere
         */

        @Override
        public void create(Enchere enchere)throws BusinessException {
                if (enchere == null) {
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
                        } catch (SQLException throwables) {
                                throwables.printStackTrace();
                        }

                }
        }

        @Override
        public void update (Enchere enchere) throws BusinessException{
                try {
                        Connection cnx = ConectionProvider.getConnection();
                        PreparedStatement stmt = cnx.prepareStatement(UPDATE);

                        stmt.setString(1, enchere.getDate_enchere());
                        stmt.setInt(2, enchere.getMontant_enchere());
                        stmt.setInt(3, enchere.getNo_article());
                        stmt.setInt(4, enchere.getNo_utilisateur());
                        stmt.executeUpdate();
                } catch (SQLException throwables) {
                        throwables.printStackTrace();
                }
        }

        @Override
        public void delete (Enchere enchere) throws BusinessException{
                try {
                        Connection cnx = ConectionProvider.getConnection();
                        PreparedStatement stmt = cnx.prepareStatement(DELETE);

                        stmt.executeUpdate();
                } catch (SQLException throwables) {
                        throwables.printStackTrace();
                }
        }


}

