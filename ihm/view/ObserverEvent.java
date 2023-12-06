package fr.eni.papeterie.ihm.view;

import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.ihm.observer.ICatalogueObserver;

public class ObserverEvent {
    public List<ICatalogueObserver> catalogueOberservers = new ArrayList<ICatalogueObserver>() ;
    private static ObserverEvent instance;

    public static ObserverEvent getInstance() {
        if (instance == null) {
            instance = new ObserverEvent();
        }
        return instance;
    }

    public void onUpdateCatalogue(List<Article> articles) {
        for (ICatalogueObserver observer : catalogueOberservers) {
            observer.miseAJourDesDonnees(articles);
        }
    }
}
