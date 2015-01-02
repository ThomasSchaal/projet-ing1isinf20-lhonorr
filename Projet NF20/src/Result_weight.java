import java.util.LinkedList;
import java.util.List;


public class Result_weight {
	private List<Edge> edges = new LinkedList<>();
	private int indice;
	private boolean delete;

	public Result_weight(boolean delete, int indice, List<Edge> edges) {
		this.delete = delete;
		this.indice = indice;
		this.edges = edges;
	}
	

	public List<Edge> getEdges() {
		return edges;
	}

	public int getIndice() {
		return indice;
	}

	public boolean isDelete() {
		return delete;
	}
}
