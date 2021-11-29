package nc.unc.kevin.trochon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GrilleImplTest {

  @Test
  public void setValueTest() throws ValeurImpossibleException,CaractereInterditException,HorsBornesException{
    GrilleImpl grille = new GrilleImpl(9);
    grille.setValue(0,0,'3');
    Assertions.assertEquals('3',grille.getValue(0,0));
    Assertions.assertThrows(ValeurImpossibleException.class,()->{grille.setValue(0,0,'1');});
    Assertions.assertThrows(ValeurImpossibleException.class,()->{grille.setValue(1,1,'6');});
    Assertions.assertThrows(CaractereInterditException.class,()->{grille.setValue(0,0,'0');});
    Assertions.assertThrows(HorsBornesException.class,()->{grille.setValue(-1,0,'2');});
    Assertions.assertThrows(HorsBornesException.class,()->{grille.setValue(10,0,'2');});
    Assertions.assertThrows(HorsBornesException.class,()->{grille.setValue(0,-1,'2');});
    Assertions.assertThrows(HorsBornesException.class,()->{grille.setValue(0,10,'2');});
    try{
      grille.setValue(-1,0,'2');
    }catch (HorsBornesException horsBornes){
      Assertions.assertEquals("La coordonnées : -1 n'est pas correcte, elle est hors grille",horsBornes.getMessage());
    }
  }

  @Test
  public void constructeurTest(){
    GrilleImpl petiteGrille = new GrilleImpl(4);
    GrilleImpl grille = new GrilleImpl(9);
    GrilleImpl grandeGrille = new GrilleImpl(16);
    GrilleImpl geanteGrille = new GrilleImpl(25);
    GrilleImpl defaultGrille = new GrilleImpl(15);
  }

  @Test
  public void completeTest(){
    GrilleImpl grille = new GrilleImpl(9);
    Assertions.assertFalse(grille.complete());
    GrilleImpl petiteGrille = new GrilleImpl(4);
    Assertions.assertTrue(petiteGrille.complete());
    GrilleImpl nimpGrille = new GrilleImpl(12);
    Assertions.assertFalse(nimpGrille.complete());
  }

  @Test
  public void possibleTest() throws CaractereInterditException,HorsBornesException{
    GrilleImpl grille = new GrilleImpl(9);
    Assertions.assertTrue(grille.possible(0,0,'3'));
    Assertions.assertFalse(grille.possible(0,0,'1'));
    Assertions.assertFalse(grille.possible(2,1,'7'));
    Assertions.assertFalse(grille.possible(3,1,'7'));
    Assertions.assertThrows(CaractereInterditException.class,()->{grille.possible(0,0,'0');});
    Assertions.assertThrows(HorsBornesException.class,()->{grille.possible(-1,0,'2');});
    Assertions.assertThrows(HorsBornesException.class,()->{grille.possible(10,0,'2');});
    Assertions.assertThrows(HorsBornesException.class,()->{grille.possible(0,-1,'2');});
    Assertions.assertThrows(HorsBornesException.class,()->{grille.possible(0,10,'2');});
  }

  @Test
  public void getValueTest() throws HorsBornesException {
    GrilleImpl grille = new GrilleImpl(9);
    Assertions.assertEquals('@',grille.getValue(0,0));
    Assertions.assertThrows(HorsBornesException.class,()->{grille.possible(-1,0,'2');});
    Assertions.assertThrows(HorsBornesException.class,()->{grille.possible(10,0,'2');});
    Assertions.assertThrows(HorsBornesException.class,()->{grille.possible(0,-1,'2');});
    Assertions.assertThrows(HorsBornesException.class,()->{grille.possible(0,10,'2');});
    try{
      grille.getValue(-1,0);
    }catch (HorsBornesException horsBornes){
      Assertions.assertEquals("La coordonnées : -1 n'est pas correcte, elle est hors grille",horsBornes.getMessage());
    }
    try{
      grille.getValue(10,0);
    }catch (HorsBornesException horsBornes){
      Assertions.assertEquals("La coordonnées : 10 n'est pas correcte, elle est hors grille",horsBornes.getMessage());
    }
    try{
      grille.getValue(0,-1);
    }catch (HorsBornesException horsBornes){
      Assertions.assertEquals("La coordonnées : -1 n'est pas correcte, elle est hors grille",horsBornes.getMessage());
    }
    try{
      grille.getValue(0,10);
    }catch (HorsBornesException horsBornes){
      Assertions.assertEquals("La coordonnées : 10 n'est pas correcte, elle est hors grille",horsBornes.getMessage());
    }
  }

  @Test
  public void verifColonneTest(){
    GrilleImpl grille = new GrilleImpl(9);
    try{
      grille.verifColonne(10,'7');
    }catch (HorsBornesException horsBornes){
      Assertions.assertEquals("La coordonnées : 10 n'est pas correcte, elle est hors grille",horsBornes.getMessage());
    }catch (CaractereInterditException caractereInterdit){
      Assertions.assertEquals("Le caractère : @  que vous avez renseignée n'est pas permise.",caractereInterdit.getMessage());
    }

    try{
      grille.verifColonne(-1,'7');
    }catch (HorsBornesException horsBornes){
      Assertions.assertEquals("La coordonnées : -1 n'est pas correcte, elle est hors grille",horsBornes.getMessage());
    }catch (CaractereInterditException caractereInterdit){
      Assertions.assertEquals("Le caractère : @ que vous avez renseignée n'est pas permise.",caractereInterdit.getMessage());
    }
    try{
      grille.verifColonne(1,'@');
    }catch (HorsBornesException horsBornes){
      Assertions.assertEquals("La coordonnées : 10 n'est pas correcte, elle est hors grille",horsBornes.getMessage());
    }catch (CaractereInterditException caractereInterdit){
      Assertions.assertEquals("Le caractère : @ que vous avez renseignée n'est pas permise.",caractereInterdit.getMessage());
    }
  }

  @Test
  public void verifLigneTest(){
    GrilleImpl grille = new GrilleImpl(9);
    try{
      grille.verifLigne(10,'7');
    }catch (HorsBornesException horsBornes){
      Assertions.assertEquals("La coordonnées : 10 n'est pas correcte, elle est hors grille",horsBornes.getMessage());
    }catch (CaractereInterditException caractereInterdit){
      Assertions.assertEquals("Le caractère : @  que vous avez renseignée n'est pas permise.",caractereInterdit.getMessage());
    }

    try{
      grille.verifLigne(-1,'7');
    }catch (HorsBornesException horsBornes){
      Assertions.assertEquals("La coordonnées : -1 n'est pas correcte, elle est hors grille",horsBornes.getMessage());
    }catch (CaractereInterditException caractereInterdit){
      Assertions.assertEquals("Le caractère : @ que vous avez renseignée n'est pas permise.",caractereInterdit.getMessage());
    }
    try{
      grille.verifLigne(1,'@');
    }catch (HorsBornesException horsBornes){
      Assertions.assertEquals("La coordonnées : 10 n'est pas correcte, elle est hors grille",horsBornes.getMessage());
    }catch (CaractereInterditException caractereInterdit){
      Assertions.assertEquals("Le caractère : @ que vous avez renseignée n'est pas permise.",caractereInterdit.getMessage());
    }
  }

  @Test
  public void getDimensionTest(){
    GrilleImpl grille = new GrilleImpl(9);
    Assertions.assertEquals(9,grille.getDimension());
  }

  @Test
  public void verifRegionTest() throws CaractereInterditException, HorsBornesException {
    GrilleImpl grille = new GrilleImpl(9);

    try{
      grille.verifRegion(-1, 0,'7');
    }catch (HorsBornesException horsBornes){
      Assertions.assertEquals("La coordonnées : -1 n'est pas correcte, elle est hors grille",horsBornes.getMessage());
    }catch (CaractereInterditException caractereInterdit){
      Assertions.assertEquals("Le caractère : @  que vous avez renseignée n'est pas permise.",caractereInterdit.getMessage());
    }

    try{
      grille.verifRegion(10, 1, '7');
    }catch (HorsBornesException horsBornes){
      Assertions.assertEquals("La coordonnées : 10 n'est pas correcte, elle est hors grille",horsBornes.getMessage());
    }catch (CaractereInterditException caractereInterdit){
      Assertions.assertEquals("Le caractère : @ que vous avez renseignée n'est pas permise.",caractereInterdit.getMessage());
    }

    try{
      grille.verifRegion(1, -1, '7');
    }catch (HorsBornesException horsBornes){
      Assertions.assertEquals("La coordonnées : -1 n'est pas correcte, elle est hors grille",horsBornes.getMessage());
    }catch (CaractereInterditException caractereInterdit){
      Assertions.assertEquals("Le caractère : @ que vous avez renseignée n'est pas permise.",caractereInterdit.getMessage());
    }

    try{
      grille.verifRegion(1, 10, '7');
    }catch (HorsBornesException horsBornes){
      Assertions.assertEquals("La coordonnées : 10 n'est pas correcte, elle est hors grille",horsBornes.getMessage());
    }catch (CaractereInterditException caractereInterdit){
      Assertions.assertEquals("Le caractère : @ que vous avez renseignée n'est pas permise.",caractereInterdit.getMessage());
    }

    try{
      grille.verifRegion(1, 1, '@');
    }catch (HorsBornesException horsBornes){
      Assertions.assertEquals("La coordonnées : -1 n'est pas correcte, elle est hors grille",horsBornes.getMessage());
    }catch (CaractereInterditException caractereInterdit){
      Assertions.assertEquals("Le caractère : @ que vous avez renseignée n'est pas permise.",caractereInterdit.getMessage());
    }

    Assertions.assertFalse(grille.verifRegion(0,0,'6'));
    Assertions.assertFalse(grille.verifRegion(8,0,'5'));
    Assertions.assertFalse(grille.verifRegion(0,8,'3'));
    Assertions.assertFalse(grille.verifRegion(8,8,'4'));
    Assertions.assertFalse(grille.verifRegion(0,1,'7'));
    Assertions.assertFalse(grille.verifRegion(1,0,'7'));
    Assertions.assertFalse(grille.verifRegion(8,1,'6'));
    Assertions.assertFalse(grille.verifRegion(1,8,'2'));
  }

  @Test
  public void remisePointZeroRegionTest(){
    GrilleImpl grille = new GrilleImpl(9);
    Assertions.assertEquals(0, grille.remisePointZeroRegion(1));
    Assertions.assertEquals(4, grille.remisePointZeroRegion(5));
    Assertions.assertEquals(7, grille.remisePointZeroRegion(8));
    GrilleImpl grandeGrille = new GrilleImpl(25);
    Assertions.assertEquals(11, grandeGrille.remisePointZeroRegion(11));
    Assertions.assertEquals(16, grandeGrille.remisePointZeroRegion(16));
    Assertions.assertEquals(21, grandeGrille.remisePointZeroRegion(22));
  }
}
