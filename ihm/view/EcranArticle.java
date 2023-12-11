package fr.eni.papeterie.ihm.view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.ihm.controller.ArticleController;

@SuppressWarnings("serial")
public class EcranArticle extends JFrame{
    //form
    private JTextField txtReference, txtDesignation, txtMarque, txtStock, txtPrix;
    private JRadioButton radioRamette, radioStylo;
    private JPanel panelType, panelGrammage;
    private JCheckBox chk80, chk100;
    private JComboBox<String> cboCouleur;

    //buttons
    private JPanel panelBoutons;
    private JButton btnPrecedent;
    private JButton btnNouveau;
    private JButton btnEnregistrer;
    private JButton btnSupprimer;
    private JButton btnSuivant;

    //Print idArticle
    private Integer idCourant;

    //BLL
    private CatalogueManager mgr;

    private List<Article> catalogue;

    private int indexCatalogue;
    private ArticleController articleController;

    public void setArticleController(ArticleController articleController) {
        this.articleController = articleController;
    }

    public CatalogueManager getCatalogueManager() {
        return mgr;
    }

    public EcranArticle(ArticleController articleController) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(500, 400);
		setResizable(false);
		setTitle("Détail article");
		initIhm();

		initData();
		afficherPremierArticle();
		setVisible(true);
        this.articleController = articleController;
	}

    private void initIhm() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 0;
        gbc.gridx = 0;
        panelPrincipal.add(new JLabel("Référence"), gbc);

        gbc.gridx = 1;
        panelPrincipal.add(getTxtReference(), gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        panelPrincipal.add(new JLabel("Désignation"), gbc);

        gbc.gridx = 1;
        panelPrincipal.add(getTxtDesignation(), gbc);

        gbc.gridy = 2;
		gbc.gridx = 0;
		panelPrincipal.add(new JLabel("Marque"), gbc);

		gbc.gridx = 1;
		panelPrincipal.add(getTxtMarque(), gbc);

        gbc.gridy = 3;
		gbc.gridx = 0;
		panelPrincipal.add(new JLabel("Stock"), gbc);

		gbc.gridx = 1;
		panelPrincipal.add(getTxtStock(), gbc);

        gbc.gridy = 4;
		gbc.gridx = 0;
		panelPrincipal.add(new JLabel("Prix"), gbc);

		gbc.gridx = 1;
		panelPrincipal.add(getTxtPrix(), gbc);

        gbc.gridy = 5;
		gbc.gridx = 0;
		panelPrincipal.add(new JLabel("Type"), gbc);

		gbc.gridx = 1;
		gbc.gridheight = 1;
		panelPrincipal.add(getPanelType(), gbc);

		gbc.gridy = 6;
		gbc.gridx = 0;
		panelPrincipal.add(new JLabel("Grammage"), gbc);

		gbc.gridx = 1;
		panelPrincipal.add(getPanelGrammage(), gbc);

		gbc.gridy = 7;

		gbc.gridx = 0;
		panelPrincipal.add(new JLabel("Couleur"), gbc);

		gbc.gridx = 1;
		panelPrincipal.add(getCboCouleur(), gbc);

		gbc.gridy = 8;

		gbc.gridx = 0;
		gbc.gridwidth = 2;
		panelPrincipal.add(getPanelBoutons(), gbc);

		setContentPane(panelPrincipal);
    }

    private void initData() {
        try {
            mgr = CatalogueManager.getInstance();
            catalogue = mgr.getCatalogue();
            indexCatalogue = 0;
        } catch (Exception e) {
            infoErreur(e.getMessage());
        }
    }

    public JTextField getTxtReference() {
        if (txtReference == null) {
            txtReference = new JTextField(30);
        }
        return txtReference;
    }

    public JTextField getTxtDesignation() {
        if (txtDesignation == null) {
            txtDesignation = new JTextField(30);
        }
        return txtDesignation;
    }

    public JTextField getTxtMarque() {
        if (txtMarque == null) {
            txtMarque = new JTextField(30);
        }
        return txtMarque;
    }

    public JTextField getTxtStock() {
        if (txtStock == null) {
            txtStock = new JTextField(30);
        }
        return txtStock;
    }

    public JTextField getTxtPrix() {
        if (txtPrix == null) {
            txtPrix = new JTextField(30);
        }
        return txtPrix;
    }

    public JPanel getPanelType() {
		if (panelType == null) {
			panelType = new JPanel();
			panelType.setLayout(new BoxLayout(panelType, BoxLayout.Y_AXIS));
			panelType.add(getRadioRamette());
			panelType.add(getRadioStylo());
			ButtonGroup bg = new ButtonGroup();
			bg.add(getRadioRamette());
			bg.add(getRadioStylo());
		}
		return panelType;
	}

    public JPanel getPanelGrammage() {
        if (panelGrammage == null) {
            panelGrammage = new JPanel();
            panelGrammage.setLayout(new BoxLayout(panelGrammage, BoxLayout.Y_AXIS));
            panelGrammage.add(getChk80());
            panelGrammage.add(getChk100());
            ButtonGroup bg = new ButtonGroup();
            bg.add(getChk80());
            bg.add(getChk100());
        }
        return panelGrammage;
    }

    public JRadioButton getRadioRamette() {
        if (radioRamette == null) {
            radioRamette = new JRadioButton("Ramette");
            radioRamette.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getChk100().setEnabled(true);
                    getChk80().setEnabled(true);
                    getCboCouleur().setEnabled(false);
                }
            });
        }

        return radioRamette;
    }

    public JRadioButton getRadioStylo() {
		if (radioStylo == null) {
			radioStylo = new JRadioButton("Stylo");
			radioStylo.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					getChk100().setEnabled(false);
					getChk80().setEnabled(false);
					getCboCouleur().setEnabled(true);

				}

			});

		}

		return radioStylo;
	}

    public JCheckBox getChk80() {
		if (chk80 == null) {
			chk80 = new JCheckBox("80 grammes");
		}
		return chk80;
	}

	public JCheckBox getChk100() {
		if (chk100 == null) {
			chk100 = new JCheckBox("100 grammes");
		}

		return chk100;
	}

    public JComboBox<String> getCboCouleur() {
		if (cboCouleur == null) {
			String[] couleurs = { "noir", "rouge", "vert", "bleu", "jaune" };
			cboCouleur = new JComboBox<String>(couleurs);
		}
		return cboCouleur;
	}

    public void afficherNouveau() {
		Ramette a = new Ramette(null, "", "", "", 0.0f, 0, 0);

		afficherArticle(a);

	}

    public void afficherArticle(Article a) {

		idCourant = a.getIdArticle();
		getTxtReference().setText(a.getReference() + "");
		getTxtMarque().setText(a.getMarque() + "");
		getTxtDesignation().setText(a.getDesignation() + "");
		getTxtPrix().setText(String.valueOf(a.getPrixUnitaire()) + "");
		getTxtStock().setText(Integer.valueOf(a.getQteStock()) + "");

		if (a.getClass().equals(Stylo.class)) {
			getRadioStylo().setSelected(true);
			getCboCouleur().setEnabled(true);
			getCboCouleur().setSelectedItem(((Stylo) a).getCouleur());
			getChk80().setEnabled(false);
			getChk100().setEnabled(false);
		} else {
			getRadioRamette().setSelected(true);
			getChk80().setEnabled(true);
			getChk100().setEnabled(true);
			getChk80().setSelected(((Ramette) a).getGrammage() == 80);
			getChk100().setSelected(((Ramette) a).getGrammage() == 100);
			getCboCouleur().setSelectedItem(null);
			getCboCouleur().setEnabled(false);
		}
		getRadioStylo().setEnabled(a.getIdArticle() == null);
		getRadioRamette().setEnabled(a.getIdArticle() == null);
	}

    public JPanel getPanelBoutons() {
        if(panelBoutons == null) {
            panelBoutons = new JPanel();
            panelBoutons.setLayout(new FlowLayout());
            panelBoutons.add(getBtnPrecedent());
            panelBoutons.add(getBtnNouveau());
            panelBoutons.add(getBtnEnregistrer());
            panelBoutons.add(getBtnSupprimer());
            panelBoutons.add(getBtnSuivant());
        }
        return panelBoutons;
    }

    public JButton getBtnPrecedent() {
		if (btnPrecedent == null) {
			btnPrecedent = new JButton();
			ImageIcon image = new ImageIcon(this.getClass().getResource("../ressources/Back24.gif"));
			btnPrecedent.setIcon(image);
			btnPrecedent.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					precedent();
				}

			});
		}
		return btnPrecedent;
	}

    public JButton getBtnNouveau() {
		if (btnNouveau == null) {
			btnNouveau = new JButton();
			ImageIcon image = new ImageIcon(this.getClass().getResource("../ressources/New24.gif"));
			btnNouveau.setIcon(image);
			btnNouveau.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					nouveau();
				}

			});
		}

		return btnNouveau;
	}

    public JButton getBtnEnregistrer() {
		if (btnEnregistrer == null) {
			btnEnregistrer = new JButton();
			ImageIcon image = new ImageIcon(this.getClass().getResource("../ressources/Save24.gif"));
			btnEnregistrer.setIcon(image);
			btnEnregistrer.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					articleController.enregistrer();

				}

			});
		}
		return btnEnregistrer;
	}

    public JButton getBtnSupprimer() {
        if (btnSupprimer == null) {
            btnSupprimer = new JButton();
            ImageIcon image = new ImageIcon(this.getClass().getResource("../ressources/Delete24.gif"));
            btnSupprimer.setIcon(image);
            
            btnSupprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        articleController.supprimer();
                }
            });
        }
        return btnSupprimer;
    }

    public JButton getBtnSuivant() {
		if (btnSuivant == null) {
			btnSuivant = new JButton();
			ImageIcon image = new ImageIcon(this.getClass().getResource("../ressources/Forward24.gif"));
			btnSuivant.setIcon(image);
			btnSuivant.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					suivant();
				}

			});
		}
		return btnSuivant;
	}

    public Article getArticle() {
        Article article = null;
        if (getRadioStylo().isSelected()) {
            article = new Stylo();
        } else {
            article = new Ramette();
        }
        try {
            article.setIdArticle(idCourant);
            article.setReference(getTxtReference().getText());
            article.setMarque(getTxtMarque().getText());
            article.setDesignation(getTxtDesignation().getText());
            article.setPrixUnitaire(Float.parseFloat(getTxtPrix().getText()));
            article.setQteStock(Integer.parseInt(getTxtStock().getText()));
            if (getCboCouleur().isEnabled()) {
                ((Stylo) article).setCouleur((String) getCboCouleur().getSelectedItem());
            } else {
                Ramette r = (Ramette) article;
                if (getChk80().isSelected()) {
                    r.setGrammage(80);
                } else if  (getChk100().isSelected()) {
                    r.setGrammage(100);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return article;
    }


    public void infoErreur(String msg) {
		JOptionPane.showMessageDialog(EcranArticle.this, msg, "", JOptionPane.ERROR_MESSAGE);
	}

    public void information(String msg) {
		JOptionPane.showMessageDialog(EcranArticle.this, msg, "", JOptionPane.INFORMATION_MESSAGE);
	}

    public void afficherPremierArticle() {
        if (catalogue.size() > 0) {
            indexCatalogue = 0;
            afficherArticle(catalogue.get(indexCatalogue));
        } else {
            indexCatalogue = -1;
            afficherNouveau();
        }
    }

    public void precedent() {
        if (indexCatalogue > 0) {
            indexCatalogue--;
            afficherArticle(catalogue.get(indexCatalogue));
        }
    }

    public void suivant() {
        if (indexCatalogue < catalogue.size() - 1) {
            indexCatalogue++;
            afficherArticle(catalogue.get(indexCatalogue));
        }
    }

    public void nouveau() {
        indexCatalogue = catalogue.size();
        afficherNouveau();
    }

}
