package couverturedecode;

/**
 * Classe de calcule entre deux termes.
 */
public class CalculUtils {

  /**
   * Constante qui permet de mettre au limite à ma fonction.
   */
  private static final int LIMITE = 10;

  /**
   * CalculUtils la somme de deux nombres.
   *
   * @param premierTerme premier terme de la somme.
   * @param secondTerme second terme de la somme.
   * @return le résultat de la somme.
   */
  public static int somme(final int premierTerme, final int secondTerme) {
    return premierTerme + secondTerme;
  }

  /**
   * Procède à la division que si le dénominateur est > 10 sinon ke retourne.
   *
   * @param premierTerme numérateur.
   * @param secondTerme dénominateur.
   * @return le résultat du calcul.
   */

  public static int maFonction(final int premierTerme, final int secondTerme) {
    int resultat;
    if (secondTerme >= LIMITE) {
      resultat = premierTerme / secondTerme;
    } else {
      resultat = secondTerme;
    }
    return resultat;
  }

  /**
   * Division de nombre entier.
   *
   * @param denominateur dénominateur de la division.
   * @param numerateur numérateur de la division.
   * @return numerateur / denominateur si denominateur != 0
   * @throw IllegalArgumentException si denominateur == 0
   */
  public static int division(final int numerateur, final int denominateur) {
    if (denominateur == 0) {
      throw new IllegalArgumentException("denominateur ne doit pas etre 0");
    }
    return numerateur / denominateur;
  }
}
