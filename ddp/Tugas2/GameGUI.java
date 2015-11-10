import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class GameGUI extends JFrame {
	private static final long serialVersionUID = 1506688784L;

	public static final int FRAME_W = 800;
	public static final int FRAME_H = 600;
	public static final int FPS = 30;

	private GamePanel panel;
	private Player player1;
	private Player player2;
	private Ball ball;
	private Point p1;
	private Point p2;
	private Point b;
	private Timer timer;

	public GameGUI(){
		super("Cannon War v0.2");

		player1 = null;
		player2 = null;
		p1 = null;
		p2 = null;

		b = null;

		setSize(FRAME_W, FRAME_H);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); 

		panel = new GamePanel(p1, p2, b);

		add(panel);
	}

	public void setPlayer1(Player player){
		player1 = player;
		update();
	}

	public void setPlayer2(Player player){
		player2 = player;
		update();
	}

	public void setBall(Ball _ball){
		ball = _ball;

		timer = new Timer(1000/FPS, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ball.next(1.0/FPS);
				b = ball.getPos();

				if(!ball.flying()){
					b = null;
					// TODO
					// explode(ball.getPos());
					timer.stop();
				};

				update();
			}
		});

		timer.start();
	}

	public void update(){
		if(player1 !=null)
			p1 = player1.getPos();
		
		if(player2 != null)
			p2 = player2.getPos();

		if(ball!= null && ball.flying()){
			b = ball.getPos();
		} else {
			b = null;
		}
		
		panel.setPlayer1(p1);
		panel.setPlayer2(p2);
		panel.setBall(b);

		panel.repaint();

	}

	// -------------------------------------------------------------------------------
	// 					VARIOUS GUI INPUT/OUTPUT USING JOPTIONPANE
	// -------------------------------------------------------------------------------
	public void info(String title, String text){
		JOptionPane.showMessageDialog(this, text, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public void info(String text){
		info("Information", text);
	}

	public void error(String text){
		JOptionPane.showMessageDialog(this, text, "Error!", JOptionPane.ERROR_MESSAGE);
	}

	public String ask(String title, String message, String placeholder){
		return (String)JOptionPane.showInputDialog(this, message, title, JOptionPane.PLAIN_MESSAGE, null, null, placeholder);
	}

	public String select(String title, String message, Object[] selection){
		return (String)JOptionPane.showInputDialog(this, message, title, JOptionPane.PLAIN_MESSAGE, null, selection, selection[0]);
	}

	public int askInt(String title, String message, int low, int high){
		int ret;

		while(true){
			try{
				ret = Integer.parseInt(ask(title, message, "0"));
				if(ret<low || ret>high)
					throw new Exception("Input is not in the allowed range.");
				break;
			} catch(Exception e){
				error(e.getMessage());
			}
		}

		return ret;
	}

	public double askDouble(String title, String message, double low, double high){
		double ret;

		while(true){
			try{
				ret = Double.parseDouble(ask(title, message, "0.0"));
				if(ret<low || ret>high)
					throw new Exception("Input is not in the allowed range.");
				break;
			} catch(Exception e){
				error(e.getMessage());
			}
		}

		return ret;
	}
}
