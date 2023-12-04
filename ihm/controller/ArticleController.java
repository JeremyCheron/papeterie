package fr.eni.papeterie.ihm.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.ihm.observer.ICatalogueObserver;
import fr.eni.papeterie.ihm.view.EcranArticle;
import fr.eni.papeterie.ihm.view.ObserverEvent;

public class ArticleController {
    
private EcranArticle ecranArticle;

private int indexCatalogue;
private CatalogueManager mger;
private List<Article> catalogue;
private List<ICatalogueObserver> observers;

    public ArticleController() {
        this.catalogue = new ArrayList<Article>();
    }

    public void addCatalogueObserver(ICatalogueObserver observer) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(observer);
    }

    public void setEcranArticle(EcranArticle ecranArticle) {
        this.ecranArticle = ecranArticle;
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
        ecranArticle = new EcranArticle(this);
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
                System.out.println("article : " + articleAffiche);
                catalogue.add(articleAffiche);
                ecranArticle.afficherArticle(articleAffiche);
                ecranArticle.information("Nouvel article enregistré.");
                ObserverEvent.getInstance().onUpdateCatalogue(catalogue);
            } else {
                mger.updateArticle(articleAffiche);
                catalogue.set(indexCatalogue, articleAffiche);
                ecranArticle.information("Mise à jour effectuée.");
                ObserverEvent.getInstance().onUpdateCatalogue(catalogue);

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
            ecranArticle.information("Suppression de l'article réalisée.");
            ObserverEvent.getInstance().onUpdateCatalogue(catalogue);

        } catch (BLLException e) {
            ecranArticle.infoErreur(e.getMessage());
        }

        if (indexCatalogue >= 0 && indexCatalogue < catalogue.size()) {
            ecranArticle.afficherArticle(catalogue.get(indexCatalogue));
        } else if (indexCatalogue > 0) {
            indexCatalogue--;
            ecranArticle.afficherArticle(catalogue.get(indexCatalogue));
        } else {
            ecranArticle.afficherNouveau();
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
