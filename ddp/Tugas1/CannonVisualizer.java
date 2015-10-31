import java.util.ArrayList;
import javax.swing.JFrame;

@SuppressWarnings("serial")
class CannonVisualizer extends JFrame {

	private Visualizer visualizer;

	public CannonVisualizer(Player p1, Player p2, ArrayList<Point> pointsToDraw){
		super("Cannon War v0.2");
		setSize(Visualizer.FRAME_W + 50, Visualizer.FRAME_H + 50);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// System.out.println(p1.getPos() + " " + p2.getPos());
		// for(Point p : pointsToDraw){
		// 	System.out.println(p);
		// }
		
		visualizer = new Visualizer(p1,p2,pointsToDraw);
		add(visualizer);

		
		setVisible(true);
		Interface.delay(10000);
		setVisible(false);
		dispose();
	}
}
