/**
 * Classe modélisant une arête en objet
 * @author Robin
 *
 */
public class Edge {

	private int sommet1;
	private int sommet2;
	private int poids;
	
	public Edge(int sommet1, int sommet2, int poids) {
		this.sommet1 = sommet1;
		this.sommet2 = sommet2;
		this.poids = poids;
	}
	

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(sommet1 +" \t" + sommet2 + "\t" + poids);
		return sb.toString();
	}
	
	
	public int getSommet1() {
		return sommet1;
	}


	public void setSommet1(int sommet1) {
		this.sommet1 = sommet1;
	}


	public void setSommet2(int sommet2) {
		this.sommet2 = sommet2;
	}


	public void setPoids(int poids) {
		this.poids = poids;
	}


	public int getSommet2() {
		return sommet2;
	}


	public int getPoids() {
		return poids;
	}

	
}
