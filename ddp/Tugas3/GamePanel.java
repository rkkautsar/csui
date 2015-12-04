import java.util.ArrayList;

import java.awt.Color;
import java.awt.Shape;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import java.io.File;

class GamePanel extends JComponent {
	private static final long serialVersionUID = 1506688784L;

	/**
	*	Scale pada sumbu x
	*/
	public static final double SCALE_X = (GameGUI.FRAME_W-50)*1.0 / Game.ARENA_X_BOUNDARY;
	/**
	*	Scale pada sumbu y
	*/
	public static final double SCALE_Y = (GameGUI.FRAME_H-50)*1.0 / Game.ARENA_Y_BOUNDARY;
	
	// Berbagai konstanta
	private final int PLAYER_HEIGHT = 39;
	private final int PLAYER_WIDTH  = 25;
	private final int BALL_SIZE = 10;
	private final int FLOOR_HEIGHT = 15;
	private final int HEALTHBAR_PAD = 10;
	private final int HEALTHBAR_WIDTH = 250;
	private final int HEALTHBAR_HEIGHT = 30;

	private final Color PLAYER1_COLOR = Color.RED;
	private final Color PLAYER2_COLOR = Color.BLUE;
	private final Color BALL_COLOR    = Color.BLACK;
	private final Color FLOOR_COLOR   = Color.GRAY;
	private final Color HEALTHBAR_BACKGROUND = Color.WHITE;
	private final Color HEALTHBAR_FOREGROUND = Color.RED;

	// Berbagai obyek untuk membantu tampilan gui
	private BufferedImage imgBackground;
	private BufferedImage imgBall;
	private BufferedImage imgPlayer1; 
	private BufferedImage imgPlayer2; 

	private ArrayList<Point> balls;
	private Point player1;
	private Point player2;
	private Point p;

	private int p1health;
	private int p2health;

	/**
	*	Konstruktor GamePanel yang melakukan konfigurasi dan inisialisasi pada panel
	*	@param p1 Posisi pemain satu yang akan dicetak oleh panel
	*	@param p2 Posisi pemain dua yang akan dicetak oleh panel
	*	@param b Posisi bola (jika tidak ada, null) yang akan dicetak oleh panel
	*/
	public GamePanel(Point p1, Point p2, ArrayList<Point> b){
		player1 = p1;
		player2 = p2;
		balls = b;	
		p1health = 100;
		p2health = 100;

		try {	
			imgBackground 	= ImageIO.read(new File("./assets/background.png"));
			imgBall			= ImageIO.read(new File("./assets/ball.png"));
			imgPlayer1		= ImageIO.read(new File("./assets/player1.png")); 
			imgPlayer2		= ImageIO.read(new File("./assets/player2.png")); 
		} catch (Exception e){

		}

		setPreferredSize(new Dimension(GameGUI.FRAME_W, GameGUI.FRAME_H));
		setMinimumSize(new Dimension(GameGUI.FRAME_W, GameGUI.FRAME_H));
	}

	/**
	*	Method utama untuk melakukan pelukisan (paint) pada panel
	*/
	@Override 
	protected void paintComponent(Graphics g){
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(	RenderingHints.KEY_ANTIALIASING,
								RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.drawImage(imgBackground, 0, 0, null);
		g2d.setColor(FLOOR_COLOR);

		if(player1 != null){
			p = scaleAndInvert(player1, PLAYER_WIDTH, PLAYER_HEIGHT);
			if(player2 != null && player1.getX() > player2.getX()){
				g2d.drawImage(imgPlayer1, (int)p.getX() + 2 * PLAYER_WIDTH, (int)p.getY(),-PLAYER_WIDTH, PLAYER_HEIGHT, null);
			} else {
				g2d.drawImage(imgPlayer1, (int)p.getX(), (int)p.getY(), PLAYER_WIDTH, PLAYER_HEIGHT, null);	
			}
		}		

		if(player2 != null){
			p = scaleAndInvert(player2, PLAYER_WIDTH, PLAYER_HEIGHT);
			if(player1 != null && player2.getX() > player1.getX()){
				g2d.drawImage(imgPlayer2, (int)p.getX() + 2 * PLAYER_WIDTH, (int)p.getY(),-PLAYER_WIDTH, PLAYER_HEIGHT, null);
			} else {
				g2d.drawImage(imgPlayer2, (int)p.getX(), (int)p.getY(), PLAYER_WIDTH, PLAYER_HEIGHT, null);	
			}
		}

		for(Point ball: balls){
			p = scaleAndInvert(ball, BALL_SIZE, BALL_SIZE);
			g2d.drawImage(imgBall, (int)p.getX(), (int)p.getY() - PLAYER_HEIGHT/2, BALL_SIZE, BALL_SIZE, null);
		}

		g2d.fillRect(0, GameGUI.FRAME_H - 2 * FLOOR_HEIGHT, GameGUI.FRAME_W, FLOOR_HEIGHT + 50);
		
		g2d.setColor(HEALTHBAR_BACKGROUND);
		g2d.fillRect(HEALTHBAR_PAD, HEALTHBAR_PAD, HEALTHBAR_WIDTH, HEALTHBAR_HEIGHT);
		g2d.fillRect(GameGUI.FRAME_W - HEALTHBAR_PAD - HEALTHBAR_WIDTH, HEALTHBAR_PAD, HEALTHBAR_WIDTH, HEALTHBAR_HEIGHT);
		
		g2d.setColor(HEALTHBAR_FOREGROUND);
		g2d.fillRect(HEALTHBAR_PAD, HEALTHBAR_PAD,  (HEALTHBAR_WIDTH * p1health) / 100, HEALTHBAR_HEIGHT);
		g2d.fillRect(GameGUI.FRAME_W - HEALTHBAR_PAD - HEALTHBAR_WIDTH, HEALTHBAR_PAD, (HEALTHBAR_WIDTH * p2health) / 100, HEALTHBAR_HEIGHT);

	} 

	/**
	*	Method helper untuk mengecilkan/membersarkan, kemudian menggeser point
	*	ke tempat yang seharusnya pada koordinat frame
	*	@param point Titik yang akan diubah
	*	@param width Lebar gambar yang akan dibentuk dari titik
	*	@param height Tinggi gambar yang akan dibentuk dari titik
	*/
	private Point scaleAndInvert(Point point, int width, int height){
		Point p = new Point(point);
		
		p.scale(SCALE_X, SCALE_Y);

		p.invertY(GameGUI.FRAME_H);

		p.translate(-0.6 * width, -1.66 * height);

		return p;
	}

	// Mutator
	public void setPlayer1(Point p){
		player1 = p;
	}

	public void setPlayer2(Point p){
		player2 = p;
	}

	public void setBalls(ArrayList<Point> b){
		balls = b;
	}

	public void setPlayer1Health(int health){
		p1health = health;
	}

	public void setPlayer2Health(int health){
		p2health = health;
	}
}
