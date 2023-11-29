package fr.eni.papeterie.bo;
public class Ramette extends Article {

    private int grammage;


    public Ramette(Integer idArticle, String marque, String ref,  String designation, float pu, int qte, int grammage) {
		setIdArticle(idArticle);
		setReference(ref);
		setMarque(marque);
		setDesignation(designation);
		setPrixUnitaire(pu);
		setQteStock(qte);
		setGrammage(grammage);
	}
	
	public Ramette( String marque, String ref,  String designation, float pu, int qte, int grammage) {
		this(null, marque, ref, designation, pu, qte, grammage);
	}

    //getters and setters

    public Ramette() {
    }

    public int getGrammage() {
        return grammage;
    }

    public void setGrammage(int grammage) {
        this.grammage = grammage;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(super.toString());
        buffer.append(" ");
        buffer.append("Rammette [grammage=");
        buffer.append(getGrammage());
        buffer.append("]");
        return buffer.toString();
    }
    


    
    
}
