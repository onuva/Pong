import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class PongSet extends JPanel implements ActionListener{

	Pong po = new Pong();
	private static Font retroFont;
	private JButton back;
	private JLabel l;
	TextField tf1 = new TextField(String.valueOf(PongPlay.lim1), 7), tf2 = new TextField(String.valueOf(PongPlay.lim2), 7), tf3 = new TextField(String.valueOf(PongPlay.lim3), 7);
	private int r1, r2, r3;
	
	public PongSet(Pong p) {
		try {
			retroFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Retro Gaming.ttf"));
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/Retro Gaming.ttf")));
		} catch (IOException|FontFormatException e) {
			retroFont = new Font("Times New Roman", Font.PLAIN, 25);
		}
		 InputMap inp = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		    
		    inp.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressedEnter");
		    this.getActionMap().put("pressedEnter", new AbstractAction() {
		        public void actionPerformed(ActionEvent e) {
		        	System.out.println("rrrrr");
		            r1 = Integer.parseInt(tf1.getText());
		            r2 = Integer.parseInt(tf2.getText());
		            r3 = Integer.parseInt(tf3.getText());
		            Pong.limit1 = r1;
		            Pong.limit2 = r2;
		            Pong.limit3 = r3;
		        }
		    });
		    
		po = p;
		
		setup();
		
	}
	
	private void setup() {
		this.setBackground(Color.black);
		
		
		JLabel title = new JLabel("                   Settings:                   ");
		title.setFont(retroFont.deriveFont(85f));
		title.setForeground(Color.white);
		add(title);
		extraLabel();

		JLabel l1 = new JLabel("Pass Level 1 When Score Is: ");
		l1.setFont(retroFont.deriveFont(35f));
		l1.setForeground(Color.white);
		add(l1);
		
		
		add(tf1);
		extraLabel();
		
		JLabel l2 = new JLabel("Pass Level 2 When Score Is: ");
		l2.setFont(retroFont.deriveFont(35f));
		l2.setForeground(Color.white);
		add(l2);
		
		add(tf2);
		extraLabel();
		
		JLabel l3 = new JLabel("Pass Level 3 When Score Is: ");
		l3.setFont(retroFont.deriveFont(35f));
		l3.setForeground(Color.white);
		add(l3);
		
		add(tf3);	
		extraLabel();
		
		JLabel l4 = new JLabel("Sound Effects: ");
		l4.setFont(retroFont.deriveFont(35f));
		l4.setForeground(Color.white);
		add(l4);
		
		
		
		
	}
	
	private void extraLabel() {
		l = new JLabel("                   Settings:                   ");
		l.setFont(retroFont.deriveFont(60f));
		l.setForeground(Color.black);
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
		repaint();
	}
}
