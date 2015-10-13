/**
*	Kelas yang mengimplementasikan cannon yang menunjukkan
*	logic dan langkah-langkah detil yang dilakukan saat Player
*	menembakkan peluru
*	@author Rakha Kanz Kautsar
*/
class Cannon
{
	/**
	*	Radius dimana tembakan cannon memberikan damage
	*/
	private static final double CANNON_RADIUS = 5;

	/**
	*	Nilai damage jika peluru tepat mengenai posisi
	*	suatu objek Player
	*/
	private static final double CANNON_BASE_DAMAGE = 50;

	/**
	*	Variabel helper untuk memformat output
	*/
	private static int timeLen, xLen, yLen;


	/**
	*	Konstruktor default untuk Cannon,
	*	tidak melakukan apa-apa
	*/
	public Cannon(){

	}

	/**
	*	Method yang menunjukkan detil penembakan peluru oleh shooter ke target
	*	serta penentuan damage dan end game state dari shooter maupun target
	*	@param shooter Player yang menembak
	*	@param target Player yang ditembak
	*	@param v kecepatan awal tembakan
	*	@param angle sudut elevasi tembakan
	*	@param wind angin yang mempengaruhi penembakan
	*/
	public void shoot(Player shooter, Player target, double v, double angle, Wind wind){
		ParabolicMotion motion = new ParabolicMotion(shooter.getPos(), v, angle, wind);

		calculateLength(motion);

		int time = 0;
		do {
			printAtTime(time, motion.at(time));
			++time;
		} while(motion.at(time).getY() > Game.EPS);

		if(motion.timeMax() > time - 1 + Game.EPS)
			printAtTime(motion.timeMax(), motion.max());

		double 	radiusTarget  = Math.abs(motion.max().getX() - target.getPos().getX()),
				radiusShooter = Math.abs(motion.max().getX() - shooter.getPos().getX());
		int damage;

		boolean miss = true, gameover = false;

		if(radiusTarget < CANNON_RADIUS + Game.EPS){
			miss = false;
			damage = (int) ((1-(radiusTarget/CANNON_RADIUS)) * CANNON_BASE_DAMAGE);

			System.out.println( "Tembakan " + shooter.getName() + 
								" mengenai " + target.getName() +
								" dengan damage " + damage);

			if(target.hit(damage)) {
				System.out.println( "HP " + target.getName() + " habis!");
				System.out.println(shooter.getName() + " menang!");
				gameover = true;
			} else {
				System.out.println( "HP " + target.getName() + 
									" berkurang menjadi " + target.getHealth());
			}
		}


		if(radiusShooter < CANNON_RADIUS + Game.EPS){
			miss = false;
			damage = (int) ((1-(radiusShooter/CANNON_RADIUS)) * CANNON_BASE_DAMAGE);

			System.out.println("Tembakan " + shooter.getName() +
								" mengenai dirinya sendiri dengan damage " + damage);

			if(shooter.hit(damage)){
				System.out.println("HP " + shooter.getName() + " habis!");
				if(target.getHealth() > 0)
					System.out.println(target.getName() + " menang!");
				else
					System.out.println("Pertandingan ini seri.");

				gameover = true;
			} else{
				System.out.println( "HP " + shooter.getName() +
									" berkurang menjadi " + target.getHealth());
			}
		}

		if(gameover){
			System.out.println();
			System.out.println("------------------------------------");
			System.out.println("             GAME OVER");
			System.out.println("------------------------------------");
			System.out.println();
			System.exit(0);
		}

		if(miss){
			System.out.println("Tembakan " + shooter.getName() + " meleset.");
		}
	}

	/**
	*	Method helper untuk memperhitungkan panjang maksimum output
	*	waktu, posisi x, dan posisi y pada suatu ParabolicMotion
	*	yang kemudian disimpan dalam variabel statis timeLen, xLen,dan yLen
	*	@param motion ParabolicMotion yang dimaksud
	*/
	private void calculateLength(ParabolicMotion motion){
		String 	tmpT = new String(),
				tmpX = new String(),
				tmpY = new String();
		tmpT += (int)motion.timeMax();
		tmpX += (int)motion.xMax();
		tmpY += (int)motion.yMax();

		timeLen = tmpT.length() + 4;
		xLen	= tmpX.length() + 4;
		yLen	= tmpY.length() + 4;
	}

	/**
	*	Method helper yang akan mengoutputkan posisi p
	*	di waktu time dengan format yang telah ditentukan
	*	@param time variabel waktu yang ingin dicetak
	*	@param p Point p yang ingin dicetak
	*/
	private void printAtTime(double time, Point p){
		String format = new String();

		format += "t:%" + timeLen + ".2f ";
		format += "x:%" + xLen + ".2f ";
		format += "y:%" + yLen + ".2f\n";
		System.out.printf(format, time, p.getX(), p.getY());
	}
}
