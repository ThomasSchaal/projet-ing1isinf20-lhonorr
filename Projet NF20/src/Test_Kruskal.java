


public class Test_Kruskal {

	/**
	 * Retourne 132
	 * Pas encore valid� completement  
	 * @param args
	 */
	public static void main(String[] args) {
		Graph a = new Graph();

		// Test lecture de fichiers invalides
		a.loadFile("a");
		a.loadFile("");
		System.out.println();

		// Test lecture fichier valide (n'oubliez pas de le changer et mettre un
		// chemin valide !)
		// a.loadFile("C:/Users/Robin/Desktop/Projet NF20/files/inst_v100.dat");
		//a.loadFile("H:/ISI1/NF20/Projet/inst_v100.dat");
		a.loadFile("C:/Users/Robin/Desktop/Projet NF20/files/inst_v10.txt"); // Chemin de Robin
		System.out.println("Lecture du fichier : ");
		for (String s : a.getReadfile()) {
			System.out.println(s);
		}

		// R�cup�ration de le l'attribut directed (si l'arbre est orient�)
		System.out.println();
		a.supplyDirected();
		System.out.println("Graphe (a) orient� ? " + a.getDirected());

		// R�cup�ration du nombre de noeuds de l'arbre
		a.supplyNbNodes();
		System.out
				.println("Nombre de noeuds du graphe (a) : " + a.getNbNodes());

		// R�cup�ration du nombre d'ar�tes de l'arbre
		a.supplyNbEdges();
		System.out.println("Nombre d'ar�tes du graphe (a) : " + a.getNbEdges());

		// Alimentation de la matrice de l'arbre
		a.supplyMatrice();
		Kruskal algo = new Kruskal();
		System.out.println("Algo Kruskal " +algo.agloKruskal(a));
	}
}