package org.enchere.bll;

import org.enchere.dal.DAOFactory;
import org.enchere.dal.GlobalDAO;

public class GlobalManager {
    private GlobalDAO globalDAO;

    public GlobalManager(){
        this.globalDAO = DAOFactory.getGlobalDAO();
    }
}
