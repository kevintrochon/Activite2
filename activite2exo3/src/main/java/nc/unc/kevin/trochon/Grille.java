package nc.unc.kevin.trochon;

public interface Grille {
  /** Caractere de case vide */
  char EMPTY = '@';
  /**
   * Caractere possible a mettre dans la grille
   * pour une grille 4x4 : 1..4
   * pour une grille 9x9 : 1..9
   * pour une grille 16x16: 0..9-a..f
   * pour une grille 25x25: 0..9-a..o
   */
  char[] possible = new char[] {
      '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
      'k', 'l', 'm', 'n', 'o'
  };
  /** @return largeur/hauteur de la grille */
  int getDimension();
  /**
   * Affecte une valeur dans la grille
   * @param x position x dans la grille
   * @param y position y dans la grille
   * @param value valeur a mettre dans la case
   * @throw HorsBornesException si x ou y sont hors bornes (0-8)
   * @throw ValeurImpossibleException si la valeur est interdite aux vues des autres valeurs de la grille
   * @throw CaractereInterditException si value n'est pas un caractere autorise ('1',...,'9')
   */
  void setValue(int x, int y, char value) throws HorsBornesException, ValeurImpossibleException,
      CaractereInterditException;
  /**
   * Recupere une valeur de la grille
   * @param x position x dans la grille
   * @param y position y dans la grille
   * @return valeur dans la case x,y
   * @throw HorsBornesException si x ou y sont hors bornes (0-8)
   */
  char getValue(int x, int y) throws HorsBornesException;
  /**
   * Test si une grille est terminee
   * @return true si la grille est complete
   */
  boolean complete();
  /**
   * Test si une valeur est possible dans la grille par rapport a ce qu'elle
   * contient deja
   * @param x position x dans la grille
   * @param y position y dans la grille
   * @param value valeur a mettre dans la case
   * @throw HorsBornesException si x ou y sont hors bornes (0-8)
   * @throw CaractereInterditException si value n'est pas un caractere autorise ('1', ..., '9', ...)
   */
  boolean possible(int x, int y, char value) throws HorsBornesException, CaractereInterditException;
}
