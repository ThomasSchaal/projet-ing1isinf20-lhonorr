/**
 * Classe abstraite - gestion de cas particuliers hors arbre
 * @author Robin
 *
 */
public abstract class Handling {
	
	
	/**
	 * Retourne Vrai si la chaîne de caractères est null, vide ou avec
	 * uniquement des espaces
	 * 
	 * @param s
	 *         Chaîne de caractères
	 * @return Vrai si la chaîne de caractères est null, vide ou avec uniquement
	 *         des espaces ; Sinon Faux
	 */
	public static boolean nullOrSpacesOrEmpty(String s) {
		return s == null || s.trim().equalsIgnoreCase("");
	}
}
