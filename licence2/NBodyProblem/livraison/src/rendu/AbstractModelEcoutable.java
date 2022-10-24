package rendu;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;


public abstract class AbstractModelEcoutable implements ModelEcoutable{
  protected List<EcouteurModel>ecouteurs;

  @Override
  public void ajoutEcouteur(EcouteurModel ec){
    ecouteurs.add(ec);
  }

  @Override
  public void retraitEcouteur(EcouteurModel ec){
    ecouteurs.remove(ec);
  }

  protected abstract void fireChangement();//methode appellee par le modele pour prevenir les ecouteurs d'un changement;
}
