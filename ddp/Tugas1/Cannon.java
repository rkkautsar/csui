class Cannon
{
	private static final double EPS = 1e-9,
								CANNON_RADIUS = 5,
								CANNON_BASE_DAMAGE = 75;
	private static int timeLen, xLen, yLen;
	Point pos;

	public Cannon(){
		pos = new Point();
	}

	public Cannon(Point pos){
		this.pos = pos;
	}

	public void shoot(Player shooter, Player target, double v, double angle, Wind wind){
		ParabolicMotion motion = new ParabolicMotion(shooter.getPos(), v, angle, wind);

		calculateLength(motion);

		int time = 0;
		do {
			printAtTime(time, motion.at(time));
			++time;
		} while(motion.at(time).getY() >= 0);

		if(motion.timeMax() > time - 1 + EPS)
			printAtTime(motion.timeMax(), motion.max());

		double 	radiusTarget  = Math.abs(motion.max().getX() - target.getPos().getX()),
				radiusShooter = Math.abs(motion.max().getX() - shooter.getPos().getX());
		int damage;
		boolean miss = true;

		if(radiusTarget < CANNON_RADIUS + EPS){
			miss = false;
			damage = (int) ((1-(radiusTarget/CANNON_RADIUS)) * CANNON_BASE_DAMAGE);

			System.out.println( "Tembakan " + shooter.getName() + 
								" mengenai " + target.getName() +
								" dengan damage " + damage);

			if(target.hit(damage)) {
				System.out.println( "HP " + target.getName() + " habis!");
				System.out.println(shooter.getName() + " menang!");
				System.exit(0);
			} else {
				System.out.println( "HP " + target.getName() + 
									" berkurang menjadi " + target.getHealth());
			}
		}


		if(radiusShooter < CANNON_RADIUS + EPS){
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

				System.exit(0);
			} else{
				System.out.println( "HP " + shooter.getName() +
									" berkurang menjadi " + target.getHealth());
			}
		}

		if(miss){
			System.out.println("Tembakan " + shooter.getName() + " meleset.");
		}
	}

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

	private void printAtTime(double time, Point p){
		String format = new String();

		format += "t:%" + timeLen + ".2f ";
		format += "x:%" + xLen + ".2f ";
		format += "y:%" + yLen + ".2f\n";
		System.out.printf(format, time, p.getX(), p.getY());
	}
}
