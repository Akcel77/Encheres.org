package org.enchere.dal;

import org.enchere.bo.Retrait;
import org.enchere.outils.BusinessException;

import java.util.List;

public interface RetraitDAO {

    public Retrait insert(Retrait retrait) throws BusinessException;
    public Retrait getById(int id) throws BusinessException;
    public List<Retrait> getAll() throws BusinessException;
    public void update(Retrait retrait) throws BusinessException;
    public void delete(int id) throws BusinessException;
}
