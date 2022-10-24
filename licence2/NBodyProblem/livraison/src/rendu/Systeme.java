package rendu;
import java.util.*;

public class Systeme extends AbstractModelEcoutable{
	protected ArrayList<Corps> listeCorps;

	public Systeme() {
		this.listeCorps=new ArrayList<Corps>();
		this.ecouteurs=new ArrayList<EcouteurModel>();
	}
	public ArrayList<Corps> getCorps(){
		return this.listeCorps;
	}

	public void fireChangement() {
		for(EcouteurModel i: this.ecouteurs) {
			i.ModelMiseAjour(this);
		}
	}

	public void addCorps(Corps c) {
		this.listeCorps.add(c);
		this.fireChangement();
	}


	public void attractionNCorps(double dt) {
		int nbCorps=listeCorps.size();
		for(int i=0;i<nbCorps;i++) {
			Corps c1=listeCorps.get(i);
			for(int j=i+1;j<nbCorps;j++) {
				Corps c2=listeCorps.get(j);
				c1.addAccelerationByCorps(c2);//pour chaque Corps , on calcule ,avec tous les Corps qui se situent après dans la liste, l'attraction gravitationnelle et on met a jour l'acceleration
				c2.addAccelerationByCorps(c1);
			}
			c1.updateAll(dt);//on met a jour toutes les donnees du Corps
		}
		this.fireChangement();
	}

	public String toString() {
		String affichage="Corps dans ce System : \n";
		for(Corps c: listeCorps) {
			affichage+=c.toString()+"\n";
		}
		return affichage;
	}


}
