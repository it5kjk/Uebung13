package gui13;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class JStreetLightPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5818179463391892316L;
	private int phase = 0;
	private Color ctop;
	private Color cmid;
	private Color cbot;
	
	public JStreetLightPanel() {
		super();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		setBackground(new Color(0, 80, 0));
		switch (phase) {
		case 0:
			ctop = Color.gray;
			cmid = Color.gray;
			cbot = Color.gray;
			break;
		case 1:
			ctop = Color.red;
			cmid = Color.gray;
			cbot = Color.gray;
		case 2:
			ctop = Color.red;
			cmid = Color.orange;
			cbot = Color.gray;
			break;
		case 3:
			ctop = Color.gray;
			cmid = Color.gray;
			cbot = Color.green;
			break;
		case 4:
			ctop = Color.red;
			cmid = Color.orange;
			cbot = Color.gray;
			break;
		}
		int h = getHeight() /3-12;
		int b = getWidth() -6;
		g.setColor(ctop);
		g.fillOval(3, 3, b, h);
		g.setColor(cmid);
		g.fillOval(3, getHeight() /3+6, b, h);
		g.setColor(cbot);
		g.fillOval(3, 2* getHeight()/3+9, b, h);
		g.setColor(Color.black);
		g.drawOval(3, 3, b, h);
		g.drawOval(3, getHeight()/3+6, b, h);
		g.drawOval(3, 2*getHeight()/3+9, b, h);
	}
	
	public void setPhase(int i) {
		phase = i;
		repaint();
	}
}
