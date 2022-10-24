package rendu;
import java.util.*;
import java.lang.Math.*;

public class Point {//classe representant un point 3D et permettant de faire des operations basiques dessus
  protected double X;
  protected double Y;
  protected double Z;

  public Point(double X,double Y, double Z){
    this.X=X;
    this.Y=Y;
    this.Z=Z;
  }
  public Point() {
	  this(0,0,0);
  }
  public Point(Point p) {
	  this(p.getX(),p.getY(),p.getZ());
  }

  public double getX(){
    return this.X;
  }

  public double getY(){
    return this.Y;
  }

  public double getZ(){
    return this.Z;
  }

  public Point translate(double x,double y,double z){
    return new Point(this.X+x,this.Y+y,this.Z+z);
  }
  public Point translate(Vecteur v) {
	  return(this.translate(v.getX(), v.getY(), v.getZ()));
  }


  public String toString(){
    return "("+this.X+","+this.Y+","+this.Z+")";
  }

  public double distance(Point p2){//calcule la distance entre ce point et le point p2
    return Math.sqrt((this.X-p2.getX())*(this.X-p2.getX()) + (this.Y-p2.getY())*(this.Y-p2.getY()) + (this.Z-p2.getZ())*(this.Z-p2.getZ()) );
  }
}
