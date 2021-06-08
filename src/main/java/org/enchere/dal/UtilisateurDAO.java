package org.enchere.dal;

import org.enchere.bo.Articles;
import org.enchere.bo.Utilisateur;
import org.enchere.outils.BusinessException;

import java.util.List;

public interface UtilisateurDAO {

    public Utilisateur insert(Utilisateur utilisateur) throws BusinessException;
    public void update (Utilisateur utilisateur) throws BusinessException;
    public void delete (int no) throws BusinessException;
    public Utilisateur getByPseudo(String pseudo) throws BusinessException;
    public Utilisateur getByMail(String email) throws BusinessException;
    public List<Utilisateur> getAllUser()throws BusinessException;
    public List<Articles> getAllArticle(Utilisateur utilisateur) throws BusinessException;
}
