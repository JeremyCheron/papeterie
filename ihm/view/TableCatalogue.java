package fr.eni.papeterie.ihm.view;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import fr.eni.papeterie.bo.Article;

public class TableCatalogue extends JTable{

    public static final int COL_ICON = 0;
	public static final int COL_REFERENCE = 1;
	public static final int COL_MARQUE = 2;
	public static final int COL_DESIGNATION = 3;
	public static final int COL_PU = 4;
	public static final int COL_STOCK = 5;

    private static ImageArticleTableCellRenderer imageArticleCellRenderer;

    public TableCatalogue(List<Article> catalogue) {
        super(new TableCatalogueModel(catalogue));

        setPreferredScrollableViewportSize(new Dimension(500, 70));
        setFillsViewportHeight(true);

        miseAJour();

    }

    public void miseAJour() {
        setPreferredScrollableViewportSize(new Dimension(500, 70));
        setFillsViewportHeight(true);

        this.getColumnModel().getColumn(COL_ICON).setPreferredWidth(50);
        this.getColumnModel().getColumn(COL_REFERENCE).setPreferredWidth(100);
        this.getColumnModel().getColumn(COL_MARQUE).setPreferredWidth(100);
        this.getColumnModel().getColumn(COL_DESIGNATION).setPreferredWidth(200);
        this.getColumnModel().getColumn(COL_PU).setPreferredWidth(50);
        this.getColumnModel().getColumn(COL_STOCK).setPreferredWidth(50);

        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        imageArticleCellRenderer = new ImageArticleTableCellRenderer();

        this.getColumnModel().getColumn(COL_ICON).setCellRenderer(imageArticleCellRenderer);

        this.setRowHeight(30);

        setEnabled(false);
    }

}
