import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Diane Pacaud & Robin L'Honor�
 *
 */

public class Prim {

	public static Result_list do_Prim(int[][] tab) {
		Set<Integer> dif_nodes = new HashSet<Integer>();
		List<Integer> checked_node = new LinkedList<Integer>();
		boolean cycle_found = true;
		int[][] result = new int[tab.length][3];
		int iterator = 0;
		int[] minimal_node = null;
		int total_weight = 0;

		//System.out.println("Nb nodes : "+Graph.getNbNodes());
		// Tant que le nombre de noeuds de notre graphe r�sultat n'est pas identique au nombre de noeuds du graphe initial
		while(totalNbNodes(dif_nodes) != Graph.getNbNodes() ) {
				
			//System.out.println("Tour : "+iterator);

			//System.out.println("Iterator : "+ iterator);
			//System.out.println("Total de Noeuds du tableau : " + totalNbNodes(dif_nodes));

			// On stocke premier sommet dans le tableau de l'arbre couvrant
			result[iterator][0] = tab[0][0];
			// On le stocke �galement dans nos listes de sommets afin de conna�tre le nombre de sommet r�cup�r� et ceux � checker
			dif_nodes.add(result[iterator][0]);
			addDifferentNodes(checked_node, dif_nodes);

			// On regarde les voisins des sommets check�s et on stocke l'ar�te minimale
			//System.out.println("Taille de la liste checked_node : " +checked_node.size() +", contenu : ");
			//for(Integer i : checked_node)
			//	System.out.println("-> " +i);

			cycle_found = true;
			// Tant qu'on ne trouve pas une ar�te qui ne forme pas de cycle, on recherche
			while(cycle_found) {
				// On r�cup�re l'ar�te minimale de la liste des sommets � check via la m�thode look_neightbourList
				minimal_node = Prim.look_neighbourtabList(tab, checked_node);
				//System.out.println("Ar�te mini trouv� : "+minimal_node[0] + ", " + minimal_node[1] + ", " + minimal_node[2]);

				if(!checkCycle(checked_node, minimal_node)) {
					tab[minimal_node[3]][2] = -1;
					//System.out.println("Je trouve un cycle");

				}
				else
					cycle_found = false;
			}

			// On ajoute cette ar�te � notre r�sultat ainsi qu'aux listes de sommets
			result[iterator][0] = minimal_node[0];
			result[iterator][1] = minimal_node[1];
			result[iterator][2] = minimal_node[2];
			dif_nodes.add(minimal_node[0]);
			dif_nodes.add(minimal_node[1]);

			//System.out.println("Noeud ajout� : "+result[iterator][0] + ", " +result[iterator][1] + ", " + result[iterator][2]);

			//System.out.println("Tour " +iterator +" termin�");
			//System.out.println();
			iterator++;
			total_weight += minimal_node[2];
			//System.out.println("Nombre de sommets : "+dif_nodes.size());
			//System.out.println(dif_nodes);
		}
		return new Result_list(total_weight, cleared_graph(result));
	}

	/**
	 * Regarde les ar�tes de poids minimale d'un sommet donn� et retourne la plus petite
	 * @param tab
	 * @param checked_node
	 * @return
	 */
	public static int[] look_neighbourtabList(int[][] tab, List<Integer> checked_node) {
		int minimum_weight = -1;
		int index_i_minimal = 0;
		int[] minimum = new int[4];
		minimum[0] = -1;

		for(int k=0; k <= checked_node.size()-1; k++) {
			//System.out.println("On check le sommet : "+ checked_node.get(k));
			if(minimum[0] == -1)
				minimum[0] = checked_node.get(k);


			// On r�cup�re le poids de la premi�re ar�te rencontr�e pour les sommets pass�s en param�tre
			// Uniquement s'ils correspondent aux crit�res de poids (-1) 
			for(int i=0; i != tab.length; i++) {
				if( (tab[i][0] == checked_node.get(k) || tab[i][1] == checked_node.get(k) ) && minimum_weight == -1 && tab[i][2] != -1) {
					//System.out.println("Sommet trouv� : " +tab[i][0] + ", " + tab[i][1] +", "+ tab[i][2]);
					minimum_weight = tab[i][2];
					index_i_minimal = i;
					minimum[0] = tab[i][0];
					minimum[1] = tab[i][1];
					minimum[2] = tab[i][2];
					//System.out.println("Premiere ar�te trouv�e : " +minimum[0] + ", " + minimum[1] + ", " + minimum[2]);
					break;
				}
			}

			// On recherche l'ar�te de poids minimale
			for(int i=0; i<tab.length; i++) {
				if(tab[i][0] == checked_node.get(k) || tab[i][1] == checked_node.get(k)) {
					if(tab[i][2] < minimum_weight && tab[i][2] != -1) {
						index_i_minimal = i;
						minimum_weight = tab[i][2];
						minimum[0] = tab[i][0];
						minimum[1] = tab[i][1];
						minimum[2] = tab[i][2];
					}
				}
			}
		}

		// On change le poids de l'ar�te trouv�e � -1, afin qu'elle ne soit pas reprise une seconde fois
		tab[index_i_minimal][2] = -1;

		//System.out.println("Etat du tableau :");
		//for(int p=0; p<tab.length; p++) {
		//	System.out.println("Sommet : " +tab[p][0] + ", " + tab[p][1] +", "+ tab[p][2]);
		//}


		minimum[3] = index_i_minimal;
		return minimum;

	}


	public static boolean checkCycle(List<Integer> list, int[] t) {
		if(list.contains(t[0]) && list.contains(t[1]))
			return false;
		else
			return true;
	}

	/**
	 * 
	 * Retourne le nombre de noeuds diff�rents qu'il y a au sein d'un tableau � deux dimensions
	 * @param tab
	 * @return nombre de sommet total
	 */
	public static int totalNbNodes(Set<Integer> dif_nodes) {
		return dif_nodes.size();
	}

	/**
	 * Ajoute les sommets de la structure dif_nodes dans la liste l
	 * @param l
	 * @param dif_nodes
	 */
	public static void addDifferentNodes(List<Integer> l, Set<Integer> dif_nodes) {
		l.clear();
		for(Integer i : dif_nodes) {
			l.add(i);
		}
	}

	public static List<Edge> cleared_graph(int[][] tab) {
		List<Edge> ret = new ArrayList<Edge>();
		for(int i=0; i<tab.length-1; i++) {
			if(tab[i][0] != 0 && tab[i][1] != 0 && tab[i][2] != 0)
				ret.add(new Edge(tab[i][0], tab[i][1], tab[i][2]));
		}
		return ret;
	}
}
