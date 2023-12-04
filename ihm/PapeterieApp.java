package fr.eni.papeterie.ihm;

import javax.swing.SwingUtilities;

import fr.eni.papeterie.ihm.controller.ArticleController;
import fr.eni.papeterie.ihm.controller.CatalogueController;

public class PapeterieApp {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ArticleController articleC = new ArticleController();
				articleC.startApp();


				CatalogueController catalogueC = new CatalogueController();
				catalogueC.showEcranCatalogue();
				articleC.addCatalogueObserver(catalogueC);
			}

		});

	}

}
