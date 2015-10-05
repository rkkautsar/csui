import java.util.Scanner;

public class Main
{
	private static final double EPS = 1e-9;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		double p1x,p2x,angle,v;
		
		System.out.print("Masukkan posisi x Player 1 (m): ");
		p1x = sc.nextDouble();
		System.out.print("Masukkan posisi x Player 2 (m): ");
		p2x = sc.nextDouble();
		System.out.print("Masukkan kecepatan awal tembakan Player 1 (m/s): ");
		v = sc.nextDouble();
		System.out.print("Masukkan sudut elevasi tembakan Player 1 (derajat): ");
		angle = sc.nextDouble();

		Point p1 				= new Point(p1x, 0);
		Point p2				= new Point(p2x, 0);
		ParabolicMotion motion 	= new ParabolicMotion(p1,v,angle);
		double time 			= 0;

		do {
			System.out.println(motion.at(time));
			++time;
		} while(motion.at(time).getY() >= 0);

		System.out.println(motion.max());

		if(Math.abs(motion.max().getX() - p2x) + EPS <= 5)
			System.out.println("Tembakan P1 berhasil mengenai P2!");
		else
			System.out.println("Tembakan P1 gagal mengenai P2!");
	}
}
