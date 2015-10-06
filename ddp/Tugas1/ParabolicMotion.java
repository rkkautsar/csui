public class ParabolicMotion
{
	private final double GRAVITY = -9.81;
	private Point origin;
	private double vx, vy;
	private Wind wind;
	
	public ParabolicMotion() {
		vx = 0;
		vy = 0;
		origin = new Point();
		wind = new Wind(0);
	}

	public ParabolicMotion(Point origin, double v, double angle, Wind wind) {
		this.origin = origin;
		vx = v * Math.cos(Math.toRadians(angle));
		vy = v * Math.sin(Math.toRadians(angle));
		this.wind = wind;	
	}

	public Point at(double time){
		double xt, yt;
		xt = origin.getX() + (vx + wind.getDx()) * time;
		yt = origin.getY() + (vy + wind.getDy()) * time + 0.5 * GRAVITY * time * time;
		return new Point(xt,yt); 
	}

	public double timeMax(){
		return Math.abs(2 * ((vy + wind.getDy()) / GRAVITY));
	}

	public Point max(){
		return this.at(this.timeMax());
	}

	public double xMax(){
		return this.max().getX();
	}

	public double yMax(){
		return this.at(this.timeMax()/2).getY();
	}
}
