package org.enchere.dal;

import org.enchere.bo.Categorie;
import org.enchere.outils.BusinessException;

import java.util.List;

public interface CategorieDAO {

    public Categorie selectById(int noCategorie) throws BusinessException;

    public List<Categorie> selectAll() throws BusinessException;

    public void insert(Categorie c) throws BusinessException;

    public void update(Categorie c) throws BusinessException;

    public void delete(int id) throws BusinessException;


}
