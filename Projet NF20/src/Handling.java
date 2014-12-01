/**
 * Classe abstraite - gestion de cas particuliers hors arbre
 * @author Robin
 *
 */
public abstract class Handling {
	
	
	/**
	 * Retourne Vrai si la cha�ne de caract�res est null, vide ou avec
	 * uniquement des espaces
	 * 
	 * @param s
	 *         Cha�ne de caract�res
	 * @return Vrai si la cha�ne de caract�res est null, vide ou avec uniquement
	 *         des espaces ; Sinon Faux
	 */
	public static boolean nullOrSpacesOrEmpty(String s) {
		return s == null || s.trim().equalsIgnoreCase("");
	}
}
