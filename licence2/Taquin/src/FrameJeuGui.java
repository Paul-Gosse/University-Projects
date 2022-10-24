package taquin;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;


/**
 * Interface graphique du jeu 
 */
public class FrameJeuGui extends JFrame {
	public JPanel pan=new JPanel();
	/**
	 * Créer la classe FrameJeuGui
	 * @param jeu Jeu affiché
	 * @param withImage true avec une image et false sinon
	 * @throws Exception throws Exceptions
	 */
	public FrameJeuGui(Jeu jeu,boolean withImage) throws Exception{
		super("Taquin");
		JPanel pjw=null;
		if(withImage) {
			pjw=new PanelJeuViewImage(jeu,"lenna.jpg");
		}
		else {
			pjw=new PanelJeuView(jeu);
		}
		JButton melangeur=new JButton("MÃ©langer");
		
		melangeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.shuffle();
			}
		});
	    melangeur.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				switch(keyCode) {
					case KeyEvent.VK_UP:
						jeu.deplacement("haut");
						break;
					case KeyEvent.VK_DOWN:
						jeu.deplacement("bas");
						break;
					case KeyEvent.VK_LEFT:
						jeu.deplacement("gauche");
						break;
					case KeyEvent.VK_RIGHT:
						jeu.deplacement("droite");
						break;
				}	
			}
			
			public void keyReleased(KeyEvent e) {}

			public void keyTyped(KeyEvent e) {}
		}); 
	    
	    LabelCompteurCoups lcp=new LabelCompteurCoups(jeu);

        JPanel pan2=new JPanel();
        pan2.setLayout(new GridLayout(2,1));
        pan2.add(melangeur);
        pan2.add(lcp);
        
	    this.pan.add(pjw);
	    this.pan.add(pan2);
	    this.setContentPane(pan);
	    this.pack();
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
