import java.awt.Color;
import java.awt.Shape;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

class GamePanel extends JComponent {
	private static final long serialVersionUID = 1506688784L;

	private Point player1;
	private Point player2;
	private Point ball;
	private Point p;

	private final int PLAYER_SIZE = 30;
	private final int BALL_SIZE = 15;
	private final Color PLAYER1_COLOR = Color.RED;
	private final Color PLAYER2_COLOR = Color.BLUE;
	private final Color BALL_COLOR    = Color.BLACK;
	
	public GamePanel(Point p1, Point p2, Point b){
		player1 = p1;
		player2 = p2;
		ball = b;	

		setMinimumSize(new Dimension(GameGUI.FRAME_W, GameGUI.FRAME_H));
	}

	@Override 
	protected void paintComponent(Graphics g){
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(	RenderingHints.KEY_ANTIALIASING,
								RenderingHints.VALUE_ANTIALIAS_ON);

		if(ball != null){
			p = scaleAndInvert(ball, BALL_SIZE);
			g2d.setColor(BALL_COLOR);
			g2d.fill(new Ellipse2D.Double(p.getX(), p.getY(), BALL_SIZE, BALL_SIZE));
		}

		if(player1 != null){
			p = scaleAndInvert(player1, PLAYER_SIZE);
			g2d.setColor(PLAYER1_COLOR);
			g2d.fill(new Rectangle2D.Double(p.getX(), p.getY(), PLAYER_SIZE, PLAYER_SIZE));			
		}		

		if(player2 != null){
			p = scaleAndInvert(player2, PLAYER_SIZE);
			g2d.setColor(PLAYER2_COLOR);
			g2d.fill(new Rectangle2D.Double(p.getX(), p.getY(), PLAYER_SIZE, PLAYER_SIZE));
		}

	} 

	private Point scaleAndInvert(Point point, int size){
		Point p = new Point(point);
		double scaleFactorX = GameGUI.FRAME_W*1.0 / Game.ARENA_X_BOUNDARY;
		double scaleFactorY = GameGUI.FRAME_H*1.0 / Game.ARENA_Y_BOUNDARY;
		p.scale(scaleFactorX, scaleFactorY);

		p.invertY(GameGUI.FRAME_H);

		p.translate(0,-2 * size);

		return p;
	}

	public void setPlayer1(Point p){
		player1 = p;
	}

	public void setPlayer2(Point p){
		player2 = p;
	}

	public void setBall(Point p){
		ball = p;
	}
}
