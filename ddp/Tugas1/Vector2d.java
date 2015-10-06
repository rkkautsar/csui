class Vector2d
{
	protected double dx,dy; // dx i + dx j

	public Vector2d(){
		dx = 0;
		dy = 0;
	}

	public Vector2d(Vector2d other){
		dx = other.getDx();
		dy = other.getDy();
	}

	public Vector2d(double dx, double dy){
		this.dx = dx;
		this.dy = dy;
	}

	public void add(Vector2d v, double scale){
		dx += scale * v.getDx();
		dy += scale * v.getDy();
	}

	// accessor
	public double getDx(){
		return dx;
	}

	public double getDy(){
		return dy;
	}
}
