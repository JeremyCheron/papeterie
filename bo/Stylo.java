package fr.eni.papeterie.bo;
public class Stylo extends Article{

    private String couleur;

    public Stylo(Integer idArticle, String marque, String ref, String designation, float pu, int qte, String couleur)  {
		
		setIdArticle(idArticle);
		setMarque(marque);
		setReference(ref);
		setDesignation(designation);
		setPrixUnitaire(pu);
		
		setCouleur(couleur);
		setQteStock(qte);
	}
	
	public Stylo( String marque, String ref, String designation, float pu, int qte, String couleur)  {
		this(null,  marque, ref, designation, pu, qte, couleur);
	}

    //getters and setters

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        String s = String.format("%s Stylo [Couleur=%s]", super.toString(), getCouleur());

        return s;
    }

    
}
