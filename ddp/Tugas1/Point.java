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

	void add(Vector2d v){
		x += v.getDx();
		y += v.getDy();
	}

	@Override
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
