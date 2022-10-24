package taquin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe représentant le jeu
 */

public class Jeu extends AbstractModelEcoutable {
  protected Piece grille[][];
  protected int n;
  protected int m;
  protected int posVide[];
  protected int nbCoups=0;
  
  /**
   * Créer la classe Jeu 
   * @param n nombre de ligne du jeu
   * @param m nombre de colonne du jeu
   */
  public Jeu(int n,int m){ //longueur et largeur du jeu
    this.grille=new Piece[n][m];
    this.n=n;
    this.m=m;
    this.posVide=new int[2];
    this.posVide[0]=n-1; //Stock la position de la piece vide du jeu
    this.posVide[1]=m-1;
    this.autoFill();
    
    this.ecouteurs=new ArrayList<EcouteurModel>();
  }
  
  public Piece[][] getGrille() {
	  return this.grille;
  }
  public int getN() {
	  return this.n;
  }
  public int getM() {
	  return this.m;
  }
  public int[] getPosVide() {
	  return this.posVide;
  }
  
  public int getNbCoups() {
	  return this.nbCoups;
  }
  
  
/**
 * Fonction qui créer les pièces et permet de remplir le jeu
 */
  public void autoFill(){
    for (int i=0;i<this.n;i++){
      for (int j=0;j<this.m;j++){
        this.addPiece(new Piece(false,i*m + j+1) , i , j);
      }
    }
    this.addPiece(new Piece(true,n*m),n-1,m-1);//On ajoute la piece vide sur la derniere case du jeu;
  }

/**
 * Fonction qui permet d'ajouter une pièce à la position précisée
 * @param p pièce
 * @param pos1 posY
 * @param pos2 posX
 */
  public void addPiece(Piece p,int pos1,int pos2){
    this.grille[pos1][pos2]=p;
  }
 
/**
 * On appelle cette fonction avec la position de la piece qu'on veut bouger si cette position ne permet pas de déplacement, rien ne se passe
 * @param pos1 posY
 * @param pos2 posX
 * @param affiche si cela vaut true alors les écouteurs sont prévenus du changement
 */
  public void deplacement(int pos1,int pos2,boolean affiche){ // On appelle cette fonction avec la position de la piece qu'on veut bouger
    if(deplaPossible(pos1,pos2)){			  // Si cette position ne permet pas de dÃ©placement, rien ne se passe
      Piece p=this.grille[pos1][pos2];
      this.grille[pos1][pos2]=this.grille[this.posVide[0]][this.posVide[1]];
      this.grille[this.posVide[0]][this.posVide[1]]=p;
      this.posVide[0]=pos1;this.posVide[1]=pos2;
      if(affiche) {
    	  this.fireChangement();
    	  }
    }
  }
  
  /**
   * Fonction qui permet de déplacer une pièce sur une direction donnée si cela est possible
   * @param direction bas, haut, droite ou gauche
   * @param affiche si cela vaut true alors les écouteurs sont prévenus du changement
   */
  public void deplacement(String direction,boolean affiche){
    if(direction.equals("haut")){ 							 // On appelle la fonction deplacement
      deplacement(this.posVide[0]+1 , this.posVide[1]  ,affiche);// pour faire monter la piÃ¨ce en dessous de la case vide
    }
    if(direction.equals("bas")){
      deplacement(this.posVide[0]-1 , this.posVide[1]  ,affiche);
    }
    if(direction.equals("gauche")){
      deplacement(this.posVide[0]   , this.posVide[1]+1,affiche);
    }
    if(direction.equals("droite")){
      deplacement(this.posVide[0]   , this.posVide[1]-1,affiche);
    }
  }
  
  /**
   * Fonction qui permet de déplacer une pièce sur une direction donnée si cela est possible
   * @param direction bas, haut, droite ou gauche
   */
  public void deplacement(String direction) {
	  deplacement(direction,true);
  }
  
  
/**
 * Fonction booléenne permettant de savoir si un déplacement est possible en fonction des coordonnées
 * @param pos1 posY
 * @param pos2 posX
 * @return true si le déplacement est possible et false sinon
 */
  public boolean deplaPossible(int pos1,int pos2){
    if(pos1<0 || pos1>=this.n || pos2<0 || pos2>=this.m){ //On vÃ©rifie que les coordonnÃ©es sont dans la grille
      return false;
    }
    if( (pos1==this.posVide[0] && (pos2==this.posVide[1]+1 || pos2==this.posVide[1]-1 ) ) || ( (pos1==this.posVide[0]-1 || pos1==this.posVide[0]+1)&& pos2==this.posVide[1]) ){
      return true; //Si les coordonnÃ©es correspondent Ã  une case adjacente Ã Â la case vide, on retourne true
    }
    else{          //Sinon on retourne false
      return false;
    }
  }

  /**
   * Fonction qui verifie si toutes les pièces de notre puzzle sont dans l'ordre
   * @return true si le puzzle est bien rangé sinon false
   */
  public boolean isFinished(){ // Regarde si toutes les piÃ¨ces du jeu sont dans le bon ordre
    for (int i=0;i<this.n;i++){
      for (int j=0;j<this.m;j++){
        Piece p = this.grille[i][j];
        if(p.getNumero() != (i*n+j+1) ){
          return false;
        }
      }
    }
    System.out.println("Bravo, vous avez gagnÃ© !");
    return true;
  }
  
  /**
   * Fonction qui mélange le jeu
   */
  public void shuffle() {  // Fait des dÃ©placements alÃ©atoires pour mÃ©langer le jeu au dÃ©but
	 String depla[]=new String[4];
	 depla[0]="haut";depla[1]="droite";depla[2]="bas";depla[3]="gauche";
	 java.util.Random randomGenerator=new Random();
	 for(int i=0;i<1000;i++) {
		 deplacement(depla[randomGenerator.nextInt(4)],false);
	 }
	 nbCoups=0;
	 this.fireChangement();//On signale aux Ã©couteurs que le modÃ¨le a changÃ© une fois que le mÃ©lange est fini
  }
  
  
  /**
   * Fonction qui permet de jouer à partir de la console
   * @throws IOException throws Exceptions
   */
  public void jouerConsole() throws IOException {
	  System.out.println("Pour jouer, tapez:   haut, bas, droite ou gauche");
	  BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	  while(!this.isFinished()) {
		  String depla=br.readLine();
		  deplacement(depla);
	  }
  }

/**
 * Fonction qui prévient les écouteurs que le modèle a été modifié
 */
  protected void fireChangement() {
	for(EcouteurModel ecm : this.ecouteurs) {
		ecm.ModelMiseAjour(this);
	}
  }
  
}
