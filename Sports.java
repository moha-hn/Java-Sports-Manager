import java.io.Serializable;

public class Sports implements Serializable {
	private String name;
	private String category;
	private int numberOfPlayers;

	// Constructeur pour initialiser les variables d'instance
	public Sports(String name, String category, int numberOfPlayers) {
		this.name = name;
		this.category = category;
		this.numberOfPlayers = numberOfPlayers;
	}

	// Obtenir le nom du sport
	public String getName() {
		return name;
	}

	// Obtenir la catégorie du sport
	public String getCategory() {
		return category;
	}

	// Obtenir le nombre de joueurs du sport
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	// Redéfinir la méthode toString pour afficher les informations du sport
	@Override
	public String toString() {
		return name + " - " + category + " (" + numberOfPlayers + " players)";
	}
}
