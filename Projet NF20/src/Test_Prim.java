


public class Test_Prim {

	public static void main(String[] arg) {
		
		// Test Prim sur un graph fichier
		
		Graph g = new Graph();
		g.loadFile("C:/Users/Robin/Desktop/Projet NF20/files/inst_v111.txt"); // Chemin de Robin
		//g.loadFile("H:/ISI1/NF20/Projet/inst_v100.dat"); // Chemin de Thomas
		g.supplyNbNodes();
		g.supplyMatrice();
		
		System.out.println(Prim.do_Prim(g.getMatrice()));
	}
}
