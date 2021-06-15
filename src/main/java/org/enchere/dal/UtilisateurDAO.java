package org.enchere.dal;

import org.enchere.bo.Utilisateur;
import org.enchere.outils.BusinessException;

import java.util.List;

public interface UtilisateurDAO {

    Utilisateur selectUserByID(int id) throws BusinessException;
    public Utilisateur insert(Utilisateur utilisateur) throws BusinessException;
    public void update (Utilisateur utilisateur) throws BusinessException;
    public void delete (int no) throws BusinessException;
    public Utilisateur getByPseudo(String pseudo) throws BusinessException;
    public Utilisateur getByMail(String email) throws BusinessException;
    public List<Utilisateur> getAllUser()throws BusinessException;
    public List<String> getAllPseudo() throws BusinessException;
    public Utilisateur searchByID(int id) throws BusinessException;
    public boolean checkIfEmailOrPseudo(Utilisateur utilisateur)throws BusinessException;
    public List<String> getAllMail() throws BusinessException;
}
