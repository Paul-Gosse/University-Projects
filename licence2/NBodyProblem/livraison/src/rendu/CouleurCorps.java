package rendu;
import java.util.*;
import java.awt.Color;
public class CouleurCorps {//classe contenant une HashMap dans laquelle des noms de Corps sont associes a une couleur
	public HashMap<String,Color> mapCouleur=new HashMap<String,Color>();
	public CouleurCorps() {
		mapCouleur.put("Soleil", Color.YELLOW);
		mapCouleur.put("Mercure",Color.GRAY);
		mapCouleur.put("Venus",new Color(236,153,66));
		mapCouleur.put("Terre",new Color(50,102,213));
		mapCouleur.put("Mars",Color.RED);
		mapCouleur.put("Jupiter",new Color(237,71,34));
		mapCouleur.put("Saturne",new Color(234,177,133));
		mapCouleur.put("Uranus", Color.BLUE);
		mapCouleur.put("Neptune",new Color(109,240,154));
		mapCouleur.put("",new Color(255,0,193));
		mapCouleur.put("c1",Color.BLUE);
		mapCouleur.put("c2",Color.GREEN);
		mapCouleur.put("c3",Color.RED);
	}
}
