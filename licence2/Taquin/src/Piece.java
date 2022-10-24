package taquin;

/**
 * Classe Pi�ce du jeu
 */
public class Piece{
  protected boolean estVide;
  protected int numero;
  /**
   * Cr�er une pi�ce du jeu
   * @param estVide true si c'est la pi�ce vide et false sinon
   * @param numero num�ro de la pi�ce
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

