package taquin;


/**
 * Classe de la vue console du jeu 
 */
public class VueConsole implements EcouteurModel {
	protected Jeu j;
	
	/**
	 * Cr�er la vue console du jeu
	 * @param j jeu affich�
	 */
	public VueConsole(Jeu j) {
		this.j=j;
		j.ajoutEcouteur(this);
	}
	
	/**
	 * Affiche le jeu dans la console
	 */
	public void afficheJeu() {
		int n =j.getN();
		int m=j.getM();
		Piece[][] grille=j.getGrille();
		String ligne="";
	    for(int i=0;i<n;i++){
	      ligne= " | ";
	      for(int j=0;j<m;j++){
	    	String affichagePiece=null;
	    	if(!grille[i][j].estVide()) {
	    		affichagePiece=""+grille[i][j].getNumero();
	    		if(affichagePiece.length()!=2) {  // On fixe l'affichage de chaque numéro de pièce à 2 caracteres afin que les nombres de 1 chiffre et ceux de 2 chiffres prennent la même place  
	    			affichagePiece+=" ";
	    		}
	    	}
	    	else {// si c'est la pièce vide , on n'affiche pas son numéro
	    		affichagePiece="  ";
	    	}
	        ligne+= affichagePiece + " | ";
	      }
	      System.out.println(ligne);
	    }
	    System.out.println("--------------");
	}
	
	/**
	 * Mise � jour de la vue
	 */
	public void ModelMiseAjour(Object Source) {
		this.afficheJeu();
	}
}
