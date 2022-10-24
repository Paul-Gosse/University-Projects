package rendu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class PanelInteractions extends JPanel {
	public Systeme s;
	public Lanceur l;
	public int hauteur=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-70;
	public int largeur=200;
	private ArrayList<JTextField> listJTF=new ArrayList<JTextField>();//liste des JTextField dont le contenu represente des nombres


	public PanelInteractions(Systeme s,Lanceur l) {
		super();
		this.setPreferredSize(new Dimension(largeur,hauteur));
		this.s=s;
		this.l=l;

		JLabel centre=new JLabel("centre:");JLabel masse=new JLabel("masse:");JLabel vitesse=new JLabel("vitesse:");JLabel nom=new JLabel("nom:");
		JLabel quelS=new JLabel("Chore?(y/n):");//"y" si on est dans une choregraphie ."n" sinon
		JTextField x=new JTextField() ;JTextField y=new JTextField() ;JTextField z=new JTextField();//zone pour les coordonnees du centre
		JTextField m=new JTextField() ;//pour la masse
		JTextField vx=new JTextField();JTextField vy=new JTextField();JTextField vz=new JTextField();//pour la vitesse
		JTextField n=new JTextField();//pour le nom
		JTextField quelSysteme=new JTextField();//"y" si on est dans une choregraphie ."n" sinon

		listJTF.add(x);listJTF.add(y);listJTF.add(z);listJTF.add(m);listJTF.add(vx);listJTF.add(vy);listJTF.add(vz);


		JButton accel=new JButton("Accelerer +20%");//boutton permettant d'accelerer de 20%
		accel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				l.accelerer();
			}
		});


		JButton slow=new JButton("Ralentir -20%");//boutton permettant de ralentir de 20%
		slow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				l.ralentir();
			}
		});


		JButton ajoute=new JButton("Ajouter le corps");//boutton qui recupere les donnees renseignees dans les zones de texte pour creer un corps et l'ajouter au systeme
		ajoute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Double> listVal=new ArrayList<Double>();//liste contenant les valeurs des JTextField sous forme de Double
				double val=0;
				for(JTextField JTF:listJTF) {//pour les zones de texte representant des nombres , on cherche s'il y a un "E" qui doit etre interprete comme *10puissance
					String text=JTF.getText();
					int indexE=-1;
					for(int i=0;i<text.length();i++) {
						if(text.substring(i,i+1).equals("E")) {
							indexE=i;
						}
					}
					if(indexE==-1) {val=Double.parseDouble(text);}
					else {
						if(indexE==0) {val=1*Math.pow(10, Double.parseDouble(text.substring(indexE+1)));}
						else{val=Double.parseDouble(text.substring(0,indexE))*Math.pow(10, Double.parseDouble(text.substring(indexE+1)));}
						}
					listVal.add(val);
				}


				String name=n.getText();
				double G=0;double rayon=0;
				if(quelSysteme.getText().equals("y")) {//il faut donner la bonne valeur a la constante de gravitation G
					G=Corps.G2;//si l'utilisateur a mis "y" a la question Chore, on met G=1
				}
				else {
					G=Corps.G1;//sinon G=6.67E-11
				}

				Corps c=new Corps(new Point(listVal.get(0),listVal.get(1),listVal.get(2)),listVal.get(3),new Vecteur(listVal.get(4),listVal.get(5),listVal.get(6)),G,10000,name);//On cree le Corps avec toutes les donnees
				s.addCorps(c);//on ajoute le Corps qu'on vient de creer au Systeme
			}

		});


		//Ajout de tous les bouttons et zones de texte a la fenetre
		JPanel panCentre=new JPanel();JPanel panMasse=new JPanel();JPanel panVitesse=new JPanel();JPanel panNom=new JPanel();JPanel panSys=new JPanel();
		panCentre.setLayout(new GridLayout(1,4));
		panMasse.setLayout(new GridLayout(1,2));
		panVitesse.setLayout(new GridLayout(1,4));
		panNom.setLayout(new GridLayout(1,2));
		panSys.setLayout(new GridLayout(1,2));

		panCentre.add(centre);panCentre.add(x);panCentre.add(y);panCentre.add(z);
		panMasse.add(masse);panMasse.add(m);
		panVitesse.add(vitesse);panVitesse.add(vx);panVitesse.add(vy);panVitesse.add(vz);
		panNom.add(nom);panNom.add(n);
		panSys.add(quelS);panSys.add(quelSysteme);

		this.add(panCentre);
		this.add(panMasse);this.add(panVitesse);this.add(panNom);this.add(panSys);this.add(ajoute);this.add(accel);this.add(slow);
		this.setLayout(new GridLayout(8,1));
	}
}
