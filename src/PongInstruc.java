import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class PongInstruc extends JPanel implements ActionListener{
	
	
	Pong po = new Pong();
	private static Font retroFont;
	private JButton back;
	private JLabel l;
	
	public PongInstruc(Pong p) {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			po.showHome();
		}
		
	}

	private void setup() {
		this.setBackground(Color.black);
		
		JLabel title = new JLabel("          Instructions:          ");
		title.setFont(retroFont.deriveFont(85f));
		title.setForeground(Color.white);
		
		JLabel first = new JLabel("          Player 1 controls the left racket while Player 2 controls the right racket          ");
		first.setFont(retroFont.deriveFont(30f));
		first.setForeground(Color.white);
		
		JLabel first2 = new JLabel("          Player 1 controls the left racket while Player 2 controls the right racket          ");
		first2.setFont(retroFont.deriveFont(30f));
		first2.setForeground(Color.black);
		
		JLabel second = new JLabel("          Slide the rackets up and down so the ball doesn't bounce out of the screen          ");
		second.setFont(retroFont.deriveFont(30f));
		second.setForeground(Color.white);
		
		JLabel second2 = new JLabel("          Slide the rackets up and down so the ball doesn't bounce out of the screen          ");
		second2.setFont(retroFont.deriveFont(30f));
		second2.setForeground(Color.black);
		
		JLabel third = new JLabel("          Each time the ball goes out behind your racket,          ");
		third.setFont(retroFont.deriveFont(30f));
		third.setForeground(Color.white);
		
		JLabel fourth = new JLabel("          the other player receives a point          ");
		fourth.setFont(retroFont.deriveFont(30f));
		fourth.setForeground(Color.white);
		
		JLabel fourth2 = new JLabel("          the other player receives a point          ");
		fourth2.setFont(retroFont.deriveFont(30f));
		fourth2.setForeground(Color.black);
		
		JLabel fifth = new JLabel("          To free play continuously, click \"Quick Play\"          ");
		fifth.setFont(retroFont.deriveFont(30f));
		fifth.setForeground(Color.white);
		
		JLabel fifth2 = new JLabel("          To free play continuously, click \"Quick Play\"          ");
		fifth2.setFont(retroFont.deriveFont(30f));
		fifth2.setForeground(Color.black);
		
		JLabel sixth = new JLabel("          To play in progressing levels, click \"Level Up\"          ");
		sixth.setFont(retroFont.deriveFont(30f));
		sixth.setForeground(Color.white);
		
		JLabel lucky = new JLabel("          Good luck players, and have fun!          ");
		lucky.setFont(retroFont.deriveFont(30f));
		lucky.setForeground(Color.white);
		
		add(title);
		add(first);
		add(first2);
		add(second);
		add(second2);
		add(third);
		add(fourth);
		add(fourth2);
		add(fifth);
		add(fifth2);
		add(sixth);
		add(lucky);
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
	
	
}
