package rendu;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Math.*;


public class PanelSystemView extends JPanel implements EcouteurModel  {//Vue du systeme
	public Systeme systeme;
	public Lanceur l;
	public ArrayList<Corps> corps;
	public double ratioDiminution;//ratio pour le zoom
	public int hauteur=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-70;//on adapte la taille de la fenetre en fonction de la taille de l'écran
	public int largeur=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-230;
	private int transX;
	private int transY;
	private int mouseX=-9999;//valeur arbitraire pour l'initialisation
	private int mouseY=-9999;
	private HashMap<String,Color> mesCouleurs=new CouleurCorps().mapCouleur;


	public PanelSystemView(Systeme s,Lanceur l) {
		super();
		this.systeme=s;
		this.l=l;
		s.ajoutEcouteur(this);
		this.corps=s.getCorps();
		this.setPreferredSize(new Dimension(largeur,hauteur));
		initRatioDiminution(corps);//appelle a la fonction qui permet d'initialiser le zoom en fonction des Corps du systeme

		this.addMouseWheelListener(new MouseWheelListener() {// Permet de zoomer/dezoomer avec la molette de la souris

			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				ratioDiminution=ratioDiminution*(1+arg0.getPreciseWheelRotation()/25);
				repaint();
			}

		});
		this.addMouseListener(new MouseListener( ) {

			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {//quand le clic est relaché , on reinitialise les valeurs (sert pour le MouseDragged ci-dessous)
				mouseX=-9999;mouseY=-9999;
			}

		});

		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent arg0) {//permet de deplacer la vue en cliquant et deplacant la souris
				int newMouseX=arg0.getX();
				int newMouseY=arg0.getY();
				if(mouseX!=-9999) {
					transX+=newMouseX-mouseX;
					transY+=newMouseY-mouseY;
					repaint();
				}
				mouseX=newMouseX;
				mouseY=newMouseY;
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {}

		});

		this.transX=this.largeur/2;//permet que le point (0,0) soit au milieu de la fenetre et non en haut a gauche
		this.transY=this.hauteur/2;
		this.repaint();


	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.translate(transX, transY);
		g.setColor(Color.BLACK);
		g.fillRect(-transX, -transY, largeur, hauteur);//dessine le background en noir

		int legendX=-transX+5;//coordonnees du debut de la legende
		int legendY=-transY+hauteur-25;

		int compteur=0;
		for(Corps c : corps) {//pour chaque corps on le dessine et on dessine sa legende
			String nomCorps=c.getNom();
			Color couleur;
			if(mesCouleurs.get(nomCorps)==null) {//on regarde si le nom du Corps est associe a une couleur
				couleur=new Color(255,0,193);
			}
			else {
				couleur=mesCouleurs.get(nomCorps);
			}
			g.setColor(couleur);

			int x =(int) (c.getCentre().getX()/ratioDiminution );
			int y =(int) (c.getCentre().getY()/ratioDiminution );
			int r =(int) (Math.log(c.getRayon()));

			g.fillOval(x, y, r, r);//on dessine le corps en fonction de ses coordonnees

			//Legende
			g.fillOval(legendX, legendY-20*compteur, r, r);
			g.drawString(c.getNom(), legendX+r+5, legendY-20*compteur + 7);

			compteur+=1;
		}

		//dessin de l'echelle
		g.setColor(Color.WHITE);
		g.drawLine(legendX, legendY-20*compteur + 7, legendX+60, legendY-20*compteur + 7);
		g.drawLine(legendX, legendY-20*compteur + 10, legendX, legendY-20*compteur + 4);
		g.drawLine(legendX+60, legendY-20*compteur + 10, legendX+60, legendY-20*compteur + 4);
		String echelle1=""+(60*ratioDiminution);
		int indexE=echelle1.indexOf("E");
		String echelle=echelle1.substring(0,5);
		if(indexE!=-1) {echelle=echelle+echelle1.substring(indexE);}
		g.drawString(echelle+" m", legendX, legendY-20*compteur );
	}

	public void ModelMiseAjour(Object source) {
		this.repaint();
	}



	public void initRatioDiminution(ArrayList<Corps> list) {//fonction permettant d'initialiser le zoom initial en fonction des Corps du systeme
		int n=0;
		while(n<list.size()&&list.get(n).getCentre().getX()==0) {
			n+=1;
		}

		double centreX=list.get(n).getCentre().getX();
		double centreY=list.get(n).getCentre().getY();

		if(centreX==0) {centreX=1;}
		ratioDiminution=Math.abs((100*centreX)/(largeur*35));

		if(Math.abs(centreY/ratioDiminution/hauteur)>0.35) {
			ratioDiminution=Math.abs((100*centreY)/(hauteur*35));

		}

		for(int i =n+1;i<list.size();i++) {
			centreX=list.get(i).getCentre().getX();
			centreY=list.get(i).getCentre().getY();
			if(Math.abs(centreY/ratioDiminution/hauteur)>0.35) {
				ratioDiminution=Math.abs((100*centreY)/(hauteur*35));
			}
			if(Math.abs(centreX/ratioDiminution/largeur)>0.35) {
				ratioDiminution=Math.abs((100*centreX)/(largeur*35));
			}

		}
	}

}
