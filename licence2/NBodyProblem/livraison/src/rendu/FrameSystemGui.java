package rendu;

import javax.swing.*;

public class FrameSystemGui extends JFrame {//interface graphique de la simulation. Elle regroupe la vue du Syteme et les bouttons permettant d'interagir sur le systeme
	public JPanel pan=new JPanel();
	public Systeme s;
	public Lanceur l;
	public FrameSystemGui(Systeme s,Lanceur l) {
		super("N-Corps");
		this.s=s;
		this.l=l;
		PanelSystemView psw=new PanelSystemView(s,l);
		PanelInteractions pi=new PanelInteractions(s,l);


		this.pan.add(psw);
		this.pan.add(pi);
		this.setContentPane(pan);
	  this.pack();
	  this.setVisible(true);
	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  this.toFront();

	}
}
