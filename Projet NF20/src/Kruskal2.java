import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Kruskal2 {

	private static int[][] matTrie;
	private static int cout;

	/**
	 * Algo de Kruskal
	 * 
	 * @param a
	 * @return le cout du MST
	 */
	public static Result_list do_Kruksal(Graph a) {
		// récupère la matrice du graphe d'entrée
		matTrie = a.getMatrice();
		// tri la matrice
		TriHeapSort.sort(matTrie);
		// Liste de liste
		LinkedList<Set<Integer>> listList = new LinkedList<>();
		// Arbre de résultat
		Set<Integer> arbre = new HashSet<Integer>();
		listList.add(arbre);
		// arbre de résultat
		List<Edge> result = new LinkedList<Edge>();

		/**
		 * Parcours des arêtes de la matrice d'entrée si l'arbre de sortie est
		 * vide, on ajoute la première arête en entier si l'arête suivante
		 * n'ajoute pas de cycle, on l'ajoute a l'arbre
		 */

		for (int i = 0; i < matTrie.length; i++) {
			System.out.println("Etat de l'arbre couvrant : ");
			for (Edge e : result)
				System.out.println(e.toString());
			if (arbre.isEmpty() == true) {
				result.add(new Edge(matTrie[i][0], matTrie[i][1], matTrie[i][2]));
				arbre.add(matTrie[i][0]);
				arbre.add(matTrie[i][1]);
				listList.add(arbre);
			}

			else {
				System.out.println("Etat de l'arbre sommet : ");
				for (Integer j : arbre)
					System.out.print(j + ",");
				System.out.println();
				System.out.println("On veut ajouter : " + matTrie[i][0] + " "
						+ matTrie[i][1] + " " + matTrie[i][2]);

				if (isConnected(listList, new Edge(matTrie[i][0],
						matTrie[i][1], matTrie[i][2]))) {

					if (checkCycle(arbre, matTrie[i])) {
						result.add(new Edge(matTrie[i][0], matTrie[i][1],
								matTrie[i][2]));
						arbre.add(matTrie[i][0]);
						arbre.add(matTrie[i][1]);
						System.out.println("On ajoute : " + matTrie[i][0] + " "
								+ matTrie[i][1] + " " + matTrie[i][2]);
					}
				} else {
					result.add(new Edge(matTrie[i][0], matTrie[i][1],
							matTrie[i][2]));
					Set<Integer> newArbre = new HashSet<Integer>();
					newArbre.add(matTrie[i][0]);
					newArbre.add(matTrie[i][1]);
					listList.add(newArbre);
				}
//				for (int j = 1; j < listList.size(); j++) {
//					fusionArbre(arbre, listList.get(j));
//				}
			}
			System.out.println();

		}

		/**
		 * Coût total du MST
		 */
		cout = getWeight(result);

		return new Result_list(cout, result);
	}

	/**
	 * Méthode qui permet de vérifier si il y a un cycle Si les deux sommets de
	 * l'arête sont déjà présent dans la list alors on retourne faux
	 * 
	 * @param list
	 * @param t
	 * @return boolean
	 */
	public static boolean checkCycle(Set<Integer> list, int[] t) {
		if (list.contains(t[0]) && list.contains(t[1]))
			return false;
		else
			return true;
	}

	/**
	 * Methode qui permet de vérifier si l'arête est déjà dans la liste Pour
	 * créer un nouvelle arbre Si les deux sommets ne sont pas déjà présent dans
	 * la list alors on retourne vrai
	 * 
	 * @param list
	 * @param t
	 * @return boolean
	 */
	public static boolean checkNotAlreadyExist(Set<Integer> list, int[] t) {
		if (!list.contains(t[0]) && !list.contains(t[1]))
			return true;
		else
			return false;
	}

	/**
	 * Méthode qui vérifie si l'arbreSecondaire peut fusionner avec l'arbre de
	 * résultat
	 * 
	 * @param mainArbre
	 * @param arbre
	 * @return
	 */
	public static boolean fusionArbre(Set<Integer> mainArbre, Set<Integer> arbre) {
		Iterator<Integer> arbreIterator = arbre.iterator();
		if (mainArbre.contains(arbreIterator.next())) {
			mainArbre.addAll(arbre);
			return true;
		} else
			return false;
	}

	/**
	 * Retourne le poids total d'une liste de Edges
	 * 
	 * @param tab
	 * @return total_weight
	 * @author Robin
	 */
	public static int getWeight(List<Edge> tab) {
		int total_weight = 0;
		for (Edge e : tab)
			total_weight += e.getPoids();
		return total_weight;
	}

	public static boolean isConnected(LinkedList<Set<Integer>> list, Edge edge) {
		boolean connexion_found = false;
		for (Set<Integer> s : list) {
			if (s.contains(edge.getSommet1()) || s.contains(edge.getSommet2()))
				connexion_found = true;
		}
		return connexion_found;
	}

}
