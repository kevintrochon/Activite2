package nc.unc.kevin.trochon;

public class GrilleImpl implements Grille{

  @Override
  public int getDimension() {
    return 0;
  }

  @Override
  public void setValue(int x, int y, char value) throws HorsBornesException, ValeurImpossibleException, CaractereInterditException {

  }

  @Override
  public char getValue(int x, int y) throws HorsBornesException {
    return 0;
  }

  @Override
  public boolean complete() {
    return false;
  }

  @Override
  public boolean possible(int x, int y, char value) throws HorsBornesException, CaractereInterditException {
    return false;
  }
}
