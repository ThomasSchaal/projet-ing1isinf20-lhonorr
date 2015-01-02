import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Classe de l'algorithme d'élément de cycle
 * @author Robin
 *
 */
public class CycleElimination {

	public static Result_list do_CycleElimination(List<Edge> tab) {
		int total_weight = 0;
		Set<Integer> dif_nodes = new HashSet<Integer>();
		List<Edge> result = new LinkedList<Edge>();
		int iterator = 0;
		int[] minimal_node = null;
		boolean cycle_found = true;
		boolean add_edge = true;
		boolean finished = false;
		boolean next_checked = true;
		int checked_node = 0;

		// Boucle principale
		while(!finished) {

			// On stocke premier sommet dans le tableau de l'arbre couvrant
			if(tab.size() >= 1) {
				result.add(new Edge(tab.get(0).getSommet1(), 0, 0));
				dif_nodes.add(result.get(0).getSommet1());
			}

			// On ajoute le sommet check
			if(iterator == 0)
				checked_node = result.get(iterator).getSommet1();


			cycle_found = true;
			add_edge = true;
			next_checked = true;

			// On boucle tant que l'arête que l'on souhaite ajouter ne forme pas de cycle
			while(cycle_found) {

				// On récupere l'arête minimale de la liste des sommets à check via la méthode look_neightbourList
				minimal_node = CycleElimination.look_neighbourtabList(tab, checked_node, dif_nodes);
				//System.out.println("Noeud minimum trouvé : "+minimal_node[0]+ "\t" + minimal_node[1] + "\t" +minimal_node[2]);

				// Si l'arête forme un cycle, on vérifie que son poids est inférieur à celui du chemin du sommet de départ au sommet de destination
				if(!checkCycle(dif_nodes, minimal_node)) {
					//System.out.println("Cycle trouvée !");
					int dep_node = 0;

					if(minimal_node[0] == checked_node)
						dep_node = minimal_node[1];
					else
						dep_node = minimal_node[0];

					Result_weight rw = checkWeightCycle(result, minimal_node, dep_node, checked_node);

					//System.out.println(rw.isDelete());

					// On regarde le boolean delete du resultat et on supprime l'arête si false
					if(rw.isDelete()) {
						result.remove(rw.getIndice());
						minimal_node[0] = rw.getEdges().get(0).getSommet1();
						minimal_node[1] = rw.getEdges().get(0).getSommet2();
						minimal_node[2] = rw.getEdges().get(0).getPoids();
						cycle_found = false;
						iterator--;
						break;
					}

					else {
						//System.out.println("Je retourne faux !");
						add_edge = false;
						result.remove(iterator);
						iterator--;
						next_checked = false;
						break;
					}

				}

				else
					cycle_found = false;

			}


			// On ajoute cette arête à notre résultat ainsi qu'aux listes de sommets
			if(add_edge) {
				result.get(iterator).setSommet1(minimal_node[0]);
				result.get(iterator).setSommet2(minimal_node[1]);
				result.get(iterator).setPoids(minimal_node[2]);
			}

			//System.out.println("Result : "+ result.toString());

			//System.out.println(dif_nodes + " | minimal node : " + minimal_node[0] +", " + minimal_node[1]);
			if(next_checked)
				checked_node = nextCheckedNode(minimal_node, dif_nodes);

			//System.out.println("checked node : "+checked_node);
			dif_nodes.add(minimal_node[0]);
			dif_nodes.add(minimal_node[1]);

			//System.out.println("Noeud conservé : "+result.get(iterator).toString());

			iterator++;

			//System.out.println("On supprime : "+tab.get(minimal_node[3]).toString());
			tab.remove(minimal_node[3]);
			//System.out.println("Prochain noeud à vérifier : "+ checked_node);

			//System.out.println("Liste des arêtes arbre couvrant : ");
			//for(Edge e : result) 
			//	System.out.println(e.toString());

			//System.out.println("Matrice graphe actuelle ::");
			//for(int i=0; i<tab.size(); i++)
			//	System.out.println(tab.get(i));
			//System.out.println();

			//System.out.println("Dif node.size : "+dif_nodes.size());
			//System.out.println("Nb nodes graphes : "+Graph.getNbNodes());
			System.out.println("Tab.size : "+ tab.size());
			if(dif_nodes.size() == Graph.getNbNodes() && tab.size() == 1)
				finished = true;
		}
		//}

		//System.out.println("Taille de la matrice graphe : "+tab.size());
		total_weight = getWeight(result);
		//System.out.println("Nombre de sommets : "+dif_nodes.size());
		return new Result_list(total_weight, result);

	}



	/**
	 * Regarde les arêtes de poids minimale d'un sommet donné et retourne la plus petite
	 * @param tab
	 * @param checked_node
	 * @return
	 */
	public static int[] look_neighbourtabList(List<Edge> tab, int checked_node, Set<Integer> dif_nodes) {
		int minimum_weight = -1;
		int index_i_minimal = 0;
		int[] minimum = new int[4];
		boolean valide = false;
		minimum[0] = -1;
		Set<Integer> list_nodes = new HashSet<Integer>();

		for(Integer i : dif_nodes) 
			list_nodes.add(i);

		while(!valide) {

			// On récupère le poids de la première arête rencontrée pour les sommets passés en paramètre
			for(int i=0; i != tab.size(); i++) {
				if(tab.get(i).getSommet1() == checked_node|| tab.get(i).getSommet2() == checked_node ) {
					//System.out.println("Sommet trouvé : " +tab[i][0] + ", " + tab[i][1] +", "+ tab[i][2]);
					minimum_weight = tab.get(i).getPoids();
					index_i_minimal = i;
					minimum[0] = tab.get(i).getSommet1();
					minimum[1] = tab.get(i).getSommet2();
					minimum[2] = tab.get(i).getPoids();
					//System.out.println("Premiere arête trouvée : " +minimum[0] + ", " + minimum[1] + ", " + minimum[2]);
					break;
				}
			}

			// On recherche l'arête de poids minimale
			for(int i=0; i<tab.size(); i++) {
				if(tab.get(i).getSommet1() == checked_node || tab.get(i).getSommet2() == checked_node) {
					if(tab.get(i).getPoids() < minimum_weight) {
						index_i_minimal = i;
						minimum_weight = tab.get(i).getPoids();
						minimum[0] = tab.get(i).getSommet1();
						minimum[1] = tab.get(i).getSommet2();
						minimum[2] = tab.get(i).getPoids();
					}
				}
			}

			if(minimum[0] != -1)
				valide = true;
			else {
				list_nodes.remove(checked_node);
				checked_node = list_nodes.iterator().next();
			}

			//System.out.println("Etat du tableau :");
			//for(int p=0; p<tab.length; p++) {
			//	System.out.println("Sommet : " +tab[p][0] + ", " + tab[p][1] +", "+ tab[p][2]);
			//}

		}
		minimum[3] = index_i_minimal;
		return minimum;

	}


	/**
	 * Vérifie l'arête que l'on rajoute par rapport à la liste des noeuds
	 * @param minimal_node
	 * @param dif_nodes
	 * @return le prochain noeud à verifier
	 */
	public static int nextCheckedNode(int[] minimal_node, Set<Integer> dif_nodes) {
		if(dif_nodes.contains(minimal_node[0])) 
			return minimal_node[1];
		else
			return minimal_node[0];
	}


	/**
	 * Verifie si l'arête que l'on souhaite ajouter va former un cycle
	 * @param dif_nodes
	 * @param t
	 * @return
	 */
	public static boolean checkCycle(Set<Integer> dif_nodes, int[] t) {
		//System.out.println(dif_nodes);
		//System.out.println(t[0] +" "+ t[1] + " "+t[2]);
		if(dif_nodes.contains(t[0]) && dif_nodes.contains(t[1]))
			return false;
		else
			return true;
	}


	public static Result_weight checkWeightCycle(List<Edge> result, int[] minimal_node, int dep_node, int dest_node) {
		int[] edge_max = new int[4];
		int[] edge_read = new int[4];
		int[] max_weight_node = new int[4];
		int checked_node = dep_node;
		int max_weight = -1;
		int max_weight_total = -1;
		boolean cycle_finished = false;
		List<Edge> result_tmp = new LinkedList<>();
		List<Edge> edge_return =  new LinkedList<>();
		int index_delete = 0;

		// On ajoute les noeuds dans la liste temporaire
		for(Edge e : result) 
			result_tmp.add(e);

		while(!cycle_finished && result_tmp.size() != 0) {
			int i = 0;
			//System.out.println("Petit tour ! ");
			//System.out.println("Arbre couvrant actuel : ");
			//for(Edge e : result_tmp )
			//	System.out.println(e.toString());

			//System.out.println("Noeud de départ : "+checked_node);

			for(int k = 0; k < result_tmp.size() -1; k++) {
				//	System.out.println("Tour : "+ k);

				// On stocke l'arête sur laquelle on se trouve pour verifier si l'on est à la fin du chemin
				edge_read[0] = result_tmp.get(k).getSommet1();
				edge_read[1] = result_tmp.get(k).getSommet2();
				edge_read[2] = result_tmp.get(k).getPoids();
				edge_read[3] = k;

				//System.out.println("On lit l'arête : "+result_tmp.get(k).toString());
				//System.out.println("Noeud checké : "+checked_node);
				// Si le noeud est présent dans une arête du chemin et que le poids maximal n'est pas initialisé
				if( (result_tmp.get(k).getSommet1() == checked_node || result_tmp.get(k).getSommet2() == checked_node) && max_weight == -1) {
					edge_max[0] = result_tmp.get(k).getSommet1();
					edge_max[1] = result_tmp.get(k).getSommet2();
					edge_max[2] = result_tmp.get(k).getPoids();
					edge_max[3] = k;
					//System.out.println("Init : "+k);
					//System.out.println("1ère Arête maximale : "+ edge_max[0] +"\t"+edge_max[1]+"\t"+edge_max[2]);
					break;
				}

				// Si le noeud est présent dans une arête du chemin et que le poids maximal est initialisé
				else if( (result_tmp.get(k).getSommet1() == checked_node || result_tmp.get(k).getSommet2() == checked_node) && result_tmp.get(k).getPoids() > max_weight) {
					edge_max[0] = result_tmp.get(k).getSommet1();
					edge_max[1] = result_tmp.get(k).getSommet2();
					edge_max[2] = result_tmp.get(k).getPoids();
					edge_max[3] = k;
					//System.out.println("Nouvelle arête max trouvée : "+edge_max[0] +"\t"+edge_max[1]+"\t"+edge_max[2]);
					break;
				}


				//System.out.println("Dep : "+dep_node + " | edge_read[0] : "+edge_read[0] + "| edge_read[1] : "+edge_read[1]);
				//System.out.println("Noeud checké : : "+checked_node);
				if(edge_read[0] == dep_node)
					checked_node = edge_read[1];
				else
					checked_node = edge_read[0];
				dep_node = checked_node;

				//System.out.println("Noeud à check : "+checked_node);


				//System.out.println("Passage 3");
				//System.out.println("poids trouvé : "+edge_read[2] +" > poids max : "+edge_max[2]);
				if(edge_read[0] == checked_node || edge_read[1] == checked_node && edge_read[2] > edge_max[2] && edge_max[2] > max_weight) {
					edge_max[0] = edge_read[0];
					edge_max[1] = edge_read[1];
					edge_max[2] = edge_read[2];
					edge_max[3] = k;
				}

			}



			max_weight = edge_max[2];
			//System.out.println("Arête maximale : "+ edge_max[0] +"\t"+edge_max[1]+"\t"+edge_max[2] + "\t"+ edge_max[3]);
			//System.out.println("Arête trouvée : "+ edge_read[0] +"\t"+edge_read[1]+"\t"+edge_read[2]);
			//max_weight = edge_max[2];

			//System.out.println("Arête maximale : "+ edge_max[0] +"\t"+edge_max[1]+"\t"+edge_max[2]);
			//System.out.println("Arête trouvée : "+ edge_read[0] +"\t"+edge_read[1]+"\t"+edge_read[2]);

			//max_weight = edge_max[2];

			// Comparaison du poids le plus gros
			if(max_weight_total == -1) {
				max_weight_node[0] = edge_max[0];
				max_weight_node[1] = edge_max[1];
				max_weight_node[2] = edge_max[2];
				max_weight_node[3] = edge_max[3];
				//System.out.println("position de max_weight_node : "+ max_weight_node[3]);
				max_weight_total = max_weight;

			}

			else if(max_weight > max_weight_total) {
				max_weight_total = max_weight;
				max_weight_node[0] = edge_max[0];
				max_weight_node[1] = edge_max[1];
				max_weight_node[2] = edge_max[2];
				max_weight_node[3] = edge_max[3];
			}

			//System.out.println("Poids de l'arête maximale : "+max_weight_node[2]);

			// Si l'arête trouvée contient le noeud de destination, on sort de la boucle
			if(edge_read[0] == dest_node || edge_read[1] == dest_node) {
				//System.out.println("Chemin terminé !");
				cycle_finished = true;
			}

			// Sinon, on va chercher une prochaine arête pour continuer à vérifier le chemin jusqu'au sommet de destination
			/*
			else {
				System.out.println("Dep : "+dep_node);
				System.out.println("Dest : "+dest_node);
				if(edge_read[0] == dep_node)
					checked_node = edge_read[1];
				else
					checked_node = edge_read[0];

			}
			 */
			//dep_node = checked_node;
			//System.out.println("Position edge[3] :"+ edge_max[3]);
			//System.out.println("On supprime l'arrête : " + result_tmp.get(edge_max[3]).toString());

			// On met à jour l'indice à delete
			for(int j=0; j != result.size(); j++) {
				//System.out.println("Arête result : "+result.get(j).toString());
				//System.out.println("Arête tmp : "+result_tmp.get(edge_max[3]).toString());
				//System.out.println("position  : "+max_weight_node[3]);
				//System.out.println("Arête de result : "+result.get(j).equals(result_tmp.get(max_weight_node[3])));
				//System.out.println("Arête result_tmp : "+result_tmp.get(edge_max[3]));
				if(result.get(j).equals(result_tmp.get(max_weight_node[3]))) {
					index_delete = j;
					//System.out.println("j : "+j);
				}

			}

			//System.out.println("FINAL position de max_weight_node : "+ max_weight_node[3]);
			//System.out.println("Arête maximal à enlever : "+ max_weight_node[0] +"\t"+max_weight_node[1]+"\t"+max_weight_node[2]);
			//index_delete = edge1[3];

			//System.out.println("On supprime dans result_tmp : "+max_weight_node[0] +"\t"+max_weight_node[1]+"\t"+max_weight_node[2]);
			//result_tmp.remove(max_weight_node[3]);

			//System.out.println("Index a supprimer : "+ index_delete);
			//System.out.println();
		}

		// Si le poids de l'arête est inférieur a celui du chemin, on retourne 'vrai, position_edge_a_delete, nouveau_edge'
		//System.out.println(minimal_node[2] + " < " + max_weight_total);
		if(minimal_node[2] < max_weight_total) {
			edge_return.add(new Edge(minimal_node[0], minimal_node[1],minimal_node[2]));
			return new Result_weight(true, index_delete, edge_return);
		}

		else {
			return new Result_weight(false,  minimal_node[2], null);
		}

	}


	/**
	 * Retourne le poids total d'une liste de Edges
	 * @param tab
	 * @return total_weight
	 */
	public static int getWeight(List<Edge> tab) {
		int total_weight = 0;
		for(Edge e : tab)
			total_weight += e.getPoids();
		return total_weight;
	}
}

