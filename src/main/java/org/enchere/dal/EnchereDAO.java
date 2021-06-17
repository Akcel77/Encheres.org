package org.enchere.dal;

import org.enchere.bo.Enchere;
import org.enchere.outils.BusinessException;

import java.sql.SQLException;
import java.util.List;

public interface EnchereDAO {
    public void create(Enchere enchere)throws BusinessException;
    List<Enchere> findAllByArticleId(int id);
}
