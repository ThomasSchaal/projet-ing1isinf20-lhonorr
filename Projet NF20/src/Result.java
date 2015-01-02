/**
 * Classe qui permet de stocker le résultat d'un algorithme
 * @author Robin L'Honoré
 *
 */
public class Result {
	
	private int weight;
	private int[][] edges;
	
	public Result(int weight, int[][] edges) {
		this.weight = weight;
		this.edges = edges;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		sb.append("Poids de l'arbre : "+ weight +"\n");
		sb.append("Arêtes : \n");
		for(int i=0; i<edges.length; i++) {
			sb.append(edges[i][0] + "\t" + edges[i][1] + "\t" + edges[i][2] +"\n");
		}
		return sb.toString();
	}
}
