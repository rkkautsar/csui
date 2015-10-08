class Point
{
	private double x,y;

	public Point(){
		x = 0;
		y = 0;
	}

	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}

	void translate(double dx,double dy){
		x += dx;
		y += dy;
	}

	void add(Vector2d v){
		translate(v.getDx(), v.getDy());
	}

	/**
	*	Method helper untuk memformat Point dalam representasi String
	*/
	public String toString(){
		return "(" + x + ", " + y + ")";
	}

	// accessor
	double getX(){
		return x;
	}
	
	double getY(){
		return y;
	}
}
