import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un arbre construit à partir d'un fichier
 * @author Robin
 *
 */

public class Graph {

	/**
	 *  Dictionnaire des prénoms trouvés dans le fichier CSV
	 */
	private List<String> readfile = new ArrayList<String>();
	private boolean directed;
	private static int nb_nodes = 0;
	private int nb_edges = 0;
	private int[][] matrice;

	/**
	 * Methode qui va charger un fichier passé en paramètre et alimenter la structure readfile
	 * @param filepath
	 * Retourne vrai si la lecture s'est bien passé, sinon retourne faux
	 */
	public boolean loadFile(String filepath) {

		// Vide la liste de lecture du fichier pour s'assurer qu'elle soit vide
		readfile.clear();

		// Vérification du chemin vers le fichier passé en paramètre
		if(!Handling.nullOrSpacesOrEmpty(filepath)) {

			try {
				// Lecture du fichier
				FileReader fileR = new FileReader(filepath);
				BufferedReader bufferR = new BufferedReader(fileR);

				// Alimentation de la liste 'readfile'
				for(String line = bufferR.readLine(); line != null; line = bufferR.readLine()) {
					readfile.add(line);
				}

				// Fermeture des readers
				bufferR.close();
				fileR.close();
			}

			// Si le fichier est introuvable, on retourne faux
			catch (FileNotFoundException e) {
				System.out.println("> Fichier introuvable ou saisie incorrecte !");
				return false;
			} 
			// Exceptions IO
			catch (IOException e) {
				System.out.println("Erreur IO");
			}

			// Si la lecture s'est correctement déroulée, on instancie la matrice et on retourne vrai
			matrice = new int[readfile.size()-6][3];
			return true;
		}

		// Si le chemin indiqué en paramètre est vide, null ou rempli d'espaces, on retourne faux, le dictionnaire ne sera pas alimenté
		else {
			System.out.println("> Chaîne vide ou null");
			return false;	
		}
	}

	/**
	 * Alimente l'attribut directed de l'arbre
	 * Vrai si orienté, faux si non-orienté
	 */
	public void supplyDirected() {
		// On s'assure que le premier élément de la liste readfile soit une chaîne valide
		if(!Handling.nullOrSpacesOrEmpty(this.readfile.get(0))) {

			// On découpe la chaîne et on récupère le premier élément de la chaîne splitée
			String[] tmp = readfile.get(0).split("\\s+");
			if(tmp[0].equalsIgnoreCase("DIRECTED"))
				this.directed = true;
			else 
				this.directed = false;
		}
	}

	/**
	 * Alimente l'attribut nb_nodes de l'arbre
	 */
	public void supplyNbNodes() {
		// On s'assure que le deuxième élément de la liste readfile soit une chaîne valide
		if(!Handling.nullOrSpacesOrEmpty(this.readfile.get(1))) {

			// On découpe la chaîne et on récupère le premier élément de la chaîne splitée
			String[] tmp = readfile.get(1).split("\\s+");
			this.nb_nodes = Integer.parseInt(tmp[1]);
		}
	}

	/**
	 * Alimente l'attribut nb_edges de l'arbre
	 */
	public void supplyNbEdges() {
		// On s'assure que le troisième élément de la liste readfile soit une chaîne valide
		if(!Handling.nullOrSpacesOrEmpty(this.readfile.get(1))) {

			// On découpe la chaîne et on récupère le premier élément de la chaîne splitée
			String[] tmp = readfile.get(2).split("\\s+");
			this.nb_edges = Integer.parseInt(tmp[1]);
		}
	}

	/**
	 * Alimente la matrice de l'arbre
	 */
	public void supplyMatrice() {
		for(int i=4; i<readfile.size()-2; i++) {
			for(int j=0; j<3; j++) {
				matrice[i-4][j] = Integer.parseInt(readfile.get(i).split(("\\s+"))[j]);
			}
		}
	}

	// Getters des attributs
	public List<String> getReadfile() {
		return readfile;
	}
	public boolean getDirected() {
		return directed;
	}	
	public static int getNbNodes() {
		return nb_nodes;
	}
	public int getNbEdges() {
		return nb_edges;
	}
	public int[][] getMatrice() {
		return matrice;
	}
}
