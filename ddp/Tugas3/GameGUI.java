import java.util.Queue;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;

/**
*	Kelas yang akan menjadi obyek GUI utama pada permainan
*	@author Rakha Kanz Kautsar
*	@version 12-11-2015
*/
class GameGUI extends JFrame {
	private static final long serialVersionUID = 1506688784L;

	/**
	*	Lebar frame
	*/
	public static final int FRAME_W = 800;
	/**
	*	Tinggi frame
	*/
	public static final int FRAME_H = 600;
	/**
	*	FPS(Framerate per second) yang digunakan
	*/
	public static final int FPS = 30;

	/**
	*	Panel yang akan diattach pada frame
	*/
	private GamePanel gamePanel;

	/**
	*	Obyek pemain 1
	*/
	private Player player1;
	/**
	*	Obyek pemain 2
	*/
	private Player player2;
	/**
	*	Obyek bola
	*/
	private Ball ball;

	/**
	*	Point yang menunjukkan posisi pemain 1
	*/
	private Point p1;
	/**
	*	Point yang menunjukkan posisi pemain 2	
	*/
	private Point p2;
	
	/**
	*	Timer yang akan digunakan untuk animasi
	*/
	private Timer timer;

	/** 
	 * 	Animation queue untuk ball
	 */
	private Queue<Ball> ballQueue;

	/**
	 * 	Daftar point-point bola untuk ditampilkan
	 */
	private ArrayList<Point> balls;

	/**
	*	Konstruktor GUI yang berisi inisialisasi dan penambahan panel
	*/

	private JLabel lblWind;

	public GameGUI(){
		super("Cannon War v0.3");

		player1 = null;
		player2 = null;
		p1 = null;
		p2 = null;

		timer = new Timer(0,null);

		ballQueue = new ConcurrentLinkedQueue<Ball>();
		balls     = new ArrayList<Point>();
		lblWind   = new JLabel("Wind = (0.00, 0.00)");

		setSize(FRAME_W + InputPanel.PANEL_W + 5, FRAME_H + 22);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); 
		setLayout(new GridBagLayout());

		timer = new Timer(1000/FPS, new ActionListener(){
			public void actionPerformed(ActionEvent e){

				for(Ball ball: ballQueue){
					ball.next(1.0/FPS);

					if(!ball.flying()){
						hitCheck(ball, player1);
						hitCheck(ball, player2);
						ballQueue.remove(ball);
					}

				}
				
				update();
			}
		});

		timer.start();
		gamePanel = new GamePanel(p1, p2, balls);
	}

	/**
	*	Mengupdate data-data dan passing ke panel untuk direpaint
	*/
	public void update(){
		if(player1 !=null){
			p1 = player1.getPos();
			gamePanel.setPlayer1Health(player1.getHealth());
		}
		
		if(player2 != null){
			p2 = player2.getPos();
			gamePanel.setPlayer2Health(player2.getHealth());
		}

		balls = new ArrayList<Point>();
		for(Ball ball: ballQueue){
			balls.add(ball.getPos());
		}
		
		gamePanel.setPlayer1(p1);
		gamePanel.setPlayer2(p2);
		gamePanel.setBalls(balls);

		gamePanel.repaint();

	}

	public void addOthers(){
		GridBagConstraints cons = new GridBagConstraints();
		
		cons.anchor     = GridBagConstraints.FIRST_LINE_END;
		cons.fill       = GridBagConstraints.HORIZONTAL;
		cons.gridx      = 1;
		cons.gridy      = 0;
		cons.gridheight = 2;
		add(gamePanel, cons);

		cons.anchor     = GridBagConstraints.LAST_LINE_START;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.gridx = 0;
		cons.gridy = 1;
		lblWind.setAlignmentX(CENTER_ALIGNMENT);
		add(lblWind, cons);
	}

	/**
	*	Rutin yang akan menerapkan hit pada player dan menampilkan informasinya
	*	@param ball Bola yang ditembakkan
	*	@param player Pemain yang ditembak
	*/
	public void hitCheck(Ball ball, Player player){
		if(!player.dead() && ball.hit(player))
			info("The projectile hits " + player.getName() + 
				". " + player.getName() + " has " + player.getHealth() + " HP remaining.");
	}

	public void setTurn(Player p){
		// inputPanel.setTurn(p);
	}

	// Mutator
	public void setPlayer1(Player player){
		player1 = player;
		update();
	}

	public void setPlayer2(Player player){
		player2 = player;
		update();
	}

	public void addBall(Ball _ball){
		ballQueue.offer(_ball);
	}

	public void showWind(Wind w){
		lblWind.setText(String.format("Wind = (%.2f, %.2f)", w.getDx(), w.getDy()));
	}

	// -------------------------------------------------------------------------------
	// 					VARIOUS GUI INPUT/OUTPUT USING JOPTIONPANE
	// -------------------------------------------------------------------------------
	/**
	*	Menampilkan information message
	*	@param title Judul
	*	@param text Pesan
	*/
	public void info(String title, String text){
		JOptionPane.showMessageDialog(this, text, title, JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	*	Menampilkan information message
	*	@param text Pesan
	*/
	public void info(String text){
		info("Information", text);
	}

	/**
	*	Menampilkan error message
	*	@param text Pesan error
	*/
	public void error(String text){
		JOptionPane.showMessageDialog(this, text, "Error!", JOptionPane.ERROR_MESSAGE);
	}

	/**
	*	Menanyakan kepada user
	*	@param title Judul
	*	@param message Pertanyaan
	*	@param placeholder Placeholder
	*/
	public String ask(String title, String message, String placeholder){
		return (String)JOptionPane.showInputDialog(this, message, title, JOptionPane.PLAIN_MESSAGE, null, null, placeholder);
	}

	/**
	*	Menanyakan berupa pilihan kepada user
	*	@param title Judul
	*	@param message Pertanyaan
	*	@param selection Pilihan
	*/
	public String select(String title, String message, Object[] selection){
		return (String)JOptionPane.showInputDialog(this, message, title, JOptionPane.PLAIN_MESSAGE, null, selection, selection[0]);
	}

	/**
	*	Meminta input integer dan memvalidasinya
	*	@param title Judul
	*	@param message Pesan
	*	@param low Batas bawah
	*	@param high Batas atas
	*/
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

	/**
	*	Meminta input double dan memvalidasinya
	*	@param title Judul
	*	@param message Pesan
	*	@param low Batas bawah
	*	@param high Batas atas
	*/
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
