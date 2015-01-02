import java.util.LinkedList;
import java.util.List;


public class Result_list {
	private int weight;
	private List<Edge> edges = new LinkedList<>();

	public Result_list(int weight, List<Edge> edges) {
		this.weight = weight;
		this.edges = edges;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		sb.append("Poids de l'arbre : "+ weight +"\n");
		sb.append("Arêtes : \n");
		for(Edge e : edges) {
			sb.append(e.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

}
