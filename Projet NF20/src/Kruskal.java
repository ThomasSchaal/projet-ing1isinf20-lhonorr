

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Kruskal {

	private int[][] matTrie;
	private int cout;
	
	/**
	 * Algo de Kruskal 
	 * @param a
	 * @return le cout du MST 
	 */
	public int agloKruskal(Graph a){
		// r�cup�re la matrice du graphe d'entr�e
		matTrie = a.getMatrice();
		// tri la matrice
		TriHeapSort.sort(matTrie);
		// arbre de r�sultat 
		Set<Integer> arbre = new HashSet<Integer>();
		
		/**
		 *  Parcours des ar�tes de la matrice d'entr�
		 *  si l'arbre de sortie est vide, on ajoute la premi�re ar�te en entier
		 *  si l'ar�te suivante n'ajoute pas de cycle, on l'ajoute a l'arbre 
		 */
		for(int i = 0 ; i<matTrie.length ; i++){
			if(arbre.isEmpty() == true){
				arbre.add(matTrie[i][0]);
				arbre.add(matTrie[i][1]);
				cout+=matTrie[i][2];
			}else 
			if(checkCycle(arbre , matTrie[i])== true){
				arbre.add(matTrie[i][0]);
				arbre.add(matTrie[i][1]);
				cout+=matTrie[i][2];
			}
		}
		/**
		 * affiche du contenu de l'arbre 
		 */
		Iterator<Integer> it = arbre.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		return cout;
	}
	
	/**
	 * M�thode qui permet de v�rifier si il y a un cycle
	 * Si les deux sommets de l'ar�te sont d�j� pr�sent dans la list alors on retourne faux 
	 * @param list
	 * @param t
	 * @return boolean
	 */
	public static boolean checkCycle(Set<Integer> list, int[] t){
		if(list.contains(t[0]) && list.contains(t[1]))
			return false ; 
		else 
			return true;
	}
}
