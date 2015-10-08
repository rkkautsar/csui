import java.util.Scanner;

class Game
{	
	public static final int MAX_POWER = 100,
							MAX_MOVE  = 10;
	private Player player1, player2;
	private Scanner sc;

	public Game(){
		sc = new Scanner(System.in);
		readInput();

		int round = 1;

		while(true) {
			stageLoop(round++);
		}
	}

	private void readInput(){
		String name;
		int x;
		boolean success = false;

		do {
			try{
				System.out.print("Masukkan nama Player 1 > ");
				name = sc.nextLine();
				if(name.length()<=0) name = "Player 1";

				System.out.print("Masukkan posisi x " + name + " (dalam m) > ");
				x = sc.nextInt(); 
					sc.nextLine();
				player1 = new Player(name, new Point(x,0));

				System.out.print("Masukkan nama Player 2 > ");
				name = sc.nextLine();
				if(name.length()<=0) name = "Player 2";

				System.out.print("Masukkan posisi x " + name + " (dalam m) > ");
				x = sc.nextInt();
					sc.nextLine();
				player2 = new Player(name, new Point(x,0));

				success = true;

			} catch(Exception e){
				System.err.println();
				System.err.println("=========================================");
				System.err.println("      Input salah, harap ulangi lagi!");
				System.err.println("=========================================");
				System.err.println();
				sc.nextLine();
			}		
		} while(!success);
	}

	private void stageLoop(int round){	

		System.out.println();
		System.out.println("==========================");
		System.out.println("   Round #" + round);
		System.out.println("==========================");
		System.out.println();

		// ---------------------------------------------------------

		turn(player1, player2);

		turn(player2, player1);

	}

	private void turn(Player player, Player enemy){
		Wind roundWind = new Wind();	
		String command;

		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println("Giliran " + player.getName());
		System.out.println("Posisi " + player.getName() + ": " + player.getPos());
		System.out.println("Posisi " + enemy.getName() + ": " + enemy.getPos());
		System.out.printf ("Kecepatan angin dalam m/s: (%5.2f, %5.2f)\n", roundWind.getDx(), roundWind.getDy());
		System.out.println("---------------------------------------");
		System.out.println();

		do {
			System.out.print("[Move] / [Shoot]? ");
			command = sc.nextLine();
			if(command.equalsIgnoreCase("move") || command.equalsIgnoreCase("shoot"))
				break;
			else{
				System.err.println();
				System.err.println("=========================================");
				System.err.println("      Input salah, harap ulangi lagi!");
				System.err.println("=========================================");
				System.err.println();
			}
		} while(true);

		if(command.equalsIgnoreCase("move"))
			turnMove(player);
		else
			turnShoot(player, enemy, roundWind);
	}

	private void turnMove(Player player){
		int dx;

		do {
			System.out.println("Masukkan perpindahan dalam x integer,");
			System.out.println("Negatif ke kiri, positif ke kanan");
			System.out.print("(m, [-" + MAX_MOVE + ", " + MAX_MOVE + "]) : ");

			dx = 	sc.nextInt();
					sc.nextLine();

			if(dx < -MAX_MOVE || dx > MAX_MOVE){
				System.err.println();
				System.err.println("=========================================");
				System.err.println("      Input salah, harap ulangi lagi!");
				System.err.println("=========================================");
				System.err.println();
			}
		} while(dx < -MAX_MOVE || dx > MAX_MOVE);

		player.move(dx);
	}

	private void turnShoot(Player player, Player enemy, Wind roundWind){
		double angle,power;	
		do {
			System.out.print("Masukkan sudut elevasi (derajat, [0,180]) : ");
			angle = sc.nextDouble();
					sc.nextLine();

			System.out.print("Masukkan kecepatan awal (m/s, [0," + MAX_POWER + "]) : ");
			power = sc.nextDouble();
					sc.nextLine();

			if(angle < 0 || angle > 180 || power < 0 || power > MAX_POWER){
				System.err.println();
				System.err.println("=========================================");
				System.err.println("      Input salah, harap ulangi lagi!");
				System.err.println("=========================================");
				System.err.println();
			}
		} while(angle < 0 || angle > 180 || power < 0 || power > MAX_POWER);

		if(player.getPos().getX() > enemy.getPos().getX() ){
			angle = 180 - angle;
		}

		player.shoot(enemy, power, angle, roundWind);
	}
}
