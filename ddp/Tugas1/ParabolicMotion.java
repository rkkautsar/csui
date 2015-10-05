public class ParabolicMotion
{
	private final double GRAVITY = 9.81;
	private Point origin;
	private double vx, vy;
	
	public ParabolicMotion() {
		vx = 0;
		vy = 0;
		origin = new Point();
	}

	public ParabolicMotion(Point origin, double v, double angle) {
		this.origin = origin;
		vx = v * Math.cos(Math.toRadians(angle));
		vy = v * Math.sin(Math.toRadians(angle));
	}

	Point at(double time){
		double xt, yt;
		xt = origin.getX() + vx * time;
		yt = origin.getY() + vy * time - 0.5 * GRAVITY * time * time;
		return new Point(xt,yt); 
	}

	Point max(){
		double tmax = 2* (vy / GRAVITY);
		return this.at(tmax);
	}
}
