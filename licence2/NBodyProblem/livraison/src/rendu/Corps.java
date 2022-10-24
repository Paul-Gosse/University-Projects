package rendu;
import java.util.*;
import java.lang.Math.*;

public class Corps{
  protected Point centre;//coordonnées en m
  protected double masse;//en kilo
  protected Vecteur vitesse;//en m/s
  protected Vecteur acceleration;//en m/s-2
  protected double rayon;// en km
  protected double G;//la constante G=G1 si on est dans le système solaire . G=G2 si on est dans une choregraphie
  protected String nom;
  public static double G1=6.67408E-11;
  public static double G2=1;


public Corps(Point centre, double masse, Vecteur vitesse,double G,double rayon,String nom){
  this.centre=centre;
  this.masse=masse;
  this.vitesse = vitesse;
  this.acceleration=new Vecteur(0,0,0);
  this.G=G;
  this.rayon=rayon;
  this.nom=nom;
}
public Corps(Point centre, double masse, Vecteur vitesse){
	  this(centre,masse,vitesse,G1,5000,"");
	}


public Point getCentre(){
  return this.centre;
}
public double getMasse(){
  return this.masse;
}
public Vecteur getVitesse(){
  return this.vitesse;
}
public Vecteur getAcceleration() {
	return this.acceleration;
}
public double getRayon() {
	return this.rayon;
}
public String getNom() {
	return this.nom;
}



public String toString(){
	  return this.nom+" :\n"+"centre: "+this.centre.toString() + "   masse: "+this.masse + "   vitesse: "+this.vitesse.toString() + "   accelération: "+this.acceleration.toString()+"   rayon: "+this.rayon;
	}

public void resetAcceleration() {
	this.acceleration=new Vecteur();
}

public void updateVitesse(double dt){
  this.vitesse=this.vitesse.addVecteur(this.acceleration.multiplie(dt));
}

public void updatePositionCentre(double dt,Vecteur vitesseMoyenne) {
	this.centre=this.centre.translate(vitesseMoyenne.multiplie(dt));
}

public void updateAll(double dt){
	Vecteur precedenteVitesse= new Vecteur(this.vitesse);
	this.updateVitesse(dt);

	Vecteur vitesseMoyenne=precedenteVitesse.addVecteur(this.vitesse).divise(2);//a chaque iteration , on va calculer la position, la vitesse, l'acceleration pour dt secondes.
	this.updatePositionCentre(dt,vitesseMoyenne);                               //pour etre plus precis, on prend la moyenne de: (vitesse initiale + vitesse apres attraction) au lieu de prendre la vitesse apres attraction

	this.resetAcceleration();
}




public Vecteur forceAttraction(Corps c1){ // retourne la valeur de la force d'attraction de c1 sur le Corps qui appelle la méthode
  double d=this.centre.distance(c1.getCentre());;//distance entre les 2 Corps
  Point centre1=c1.getCentre();
  Vecteur vecteurDirection=new Vecteur(this.centre.getX()-centre1.getX(),this.centre.getY()-centre1.getY(),this.centre.getZ()-centre1.getZ()).normalise().multiplie(-1);//vecteur unitaire dans la direction de l'attraction

  Vecteur force=vecteurDirection.multiplie(G).multiplie(this.masse).multiplie(c1.getMasse()).divise(d*d);//On calcule la force d'attraction grace a la loi universelle de la gravitation

  return force;

}

public void force2acceleration(Vecteur force) {
	Vecteur accel1=force.divise(this.masse);//on divise la force par la masse pour obtenir l'acceleration
	this.acceleration=this.acceleration.addVecteur(accel1);
}


public void addAccelerationByCorps(Corps c1) {//calcule la force d'attraction puis change l'acceleration en fonction de cette force
	Vecteur force = this.forceAttraction(c1);
	force2acceleration(force);
}


}
