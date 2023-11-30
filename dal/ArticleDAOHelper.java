package fr.eni.papeterie.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

public class ArticleDAOHelper {
    
    private static final String TYPE_RAMETTE = "RAMETTE";
    private static final String TYPE_STYLO = "STYLO";

    //prepare statement with a variable number of parameters
    public static PreparedStatement preparedStatementWithParams(PreparedStatement preparedStatement, Object... params) throws SQLException {
        int index = 1;
        for (Object param : params) {
            preparedStatement.setObject(index++, param);
        }
        return preparedStatement;
    }

    //method to set common parameters for Article
    public static void setCommonArticleParameters(PreparedStatement query, Article article) throws SQLException {
        query.setString(1, article.getReference());
        query.setString(2, article.getMarque());
        query.setString(3, article.getDesignation());
        query.setFloat(4, article.getPrixUnitaire());
        query.setInt(5, article.getQteStock());
    }

    //Method to set parameters for Ramette
    public static void setRametteParameters(PreparedStatement query, Ramette ramette) throws SQLException {
        query.setString(6, TYPE_RAMETTE);
        query.setInt(7, ramette.getGrammage());
        query.setNull(8, Types.VARCHAR);
    }

    //Method to set parameters for Stylo
    public static void setStyloParameters(PreparedStatement query, Stylo stylo) throws SQLException {
        query.setString(6, TYPE_STYLO);
        query.setNull(7, Types.INTEGER);
        query.setString(8, stylo.getCouleur());
    }

    // method to map ResultSet to Article
    public static Article mapResultSetToArticle(ResultSet resultSet) throws SQLException {
        Article article = null;

        if (TYPE_STYLO.equalsIgnoreCase(resultSet.getString("type").trim())) {
            article = new Stylo(resultSet.getInt("idArticle"), resultSet.getString("marque"),
                    resultSet.getString("reference").trim(), resultSet.getString("designation"),
                    resultSet.getFloat("prixUnitaire"), resultSet.getInt("qteStock"), resultSet.getString("couleur"));

        } else if (TYPE_RAMETTE.equalsIgnoreCase(resultSet.getString("type").trim())) {
            article = new Ramette(resultSet.getInt("idArticle"), resultSet.getString("marque"),
                    resultSet.getString("reference").trim(), resultSet.getString("designation"),
                    resultSet.getFloat("prixUnitaire"), resultSet.getInt("qteStock"), resultSet.getInt("grammage"));
        }

        return article;
    }

    //method to set the gerated ID for the inserted Article
    public static void setGeneratedArticleId(PreparedStatement query, Article article) throws SQLException, DALException {
        try (ResultSet generatedKeys = query.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                article.setIdArticle(generatedId);
            } else {
                throw new DALException("Insertion failed, no ID obtained.");
            }
        }
    }
}


