package taquin;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Interface du mod�le �coutable
 */
public interface ModelEcoutable{
  public void ajoutEcouteur(EcouteurModel ec);
  public void retraitEcouteur(EcouteurModel ec);
}