package taquin;

import javax.swing.JLabel;

public class LabelCompteurCoups extends JLabel implements EcouteurModel{
    protected Jeu jeu;
    public LabelCompteurCoups(Jeu j) {
        super("Nombre coups: "+j.getNbCoups());
        this.jeu=j;
        j.ajoutEcouteur(this);
    }
    @Override
    public void ModelMiseAjour(Object source) {
        this.setText("Nombre coups: "+this.jeu.getNbCoups());
    }


}