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
public class PongSave extends JPanel implements ActionListener{
	
	private JButton save, notSave;
	Pong po = new Pong();
	private static Font retroFont;
	
	
	public PongSave(Pong p) {
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
		
		JLabel title = new JLabel("          Save Score?          ");
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
		
		
		save = new JButton("Save");
		save.setBounds(Pong.w/2 - 400, 350, 350, 75);
		save.setFont(retroFont.deriveFont(45f));
		save.setBackground(Color.black);
		save.setForeground(Color.white);
		save.setFocusPainted(false);
		save.setOpaque(true);
		save.addActionListener(this);
		add(save);
		
		notSave = new JButton("Don't Save");
		notSave.setBounds(Pong.w/2 + 50, 350, 350, 75);
		notSave.setFont(retroFont.deriveFont(40f));
		notSave.setBackground(Color.black);
		notSave.setForeground(Color.white);
		notSave.setFocusPainted(false);
		notSave.setOpaque(true);
		notSave.addActionListener(this);
		add(notSave);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == save) {
			Pong.savePL = PongPlay.scoreL;
			Pong.savePR = PongPlay.scoreR;
			Pong.lev = Pong.play.level;
			po.showHome();
		}
		if (e.getSource() == notSave) {
			Pong.savePL = 0;
			Pong.savePR = 0;
			Pong.lev = 0;
			po.showHome();
		}
		
	}

}
