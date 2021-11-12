package couverturedecode.test;

import couverturedecode.CalculUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculTest {

  @Test
  public void testConstructeur() {
    new CalculUtils();
  }
  @Test
  public void testSomme() {
    assertEquals(5, CalculUtils.somme(2,3));
  }

  @Test
  public void testDivision() {
    assertEquals(4, CalculUtils.division(8,2));
  }

  @Test
  public void testDivisionEchecTableauVide() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      CalculUtils.division(8,0); });
  }

  @Test
  public void testMafonction(){
    assertEquals(2, CalculUtils.maFonction(8,2));
    assertEquals(8, CalculUtils.maFonction(80,10));
  }
}
