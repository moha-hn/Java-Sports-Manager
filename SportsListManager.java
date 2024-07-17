import java.io.*;
import java.util.ArrayList;

public class SportsListManager {
    private ArrayList<Sports> sportsList;

    // Initialiser la liste des sports
    public SportsListManager() {
        sportsList = new ArrayList<>();
    }

    // Ajouter un sport à la liste
    public void addSports(Sports sports) {
        sportsList.add(sports);
    }

    // Supprimer un sport de la liste
    public void removeSports(Sports sports) {
        sportsList.remove(sports);
    }

    // Modifier un sport dans la liste
    public void modifySports(Sports oldSports, Sports newSports) {
        int index = sportsList.indexOf(oldSports);
        if (index != -1) {
            sportsList.set(index, newSports);
        }
    }

    // Obtenir la liste des sports
    public ArrayList<Sports> getSportsList() {
        return sportsList;
    }

    // Sauvegarder la liste des sports dans un fichier
    public void saveSportsList(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(sportsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Charger la liste des sports à partir d'un fichier
    @SuppressWarnings("unchecked")
    public void loadSportsList(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            sportsList = (ArrayList<Sports>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
