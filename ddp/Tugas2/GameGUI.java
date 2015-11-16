import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private GamePanel panel;
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
	*	Point yang menunjukkan posisi bola
	*/
	private Point b;
	
	/**
	*	Timer yang akan digunakan untuk animasi
	*/
	private Timer timer;

	/**
	*	Konstruktor GUI yang berisi inisialisasi dan penambahan panel
	*/
	public GameGUI(){
		super("Cannon War v0.2");

		player1 = null;
		player2 = null;
		p1 = null;
		p2 = null;

		b = null;
		timer = new Timer(0,null);

		setSize(FRAME_W, FRAME_H);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); 

		panel = new GamePanel(p1, p2, b);

		add(panel);
	}

	/**
	*	Mengupdate data-data dan passing ke panel untuk direpaint
	*/
	public void update(){
		if(player1 !=null){
			p1 = player1.getPos();
			panel.setPlayer1Health(player1.getHealth());
		}
		
		if(player2 != null){
			p2 = player2.getPos();
			panel.setPlayer2Health(player2.getHealth());
		}

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

	// Mutator
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

		// TODO add concurrent shoot and explosions

		timer = new Timer(1000/FPS, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ball.next(1.0/FPS);
				b = ball.getPos();

				if(!ball.flying()){
					b = null;
					timer.stop();
				};

				update();
			}
		});

		timer.start();
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
