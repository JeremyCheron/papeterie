package fr.eni.papeterie.ihm.observer;

import java.util.List;

import fr.eni.papeterie.bo.Article;

public interface ICatalogueObserver {
    void miseAJourDesDonnees(List<Article> articles);
}
