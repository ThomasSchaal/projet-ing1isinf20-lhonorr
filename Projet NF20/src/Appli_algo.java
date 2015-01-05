import java.io.IOException;
import java.util.Scanner;

/**
 * Classe permettant l'utilisation des différents algorithmes par l'utilisateur
 * @author Robin
 *
 */
public class Appli_algo {


	public static void main(String[] args) throws IOException {
		Graph g = new Graph();
		String path;
		String saisie = null;
		boolean correct = false;

		Scanner scan = new Scanner(System.in);
		System.out.println("Veuillez saisir le chemin menant au fichier contenant le graphe :");
		path = scan.nextLine();

		if(g.loadFile(path)) {
			System.out.println("> Graphe correctement récupéré !");
			g.supplyNbNodes();
			g.supplyMatrice();
			correct = true;
		}

		if(correct) {
			// C:\Users\Robin\Desktop\Projet NF20\files\inst_v10.txt
			System.out.println("Veuillez saisir votre choix pour l'algorithme :");
			System.out.println("1. Prim");
			System.out.println("2. Kruskal");
			System.out.println("3. Elimination de Cycle");
			System.out.print("Votre choix : ");
			saisie = scan.nextLine();
			System.out.println("------------------------");
			switch(saisie) {
			case "1" :
				System.out.println("Algorithme de Prim : ");
				System.out.println(Prim.do_Prim(g.getMatrice()));
				break;
			case "2" :
				System.out.println("Algorithme de Kruskal : ");
				System.out.println(Kruskal.do_Kruksal(g));
				break;
			case "3" :
				System.out.println("Algorithme de l'Elimination de Cycle : ");
				System.out.println(CycleElimination.do_CycleElimination(g.getMatriceTest()));
				break;
			default :
				System.out.println("> Choix invalide !");
			}
		}
	}
}