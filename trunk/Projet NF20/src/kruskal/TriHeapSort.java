package kruskal;
/**
 * 
 * @author THOMAS
 *
 */

public class TriHeapSort {
	private static int[][] a;
	private static int n;
	private static int left;
	private static int right;
	private static int largest;

	// Le tri fonctionne, il a un main indépendant pour faire des petits tests, il sera à enlever par la suite 
	// Il ne prend pas en compte une vrai matrice d'entrée pour l'instant, juste une 3*3 de test 
	
	
	public static void buildheap(int[][] a) {
		n = a.length - 1;
		for (int i = n / 2; i >= 0; i--) {
			maxheap(a, i);
		}
	}

	public static void maxheap(int[][] a, int i) {
		left = 2 * i;
		right = 2 * i + 1;
		if (left <= n && a[left][2] > a[i][2]) {
			largest = left;
		} else {
			largest = i;
		}

		if (right <= n && a[right][2] > a[largest][2]) {
			largest = right;
		}
		if (largest != i) {
			exchange(i, largest);
			maxheap(a, largest);
		}
	}

	/**
	 * Cette fonction fait un échange de ligne dans un tableau bidimensionnelle, obvious ! 
	 * @param i
	 * @param j
	 */
	public static void exchange(int i, int j) {
		for(int x =0; x<a.length ;x++){
		int t = a[i][x];
		a[i][x] = a[j][x];
		a[j][x] = t;
		}
	}

	/**
	 * Prend en paramètre une matrice bidimensionnelle 
	 * @param a0
	 */
	public static void sort(int[][] a0) {
		a = a0;
		buildheap(a);

		for (int i = n; i > 0; i--) {
			exchange(0, i);
			n = n - 1;
			maxheap(a, 0);
		}
	}

	/**
	 * Le tri ce fait sur la troisième colonne 
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] a1 = { {4, 1, 3}, {2, 16, 9}, {10, 14, 8}};
		sort(a1);
		for (int i = 0; i < a1.length; i++) {
			System.out.println();
			for (int j =0 ; j< a1.length ; j++){
			System.out.print(a1[i][j] + " ");
			}
		}
	}
}
