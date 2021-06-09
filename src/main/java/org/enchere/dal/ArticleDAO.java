package org.enchere.dal;

import org.enchere.bo.Articles;

import java.sql.SQLException;
import java.util.List;

public interface ArticleDAO {

    void insert(Articles articles);
    void update(Articles article);
    void delete(Articles articles);
    List<Articles> findAll() throws SQLException;
    Articles find(int id) throws SQLException;
}
