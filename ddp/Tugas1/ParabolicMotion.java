/**
*	Kelas yang mengimplementasi gerakan parabola dengan gravitasi dan
*	dipengaruhi kecepatan angin tertentu.
*	@author Rakha Kanz Kautsar
*	@version 06.10.2015
*/
class ParabolicMotion
{	
	/**
	*	Konstanta percepatan gravitasi dalam m/s^2
	*/
	public static final double GRAVITY = -9.81;
	/**
	*	Titik asal dari gerak parabola
	*/
	private Point origin;
	/**
	*	Kecepatan pada sumbu x dan sumbu y
	*/
	private double vx, vy;
	/**
	*	Angin yang mempengaruhi lintasan parabola
	*/
	private Wind wind;
	
	/**
	*	Konstruktor default dari kelas ParabolicMotion,
	*	membuat kecepatan awal nol dan variabel lain mengikuti
	*	konstruktor default masing-masing.
	*/
	public ParabolicMotion() {
		vx = 0;
		vy = 0;
		origin = new Point();
		wind = new Wind();
	}

	/**
	*	Konstruktor untuk kelas ParabolicMotion, mengubah vektor kecepatan
	*	dari bentuk polar coordinate ke bentuk umum xi + yj
	*	@param origin Titik awal gerak parabola
	*	@param v Besar kecepatan dalam m/s
	*	@param angle Besar sudut elevasi dalam derajat
	*	@param wind Kecepatan angin yang mempengaruhi lintasan parabola
	*/
	public ParabolicMotion(Point origin, double v, double angle, Wind wind) {
		this.origin = origin;
		vx = v * Math.cos(Math.toRadians(angle));
		vy = v * Math.sin(Math.toRadians(angle));
		this.wind = wind;	
	}

	/**
	*	Method yang menghitung posisi pada lintasan parabola pada waktu yang diinginkan
	*	@param time Waktu yang diinginkan
	*	@return Posisi di lintasan parabola pada saat time
	*/
	public Point at(double time){
		double xt, yt;
		xt = origin.getX() + vx * time + 0.5 * wind.getDx() * time * time;
		yt = origin.getY() + vy * time + 0.5 * (GRAVITY + wind.getDy()) * time * time;
		return new Point(xt,yt); 
	}

	/**
	*	Method yang mengembalikan waktu yang dibutuhkan untuk mencapai nilai x maksimal
	*	pada lintasan parabola
	*	@return Waktu yang dibutuhkan untuk mencapai x maksimal
	*/
	public double timeMax(){
		return Math.abs(2 * ((vy + wind.getDy()) / GRAVITY));
	}

	/**
	*	Method yang mengembalikan posisi x maksimal saat y = 0
	*	@return titik saat x maksimal
	*/
	public Point max(){
		return this.at(this.timeMax());
	}

	/**
	*	Method yang mengembalikan posisi maksimal lintasan di sumbu x
	*	@return nilai maksimal di sumbu x
	*/
	public double xMax(){
		return this.max().getX();
	}

	/** 
	*	Method yang mengembalikan posisi maksimal lintasan di sumbu y
	*	@return nilai maksimal di sumbu y
	*/
	public double yMax(){
		return this.at(this.timeMax()/2).getY();
	}
}
