package fr.eni.papeterie.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;

public class ArticleController {
    
private EcranArticle ecranArticle;

private int indexCatalogue;
private CatalogueManager mger;
private List<Article> catalogue;

    public ArticleController() {

    }

    private void initData() {
        try {
            mger = CatalogueManager.getInstance();
            catalogue = mger.getCatalogue();
            indexCatalogue = 0;
        } catch (Exception e) {
            ecranArticle.infoErreur(e.getMessage());
        }
    }

    public void startApp() {
        ecranArticle = new EcranArticle();
        panelButton();
        initData();
        afficherPremierArticle();
        ecranArticle.setVisible(true);
    }

    public void afficherPremierArticle() {
        if (catalogue.size() > 0) {
            indexCatalogue = 0;
            ecranArticle.afficherArticle(catalogue.get(indexCatalogue));
        } else {
            indexCatalogue = -1;
            ecranArticle.afficherNouveau();
        }
    }

    public void precedent() {
        if (indexCatalogue > 0) {
            indexCatalogue--;
            ecranArticle.afficherArticle(catalogue.get(indexCatalogue));
        }
    }

    public void suivant() {
        if (indexCatalogue < catalogue.size() -1) {
            indexCatalogue++;
            ecranArticle.afficherArticle(catalogue.get(indexCatalogue));
        }
    }

    public void nouveau() {
        indexCatalogue = catalogue.size();
        ecranArticle.afficherNouveau();
    }

    public void enregistrer() {
        Article articleAffiche = ecranArticle.getArticle();

        try {
            if (articleAffiche.getIdArticle() == null) {
                mger.addArticle(articleAffiche);
                System.out.println("article: " + articleAffiche); 
                catalogue.add(articleAffiche);
                ecranArticle.afficherArticle(articleAffiche);
                ecranArticle.information("Nouvel article sauvegardé.");
            } else {
                mger.updateArticle(articleAffiche);
                catalogue.set(indexCatalogue, articleAffiche);
                ecranArticle.information("Mise à jour efféctuée.");
            }
        } catch (BLLException e) {
            ecranArticle.infoErreur(e.getMessage());
        }
    }

    public void supprimer() {
        try {
            int id = catalogue.get(indexCatalogue).getIdArticle();
            mger.removeArticle(id);
            catalogue.remove(indexCatalogue);
            ecranArticle.information(("Suppresion de l'article réalisée."));
            if (indexCatalogue < catalogue.size()) {
                ecranArticle.afficherArticle(catalogue.get(indexCatalogue));
            } else if (indexCatalogue > 0) {
                indexCatalogue--;
                ecranArticle.afficherArticle(catalogue.get(indexCatalogue));
            } else {
                ecranArticle.afficherNouveau();
            }
        } catch (BLLException e) {
            ecranArticle.infoErreur(e.getMessage());
        }
    }

    public List<Article> getCatalogue() {
        return catalogue;
    }

    public void panelButton() {
        ecranArticle.getBtnPrecedent().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                precedent();
            }
        });

        ecranArticle.getBtnNouveau().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nouveau();
			}

		});

		ecranArticle.getBtnEnregistrer().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				enregistrer();
			}

		});

		ecranArticle.getBtnSupprimer().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				supprimer();
			}

		});

		ecranArticle.getBtnSuivant().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				suivant();
			}

		});

    }

}
