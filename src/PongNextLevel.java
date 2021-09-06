import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class PongNextLevel extends JPanel implements ActionListener{
	private JButton back, next;
	Pong po = new Pong();
	private static Font retroFont;
	private static Random r = new Random();
	private JLabel lc, lo1, ln1, lg, lr, la1, lt1, lu, ll, la2, lt2, li, lo2, ln2, ls, le;
	//Color z = new Color (r.nextInt(255), r.nextInt(255), r.nextInt(255));
	
	
	public PongNextLevel(Pong p) {
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
	
	public void setup() {
		this.setBackground(Color.black);
		
		
		
	}
	
	public void startColors() {
		Pong.cl.show(Pong.cards, "Next Level");
		labels(15);
	}
	
	private void colorChange(int n) {
		lc.setForeground(new Color(r.nextInt(255-n)+n, r.nextInt(255-n)+n, r.nextInt(255-n)+n));
		lo1.setForeground(new Color(r.nextInt(255-n)+n, r.nextInt(255-n)+n, r.nextInt(255-n)+n));
		ln1.setForeground(new Color(r.nextInt(255-n)+n, r.nextInt(255-n)+n, r.nextInt(255-n)+n));
		lg.setForeground(new Color(r.nextInt(255-n)+n, r.nextInt(255-n)+n, r.nextInt(255-n)+n));
		lr.setForeground(new Color(r.nextInt(255-n)+n, r.nextInt(255-n)+n, r.nextInt(255-n)+n));
		la1.setForeground(new Color(r.nextInt(255-n)+n, r.nextInt(255-n)+n, r.nextInt(255-n)+n));
		lt1.setForeground(new Color(r.nextInt(255-n)+n, r.nextInt(255-n)+n, r.nextInt(255-n)+n));
		lu.setForeground(new Color(r.nextInt(255-n)+n, r.nextInt(255-n)+n, r.nextInt(255-n)+n));
		ll.setForeground(new Color(r.nextInt(255-n)+n, r.nextInt(255-n)+n, r.nextInt(255-n)+n));
		la2.setForeground(new Color(r.nextInt(255-n)+n, r.nextInt(255-n)+n, r.nextInt(255-n)+n));
		lt2.setForeground(new Color(r.nextInt(255-n)+n, r.nextInt(255-n)+n, r.nextInt(255-n)+n));
		li.setForeground(new Color(r.nextInt(255-n)+n, r.nextInt(255-n)+n, r.nextInt(255-n)+n));
		lo2.setForeground(new Color(r.nextInt(255-n)+n, r.nextInt(255-n)+n, r.nextInt(255-n)+n));
		ln2.setForeground(new Color(r.nextInt(255-n)+n, r.nextInt(255-n)+n, r.nextInt(255-n)+n));
		ls.setForeground(new Color(r.nextInt(255-n)+n, r.nextInt(255-n)+n, r.nextInt(255-n)+n));
		le.setForeground(new Color(r.nextInt(255-n)+n, r.nextInt(255-n)+n, r.nextInt(255-n)+n));
	}
	
	private void labels(int n) {
		lc = new JLabel("Congratulations!");
		lc.setFont(retroFont.deriveFont(85f));
		lc.setForeground(new Color(r.nextInt(255-n)+n, r.nextInt(255-n)+n, r.nextInt(255-n)+n));
		add(lc);
		
		
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
		
		next = new JButton("Next Level");
		next.setBounds(515, 350, 300, 75);
		next.setFont(retroFont.deriveFont(30f));
		next.setBackground(Color.black);
		next.setForeground(Color.white);
		next.setFocusPainted(false);
		next.setOpaque(true);
		next.addActionListener(this);
		add(next);
		
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			po.showHome();
		}
		if (e.getSource() == next) {
			
			po.showPlay();
		}
	}
	
}
