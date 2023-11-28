package fr.eni.papeterie.bo;
public abstract class Article {

    private Integer idArticle;
    private String reference;
    private String marque;
    private String designation;
    private float prixUnitaire;
    private int qteStock;

    
    //Getters and setters

    public Integer getIdArticle() {
        return this.idArticle;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public void setQteStock(int qteStock) {
        this.qteStock = qteStock;
    }

    public String getReference() {
        return this.reference;
    }

    public String getMarque() {
        return this.marque;
    }

    public String getDesignation() {
        return this.designation;
    }

    public float getPrixUnitaire() {
        return this.prixUnitaire;
    }

    public int getQteStock() {
        return this.qteStock;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    @Override
    public String toString() {
        return "Article [idArticle=" + idArticle + ", reference=" + reference + ", marque=" + marque + ", designation=" + designation + ", prixUnitaire=" + prixUnitaire + ", qteStock=" + qteStock + "]";
    }
    
}
