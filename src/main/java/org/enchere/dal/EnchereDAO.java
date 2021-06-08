package org.enchere.dal;

import org.enchere.bo.Enchere;
import org.enchere.outils.BusinessException;

public interface EnchereDAO {
    public void create(Enchere enchere)throws BusinessException;
    public void update(Enchere enchere)throws BusinessException;
    public void delete (Enchere enchere) throws BusinessException;

}
