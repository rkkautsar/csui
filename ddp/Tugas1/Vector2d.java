class Vector2d
{
	private double 	dx,dy, // dx i + dx j
					ax,ay; // percepatan di sumbu x, percepatan di sumbu y

	public Vector2d(){
		dx = 0;
		dy = 0;
	}

	public Vector2d(double dx, double dy){
		this.dx = dx;
		this.dy = dy;
	}

	public Vector2d(PolarVector v){
		double angle = v.getAngle();
		double magnitude = v.getMagnitude();

		dx = magnitude * Math.cos(Math.toRadians(angle));
		dy = magnitude * Math.sin(Math.toRadians(angle));
	}

	void add(Vector2d v, double scale){
		dx += scale * v.getDx();
		dy += scale * v.getDy();
	}

	// accessor
	double getDx(){
		return dx;
	}

	double getDy(){
		return dy;
	}
}
