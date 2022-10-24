package rendu;

public class LanceurSystemeSolaire implements Lanceur {//créer une instance de cette classe permet de lancer la simulation pour le Systeme Solaire
	public long freq=100000;//frequence en nanoseconde pour l'actualisation du modele
	public Systeme s;

	public LanceurSystemeSolaire() {
		lancement();
	}
	public void lancement() {
		double dt=1800;//en s
		long lastUpdate=System.nanoTime();
		this.s=new SystemeSolaire();
		new FrameSystemGui(s,this);//lance l'interface graphique


		while(true) {
		  	if(System.nanoTime()-lastUpdate>freq) {//On verifie que la derniere actualisation n'est pas trop recente
		  		s.attractionNCorps(dt);//on appelle la fonction pour l'attraction gravitationnelle sur tous les corps
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
