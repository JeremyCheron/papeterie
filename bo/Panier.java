package fr.eni.papeterie.bo;
import java.util.ArrayList;
import java.util.List;

public class Panier {

    private List<Ligne> lignesPanier;
    private float montant;

    public Panier() {
        lignesPanier = new ArrayList<>();
        montant = 0f;
    }

    public float getMontant() {
        return montant;
    }

    public final Ligne getLigne(int index) {
        return lignesPanier.get(index);
    }

    public final List<Ligne> getLignesPanier() {
        return lignesPanier;
    }

    public void addLigne(Article article, int qte){
        Ligne ligneAdding = new Ligne(article, qte);
        lignesPanier.add(ligneAdding);
        montant += ligneAdding.getPrix() * qte;
    }

    public void updateLigne(int index, int newQte) {
        Ligne l = this.getLigne(index);
        montant -= l.getPrix() * l.qte;
        l.setQte(newQte);
        montant += l.getPrix()*l.qte;
    }

    public void removeLigne(int index) {
        Ligne l = lignesPanier.remove(index);
        montant -= l.getPrix() * l.qte;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Panier : \n\n");
        for (Ligne ligne : lignesPanier) {
            buffer.append("ligne ");
            buffer.append(lignesPanier.indexOf(ligne));
            buffer.append(" :\t");
            buffer.append(ligne.toString());
            buffer.append("\n");
        }
        buffer.append("\nValeur du panier : " + getMontant());
        buffer.append("\n\n");
        return buffer.toString();
    }

}