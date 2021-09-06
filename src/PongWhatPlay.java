import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class PongWhatPlay extends JPanel implements ActionListener{
	
	private JButton back, levels, quick;
	Pong po = new Pong();
	private static Font retroFont;
	
	
	public PongWhatPlay(Pong p) {
		try {
			retroFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Retro Gaming.ttf"));
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/Retro Gaming.ttf")));
		} catch (IOException|FontFormatException e) {
			retroFont = new Font("Times New Roman", Font.PLAIN, 25);
		}
		
		po = p;
		
		setup();
	}
	
	private void setup() {
		this.setBackground(Color.black);
		
		JLabel title = new JLabel("          Choose Game Type:          ");
		title.setFont(retroFont.deriveFont(85f));
		title.setForeground(Color.white);
		add(title);
	}
	
	
	public void paintComponent(Graphics g) //paints levelsground image and button
	{
		super.paintComponent(g);
		try {
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		back = new JButton("Back");
		back.setBounds(10, 690, 200, 75);
		back.setFont(retroFont.deriveFont(30f));
		back.setBackground(Color.black);
		back.setForeground(Color.white);
		back.setFocusPainted(false);
		back.setOpaque(true);
		back.addActionListener(this);
		add(back);
		
		quick = new JButton("Quick Play");
		quick.setBounds(Pong.w/2 - 400, 350, 350, 75);
		quick.setFont(retroFont.deriveFont(45f));
		quick.setBackground(Color.black);
		quick.setForeground(Color.white);
		quick.setFocusPainted(false);
		quick.setOpaque(true);
		quick.addActionListener(this);
		add(quick);
		
		levels = new JButton("Level Up");
		levels.setBounds(Pong.w/2 + 50, 350, 350, 75);
		levels.setFont(retroFont.deriveFont(50f));
		levels.setBackground(Color.black);
		levels.setForeground(Color.white);
		levels.setFocusPainted(false);
		levels.setOpaque(true);
		levels.addActionListener(this);
		add(levels);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			po.showHome();
		}
		if (e.getSource() == levels) {
			Pong.cards.remove(Pong.play);
			Pong.lev = 1;
			Pong.play = new PongPlay(po);
			Pong.cards.add(Pong.play, "Pong Play");
			po.cl.show(Pong.cards, "Pong Play");
		}
		if (e.getSource() == quick) {
			Pong.cards.remove(Pong.play);
			Pong.lev = 0;
			Pong.play = new PongPlay(po);
			Pong.cards.add(Pong.play, "Pong Play");
			po.cl.show(Pong.cards, "Pong Play");
		}
		
	}

}
