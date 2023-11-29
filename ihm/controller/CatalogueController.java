package fr.eni.papeterie.ihm.controller;

import javax.swing.JOptionPane;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.ihm.view.EcranCatalogue;

public class CatalogueController {
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

	public void onUpdate() {
		try {
			ecran.miseAJourDesDonnees(mger.getCatalogue());
		} catch (Exception e) {

		}
	}

	
}
