/* Programmer Name: Onuva Ekram
 * Date: 4/16/2021 - 
 * Purpose: Remake a version of the famous ping-pong game Pong!
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

@SuppressWarnings("serial")
public class PongPlay extends JPanel implements ActionListener{

	private Image bg;//Background
	private static int rX1 = 65, rX2 = 1345; //Racket x-positions
	static int rY1 = 390, rY2 = 390; //Racket y-positions
	private static int rW = 30, rH = 120; //Racket width and height dimensions
	private static int wB = 10, hB=10; //Ball dimensions
	private static Random r = new Random();
	private static int b = r.nextInt(2);
	private static int xI = 3;//Ball movement
	private static int yI = 1;
	private int tempX = xI, tempY = yI; //Ball temporary positions if needed
	private int timer = 10;
	private static int w = 1430, h = 800; //Screen/Window Size
	private static int MX = rX2-wB, mx = rX1 + rW; //Max/min x-position of ball
	private int MY = h-35-(hB/2); //Max y-position of ball
	private static int x, y; //Ball positions
	static int scoreL = 0;
	static int scoreR = 0;
	private boolean reset = false, pause = false;
	private int rI = 0, lI = 0;
	private JLabel l = new JLabel(), l2;
	int level = 0;
	//private 
	
	Pong po = new Pong();
	static int lim1 = Pong.limit1, lim2 = Pong.limit2, lim3 = Pong.limit3;
	JButton back;
	private static Font retroFont;
	
	public static Mixer mixer;
	
	AudioInputStream pingSound;
	Clip ping;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int yPL1 = rY1, yPL2 = rY1 + rH, yPR1 = rY2, yPR2 = rY2 + rH;//Racket y-positons and heights
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bg, 0, 0, null);
		
		g.setColor(Color.WHITE); // Left Racket
		g.fillRect(rX1, rY1, rW, rH);
		
		g.setColor(Color.WHITE); // Right Racket
		g.fillRect(rX2, rY2, rW, rH);
		
		if(rY2 <=0 && rI <=0) rY2 -= rI; //If right racket is at upper limit
		else if (rY2 >= 0 && rY2 <= (MY-rH + (hB/2))) rY2 -= rI; // right racket normal movement
		if (rY2 > (MY-rH+(hB/2)) && rI >=0) rY2 -= rI; //If right racket is at lower limit
		if(rY1 <=0 && lI <=0) rY1 -= lI; //If left racket is at upper limit
		else if((rY1 >=0) && rY1 <= (MY-rH + (hB/2))) rY1 -= lI; //left racket normal movement
		if (rY1 > (MY-rH+(hB/2)) && lI >=0) rY1 -= lI; //If left racket is at lower limit
		
		if (x<= mx && (y+hB) > yPL1 && y < (yPL2+hB) && x > mx-(hB/2)) {
			xI *= -1; //Bounces off left racket
			pingHit("src/pingHit.wav");
		}
		if (x >= MX && y > (yPR1-hB) && y < (yPR2+hB) && x < rX2 + (hB/2)) {
			xI *= -1; //Bounces off right racket
			pingHit("src/pingHit.wav");
		}
		if (x < mx && x > (rX1-wB) && y <= yPL2 && y > yPL2-(hB/2)) {
			yI *= -1; //Bounces off lower side (left)
			pingHit("src/pingHit.wav");
		}
		if (x < mx && x > (rX1-wB) && y >= (yPL1-hB) && y < (yPL1-hB+(hB/2))) {
			yI *= -1; //Bounces off upper side (left)
			pingHit("src/pingHit.wav");
		}
		if (x > (rX2-wB) && x < (rX2 + rW) && y <= yPR2 && y > yPR2-(hB/2)) {
			yI *= -1; //Bounces off lower side (right)
			pingHit("src/pingHit.wav");
		}
		if (x > (rX2-wB) && x < (rX2 + rW) && y >= (yPR1-hB) && y < (yPR1-hB+(hB/2))) {
			yI *= -1; //Bounces off upper side (right)
			pingHit("src/pingHit.wav");
		}
		//if (x = mx)
		if (x<rX1 && !reset) { //If the ball goes beyond x-bounds
			scoreR++;
			l2.setText("\t\t\t\t\t\t\t\t\t\t" + String.valueOf(scoreR));
			System.out.println("Right Side score increased to: " + scoreR);
			reset = true;
		}
		if (x>rX2 + rW && !reset) { //If the ball goes beyond x-bounds
			scoreL++;
			l.setText(String.valueOf(scoreL) + "\t\t\t\t\t\t\t\t\t\t");
			System.out.println("Left Side score increased to: " + scoreL);
			reset = true;
		}
		
		if (reset == true) {
			g.setColor(Color.black);
			if(x<0 || x > w) {
				b = r.nextInt(2);
				randomB(b);
				
				try {
					Thread.sleep(1000);
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
				reset = false;
			}
		}
		if (y<=0 || y>=MY) {
			yI *= -1; //If the ball goes beyond y-bounds (bounce on top/bottom sides)
			pingHit("src/paddle.wav");
		}
		if(xI == 0 && yI != 0) xI++; //If the ball starts going vertically
		if(yI == 0 && xI != 0) yI++; //If the ball starts going horizontally
		
		
		x+=xI;
		y+=yI;
		
		if (level == 2) {
			wB = 15;
			hB = 15;
			rW = 30;
			rH = 120;
		}
		if (level == 3) {
			rW = 25;
			rH = 105;
		}
		
		g.fillRect(x, y, wB, hB);
		
		
		if(level == 1 && (scoreL == lim1 || scoreR == lim1)) {
			scoreL = 0;
			scoreR = 0;
			l2.setText("\t\t\t\t\t\t\t\t\t\t" + String.valueOf(scoreR));
			System.out.println("Right Side score increased to: " + scoreR);
			l.setText(String.valueOf(scoreL) + "\t\t\t\t\t\t\t\t\t\t");
			System.out.println("Left Side score increased to: " + scoreL);
			level = 2;
			po.showLevel();
		}
		
		if(level == 2 && (scoreL == lim2 || scoreR == lim2)) {
			scoreL = 0;
			scoreR = 0;
			l2.setText("\t\t\t\t\t\t\t\t\t\t" + String.valueOf(scoreR));
			System.out.println("Right Side score increased to: " + scoreR);
			l.setText(String.valueOf(scoreL) + "\t\t\t\t\t\t\t\t\t\t");
			System.out.println("Left Side score increased to: " + scoreL);
			level = 3;
			po.showLevel();
		}
		
		
	}
	
	private void randomB(int b) {
		if(b == 0) {
			x = r.nextInt((w/2)-(mx+50)) + mx+50;
			y = h/2;
			if (level == 1) {
				xI = 2;
				yI = r.nextInt(5) - 2;
			}
			else if (level == 2) {
				xI = 3;
				yI = r.nextInt(7) - 3;
			}
			else if (level == 3) {
				xI = r.nextInt(2) + 5;
				yI = r.nextInt(9) - 4;
			}
			else { 
				xI = r.nextInt(3) + 2;
				yI = r.nextInt(9) - 4;
			}
			while (yI < 2 && yI > -2) yI = r.nextInt(9) - 4;
		}
		else {
			x = r.nextInt((w/2)-(mx+50)) + (w/2);
			y = h/2;
			if (level == 1) {
				xI = -2;
				yI = r.nextInt(5) - 2;
			}
			else if (level == 2) {
				xI = -3;
				yI = r.nextInt(7) - 3;
			}
			else if (level == 3) {
				xI = -(r.nextInt(2) + 5);
				yI = r.nextInt(9) - 4;
			}
			else { 
				xI = -(r.nextInt(3) + 2);
				yI = r.nextInt(9) - 4;
			}
			while (yI < 2 && yI > -2) yI = r.nextInt(9) - 4;
		}
	}
	
	public PongPlay(Pong p) {
		level = Pong.lev;
		
		
		
		scoreL = Pong.savePL;
		scoreR = Pong.savePR;
		lim1 = Pong.limit1;
		lim2 = Pong.limit2;
		lim3 = Pong.limit3;
		
		if (this.level == 1) {
			wB = 20;
			hB = 20;
			rW = 35;
			rH = 135;
			rX2 += 10;
			MY -= 10;
			
		}
		else if (this.level == 2) {
			wB = 15;
			hB = 15;
			rW = 30;
			rH = 120;
		}
		else if (this.level == 3) {
			rW = 25;
			rH = 105;
		}
		Timer t = new Timer(timer, this);
		t.start();
		setFocusable(true);
		
		randomB(b);
		
		try {
			retroFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Retro Gaming.ttf"));
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/Retro Gaming.ttf")));
		} catch (IOException|FontFormatException e) {
			retroFont = new Font("Times New Roman", Font.PLAIN, 25);
			System.out.print(e);
		}
		
	    InputMap inp = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    
	   
	    
	    inp.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "pressedW");
	    this.getActionMap().put("pressedW", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	            if(!pause) {
	            	if(level == 1) moveL(5);
	            	if(level == 2) moveL(6);
	            	if(level == 3) moveL(10);
	            	else moveL(7);
	            }
	        }
	    });
	    inp.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "releasedW");
	    this.getActionMap().put("releasedW", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        	moveL(0);
	        }
	    });
	    
	    inp.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "pressedS");
	    this.getActionMap().put("pressedS", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	            if(!pause) {
	            	if(level == 1) moveL(-5);
	            	if(level == 2) moveL(-6);
	            	if(level == 3) moveL(-10);
	            	else moveL(-7);
	            }
	        }
	    });
	    inp.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "releasedS");
	    this.getActionMap().put("releasedS", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        	moveL(0);
	        }
	    });
	    
	    inp.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "pressedUP");
	    this.getActionMap().put("pressedUP", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	            if(!pause) {
	            	if(level == 1) moveR(5);
	            	if(level == 2) moveR(6);
	            	if(level == 3) moveR(10);
	            	else moveR(7);
	            }
	        }
	    });
	    inp.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "releasedUP");
	    this.getActionMap().put("releasedUP", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        	moveR(0);
	        }
	    });
	    
	    inp.put(KeyStroke.getKeyStroke(KeyEvent.VK_U, 0, true), "pressedU"); //testing ball position
	    this.getActionMap().put("pressedU", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        	System.out.println("X-Value: " + x + "\nY-Value: " + y + "\n");
	        }
	    });
	    
	    inp.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "pressedDOWN");
	    this.getActionMap().put("pressedDOWN", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	            if(!pause) {
	            	if(level == 1) moveR(-5);
	            	if(level == 2) moveR(-6);
	            	if(level == 3) moveR(-10);
	            	else moveR(-7);
	            }
	        }
	    });
	    inp.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "releasedDOWN");
	    this.getActionMap().put("releasedDOWN", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        	moveR(0);
	        }
	    });
	    
	    inp.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "pressedSp");
	    this.getActionMap().put("pressedSp", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        	if (!pause) {
		    		tempX = xI;
					tempY = yI;
					xI = 0;
					yI = 0;
					pause = true;
					backButton(true);
		    	}
		    	else {
		    		pause = false;
		    		xI = tempX;
					yI = tempY;
					backButton(false);
		    	}
	        }
	    });
	    inp.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, false), "pressedP");
	    this.getActionMap().put("pressedP", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        	if (!pause) {
		    		tempX = xI;
					tempY = yI;
					xI = 0;
					yI = 0;
					pause = true;
					backButton(true);
		    	}
		    	else {
		    		pause = false;
		    		xI = tempX;
					yI = tempY;
					backButton(false);
		    	}
	        }
	    });
	    inp.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, 0, true), "pressedY");
	    this.getActionMap().put("pressedY", new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        	pause = false;
				if(xI != 0 && yI != 0) {
					b = r.nextInt(2);
					randomB(b);
					tempX = xI;
					tempY = yI;
				}
				if(xI == 0 && yI == 0) {
					xI = tempX;
					yI = tempY;
				}
	        }
	    });
	    
		po = p;
		
		setupWindow();
	}
	
	private void backButton(boolean boo) {
		if(boo) {
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
		else {
			remove(back);
		}
	}
	
	public void pingHit(String s) {
		/*
		 * try { File pingFile = new File (s); pingSound =
		 * AudioSystem.getAudioInputStream(pingFile); ping = AudioSystem.getClip();
		 * ping.open(pingSound); } catch(Exception e){
		 * System.out.println("An error with the sound occured."); e.printStackTrace();
		 * }
		 * 
		 * ping.setFramePosition(0); ping.start();
		 */
	}
	
	
	public void setupWindow() {
		ImageIcon i = new ImageIcon(new ImageIcon("src/PongBG.png").getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
		bg = i.getImage();
	
		
		
		l = new JLabel(String.valueOf(scoreL) + "\t\t\t\t\t\t\t\t\t\t");
	    l.setForeground(Color.WHITE);
	    l.setFont(new Font("Verdana",1, 80));
	    l2 = new JLabel("\t\t\t\t\t\t\t\t\t\t" + String.valueOf(scoreR));
	    l2.setForeground(Color.WHITE);
	    l2.setFont(new Font("Verdana",1, 80));
	    add(l);
	    add(l2);
	    
	}
	
	public void moveL(int yMove) { //Moves left paddle
		lI = yMove;
	}
	
	public void moveR(int yMove) { //Moves right paddle
		rI = yMove;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == back) {
			po.showSave();
		}
		repaint();
	}

	
}
