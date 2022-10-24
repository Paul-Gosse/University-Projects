package taquin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Vue du jeu avec image
 */
public class PanelJeuViewImage extends JPanel implements EcouteurModel{
	public Jeu j;
	public int nbLignes;
	public int nbColonnes;
	public int largeur;
	public int hauteur;
	public BufferedImage img;
	public BufferedImage[] subImgs;
	
	private int mouseX=0;
	private int mouseY=0;
	
	/**
	 * Créer la classe de vue du jeu avec image
	 * @param j jeu affiché
	 * @param cheminImage chemin pour accéder à l'image
	 * @throws Exception throws Exceptions
	 */
	public PanelJeuViewImage(Jeu j,String cheminImage) throws Exception {
		super();
		this.j=j;
		this.img=ImageIO.read(new File(cheminImage));
		this.hauteur=img.getHeight();
		this.largeur=img.getWidth();
		j.ajoutEcouteur(this);
		
		this.nbLignes=j.getN();
		this.nbColonnes=j.getM();
		this.subImgs=new BufferedImage[nbLignes*nbColonnes];
		this.createSubImgs();
		
		this.setPreferredSize(new Dimension(this.largeur+5,this.hauteur+5));
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
						 if(mouseX!=mouseX2 || mouseY!=mouseY2) {// Si le curseur de la souris n'est plus sur la mÃªme Piece , on appelle repaint
							 mouseX=mouseX2;
							 mouseY=mouseY2;
							 repaint();
						 }
					}
					
				});
		
		
	}
	
	/**
	 * Fonction qui créer n*m sous-images à partir de notre image de base
	 */
	public void createSubImgs() {
		int l=this.largeur/this.nbColonnes;
		int L=this.hauteur/this.nbLignes;
		for(int y=0;y<this.nbLignes;y++) {
			for (int x=0 ; x<this.nbColonnes ; x++) {
				this.subImgs[x + y*nbColonnes]= img.getSubimage(x*l, y*L, l,L );
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Piece[][] grille=j.getGrille();
		
		int l=this.largeur/this.nbColonnes-1;
		int L=this.hauteur/this.nbLignes-1;
		g.setColor(Color.black);
		for(int y=0;y<this.nbLignes;y++) {
			for (int x=0 ; x<this.nbColonnes ; x++) {
				int numeroPiece=grille[y][x].getNumero();
				if(numeroPiece!=nbColonnes*nbLignes) {
				BufferedImage subImg=subImgs[numeroPiece-1];
				g.drawImage(subImg, x*l, y*L, this);
				}
				g.drawRect(x*l, y*L, l, L);
			}
		}
		if(j.deplaPossible(mouseY, mouseX)) { // si la souris se trouve actuellement sur une piÃ¨ce dÃ©plaÃ§able , on dessine un rectangle vert autour d'elle
			g.setColor(Color.green);
			g.drawRect(mouseX*l, mouseY*L, l, L);
		}
	}
	
	/**
	 * Mise à jour de la vue
	 */
	public void ModelMiseAjour(Object source) {
		this.repaint();
	}

}
