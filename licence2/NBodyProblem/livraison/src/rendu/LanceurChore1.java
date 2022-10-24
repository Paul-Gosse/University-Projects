package rendu;

public class LanceurChore1 implements Lanceur{//créer une instance de cette classe permet de lancer la simulation de la choregraphie Moth 1
	public long freq=500;//frequence en nanoseconde pour l'actualisation du modele
	public Systeme s;

	public LanceurChore1() {
		lancement();
	}
	public void lancement() {
		double dt=0.000001;//en s
		long lastUpdate=System.nanoTime();
		this.s=new Choregraphie1();
		new FrameSystemGui(this.s,this);//lance l'interface graphique

		while(true) {
		  	if(System.nanoTime()-lastUpdate>freq) {//On verifie que la derniere actualisation n'est pas trop recente
		  		this.s.attractionNCorps(dt);	//on appelle la fonction pour l'attraction gravitationnelle sur tous les corps
		  		lastUpdate=System.nanoTime();
		  	}
		}
	}

	public void ralentir() {//fonction pour diminuer la frequence d'actualisation
		this.freq=(long) (this.freq*1.2);

	}

	public void accelerer() {//fonction pour accelerer la frequence d'actualisation
		this.freq=(long) (this.freq*0.8);
	}


}
