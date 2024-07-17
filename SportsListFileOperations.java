import java.io.*;
import java.util.ArrayList;

public class SportsListFileOperations {
    String filename = "sport.txt";

    // Sauvegarder la liste des sports dans un fichier
    public void saveToFile(ArrayList<Sports> sportsList, String filename) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Sports sports : sportsList) {
                writer.print(sports.getName());
                writer.print(",");
                writer.print(sports.getCategory());
                writer.print(",");
                writer.println(sports.getNumberOfPlayers());
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde dans le fichier " + filename);
            e.printStackTrace();
        }
    }

    // Charger la liste des sports à partir d'un fichier
    public ArrayList<Sports> loadFromFile(String filename) {
        ArrayList<Sports> sportsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    String category = parts[1];
                    int numberOfPlayers = Integer.parseInt(parts[2].trim()); // trim pour enlever les espaces
                    sportsList.add(new Sports(name, category, numberOfPlayers));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erreur lors du chargement du fichier " + filename);
            e.printStackTrace();
        }
        return sportsList;
    }
}
