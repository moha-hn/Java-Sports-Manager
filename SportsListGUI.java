import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class SportsListGUI extends JFrame {
    private SportsListManager manager;
    private JList<Sports> listDisplay;
    private DefaultListModel<Sports> listModel;
    private JTextField nameField, categoryField, numberOfPlayersField;
    private JButton addButton, removeButton, modifyButton, saveButton, loadButton;

    public SportsListGUI(SportsListManager manager) {
        this.manager = manager;

        // Initialiser les composants
        listModel = new DefaultListModel<>();
        listDisplay = new JList<>(listModel);
        nameField = new JTextField(20);
        categoryField = new JTextField(20);
        numberOfPlayersField = new JTextField(5);
        addButton = new JButton("Ajouter Sport");
        removeButton = new JButton("Supprimer Sport");
        modifyButton = new JButton("Modifier Sport");
        saveButton = new JButton("Sauvegarder Liste");
        loadButton = new JButton("Charger Liste");

        // Configurer la disposition
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 10, 5, 10); // Marges

        // Label et champ de texte pour le nom
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Nom:"), constraints);

        constraints.gridx = 1;
        panel.add(nameField, constraints);

        // Label et champ de texte pour la catégorie
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Catégorie:"), constraints);

        constraints.gridx = 1;
        panel.add(categoryField, constraints);

        // Label et champ de texte pour le nombre de joueurs
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("Nombre de Joueurs:"), constraints);

        constraints.gridx = 1;
        panel.add(numberOfPlayersField, constraints);

        // Bouton pour ajouter
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        panel.add(addButton, constraints);

        // Bouton pour supprimer
        constraints.gridy = 1;
        panel.add(removeButton, constraints);

        // Bouton pour modifier
        constraints.gridy = 2;
        panel.add(modifyButton, constraints);

        // Bouton pour sauvegarder
        constraints.gridy = 3;
        panel.add(saveButton, constraints);

        // Bouton pour charger
        constraints.gridy = 4;
        panel.add(loadButton, constraints);

        // Affichage de la liste
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.gridheight = 2;
        constraints.fill = GridBagConstraints.BOTH;
        JScrollPane scrollPane = new JScrollPane(listDisplay);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        panel.add(scrollPane, constraints);

        // Configurer le cadre
        add(panel);
        setTitle("Gestion de Liste de Sports");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Centrer le cadre sur l'écran
        setVisible(true);

        // Initialiser l'affichage de la liste
        updateListDisplay();

        // Ajouter des écouteurs d'action
        addButton.addActionListener(e -> addSports());
        removeButton.addActionListener(e -> removeSports());
        modifyButton.addActionListener(e -> modifySports());
        saveButton.addActionListener(e -> saveSports());
        loadButton.addActionListener(e -> loadSports());

        // Écouteur de sélection de la liste
        listDisplay.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                Sports selectedSports = listDisplay.getSelectedValue();
                if (selectedSports != null) {
                    nameField.setText(selectedSports.getName());
                    categoryField.setText(selectedSports.getCategory());
                    numberOfPlayersField.setText(String.valueOf(selectedSports.getNumberOfPlayers()));
                }
            }
        });
    }

    // Ajouter un sport à la liste
    private void addSports() {
        String name = nameField.getText();
        String category = categoryField.getText();
        int numberOfPlayers = Integer.parseInt(numberOfPlayersField.getText());
        Sports sports = new Sports(name, category, numberOfPlayers);
        manager.addSports(sports);
        updateListDisplay();
        clearFields();
    }

    // Supprimer un sport de la liste
    private void removeSports() {
        Sports selectedSports = listDisplay.getSelectedValue();
        if (selectedSports != null) {
            manager.removeSports(selectedSports);
            updateListDisplay();
            clearFields();
        }
    }

    // Modifier un sport de la liste
    private void modifySports() {
        Sports selectedSports = listDisplay.getSelectedValue();
        if (selectedSports != null) {
            String name = nameField.getText();
            String category = categoryField.getText();
            int numberOfPlayers = Integer.parseInt(numberOfPlayersField.getText());
            Sports modifiedSports = new Sports(name, category, numberOfPlayers);
            manager.modifySports(selectedSports, modifiedSports);
            updateListDisplay();
            clearFields();
        }
    }

    // Sauvegarder la liste des sports
    private void saveSports() {
        manager.saveSportsList("sport.txt");
    }

    // Charger la liste des sports
    private void loadSports() {
        manager.loadSportsList("sport.txt");
        updateListDisplay();
    }

    // Mettre à jour l'affichage de la liste
    private void updateListDisplay() {
        listModel.clear();
        ArrayList<Sports> sportsList = manager.getSportsList();
        for (Sports sports : sportsList) {
            listModel.addElement(sports);
        }
    }

    // Effacer les champs de texte
    private void clearFields() {
        nameField.setText("");
        categoryField.setText("");
        numberOfPlayersField.setText("");
    }

    public static void main(String[] args) {
        SportsListManager manager = new SportsListManager();
        SwingUtilities.invokeLater(() -> new SportsListGUI(manager));
    }
}
