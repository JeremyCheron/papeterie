package fr.eni.papeterie.ihm.controller;

import java.util.List;

import javax.swing.JOptionPane;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.ihm.observer.ICatalogueObserver;
import fr.eni.papeterie.ihm.view.EcranCatalogue;
import fr.eni.papeterie.ihm.view.TableCatalogueModel;

public class CatalogueController implements ICatalogueObserver{
	private EcranCatalogue ecran;
	private CatalogueManager mger;

	public CatalogueController() {
		ecran = new EcranCatalogue();
	}

	public void showEcranCatalogue() {
		// Initialisation du catalogue en mémoire
		try {
			mger = CatalogueManager.getInstance();
			ecran.initComposants(mger.getCatalogue());
		} catch (BLLException e) {
			ecran.popup(e.getMessage(), "Erreur Technique", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void miseAJourDesDonnees(List<Article> articles) {
		try {
			ecran.getTblCatalogue().setModel(new TableCatalogueModel(mger.getCatalogue()));
			ecran.getTblCatalogue().miseAJour();
		} catch (BLLException e) {
			ecran.popup(e.getMessage(), "Erreur Technique", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
}
