import java.util.Random;

/**
*	Kelas yang mengimplentasikan sebuah AI sederhana
*	yang melakukan keputusan untuk setiap turn dan
*	melakukan perhitungan sederhana setiap kali menembak
*	@author Rakha Kanz Kautsar
*/
class Ai extends Player {
	/**
	*	Batasan maksimum posisi AI
	*/
	public static final int AI_POS_BOUND = 100;

	/**
	*	Nilai yang menunjukkan kecendrungan AI
	*	untuk melakukan gerakan *move*
	*/
	public static final double AI_MOVE_PROBABILITY = 0.25;

	/**
	*	Nilai kecepatan default pada sumbu x
	*	yang akan digunakan pada perhitungan penembakan
	*/
	public static final double AI_DEFAULT_VX = 80;

	/**
	*	Random number generator untuk membangkitkan nilai
	*	random yang unik untuk setiap pemanggilan
	*/
	private Random generator;

	/**
	*	Konstruktor AI, menjadikan nama AI menjadi "Noob Bot",
	*	HP AI menjadi 100, posisi AI random (0,AI_POS_BOUND), dan
	*	memanggil konstruktor cannon
	*/
	public Ai(){
		generator = new Random(System.currentTimeMillis());
		name = "Noob Bot";
		health = 100;
		pos = new Point(Math.round(generator.nextInt(AI_POS_BOUND)),0);
		cannon = new Cannon();
	}

	/**
	*	Method yang mengimplementasikan penentuan keputusan dari AI
	*	antara *move* atau *shoot* dengan menggunakan Random Number Generator
	*	dan memperhatikan AI_MOVE_PROBABILITY
	*	@param enemy Musuh sebagai objek Player
	*	@param wind Angin yang sedang berlangsung
	*/
	public void turn(Player enemy, Wind wind){
		if(generator.nextDouble() < AI_MOVE_PROBABILITY){
			System.out.println("Noob Bot chooses to move.");

			int dx = generator.nextInt(20)-10;
			System.out.println("Noob Bot moves by " + dx + ".");
			move(dx);
		} else {
			System.out.println("Noob Bot chooses to shoot.");
			shoot(enemy, wind);
		}
	}

	/**
	*	Method yang mengimplementasikan penghitungan sudut dan kecepatan
	*	yang tepat untuk menembak enemy saat wind berlangsung
	*	@param enemy Musuh sebagai objek Player
	*	@param wind Angin yang sedang berlangsung
	*/
	private void shoot(Player enemy, Wind wind){
		/*
			distance = vx * time
			vx = distance / time
			time = 2 * vy /g
			vx = distance * g / 2 * vy

			Let vx constants.
			
			vy = distance * g / (2*vx)
			v = hypot(vx,vy)
			theta = atan(vy/vx)
		*/

		double distance = Math.abs(pos.getX() - enemy.getPos().getX());

		double 	vx = AI_DEFAULT_VX,
				vy = (distance * ParabolicMotion.GRAVITY)/(2 * vx);

		vx -= wind.getDx();
		vy -= wind.getDy();

		double velocity = Math.sqrt(vx*vx + vy*vy);
		double angle = Math.atan2(vy,vx) * 180 / Math.PI;

		if(pos.getX() > enemy.getPos().getX())
			angle = 180 - angle;

		velocity = Math.min(velocity, Game.MAX_POWER);

		shoot(enemy, velocity, angle, wind);
	}


}
