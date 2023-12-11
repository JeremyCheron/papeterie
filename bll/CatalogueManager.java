package fr.eni.papeterie.bll;

import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
//import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DAO;
import fr.eni.papeterie.dal.DAOFactory;

public class CatalogueManager {
    private DAO<Article> daoArticle;
    private static CatalogueManager instance;

    public static CatalogueManager getInstance() {
        return (instance == null) ? (instance = new CatalogueManager()) : instance;
    }

    private CatalogueManager(){
        daoArticle = DAOFactory.getArticleDAO();
    }

    public void addArticle(Article art) throws BLLException{
        if (art.getIdArticle() != null) {
            throw new BLLException("Article already exists.");
        }
        try {
            validerArticle(art);
            daoArticle.insert(art);
        } catch (Exception e) {
            throw new BLLException("addArticle failed - article: " + art, e);
        }
    }



    public List<Article> getCatalogue() throws BLLException{
        try {
            return daoArticle.selectAll();
        } catch (Exception e) {
            throw new BLLException("Failed charging catalogue", e);
        }
    }

    public void updateArticle(Article art) throws BLLException{
        try {
            validerArticle(art);
            daoArticle.update(art);
        } catch (Exception e) {
            throw new BLLException("Failed updateArticle - article: " + art, e);
        }
    }

    public Article getArticle(int idArticle) throws BLLException{
        try {
            return daoArticle.selectById(idArticle);
        } catch (Exception e) {
            throw new BLLException("Failed getArticle - article: " + idArticle, e);
        }
    }

    public void removeArticle(Integer idArticle) throws BLLException{
        try {
            daoArticle.delete(idArticle);
        } catch (Exception e) {
            throw new BLLException("Failed removeArticle - article: " + idArticle, e);
        }
    }


        private void validerArticle(Article art) throws BLLException{
            boolean valide = true;
            StringBuffer sb = new StringBuffer();

            if (art == null) {
                throw new BLLException("Article null");
            }
            if (art.getMarque() == null || art.getMarque().trim().length() == 0) {
                sb.append("Article Marque is needed.\n");
                valide = false;
            }
            if (art.getDesignation() == null || art.getDesignation().trim().length() == 0) {
                sb.append("Article Designation is needed.\n");
                valide = false;
            }
            if (art.getReference() == null || art.getReference().trim().length() == 0) {
                sb.append("Article Reference is needed.\n");
                valide = false;
            }
            if (art instanceof Ramette && ((Ramette)art).getGrammage() <= 0) {
                sb.append("Grammage must be positive.\n");
                valide = false;
            }
            if (art instanceof Stylo) {
                Stylo stylo = (Stylo) art;
                if (stylo.getCouleur() == null || stylo.getCouleur().trim().length() == 0) {
                    sb.append("Stylo Color is needed.\n");
                    valide = false;
                }
            }

            if (!valide) {
                throw new BLLException(sb.toString());
            }
            
    }
}
