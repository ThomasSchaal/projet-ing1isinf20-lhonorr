
public class Test_CycleElimination {

	public static void main(String[] arg) {
		
		Graph g = new Graph();
		g.loadFile("C:/Users/Robin/Desktop/Projet NF20/files/inst_v13.txt"); // Chemin de Robin
		g.supplyNbNodes();
		g.supplyMatrice();
		
		System.out.println(CycleElimination.do_CycleElimination(g.getMatriceTest()));
	}
}
