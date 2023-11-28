package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.JdbcTools;

public class ArticleDAOJdbcImpl implements ArticleDAO {

    private static final String TYPE_RAMETTE = "RAMETTE";
    private static final String TYPE_STYLO = "STYLO";

	private static final String sqlInsert = "insert into articles(reference,marque,designation,prixUnitaire,qteStock,type, grammage, couleur) values(?,?,?,?,?,?,?,?)";
    private static final String sqlSelectById = "select * from articles where idArticle = ?";
    private static final String sqlSelectAll = "select * from articles";
	private static final String sqlUpdate = "update articles set reference=?,marque=?,designation=?,prixUnitaire=?,qteStock=?,grammage=?,couleur=? where idArticle=?";
	private static final String sqlDelete = "delete from articles where idArticle=?";

    @Override
    public Article selectById(int idArticle) throws DALException {
        Article article = null;
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement query = connection.prepareStatement(sqlSelectById);) {
            query.setInt(1, idArticle);
            
            try (ResultSet resultSet = query.executeQuery()) {
                if (resultSet.next()) {

                    
                    if (TYPE_STYLO.equalsIgnoreCase(resultSet.getString("type").trim())) {
                        article = new Stylo(resultSet.getInt("idArticle"), resultSet.getString("marque"), 
                                    resultSet.getString("reference").trim(), resultSet.getString("designation"), 
                                    resultSet.getFloat("prixUnitaire"), resultSet.getInt("qteStock"), resultSet.getString("couleur"));

                    }

                    if (TYPE_RAMETTE.equalsIgnoreCase(resultSet.getString("type").trim())) {

                        article = new Ramette(resultSet.getInt("idArticle"), resultSet.getString("marque"), 
                                    resultSet.getString("reference").trim(), resultSet.getString("designation"), 
                                    resultSet.getFloat("prixUnitaire"), resultSet.getInt("qteStock"), resultSet.getInt("grammage"));

                    }

                }
            }

        } catch (SQLException e) {
            throw new DALException("SelectById failed" + idArticle, e);
        }

        return article;

    }
    
    @Override
    public List<Article> selectAll() throws DALException {
        List<Article> liste = new ArrayList<>();
        try (Connection connection = JdbcTools.getConnection(); Statement query = connection.createStatement();) {
            try (ResultSet resultSet = query.executeQuery(sqlSelectAll);) {
                Article article = null;

                while (resultSet.next()) {

                    if (TYPE_STYLO.equalsIgnoreCase(resultSet.getString("type").trim())) {

                        article = new Stylo(resultSet.getInt("idArticle"), resultSet.getString("marque"), 
                                    resultSet.getString("reference").trim(), resultSet.getString("designation"), 
                                    resultSet.getFloat("prixUnitaire"), resultSet.getInt("qteStock"), resultSet.getString("couleur"));

                    }

                    if (TYPE_RAMETTE.equalsIgnoreCase(resultSet.getString("type").trim())) {

                        article = new Ramette(resultSet.getInt("idArticle"), resultSet.getString("marque"), 
                                    resultSet.getString("reference").trim(), resultSet.getString("designation"), 
                                    resultSet.getFloat("prixUnitaire"), resultSet.getInt("qteStock"), resultSet.getInt("grammage"));

                    }

                    liste.add(article);

                }

            }
        } catch (Exception e) {
            throw new DALException("SelectAll failed - ", e);
        }
        return liste;
    }

    @Override
    public void update(Article article) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement query = connection.prepareStatement(sqlUpdate);) {
            query.setString(1, article.getReference());
            query.setString(2, article.getMarque());
            query.setString(3, article.getDesignation());
            query.setFloat(4, article.getPrixUnitaire());
            query.setInt(5, article.getQteStock());
            query.setInt(8, article.getIdArticle());

            if (article instanceof Ramette) {
                Ramette ramette = (Ramette) article;
                query.setInt(6, ramette.getGrammage());
                query.setNull(7, Types.VARCHAR);
            }
            if (article instanceof Stylo) {
                Stylo stylo = (Stylo) article;
                query.setNull(6, Types.INTEGER);
                query.setString(7, stylo.getCouleur());
            }

            query.executeUpdate();

            
        } catch (Exception e) {
            throw new DALException("Update article failed - " + article, e);
        }
    }

    @Override
    public void insert(Article article) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); 
                PreparedStatement query = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);) {
            query.setString(1, article.getReference());
            query.setString(2, article.getMarque());
            query.setString(3, article.getDesignation());
            query.setFloat(4, article.getPrixUnitaire());
            query.setInt(5, article.getQteStock());
            if (article instanceof Ramette) {
                Ramette ramette = (Ramette) article;
                query.setString(6, TYPE_RAMETTE);
                query.setInt(7, ramette.getGrammage());
                query.setNull(8, Types.VARCHAR);
            }
            if (article instanceof Stylo) {
                Stylo stylo = (Stylo) article;
                query.setString(6, TYPE_STYLO);
                query.setNull(7, Types.INTEGER);
                query.setString(8, stylo.getCouleur());
            }

            int nbRows = query.executeUpdate();

            if (nbRows == 0) { 
                throw new DALException("Insertion failed, no rows affected.");
            }

            try (ResultSet generatedKeys = query.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    article.setIdArticle(generatedId);
                } else {
                        throw new DALException("Insertion failed, no ID obtained.");
                }
            }
            
        } catch (Exception e) {
            throw new DALException("Insert article failed - " + article, e);
        }
    }

    @Override
    public void delete(int idArticle) throws DALException {
        try (Connection connection = JdbcTools.getConnection(); PreparedStatement query = connection.prepareStatement(sqlDelete);) {
            query.setInt(1, idArticle);
            query.executeUpdate();
        } catch (Exception e) {
            throw new DALException("Delete article failed - " + idArticle, e);
        }
    }

}
