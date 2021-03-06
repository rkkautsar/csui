import java.awt.Color;
import java.awt.Shape;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.Timer;

@SuppressWarnings("serial")
class Visualizer extends JComponent
{
	public static final int FRAME_W = 1000;
	public static final int FRAME_H = 500;

	private final int PLAYER_SIZE = 30;
	private final int BALL_SIZE = 15;
	private final Color PLAYER1_COLOR = Color.RED;
	private final Color PLAYER2_COLOR = Color.BLUE;
	private final Color BALL_COLOR    = Color.BLACK;

	private Point player1;
	private Point player2;
	private Point ball;
	private ArrayList<Point> ball_motions;
	private int ball_index;
	private Timer timer;

	public Visualizer(Player p1, Player p2, ArrayList<Point> pointsToDraw){
		setMinimumSize(new Dimension(FRAME_W, FRAME_H));		

		player1 = new Point(p1.getPos());
		player2 = new Point(p2.getPos());
		ball_motions = new ArrayList<Point>();
		for(Point p: pointsToDraw){
			ball_motions.add(p);
		}

		ball_index = 0;
		ball = ball_motions.get(ball_index);

		scaleAndInvertPoints();

		timer = new Timer(33, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(ball_index + 1 < ball_motions.size()){
					++ball_index;
					ball = ball_motions.get(ball_index);
				} else {
					timer.stop();
				}
				repaint();
			}
		});

		timer.start();
	}

	@Override 
	protected void paintComponent(Graphics g){
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(	RenderingHints.KEY_ANTIALIASING,
								RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(BALL_COLOR);
		g2d.fill(new Ellipse2D.Double(ball.getX(), ball.getY(), BALL_SIZE, BALL_SIZE));
		
		g2d.setColor(PLAYER1_COLOR);
		g2d.fill(new Ellipse2D.Double(player1.getX(), player1.getY(), PLAYER_SIZE, PLAYER_SIZE));

		g2d.setColor(PLAYER2_COLOR);
		g2d.fill(new Ellipse2D.Double(player2.getX(), player2.getY(), PLAYER_SIZE, PLAYER_SIZE));
	}

	private void scaleAndInvertPoints(){
		double min_x, min_y, max_x, max_y;
		double scaleFactorX, scaleFactorY, scaleFactor;

		min_x = Math.min(player1.getX(), player2.getX());
		max_x = Math.max(player1.getX(), player2.getX());
		min_y = Math.min(player1.getY(), player2.getY());
		max_y = Math.max(player1.getY(), player2.getY());

		for(Point p: ball_motions){
			min_x = Math.min(min_x, p.getX());
			max_x = Math.max(max_x, p.getX());
			min_y = Math.min(min_y, p.getY());
			max_y = Math.max(max_y, p.getY());
		}

		scaleFactorX = (FRAME_W - 2 * PLAYER_SIZE) / ((max_x - min_x));
		scaleFactorY = (FRAME_H) / ((max_y - min_y));
		scaleFactor  = Math.min(scaleFactorX,  scaleFactorY);

		// normalize, scale, and invert
		player1.translate(-min_x, -min_y);
		player2.translate(-min_x, -min_y);
		player1.scale(scaleFactor);
		player2.scale(scaleFactor);
		player1.invertY(FRAME_H);
		player2.invertY(FRAME_H);
		player1.translate(0, -PLAYER_SIZE);
		player2.translate(0, -PLAYER_SIZE);

		for(Point p: ball_motions){
			p.translate(-min_x, -min_y);
			p.scale(scaleFactor);
			p.translate(-BALL_SIZE/2.0, -BALL_SIZE/2.0);
			p.translate(PLAYER_SIZE/2.0, PLAYER_SIZE/2.0);
			p.invertY(FRAME_H);
			p.translate(0, -BALL_SIZE);
		}
	}
}
