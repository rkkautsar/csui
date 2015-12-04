/**
*	Kelas yang mengimplementasikan sebuah vector dua dimensi
* 	yang memiliki vektor satuan dx dan vektor satuan dy.
*	@author Rakha Kanz Kautsar
*	@author 06.10.2015
*/
class Vector2d
{
	/**
	*	Vektor satuan (m) di sumbu x
	*/
	protected double dx;
	/**
	*	Vektor satuan (m) di sumbu y
	*/
	protected double dy;

	/**
	*	Konstruktor default dari kelas Vector2d,
	*	menjadikan dx dan dy nol.
	*/
	public Vector2d(){
		dx = 0;
		dy = 0;
	}

	/**
	*	Konstruktor kelas Vector2d
	*	@param dx Panjang vektor pada sumbu x dalam meter
	*	@param dy Panjang vektor pada sumbu y dalam meter
	*/
	public Vector2d(double dx, double dy){
		this.dx = dx;
		this.dy = dy;
	}

	/**
	*	Method helper untuk memformat Vector2d dalam representasi String
	*/
	public String toString(){
		return "(" + dx + ", " + dy + ")";
	}

	// accessor
	public double getDx(){
		return dx;
	}

	public double getDy(){
		return dy;
	}
}
