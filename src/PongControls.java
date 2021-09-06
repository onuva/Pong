import java.awt.Color;
import java.awt.Component;
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
public class PongControls extends JPanel implements ActionListener{

	Pong po = new Pong();
	private static Font retroFont;
	private JButton back;
	private JLabel l;
	
	public PongControls(Pong p) {
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
		
		JLabel title = new JLabel("               Controls:               ");
		title.setFont(retroFont.deriveFont(75f));
		title.setForeground(Color.white);
		add(title);
		
		createCredits("Right Racket:                                                                                               ", 1);
		createCredits("                                  UP - W key           ", 0);
		createCredits("                     Right Racket:                                              ", 0);
		createCredits("                                  DOWN - S key           ", 0);
		createCredits("Right Racket:                                                                                               ", 1);
		createCredits("                                 UP - UP ARROW key             ", 0);
		createCredits("       Left Racket:                                         ", 0);
		createCredits("                                      DOWN - DOWN ARROW key          ", 0);
		createCredits("Right Racket:                                                                                               ", 1);
		createCredits("                           Pause: P key/SPACE BAR                     ", 0);
		createCredits("Right Racket:                                                                                               ", 1);
		
		createCredits("                             Restart: Y key                     ", 0);
	}
	
	private void createCredits(String s, int n) {
		l = new JLabel(s);
		l.setFont(retroFont.deriveFont(30f));
		if (n == 1) l.setForeground(Color.black);
		else l.setForeground(Color.white);
		add(l);
	}
	
	public void paintComponent(Graphics g) //paints background image and button
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
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			po.showHome();
		}
	}
}
