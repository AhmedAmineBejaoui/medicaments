package medicaments;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PharmacieGUI extends JFrame {
    private final Pharmacie pharmacie;

    private final DefaultListModel<String> listModel;
    private final JList<String> medicamentList;
    private final JTextArea detailArea;

    public PharmacieGUI() {
        // Initialiser la pharmacie
        pharmacie = new Pharmacie(100);

        // Configurer le look & feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // Configurer la fenêtre principale
        setTitle("Gestion de Pharmacie - Interface Moderne");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Appliquer un padding général
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(new Color(245, 245, 250));
        setContentPane(contentPane);

        // Barre de menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(60, 63, 65));
        menuBar.setForeground(Color.WHITE);

        JMenu menuFichier = new JMenu("Fichier");
        menuFichier.setForeground(Color.WHITE);
        JMenuItem quitter = new JMenuItem("Quitter");
        quitter.addActionListener(e -> System.exit(0));
        menuFichier.add(quitter);
        menuBar.add(menuFichier);
        setJMenuBar(menuBar);

        // Panneau gauche (liste des médicaments)
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Liste des Médicaments"));
        leftPanel.setBackground(new Color(220, 230, 240));

        listModel = new DefaultListModel<>();
        medicamentList = new JList<>(listModel);
        medicamentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        medicamentList.addListSelectionListener(e -> afficherDetails());
        JScrollPane scrollPane = new JScrollPane(medicamentList);
        leftPanel.add(scrollPane, BorderLayout.CENTER);

        // Panneau droit (détails)
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Détails du Médicament"));
        rightPanel.setBackground(new Color(220, 230, 240));

        detailArea = new JTextArea();
        detailArea.setEditable(false);
        detailArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        detailArea.setBackground(new Color(240, 245, 250));
        detailArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        rightPanel.add(new JScrollPane(detailArea), BorderLayout.CENTER);

        // Diviseur central
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(400);
        contentPane.add(splitPane, BorderLayout.CENTER);

        // Panneau bas (actions)
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBackground(new Color(245, 245, 250));

        JButton btnAjouter = creerBouton("Ajouter", new Color(46, 204, 113), Color.WHITE);
        JButton btnRechercher = creerBouton("Rechercher", new Color(52, 152, 219), Color.WHITE);
        JButton btnSupprimer = creerBouton("Supprimer", new Color(231, 76, 60), Color.WHITE);
        JButton btnAfficher = creerBouton("Afficher Catégorie", new Color(241, 196, 15), Color.WHITE);

        buttonPanel.add(btnAjouter);
        buttonPanel.add(btnRechercher);
        buttonPanel.add(btnSupprimer);
        buttonPanel.add(btnAfficher);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // Actions des boutons
        btnAjouter.addActionListener(e -> ajouterMedicament());
        btnRechercher.addActionListener(e -> rechercherMedicament());
        btnSupprimer.addActionListener(e -> supprimerMedicament());
        btnAfficher.addActionListener(e -> afficherParCategorie());
    }

    private JButton creerBouton(String texte, Color couleurFond, Color couleurTexte) {
        JButton bouton = new JButton(texte);
        bouton.setBackground(couleurFond);
        bouton.setForeground(couleurTexte);
        bouton.setFocusPainted(false);
        bouton.setFont(new Font("Arial", Font.BOLD, 14));
        bouton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(couleurFond.darker(), 2),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)));
        bouton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bouton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bouton.setBackground(couleurFond.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bouton.setBackground(couleurFond);
            }
        });
        return bouton;
    }

    private void ajouterMedicament() {
        String[] options = {"Antibiotique", "Anti-Inflammatoire", "Homéopathique"};
        String type = (String) JOptionPane.showInputDialog(
                this,
                "Choisissez le type de médicament",
                "Ajout",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (type == null) return;

        String libelle = JOptionPane.showInputDialog("Libellé :");
        int reference = Integer.parseInt(JOptionPane.showInputDialog("Référence :"));
        float prix = Float.parseFloat(JOptionPane.showInputDialog("Prix :"));
        String dateFab = JOptionPane.showInputDialog("Date de fabrication (JJ/MM/AAAA) :");

        Medicament medicament = switch (type) {
            case "Antibiotique" -> {
                String bacterie = JOptionPane.showInputDialog("Bactérie :");
                yield new Antiboitique(libelle, reference, prix, dateFab, bacterie);
            }
            case "Anti-Inflammatoire" -> {
                String molecule = JOptionPane.showInputDialog("Molécule :");
                int acidite = Integer.parseInt(JOptionPane.showInputDialog("Acidité :"));
                yield new AntiInflematoire(libelle, reference, prix, dateFab, molecule, acidite);
            }
            case "Homéopathique" -> {
                String plante = JOptionPane.showInputDialog("Plante :");
                yield new Hamiopathique(libelle, reference, prix, dateFab, plante);
            }
            default -> null;
        };

        if (medicament != null) {
            pharmacie.ajouter(medicament);
            listModel.addElement(medicament.toString());
        }
    }

    private void rechercherMedicament() {
        int reference = Integer.parseInt(JOptionPane.showInputDialog("Entrez la référence :"));
        int indice = pharmacie.recherche(reference);
        if (indice == -1) {
            JOptionPane.showMessageDialog(this, "Médicament introuvable !");
        } else {
            JOptionPane.showMessageDialog(this, "Médicament trouvé !");
        }
    }

    private void supprimerMedicament() {
        int reference = Integer.parseInt(JOptionPane.showInputDialog("Entrez la référence à supprimer :"));
        pharmacie.supprimer(reference);
        listModel.removeAllElements();
        for (Medicament med : pharmacie.getMedicaments()) {
            listModel.addElement(med.toString());
        }
    }

    private void afficherParCategorie() {
        String categorie = JOptionPane.showInputDialog("Entrez la catégorie :");
        detailArea.setText("Liste des médicaments de catégorie " + categorie + " :\n");
        pharmacie.affichage(categorie);
    }

    private void afficherDetails() {
        int selectedIndex = medicamentList.getSelectedIndex();
        if (selectedIndex != -1) {
            detailArea.setText(pharmacie.getMedicaments()[selectedIndex].toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PharmacieGUI gui = new PharmacieGUI();
            gui.setVisible(true);
        });
    }
}
