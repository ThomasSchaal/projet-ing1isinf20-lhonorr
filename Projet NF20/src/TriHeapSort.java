

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

	// Le tri fonctionne, il a un main indépendant pour faire des petits tests,
	// il sera à enlever par la suite
	// Il ne prend pas en compte une vrai matrice d'entrée pour l'instant, juste
	// une 3*3 de test

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
	 * 
	 * @param i
	 * @param j
	 */
	public static void exchange(int i, int j) {
		for (int k = 0; k < 3; k++) {
			int t = a[i][k];
			a[i][k] = a[j][k];
			a[j][k] = t;
		}
	}

	/**
	 * Prend en paramètre une matrice bidimensionnelle
	 * 
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
}
