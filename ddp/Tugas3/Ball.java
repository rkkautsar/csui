/**
*	Kelas yang mengimplementasikan bola sebagai
*	obyek yang bergerak secara parabolik
*	@author Rakha Kanz Kautsar
*	@version 10-11-2015
*/
class Ball extends ParabolicMotion{
	/**
	*	Jangkauan damage bola
	*/
	public static final double HIT_RADIUS = 25;
	/**
	*	Damage dasar bola jika tepat mengenai posisi
	*/
	public static final double BASE_DAMAGE = 40;
	/**
	*	Waktu yang telah dihabiskan bola sejak dilempar
	*/
	private double time;

	/**
	*	Konstruktor bola, hanya menambahkan pengaturan waktu menjadi 0
	*	dari konstruktor subkelasnya.
	*	@param origin posisi awal
	*	@param v kecepatan awal
	*	@param angle sudut elevasi
	*	@param wind angin yang berlangsung
	*/
	public Ball(Point origin, double v, double angle, Wind wind){
		super(origin, v, angle, wind);
		time = 0;
	}

	/**
	*	Memajukan waktu sebanyak dt
	*	@param dt waktu yang dimajukan
	*/
	public void next(double dt){
		time += dt;
	}

	/**
	*	Melakukan damage pada pemain
	*	@param player pemain yang dikenakan damage
	*	@return Apakah bola mengenai pemain
	*/	
	public boolean hit(Player player){
		double radius  = Math.abs(max().getX() - player.getPos().getX());

		if(radius + Game.EPS < HIT_RADIUS)
			player.hit((int) ((1-(radius/HIT_RADIUS)) * BASE_DAMAGE));

		return radius + Game.EPS < HIT_RADIUS;
	}

	/**
	*	Mengecek apakah bola sedang melayang (belum jatuh ke tanah)
	*	@return apakah bola sedang melayang
	*/
	public boolean flying(){
		return getPos().getY() > - Game.EPS;
	}

	// Accessor
	public Point getPos(){
		return at(time);
	}

}
