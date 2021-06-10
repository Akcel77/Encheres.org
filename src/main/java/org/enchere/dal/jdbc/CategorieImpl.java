package org.enchere.dal.jdbc;

import org.enchere.bo.Categorie;
import org.enchere.dal.CategorieDAO;
import org.enchere.outils.BusinessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieImpl implements CategorieDAO {

    private final String INSERT_CATEGORIE = "INSERT INTO categories(libelle) VALUES(?)";
    private final String SELECT_ALL = "SELECT * FROM categories";
    private final String SELECT_CATEGORIE = "SELECT * FROM categories WHERE no_categorie=?";
    private final String UPDATE_LIBELLE = "UPDATE categories SET libelle=? WHERE no_categorie=?";
    private final String DELETE = "DELETE FROM categories WHERE no_categorie=?";


    @Override
    public void insert(Categorie c) throws BusinessException {
        Connection cnx = null;
        PreparedStatement stmt = null;
        try {
            cnx = ConectionProvider.getConnection();
            stmt = cnx.prepareStatement(INSERT_CATEGORIE, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, (String) c.getLibelle());
            int nbRows = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                c.setNoCategorie(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if(cnx!=null) {
                    cnx.close();
                }
            } catch (SQLException e) {
                throw new BusinessException();
            }

        }
    }


    @Override
    public void update(Categorie c) throws BusinessException {
        Connection cnx = null;
        PreparedStatement stmt = null;
        try {
            cnx = ConectionProvider.getConnection();
            stmt = cnx.prepareStatement(UPDATE_LIBELLE);

            stmt.setInt(1, c.getNoCategorie());
            stmt.setString(2, (String) c.getLibelle());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (cnx!=null) {
                    cnx.close();
                }
            } catch (SQLException e) {
                throw new BusinessException();
            }

        }
    }

    @Override
    public void delete(int id) throws BusinessException {
        Connection cnx = null;
        PreparedStatement stmt = null;
        try {
            cnx = ConectionProvider.getConnection();
            stmt = cnx.prepareStatement(DELETE);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (cnx != null) {
                    cnx.close();
                }
            } catch (SQLException e) {
                throw new BusinessException();
            }
        }
    }


    //Renvoie une liste de categories selon numéro Id
    @Override
    public Categorie selectById(int noCategorie) throws BusinessException {
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Categorie categorie = null;
        try {
            cnx = ConectionProvider.getConnection();
            stmt = cnx.prepareStatement(SELECT_CATEGORIE);
            stmt.setInt(1, noCategorie);
            rs = stmt.executeQuery();

            if(rs.next()){
                categorie = new Categorie(rs.getInt(1),
                        rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (cnx!=null) {
                    cnx.close();
                }
            } catch (SQLException e) {
                throw new BusinessException();
            }

        }
        return categorie;
    }

    //Renvoie une liste de toutes les catégories
    @Override
    public List<Categorie> selectAll() throws BusinessException {
        Connection cnx = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Categorie> categories = new ArrayList<Categorie>();
        try {
            cnx = ConectionProvider.getConnection();
            stmt = cnx.createStatement();
            rs = stmt.executeQuery(SELECT_ALL);
            Categorie c = null;
            while(rs.next()){
                c = new Categorie(rs.getInt(1), rs.getString(2));
                categories.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (cnx!=null) {
                    cnx.close();
                }
            } catch (SQLException e) {
                throw new BusinessException();
            }

        }
        return categories;



    }

}