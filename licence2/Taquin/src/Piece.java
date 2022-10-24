package taquin;

/**
 * Classe Pièce du jeu
 */
public class Piece{
  protected boolean estVide;
  protected int numero;
  /**
   * Créer une pièce du jeu
   * @param estVide true si c'est la pièce vide et false sinon
   * @param numero numéro de la pièce
   */
  public Piece(boolean estVide,int numero){
    this.estVide=estVide;
    this.numero=numero;
  }

  public int getNumero(){
      return this.numero;
  }

  public boolean estVide(){
    return this.estVide;
  }

  public String toString(){
    return "Piece numero " + this.numero + "   Vide? : " + this.estVide;
  }
}

