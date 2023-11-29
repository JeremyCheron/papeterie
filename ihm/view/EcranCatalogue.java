package fr.eni.papeterie.ihm.view;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.eni.papeterie.bo.Article;

@SuppressWarnings("serial")
public class EcranCatalogue extends JFrame{

    private TableCatalogue tblCatalogue;

    public EcranCatalogue() {
        super("Catalogue");

        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        this.setIconImage(toolkit.getImage(getClass().getResource("../ressources/aim.png")));
    }

    public void initComposants(List<Article> articles) {
        JPanel mainContent = new JPanel();
        mainContent.setOpaque(true);
        mainContent.setLayout(new GridLayout(1, 0));
        tblCatalogue = new TableCatalogue(articles);
        JScrollPane scrollPane = new JScrollPane(tblCatalogue);
        mainContent.add(scrollPane);

        this.setContentPane(mainContent);
        setVisible(true);

    }

    public void popup(String msg, String title, int Logo) {
        JOptionPane.showMessageDialog(EcranCatalogue.this, msg, title, Logo);
    }

}
