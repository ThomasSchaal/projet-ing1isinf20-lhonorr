import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Test_Prim {

	public static void main(String[] arg) {
		int[][] tab =  { {2,1,10}, {2,0,7}, {2,3,64}, {1,0,12}, {1,3,2} };
		int[][] tab_test = { {1,0,5}, {2,0,4}, {2,1,67}, {3,0,26}, {3,1,75}, {3,2,75}, 
							 {4,0,43}, {4,1,43}, {4,2,48}, {4,3,6} };
	
		
		/* Test voisin seul
		 * int[] resultat = Prim.look_neighbour(tab, 2);
		System.out.println("Résultat plus petite arête : " +resultat[0] + ", " + resultat[1] + ", "+ resultat[2]);
		System.out.println("Nombre de noeuds différents : " +Prim.totalNbNodes(tab));
		*/
		
		/* Test avec Liste
		List<Integer> test_list = new LinkedList<Integer>();
		test_list.add(1);
		test_list.add(2);
		
		System.out.println("1er tour de boucle :");
		int[] resultat_tabvoisin = Prim.look_neighbourtabList(tab_test,test_list);
		System.out.println("Plus petite arête trouvée : " +resultat_tabvoisin[0] + ", " + resultat_tabvoisin[1] + ", "+ resultat_tabvoisin[2]);
		
		
		System.out.println();
		System.out.println("2ème tour de boucle");
		resultat_tabvoisin = Prim.look_neighbourtabList(tab_test,test_list);
		System.out.println("Plus petite arête trouvée : " +resultat_tabvoisin[0] + ", " + resultat_tabvoisin[1] + ", "+ resultat_tabvoisin[2]);
		
		*/
		
		// Test avec look_neightbour en tableau
		/*
		System.out.println("1er tour de boucle :");
		int[] resultat_tabvoisin = Prim.look_neighbourtab(tab_test, new int[]{1,2});
		System.out.println("Plus petite arête trouvée : " +resultat_tabvoisin[0] + ", " + resultat_tabvoisin[1] + ", "+ resultat_tabvoisin[2]);
		
		
		System.out.println();
		System.out.println("2ème tour de boucle");
		resultat_tabvoisin = Prim.look_neighbourtab(tab_test, new int[]{1,2});
		System.out.println("Plus petite arête trouvée : " +resultat_tabvoisin[0] + ", " + resultat_tabvoisin[1] + ", "+ resultat_tabvoisin[2]);
		*/
		
		// Test Prim sur un graph fichier
		
		Graph g = new Graph();
		g.loadFile("C:/Users/Robin/Desktop/Projet NF20/files/inst_v100.dat");
		g.supplyNbNodes();
		g.supplyMatrice();
		System.out.println("Terminé !");
		
		//System.out.println("Poids total de l'arbre couvrant : "+ Prim.do_Prim(g.getMatrice()));
		
		/*
		int[][] resultat = Prim.do_Prim(g.getMatrice());
		System.out.println("Arêtes de la solution Prim : ");
		for(int i = 0; i != resultat.length; i++) {
			System.out.println(resultat[i][0] + ", " + resultat[i][1] + ", " + resultat[i][2]);
		}
		*/
	
	}
}
