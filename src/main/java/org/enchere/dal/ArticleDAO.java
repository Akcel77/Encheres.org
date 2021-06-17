package org.enchere.dal;

import org.enchere.bo.Articles;
import org.enchere.outils.BusinessException;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public interface ArticleDAO {

    int insert(Articles articles) throws SQLException;
    void update(Articles article) throws SQLException;
    void delete(int id) throws SQLException;
    List<Articles> findAll() throws SQLException, BusinessException, ParseException;
    Articles find(int id) throws SQLException, BusinessException, ParseException;
    List<Articles> findAllByUser(int id) throws SQLException;

}
