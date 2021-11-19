package nc.unc.kevin.trochon;

import nc.unc.kevin.trochon.calcul.CalculUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    final Exception exception = assertThrows(IllegalArgumentException.class, () -> CalculUtils.division(4, 0));
    assertEquals("denominateur ne doit pas etre 0", exception.getMessage(),"On ne peut pas diviser par 0");
  }

  @Test
  public void testMafonction(){
    assertEquals(2, CalculUtils.maFonction(8,2));
    assertEquals(8, CalculUtils.maFonction(80,10));
  }
}
