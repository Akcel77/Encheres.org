package org.enchere.outils;

import java.util.ArrayList;
import java.util.List;

/**
 * A REVOIR
 */
public class BusinessException extends Exception {

    private static final long serialVersionUID = 1L;
    private List<Integer> listeCodesErreur;

    public BusinessException() {
        super();
        this.listeCodesErreur = new ArrayList<>();
    }

    /**
     * Ajoute une erreur en fonction de son code
     * @param code
     */
    public void ajouterErreur (int code)
    {
        if(!this.listeCodesErreur.contains(code));
        {
            this.listeCodesErreur.add(code);
        }
    }

    /**
     * Boolean pour check erreurs
     * @return
     */
    public boolean hasErreurs()
    {
        return this.listeCodesErreur.size()>0;
    }


    /**
     * Liste code erreur (int)
     * @return
     */
    public List<Integer> getListCodesErreur()
    {
        return this.listeCodesErreur;
    }


}
