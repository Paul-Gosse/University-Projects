package rendu;

public class Vecteur {//classe permettant de creer des vecteurs 3D et de faire des opérations basiques dessus
	protected double x;
	protected double y;
	protected double z;

	public Vecteur(double x,double y , double z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	public Vecteur() {
		this(0,0,0);
	}
	public Vecteur(Vecteur v) {
		this(v.getX(),v.getY(),v.getZ());
	}

	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public double getZ() {
		return this.z;
	}

	public Vecteur addVecteur(Vecteur v) {
		double x1=this.x+v.getX();
		double y1=this.y+v.getY();
		double z1=this.z+v.getZ();
		return new Vecteur(x1,y1,z1);
	}

	public Vecteur subVecteur(Vecteur v) {
		double x1=this.x-v.getX();
		double y1=this.y-v.getY();
		double z1=this.z-v.getZ();
		return new Vecteur(x1,y1,z1);
	}

	public Vecteur divise(double d) {
		if (d==0) {
			return this;
		}
		double x1=this.x/d;
		double y1=this.y/d;
		double z1=this.z/d;
		return new Vecteur(x1,y1,z1);
	}

	public Vecteur multiplie(double d) {
		double x1=this.x*d;
		double y1=this.y*d;
		double z1=this.z*d;
		return new Vecteur(x1,y1,z1);
	}
	public double normeVect() {//retourne la norme du vecteur
		return(Math.sqrt(x*x+y*y+z*z));
	}

	public Vecteur normalise() {//transforme le Vecteur en Vecteur unite
		return this.multiplie(1/this.normeVect());
	}

	public String toString() {
		return "( vx: " + this.x +"    vy: "+this.y+"   vz: "+this.z+" )";
	}

}
