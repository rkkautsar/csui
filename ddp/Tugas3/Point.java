/**
*	Kelas yang memodelkan Titik secara umum pada bidang kartesius
*	@author Rakha Kanz Kautsar
*	@version 06.10.2015
*/
class Point
{
	/**
	*	Variabel yang menjelaskan posisi titik dalam sumbu x dan sumbu y
	*	dalam diagram kartesius
	*/
	private double x,y;

	/**
	*	Konstruktor default untuk class Point,
	*	menjadikan titik (0,0) sebagai default
	*/
	public Point(){
		x = 0;
		y = 0;
	}

	public Point(Point other){
		x = other.getX();
		y = other.getY();
	}

	/**
	*	Konstruktor untuk menjadikan titik (x,y)
	*	menjadi titik yang dimaksud pada kelas Point
	*	@param x posisi pada sumbu x kartesius
	*	@param y posisi pada sumbu y kartesius
	*/
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}

	/**
	*	Method untuk melakukan translasi sebesar
	*	dx dan dy pada Point
	*	@param dx besar translasi pada sumbu x
	*	@param dy besar translasi pada sumbu y
	*/
	public void translate(double dx,double dy){
		x += dx;
		y += dy;
	}

	/**
	*	Method untuk melakukan penambahan vektor
	*	ke Point
	*	@param v vektor perpindahan titik
	*/
	public void add(Vector2d v){
		translate(v.getDx(), v.getDy());
	}

	/**
	*	Method helper untuk memformat Point dalam representasi String
	*/
	public String toString(){
		return "(" + x + ", " + y + ")";
	}

	public void scaleX(double s){
		x *= s;
	}

	public void scaleY(double s){
		y *= s;
	}

	public void scale(double sx, double sy){
		scaleX(sx);
		scaleY(sy);
	}

	public void scale(double s){
		scale(s,s);
	}

	public void invertY(double height){
		y = height - y;
	}

	// accessor
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
}
