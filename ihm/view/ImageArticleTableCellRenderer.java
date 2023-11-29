package fr.eni.papeterie.ihm.view;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class ImageArticleTableCellRenderer implements TableCellRenderer {

    private static ImageIcon imageStylo;
    private static ImageIcon imageRamette;

    public ImageArticleTableCellRenderer() {
        imageStylo = new ImageIcon(getClass().getResource("../ressources/pencil.gif"));
        imageRamette = new ImageIcon(getClass().getResource("../ressources/ramette.gif"));
    }

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
                
                String type = (String) value;

                JLabel component = new JLabel();
                if (type.equals("R")) {
                    component.setIcon( imageRamette );
                } else {
                    component.setIcon( imageStylo );
                }

                component.setHorizontalAlignment(SwingConstants.CENTER);

                return component;

	}

}
