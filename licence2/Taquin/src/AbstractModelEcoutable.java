package taquin;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

/**
 * Classe abstraite du modèle écoutable
 */
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

  protected abstract void fireChangement();
}