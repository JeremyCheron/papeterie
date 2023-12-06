package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.dal.ArticleDAOHelper;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAO;

public class ArticleDAOJdbcImpl implements DAO<Article> {

    private static final String SQL_INSERT = "INSERT INTO articles(reference,marque,designation,prixUnitaire,qteStock,type,grammage,couleur) VALUES(?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM articles WHERE idArticle = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM articles";
    private static final String SQL_UPDATE = "UPDATE articles SET reference=?,marque=?,designation=?,prixUnitaire=?,qteStock=?,type=?,grammage=?,couleur=? WHERE idArticle=?";
    private static final String SQL_DELETE = "DELETE FROM articles WHERE idArticle=?";

    @Override
    public Article selectById(int idArticle) throws DALException {
        try (PreparedStatement query = JdbcTools.preparedStatement(SQL_SELECT_BY_ID)) {
            query.setInt(1, idArticle);
            try (ResultSet resultSet = query.executeQuery()) {
                return resultSet.next() ? ArticleDAOHelper.mapResultSetToArticle(resultSet) : null;
            }
        } catch (SQLException e) {
            throw new DALException("SelectById failed" + idArticle, e);
        }
    }

    @Override
    public List<Article> selectAll() throws DALException {
        List<Article> articles = new ArrayList<>();
        try (Statement query = JdbcTools.createStatement();
            ResultSet resultSet = query.executeQuery(SQL_SELECT_ALL)) {
            while (resultSet.next()) {
                articles.add(ArticleDAOHelper.mapResultSetToArticle(resultSet));
            }
        } catch (SQLException e) {
            throw new DALException("SelectAll failed - ", e);
        }
        return articles;
    }

    @Override
    public void update(Article article) throws DALException {
        try (PreparedStatement query = JdbcTools.preparedStatement(SQL_UPDATE)) {
            ArticleDAOHelper.setArticleParameters(query, article);
            query.setInt(9, article.getIdArticle());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Update article failed - " + article, e);
        }
    }

    @Override
    public void insert(Article article) throws DALException {
        try (Connection connection = JdbcTools.getConnection();
            PreparedStatement query = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ArticleDAOHelper.setArticleParameters(query, article);
            int nbRows = query.executeUpdate();
            if (nbRows == 0) {
                throw new DALException("Insertion failed, no rows affected.");
            }
            ArticleDAOHelper.setGeneratedArticleId(query, article);
        } catch (SQLException e) {
            throw new DALException("Insert article failed - " + article, e);
        }
    }

    @Override
    public void delete(int idArticle) throws DALException {
        try (PreparedStatement query = JdbcTools.preparedStatement(SQL_DELETE)) {
            query.setInt(1, idArticle);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Delete article failed - " + idArticle, e);
        }
    }
}
