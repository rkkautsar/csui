class Point{
	private int x,y;

	public Point(){
		x=y=0;
	}
	
	public Point(int xx,int yy){
		x=xx;
		y=yy;
	}

	void geser(int dx,int dy){
		x += dx;
		y += dy;
	}

	double jarak(Point other){
		return Math.hypot(other.getX()-x, other.getY()-y);
	}

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int xx){
		x = xx;
	}
	public void setY(int yy){
		y = yy;
	}
};
