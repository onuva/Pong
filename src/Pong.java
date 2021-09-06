import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;


@SuppressWarnings("serial")
public class Pong extends JPanel{
	static JPanel cards;
	static CardLayout cl;
	private static Font retroFont;
	static int w = 1430, h = 800; //Screen/Window Size
	
	PongHome pH;
	static PongInstruc pI;
	static PongCredits pC;
	static PongPlay play;
	static PongControls pCon;
	static PongWhatPlay whatPlay;
	static PongSave pS;
	static PongSet pSe;
	static PongNextLevel pN;
	
	private static Random r = new Random();
	
	private static int x = 10;
	private static int y = 10;
	private static int wB = 45;
	private static int hB = 45;
	private static int xI = 15;
	private static int yI = 10;
	private static int Mx = w - wB;
	private static int My = h - hB - 24;
	private int mx = 0;
	private int my = 0;
	JFrame f = new JFrame ("Pong");
	
	static int lev = 0;
	
	static int savePL = 0, savePR = 0;
	static int limit1 = 1, limit2 = 10, limit3 = 15;
	
	public static void main(String[] args) {
		Pong po = new Pong();
		po.setup();
		
		xI = r.nextInt(3) + 3;
		yI = r.nextInt(3) + 3;
		
		x = r.nextInt(Mx - wB) + wB;
		y = r.nextInt(My - hB) + hB;
	}
	
	public void setup() {
		
		
		f.setSize(w,h);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Container pane = f.getContentPane();
		
		pH = new PongHome(this);
		pI = new PongInstruc(this);
		pC = new PongCredits(this);
		pCon = new PongControls(this);
		play = new PongPlay(this);
		whatPlay = new PongWhatPlay(this);
		pS = new PongSave(this);
		pSe = new PongSet(this);
		pN = new PongNextLevel(this);
		cards = new JPanel(new CardLayout());
		cards.add(pH, "Home Page");
		cards.add(play, "Pong Play");
		cards.add(pI, "Instructions Page");
		cards.add(pC, "Credits Page");
		cards.add(pCon, "Controls Page");
		cards.add(whatPlay, "Play-Type Page");
		cards.add(pS, "Save Page");
		//cards.add(pSe, "Settings Page");
		cards.add(pN, "Next Level");
		pane.add(cards, BorderLayout.CENTER);
		cl = (CardLayout)(cards.getLayout());
		f.setVisible(true);
		cl.show(cards, "Home Page"); 
		
	}
	
	public void showHome() {
		cards.remove(this);
		pH = new PongHome(this);
		cards.add(pH, "Home Page");
		cl.show(cards, "Home Page");
		System.out.println("uhhh");
		
	}
	
	public void showSave() {
		cards.remove(pS);
		pS = new PongSave(this);
		cards.add(pS, "Save Page");
		cl.show(cards, "Save Page");
	}
	
	public void showLevel() {
		cards.remove(pN);
		pN = new PongNextLevel(this);
		cards.add(pN, "Next Level");
		cl.show(cards, "Next Level");
		pN.startColors();
	}
	
	public void showPlay() {
		cl.show(cards, "Pong Play");
	}
	
	class PongHome extends JPanel implements ActionListener{
		
		private int num = 0;
		public JButton instrucB, creditB, playB, controlB, setB;
		Pong po;
		
		public PongHome(Pong p) {
			try {
				retroFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Retro Gaming.ttf"));
			    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/Retro Gaming.ttf")));
			} catch (IOException|FontFormatException e) {
				retroFont = new Font("Times New Roman", Font.PLAIN, 25);
			}
			po = p;
			
			setup();
			
			Timer t = new Timer(10, this);
			
			t.start();
			
		}
		
		private void setup() {
			this.setBackground(Color.black);
			
			JLabel title = new JLabel("          PONG          ");
			title.setFont(retroFont.deriveFont(Font.BOLD, 115f));
			title.setForeground(Color.white);
			add(title);
			
			try {
			    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
			 } catch (Exception e) {
			            e.printStackTrace();
			 }
			ImageIcon setIcon = null;

		    java.net.URL imgURL = Pong.class.getResource("/settings.png");
		    if (imgURL != null) {
		    	setIcon = new ImageIcon(imgURL);
		    }
		    else {
		    	System.out.println("Icon image not found.");
		    }
		     
			setB = new JButton(" Settings", setIcon);
			setB.setFont(retroFont.deriveFont(35f));
			setB.setBackground(Color.black);
		    setB.setForeground(Color.white);
		    setB.setFocusPainted(false);
		    setB.setOpaque(true);
		    setB.addActionListener(this);
						
		}
		
		public void paintComponent(Graphics g) //paints background image and button
		{
			super.paintComponent(g);
			 try {
				    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
				 } catch (Exception e) {
				            e.printStackTrace();
				 }

			createButton("Instructions", w/2 - 148, 250, 300, 100, 30, 1);
			createButton("Play", w/2 - 123, 450, 246, 100, 55, 2);
			createButton("Credits", 10, 690, 200, 75, 30, 3);
			createButton("Controls", w/2 - 110, 360, 220, 85, 30, 4);
			
			setB.setBounds(1100, 680, 290, 90);
		    add(setB);
			
			Color z = new Color (r.nextInt(255), r.nextInt(255), r.nextInt(255));
			
			g.setColor(z);
			
			if (y<=my || y>=My) yI *= -1; //If the ball goes beyond y-bounds (bounce on top/bottom sides)
			if(xI == 0 && yI != 0) xI++; //If the ball starts going vertically
			
			if (x<=mx || x>=Mx) xI *= -1; //If the ball goes beyond x-bounds (bounce on left/right sides)
			if(yI == 0 && xI != 0) yI++; //If the ball starts going horizontally
			
			x+=xI;
			y+=yI;
			
			
			//g.fillEllipse(x, y, wB, hB);
			g.fillOval(x, y, wB, hB);
			
		}
		
		private void createButton(String s, int x, int y, int width, int height, int size, int n) {
			JButton j = new JButton(s);
			j.setBounds(x, y, width, height);
			j.setFont(retroFont.deriveFont((float)size));
			j.setBackground(Color.black);
		    j.setForeground(Color.white);
		    j.setFocusPainted(false);
		    j.setOpaque(true);
		    j.addActionListener(this);
		    if (n==1 && num < 4) {
		    	num++;
		    	instrucB = j;
		    	add(instrucB);
		    }
		    if (n==2 && num < 4) {
		    	num++;
		    	playB = j;
		    	add(playB);
		    }
		    if (n==3 && num < 4) {
		    	num++;
		    	creditB = j;
		    	add(creditB);
		    }
		    if (n==4 && num < 4) {
		    	num++;
		    	controlB = j;
		    	add(controlB);
		    }
		    
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == instrucB) {
				cards.remove(pI);
				Pong.pI = new PongInstruc(po);
				cards.add(pI, "Instructions Page");
				cl.show(cards, "Instructions Page");
			}
			if (e.getSource() == playB) {
				cards.remove(whatPlay);
				Pong.whatPlay = new PongWhatPlay(po);
				cards.add(whatPlay, "Play-Type Page");
				cl.show(cards, "Play-Type Page");
			}
			if (e.getSource() == creditB) {
				cards.remove(pC);
				Pong.pC = new PongCredits(po);
				cards.add(pC, "Credits Page");
				cl.show(cards, "Credits Page");
			}
			
			if (e.getSource() == controlB) {
				cards.remove(pCon);
				Pong.pCon = new PongControls(po);
				cards.add(pCon, "Controls Page");
				cl.show(cards, "Controls Page");
			}
			
			if (e.getSource() == setB) {
				Pong.pSe = new PongSet(po);
				cards.add(pSe, "Settings Page");
				cl.show(cards, "Settings Page");
			}
			repaint();
		}
	}
}
