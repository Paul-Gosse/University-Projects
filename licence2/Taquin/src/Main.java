package taquin;

/**
 * Fonction qui lance notre application
 */
public class Main {

	public static void main(String[] args) throws Exception{
		boolean playConsole=false;
		boolean withImage=true;
		int hauteur=3;
		int largeur=3;
		
	    Jeu jeu = new Jeu(hauteur,largeur);
	    if(playConsole) {
	    	new VueConsole(jeu);
	    	jeu.shuffle();
	    	jeu.jouerConsole() ; 
	    }
	    else {
	    	new FrameJeuGui(jeu,withImage);
	    }
	    
	}

}
