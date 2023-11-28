package fr.eni.papeterie.bo;
public class Ligne {
    
    int qte;
    private Article article;

    public Ligne(Article article, int qte) {
        setArticle(article);
        setQte(qte);
    }

    public Article getArticle() {
        return this.article;
    }

    public int getQte() {
        return this.qte;
    }

    public float getPrix(){
        return article.getPrixUnitaire();
    }

    private void setArticle(Article article) {
        this.article = article;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Ligne [");
        buffer.append(" qte=");
        buffer.append(getQte());
        buffer.append(", prix=");
        buffer.append(getPrix());
        buffer.append(", ");
        if (article != null) {
            buffer.append("article=");
            buffer.append(getArticle().toString());
        }
        buffer.append("]");
        return buffer.toString();
    }

}
