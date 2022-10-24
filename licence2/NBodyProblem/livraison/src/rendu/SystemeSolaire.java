package rendu;

public class SystemeSolaire extends Systeme{//classe regroupant les conditions initiales pour instantier le systeme solaire
	public SystemeSolaire() {
		super();
		//                                 centre(m)                               Masse(kg)    Vitesse(m/s)                                                        G        Rayon(km)   Nom
		Corps Soleil  =new Corps(new Point(0,0,0)                                 ,1.98855E+30 ,new Vecteur(0,0,0)                                                 ,Corps.G1 ,639934 ,"Soleil");
		Corps Mercure =new Corps(new Point(1.40191E+10,-6.64159E+10,-6.71319E+9)  ,0.33011E+24 ,new Vecteur(3.78936E+1,1.25295E+01,-2.45039E+01).multiplie(1000)   ,Corps.G1 ,2440   ,"Mercure");
		Corps Venus   =new Corps(new Point(-9.90252E+10,4.14922E+10,6.28376E+9)   ,4.8675E+24  ,new Vecteur(-1.36922E+01,-3.24633E+01,3.44688E-01).multiplie(1000) ,Corps.G1 ,6052   ,"Venus");
		Corps Terre   =new Corps(new Point(-1.44286E+11,-3.97378E+10,1.86460E+6)  ,5.9723E+24  ,new Vecteur(7.42866E+00,-2.88200E+01,7.64891E-04).multiplie(1000)  ,Corps.G1 ,6371   ,"Terre");
		Corps Mars    =new Corps(new Point(-2.68704E+10,-2.18308E+11,-3.91509E+9) ,0.64171E+24 ,new Vecteur(2.49637E+01,-8.76675E-01,-6.30821E-01).multiplie(1000) ,Corps.G1 ,3390   ,"Mars");
		Corps Jupiter =new Corps(new Point(1.83152E+11,-7.54925E+11,-9.62242E+8)  ,1898.19E+24 ,new Vecteur(1.25518E+01,3.70149E+00,-2.96220E-01).multiplie(1000)  ,Corps.G1 ,69991  ,"Jupiter");
		Corps Saturne =new Corps(new Point(6.36389E+11,-1.35785E+12,-1.72278E+9)  ,568.34E+24  ,new Vecteur(8.22566E+00,4.07725E+00,-3.98732E-01).multiplie(1000)  ,Corps.G1 ,58232  ,"Saturne");
		Corps Uranus =new Corps(new Point(2.39447E+12,1.74516E+12,-2.45329E+10)  ,102.413E+24 ,new Vecteur(-4.04915E+00,5.18937E+00,7.16753E-02).multiplie(1000)  ,Corps.G1 ,24622  ,"Uranus");
		Corps Neptune  =new Corps(new Point(4.38375E+12,-9.08578E+11,-8.23305E+10) ,86.813E+24  ,new Vecteur(1.07933E+00,5.35931E+00,-1.34968E-01).multiplie(1000)  ,Corps.G1 ,25362  ,"Neptune");
		//Corps Lune    =new Corps(new Point(-1.44604E+11,-3.95639E+10,2.92307E+7)  ,7.342E+22   ,new Vecteur(6.969255E+00,-2.97927E+01,4.51775E-02).multiplie(1000) ,new Vecteur(0,0,0)   ,1737   ,"Lune");
		
		this.addCorps(Soleil);this.addCorps(Mercure);this.addCorps(Venus);this.addCorps(Terre);this.addCorps(Mars);this.addCorps(Jupiter);this.addCorps(Saturne);this.addCorps(Uranus);this.addCorps(Neptune);//this.addCorps(Lune);
	}
}
