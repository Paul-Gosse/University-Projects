package rendu;

public class Choregraphie1 extends Systeme {//classe avec les conditions initiales pour instantier la choregraphie Moth 1
	public Choregraphie1() {
		super();
		Corps c1,c2,c3;Point p1,p2,p3;Vecteur v1,v2,v3;
		p1=new Point(-1, 0,0);
		p2=new Point( 1,0,0);
		p3=new Point( 0         , 0         ,0);
		double pa1=0.464445;
		double pa2=0.396060;
		v3= new Vecteur(-2*pa1, -2*pa2,0);
		v2=new Vecteur(pa1, pa2,0);
		v1=new Vecteur(pa1, pa2,0);

		c1=new Corps(p1, 1, v1, Corps.G2, 10000, "c1");
		c2=new Corps(p2, 1, v2, Corps.G2,  10000, "c2");
		c3=new Corps(p3, 1, v3, Corps.G2, 10000, "c3");

		this.addCorps(c3);
		this.addCorps(c2);
		this.addCorps(c1);
	}
}
