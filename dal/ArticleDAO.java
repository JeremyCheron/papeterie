package fr.eni.papeterie.dal;

import java.util.List;

import fr.eni.papeterie.bo.Article;

public interface ArticleDAO {

    Article selectById(int idArticle) throws DALException;

    List<Article> selectAll() throws DALException;

    void update(Article article) throws DALException;

    void insert(Article article) throws DALException;

    void delete(int idArticle) throws DALException;
    
}