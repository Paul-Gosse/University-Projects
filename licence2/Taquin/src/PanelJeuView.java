package taquin;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Vue du jeu
 */
public class PanelJeuView extends JPanel implements EcouteurModel {
	public Jeu j;
	public int nbLignes;
	public int nbColonnes;
	public int largeur=600;
	public int hauteur=600;
	
	private int mouseX=0;//stock les coordonnées X-Y du curseur de la souris, l'unité n'est pas le pixel mais la taille d'une pièce (mouseX=a ,mouseY=b => le curseur est actuellement sur la pièce grille[b][a]) 
	private int mouseY=0;
	
	/**
	 * Cr�er la classe de vue du jeu
	 * @param j jeu affich�
	 */
	public PanelJeuView(Jeu j) {
		super();
		this.j=j;
		j.ajoutEcouteur(this);
		
		this.nbLignes=j.getN();
		this.nbColonnes=j.getM();
		
		this.setPreferredSize(new Dimension(this.largeur,this.hauteur));
		this.repaint();
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				int posX=arg0.getX()/(largeur/nbColonnes-1);
				int posY=arg0.getY()/(hauteur/nbLignes-1);
				j.deplacement(posY, posX,true);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
		});
		
		
		this.addMouseMotionListener(new MouseMotionListener() {


			public void mouseDragged(MouseEvent e) {
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				 
				 int mouseX2=e.getX()/(largeur/nbColonnes-1);
				 int mouseY2=e.getY()/(hauteur/nbLignes-1);
				 if(mouseX!=mouseX2 || mouseY!=mouseY2) {// Si le curseur de la souris n'est plus sur la même Piece , on appelle repaint
					 mouseX=mouseX2;
					 mouseY=mouseY2;
					 repaint();
				 }
			}
			
		});
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Piece[][] grille=j.getGrille();
		
		int l=this.largeur/this.nbColonnes-1;
		int L=this.hauteur/this.nbLignes-1;
		g.setColor(Color.black);
		for(int y=0;y<this.nbLignes;y++) {
			for (int x=0 ; x<this.nbColonnes ; x++) {
				Piece p=grille[y][x];
				g.drawRect(x*l, y*L, l, L);

				if(!p.estVide()) {
					String texte=""+grille[y][x].getNumero();
					g.drawString(texte, x*l+l/2, y*L+L/2);
				}
			}
		}
		if(j.deplaPossible(mouseY, mouseX)) {//si la souris se trouve actuellement sur une pièce déplaçable , on dessine un rectangle vert autour d'elle
			g.setColor(Color.green);
			g.drawRect(mouseX*l, mouseY*L, l, L);
		}
	}
	
	
	/**
	 * Mise � jour de la vue
	 */
	public void ModelMiseAjour(Object source) {
		this.repaint();
	}
	
}
