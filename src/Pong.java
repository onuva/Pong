/* Programmer Name: Onuva Ekram
 * Date: 4/16/2021
 * Purpose: Remake a version of the famous ping-pong game Pong!
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Pong extends JPanel implements ActionListener, KeyListener{
	private static JFrame f = new JFrame ("Pong");
	private Image bg;//Background
	private static int rX1 = 65, rX2 = 1345; //Racket x-positions
	static int rY1 = 390, rY2 = 390; //Racket y-positions
	private static int rW = 30; //Racket width and height dimensions
	private int rH = 120;
	private static int wB = 10, hB=10; //Ball dimensions
	private static Random r = new Random();
	private static int b = r.nextInt(2);
	private static int xI = 3;//r.nextInt(3) + 1, yI = r.nextInt(3) + 1; //Ball movement
	private static int yI = 1;
	private int tempX = xI, tempY = yI; //Ball temporary positions if needed
	private int timer = 10;
	private static int w = 1440, h = 800; //Screen/Window Size
	private static int MX = rX2-wB, mx = rX1 + rW; //Max/min x-position of ball
	private int MY = h-44-hB; //Max y-position of ball
	private static int x, y; //Ball positions
	private int scoreL = 0, scoreR = 0;
	private JMenu g;
	private JMenuItem start, stop;
	private boolean reset = false, pause = false;
	private int rI = 0, lI = 0;
	private JLabel l = new JLabel(), l2;
	int bgW;
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int yPL1 = rY1, yPL2 = rY1 + rH, yPR1 = rY2, yPR2 = rY2 + rH;//Racket y-positons and heights
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bg, 0, 0, null);
		
		g.setColor(Color.WHITE); // Left Racket
		g.fillRect(rX1, rY1, rW, rH);
		
		g.setColor(Color.WHITE); // Right Racket
		g.fillRect(rX2, rY2, rW, rH);
		
		
		if (x<= mx && (y+hB) >= yPL1 && y <= (yPL2+hB) && x > mx-5) xI *= -1; //Bounces off left racket
		if (x >= MX && y >= (yPR1-hB) && y <= (yPR2+hB) && x < rX2 + 5) xI *= -1; //Bounces off right racket
		if (x <= (mx+wB) && x >= (rX1-wB) && y <= yPL2 && y > yPL2-5) yI *= -1; //Bounces off lower side (left)
		if (x <= (mx+wB) && x >= (rX1-wB) && y >= (yPL1-hB) && y < (yPL1-hB+5)) yI *= -1; //Bounces off upper side (left)
		if (x >= (rX2-wB) && x <= (rX2 + rW) && y <= yPR2 && y > yPR2-5) yI *= -1; //Bounces off lower side (right)
		if (x >= (rX2-wB) && x <= (rX2 + rW) && y >= (yPR1-hB) && y < (yPR1-hB+5)) yI *= -1; //Bounces off upper side (right)
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
			if(x<0 || x > w) {
				b = r.nextInt(2);
				if(b == 0) {
					x = r.nextInt((w/2)-(mx+50)) + mx+50;
					y = h/2;
					xI = r.nextInt(3) + 2;
					yI = r.nextInt(9) - 4;
				while (yI < 2 && yI > -2) yI = r.nextInt(9) - 4;
				}
				else {
					x = r.nextInt((w/2)-(mx+50)) + (w/2);
					y = h/2;
					xI = -1 * (r.nextInt(3) + 2);
					yI = r.nextInt(9) - 4;
				while (yI < 2 && yI > -2) yI = r.nextInt(9) - 4;
				}
				repaint();
				reset = false;
			}
		}
		if (y<=0 || y>=MY) yI *= -1; //If the ball goes beyond y-bounds (bounce on top/bottom sides)
		if(xI == 0 && yI != 0) xI++; //If the ball starts going vertically
		if(yI == 0 && xI != 0) yI++; //If the ball starts going horizontally
		
		
		x+=xI;
		y+=yI;
		
		
		g.fillRect(x, y, wB, hB);
		
	}
	
	public Pong() {
		Timer t = new Timer(timer, this);
		t.start();
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void setupWindow(Pong po) {
		ImageIcon i = new ImageIcon("src/PongBG.png");
		bg = i.getImage();
		JMenuBar mB = new JMenuBar();
		
		g = new JMenu("General");
		
		start = new JMenuItem("Start");
		start.addActionListener(this);
		stop = new JMenuItem("Pause");
		stop.addActionListener(this);
		g.add(start);
		g.add(stop);
		mB.add(g);
	
		
		
		l = new JLabel(String.valueOf(scoreL) + "\t\t\t\t\t\t\t\t\t\t");
	    l.setForeground(Color.WHITE);
	    l.setFont(new Font("Verdana",1, 80));
	    l2 = new JLabel("\t\t\t\t\t\t\t\t\t\t" + String.valueOf(scoreR));
	    l2.setForeground(Color.WHITE);
	    l2.setFont(new Font("Verdana",1, 80));
	    po.add(l);
	    po.add(l2);
	    
	    
		f.setJMenuBar(mB);
		f.setSize(w,h);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(po, "Center");
		f.add(po);
		f.setVisible(true);
	}
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
	    if (e.getKeyCode() == KeyEvent.VK_UP) {
	    	if(!pause)
	    		moveR(5);
	    }
	    else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	    	if(!pause)
	    		moveR(-5);
	    }
	    if (e.getKeyCode() == KeyEvent.VK_W) {
	    	if(!pause)
	    		moveL(5);
	    }
	    else if (e.getKeyCode() == KeyEvent.VK_S) {
	    	if(!pause)
	    		moveL(-5);
	    }
		
	}
	
	public void moveL(int yMove) {
		lI = yMove;
	}
	
	public void moveR(int yMove) {
		rI = yMove;
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP)
	        moveR(0);
	    else if (e.getKeyCode() == KeyEvent.VK_DOWN)
	        moveR(0);
	    if (e.getKeyCode() == KeyEvent.VK_W)
	        moveL(0);
	    else if (e.getKeyCode() == KeyEvent.VK_S)
	        moveL(0);
	}
	
	public static void main(String[] args) {
		Pong po = new Pong();
		if(b == 0) {
			x = r.nextInt((w/2)-(mx+50)) + mx+50;
			y = h/2;
			xI = r.nextInt(3) + 2;
			yI = r.nextInt(9) - 4;
				while (yI < 2 && yI > -2) yI = r.nextInt(9) - 4;
		}
		else {
			x = r.nextInt((w/2)-(mx+50)) + (w/2);
			y = h/2;
			xI = -1 * (r.nextInt(3) + 2);
			yI = r.nextInt(9) - 4;
				while (yI < 2 && yI > -2) yI = r.nextInt(9) - 4;
		}
		
		po.setupWindow(po);
	}

	public void actionPerformed(ActionEvent e) {
		int yPL2 = rY1 + rH, yPR2 = rY2 + rH;
		if (e.getSource() == start) {
			pause = false;
			if(xI != 0 && yI != 0) {
				b = r.nextInt(2);
				if(b == 0) {
					x = r.nextInt((w/2)-(mx+50)) + mx+50;
					y = h/2;
					xI = r.nextInt(3) + 2;
					yI = r.nextInt(9) - 4;
					while (yI < 2 && yI > -2) yI = r.nextInt(9) - 4;
				}
				else {
					x = r.nextInt((w/2)-(mx+50)) + (w/2);
					y = h/2;
					xI = -1 * (r.nextInt(3) + 2);
					yI = r.nextInt(9) - 4;
					while (yI < 2 && yI > -2) yI = r.nextInt(9) - 4;
				}
				tempX = xI;
				tempY = yI;
			}
			if(xI == 0 && yI == 0) {
				xI = tempX;
				yI = tempY;
			}
		}
		if (e.getSource() == stop) {
			tempX = xI;
			tempY = yI;
			xI = 0;
			yI = 0;
			pause = true;
		}
		if(rY2 <=0 && rI <=0) rY2 -= rI; //If right racket is at upper limit
		else if (rY2 >= 0 && rY2 <= (MY-rH + 5)) rY2 -= rI; // right racket normal movement
		if (rY2 > (MY-rH+5) && rI >=0) rY2 -= rI; //If right racket is at lower limit
		if(rY1 <=0 && lI <=0) rY1 -= lI; //If left racket is at upper limit
		else if((rY1 >=0) && rY1 <= (MY-rH + 5)) rY1 -= lI; //left racket normal movement
		if (rY1 > (MY-rH+5) && lI >=0) rY1 -= lI; //If left racket is at lower limit
		repaint();
	}

	
}
