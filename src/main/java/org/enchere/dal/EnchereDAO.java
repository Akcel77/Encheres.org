package org.enchere.dal;

import org.enchere.bo.Articles;
import org.enchere.bo.Enchere;
import org.enchere.outils.BusinessException;

import java.sql.SQLException;
import java.util.List;

public interface EnchereDAO {
    public void create(Enchere enchere)throws BusinessException;
    public void update(Enchere enchere)throws BusinessException;
    public void delete (Enchere enchere) throws BusinessException;
    public List<Enchere> findAll() throws SQLException;
    public Enchere find(int no_enchere) throws SQLException;
}
