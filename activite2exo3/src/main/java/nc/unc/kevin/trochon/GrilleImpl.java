package nc.unc.kevin.trochon;

/**
 * Création d'une grille de Sudoku.
 */
public class GrilleImpl implements Grille {
  /**
   * taille de grille 4 x 4.
   */
  private static final int PETITE = 4;
  /**
   * taille de grille 9 x 9.
   */
  private static final int NORMALE = 9;
  /**
   * taille de grille 16 x 16.
   */
  private static final int GRANDE = 16;
  /**
   * taille de grille 25 x 25.
   */
  private static final int GEANTE = 25;
  /**
   * Grille de caractère.
   */
  private final char[][] grille;
  /**
   * Dimension de la grille.
   */
  private final int dimension;
  /** Caractere de case vide. */
  private static final char EMPTY = '@';
  /**
   * Caractere possible a mettre dans la grille.
   * pour une grille 4x4 : 1..4
   * pour une grille 9x9 : 1..9
   * pour une grille 16x16: 0..9-a..f
   * pour une grille 25x25: 0..9-a..o
   */
  private final char[] caracterePossible;

  /**
   * Constructeur de la Grille selon une taille définie.
   *
   * @param tailleUtilisateur taille de la grille.
   *
   */
  public GrilleImpl(final int tailleUtilisateur) {
    if (tailleUtilisateur == PETITE) {
      grille = new char[][]{
          {'1', '2', '3', '4'},
          {'3', '4', '2', '1'},
          {'2', '1', '4', '3'},
          {'4', '3', '1', '2'}
      };
      this.dimension = tailleUtilisateur;
      this.caracterePossible = new char[]{
          '1', '2', '3', '4'};
    } else if (tailleUtilisateur == NORMALE) {
      this.dimension = tailleUtilisateur;
      grille = new char[][]{
        {'@', '6', '@', '@', '@', '1', '@', '@', '2'},
        {'@', '7', '@', '@', '@', '4', '@', '@', '3'},
        {'8', '@', '@', '7', '3', '@', '1', '@', '@'},
        {'@', '@', '9', '@', '@', '@', '8', '@', '@'},
        {'@', '@', '@', '@', '@', '@', '7', '1', '@'},
        {'4', '3', '@', '@', '@', '@', '@', '2', '@'},
        {'@', '1', '@', '2', '@', '@', '@', '9', '@'},
        {'5', '@', '@', '8', '6', '@', '@', '@', '4'},
        {'6', '@', '@', '@', '@', '@', '5', '@', '7'}
      };
      this.caracterePossible = new char[]{
          '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    } else if (tailleUtilisateur == GRANDE) {
      grille = new char[tailleUtilisateur][tailleUtilisateur];
      this.dimension = tailleUtilisateur;
      this.caracterePossible = new char[]{
          '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
          'a', 'b', 'c', 'd', 'e', 'f'};
    } else if (tailleUtilisateur == GEANTE) {
      grille = new char[tailleUtilisateur][tailleUtilisateur];
      this.dimension = tailleUtilisateur;
      this.caracterePossible = new char[]{
          '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
          'k', 'l', 'm', 'n', 'o'};
    } else {
      this.dimension = NORMALE;
      grille = new char[][]{
          {'2', '8', '3', '5', '1', '9', '7', '4', '6'},
          {'9', '6', '4', '8', '7', '3', '5', '2', '1'},
          {'5', '1', '7', '6', '2', '4', '9', '3', '8'},
          {'1', '5', '6', '7', '4', '2', '3', '8', '9'},
          {'@', '@', '@', '@', '@', '@', '7', '1', '@'},
          {'4', '3', '@', '@', '@', '@', '@', '2', '@'},
          {'@', '1', '@', '2', '@', '@', '@', '9', '@'},
          {'@', '@', '@', '8', '6', '@', '@', '@', '@'},
          {'6', '@', '@', '@', '@', '@', '5', '@', '7'}
      };
      this.caracterePossible = new char[]{
          '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    }
  }

  /**
   * Vérification que la valeur saisie soit permise
   * selon la taille de la grille.
   */
  private boolean isCaracterPermis(final char value) {
    boolean isPermis = false;
    for (final char c : this.caracterePossible) {
      if (c == value) {
        isPermis = true;
        break;
      }
    }
    return isPermis;
  }

  /**
   * Calcule du point de départ de la région.
   */
  public int remisePointZeroRegion(final int ligne) {
    final int tailleRegion = (int) Math.sqrt((double) this.getDimension());
    int pointZeroLigne = 0;
    if (ligne >= tailleRegion && ligne < 2 * tailleRegion) {
      pointZeroLigne = tailleRegion + 1;
    } else if (ligne >= 2 * tailleRegion && ligne < 3 * tailleRegion) {
      pointZeroLigne = 2 * tailleRegion + 1;
    } else if (ligne >= 3 * tailleRegion && ligne < 4 * tailleRegion) {
      pointZeroLigne = 3 * tailleRegion + 1;
    } else if (ligne >= 4 * tailleRegion && ligne < 5 * tailleRegion) {
      pointZeroLigne = 4 * tailleRegion + 1;
    }
    return pointZeroLigne;
  }

  /**
  * Vérifie que la valeur
  * n'est pas présente autour de lui.
  */
  public boolean verifRegion(final int ligne, final int colonne, final char value)
      throws HorsBornesException, CaractereInterditException {
    if (ligne < 0 || ligne >= this.getDimension()) {
      throw new HorsBornesException("La coordonnées : " + ligne
          + " n'est pas correcte, elle est hors grille");
    } else if (colonne < 0 || colonne >= this.getDimension()) {
      throw new HorsBornesException("La coordonnées : " + colonne
          + " n'est pas correcte, elle est hors grille");
    } else if (!isCaracterPermis(value)) {
      throw new CaractereInterditException("Le caractère : " + value
          + " que vous avez renseignée n'est pas permise.");
    }
    boolean isPermis = true;
    final int tailleRegion = (int) Math.sqrt((double) this.getDimension());
    final int pointZeroLigne = remisePointZeroRegion(ligne);
    final int pointZeroColonne =  remisePointZeroRegion(colonne);
    for (int i = pointZeroLigne; i < tailleRegion + pointZeroLigne - 1; i++) {
      for (int j = pointZeroColonne; j < tailleRegion + pointZeroColonne - 1; j++) {
        if (grille[i][j] == value) {
          isPermis = false;
          break;
        }
      }
    }
    return isPermis;
  }

  /**
   * Vérifie que le caractère
   * ne soit pas présent sur la ligne.
   */
  public boolean verifLigne(final int ligne, final char value)
      throws HorsBornesException, CaractereInterditException {
    if (ligne < 0 || ligne > this.getDimension()) {
      throw new HorsBornesException("La coordonnées : " + ligne
          + " n'est pas correcte, elle est hors grille");
    } else if (!isCaracterPermis(value)) {
      throw new CaractereInterditException("Le caractère : " + value
          + " que vous avez renseignée n'est pas permise.");
    }
    boolean isPermis = true;
    for (int i = 0; i < grille[ligne].length; i++) {
      if (grille[ligne][i] == value) {
        isPermis = false;
        break;
      }
    }
    return isPermis;
  }

  /**
   * Vérifie que le caractère ne soit pas présent sur la colonne.
   */
  public boolean verifColonne(final int colonne, final char value)
      throws HorsBornesException, CaractereInterditException {
    if (colonne < 0 || colonne > this.getDimension()) {
      throw new HorsBornesException("La coordonnées : " + colonne
          + " n'est pas correcte, elle est hors grille");
    } else if (!isCaracterPermis(value)) {
      throw new CaractereInterditException("Le caractère : " + value
          + " que vous avez renseignée n'est pas permise.");
    }
    boolean isPermis = true;
    for (final char[] chars : grille) {
      if (chars[colonne] == value) {
        isPermis = false;
        break;
      }
    }
    return isPermis;
  }



  /**
   * Donne la dimension de la grille.
   */
  @Override
  public int getDimension() {
    return this.dimension;
  }


  /**
   * Affecte une valeur dans la grille.
   */
  @Override
  public void setValue(final int ligne, final int colonne, final char value)
      throws HorsBornesException, ValeurImpossibleException, CaractereInterditException {
    if (ligne < 0 || ligne > this.getDimension()) {
      throw new HorsBornesException("La coordonnées : " + ligne
          + " n'est pas correcte, elle est hors grille");
    } else if (colonne < 0 || colonne > this.getDimension()) {
      throw new HorsBornesException("La coordonnées : " + colonne
          + " n'est pas correcte, elle est hors grille");
    } else if (!isCaracterPermis(value)) {
      throw new CaractereInterditException("Le caractère : " + value
          + " que vous avez renseignée n'est pas permise.");
    } else if (!this.possible(ligne, colonne, value)) {
      throw new ValeurImpossibleException("La valeur : " + value
          + " est déjà présente dans la ligne ou la colonne ou dans la region");
    } else {
      if (this.getValue(ligne, colonne) == EMPTY) {
        grille[ligne][colonne] = value;
      }
    }
  }

  /**
   * Recupere une valeur de la grille.
   */
  @Override
  public char getValue(final int ligne, final int colonne) throws HorsBornesException {
    if (ligne > this.getDimension() || ligne < 0) {
      throw new HorsBornesException("La coordonnées : " + ligne
          + " n'est pas correcte, elle est hors grille");
    } else if (colonne < 0 || colonne > this.getDimension()) {
      throw new HorsBornesException("La coordonnées : " + colonne
          + " n'est pas correcte, elle est hors grille");
    }
    return grille[ligne][colonne];
  }

  /**
   * Vérifie si une grille est terminée.

   * @return true si la grille est complète.
   */
  @Override
  public boolean complete() {
    boolean isComplete = true;
    try {
      for (int i = 0; i < grille.length; i++) {
        for (int j = 0; j < grille[i].length; j++) {
          if (verifRegion(i, j, grille[i][j]) && verifColonne(i, grille[i][j])
              && verifLigne(j, grille[i][j]) && grille[i][j] != EMPTY) {
            isComplete = false;
            break;
          }
        }
      }
    } catch (HorsBornesException | CaractereInterditException horsBornes) {
      isComplete = false;
    }
    return isComplete;
  }

  /**
  * Vérifie si le caractère dans la grille est possible.
  */
  @Override
  public boolean possible(final int ligne, final int colonne, final char value)
      throws HorsBornesException, CaractereInterditException {
    boolean isPossible = true;
    if (ligne < 0 || ligne > this.getDimension()) {
      throw new HorsBornesException("La coordonnées : " + ligne
          + " n'est pas correcte, elle est hors grille");
    } else if (colonne < 0 || colonne > this.getDimension()) {
      throw new HorsBornesException("La coordonnées : " + colonne
          + " n'est pas correcte, elle est hors grille");
    } else if (!isCaracterPermis(value)) {
      throw new CaractereInterditException("Le caractère : " + value
          + " que vous avez renseignée n'est pas permise.");
    } else if (!verifRegion(ligne, colonne, value)) {
      isPossible = false;
    } else if (!verifColonne(colonne, value)) {
      isPossible = false;
    } else if (!verifLigne(ligne, value)) {
      isPossible = false;
    }
    return isPossible;
  }

}
