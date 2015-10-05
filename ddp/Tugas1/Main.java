import java.util.Scanner;

public class Main
{
	private static final double EPS = 1e-9,
								EXPLOSION_RANGE	= 5;
	static int timeLen, xLen, yLen;

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		double 	p1x = 0,
				p2x = 0,
				angle = 0,
				v = 0;
		
		try {
			System.out.print("Masukkan posisi x Player 1 (m): ");
			p1x = sc.nextDouble();
			System.out.print("Masukkan posisi x Player 2 (m): ");
			p2x = sc.nextDouble();
			System.out.print("Masukkan kecepatan awal tembakan Player 1 (m/s): ");
			v = sc.nextDouble();
			System.out.print("Masukkan sudut elevasi tembakan Player 1 (derajat): ");
			angle = sc.nextDouble();
		} catch (Exception e){
			System.out.println("Input salah!");
			System.exit(1);
		}

		Point p1 				= new Point(p1x, 0);
		Point p2				= new Point(p2x, 0);
		ParabolicMotion motion 	= new ParabolicMotion(p1,v,angle);
		double time 			= 0;
		Point pointAtTime;

		calculateLength(motion);

		do {
			printAtTime(time, motion.at(time));
			++time;
		} while(motion.at(time).getY() >= 0);

		if(motion.timeMax() > time - 1 + EPS)
			printAtTime(motion.timeMax(), motion.max());

		boolean miss = true;
		
		if(Math.abs(motion.max().getX() - p2.getX()) < EXPLOSION_RANGE + EPS){
			System.out.println("Tembakan P1 berhasil mengenai P2!");
			miss = false;
		}

		if(Math.abs(motion.max().getX() - p1.getX()) < EXPLOSION_RANGE + EPS){
			System.out.println("Tembakan P1 mengenai dirinya sendiri!");
			miss = false;
		}

		if(miss)
			System.out.println("Tembakan P1 meleset!");
	}

	static void calculateLength(ParabolicMotion motion){
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

	static void printAtTime(double time, Point p){
		String format = new String();

		format += "t:%" + timeLen + ".2f ";
		format += "x:%" + xLen + ".2f ";
		format += "y:%" + yLen + ".2f\n";
		System.out.printf(format, time, p.getX(), p.getY());
	}
}
