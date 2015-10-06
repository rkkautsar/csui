import java.util.Scanner;

class Game
{	
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

		try{
			System.out.print("Masukkan nama Player 1 > ");
			name = sc.nextLine();
			if(name.length()<=0) name = "Player 1";
			System.out.print("Masukkan posisi x " + name + " (dalam m) > ");
			x = sc.nextInt(); sc.nextLine();
			player1 = new Player(name, new Point(x,0));

			System.out.println("=========================================");
			System.out.println("----- Player 1 --------------------------");
			System.out.println(" Name 		: " + player1.getName());
			System.out.println(" Position 	: (" + x + ", 0)");
			System.out.println(" Health 	: 100");
			System.out.println("=========================================");

			System.out.println();

			System.out.print("Masukkan nama Player 2 > ");
			name = sc.nextLine();
			if(name.length()<=0) name = "Player 2";
			System.out.print("Masukkan posisi x " + name + " (dalam m) > ");
			x = sc.nextInt();sc.nextLine();
			player2 = new Player(name, new Point(x,0));

			System.out.println("=========================================");
			System.out.println("----- Player 2 --------------------------");
			System.out.println(" Name 		: " + player2.getName());
			System.out.println(" Position 	: (" + x + ", 0)");
			System.out.println(" Health 	: 100");
			System.out.println("=========================================");
		} catch(Exception e){
			System.err.println();
			System.err.println("=========================================");
			System.err.println("                 Error!");
			System.err.println("=========================================");
			System.err.println();
			sc.nextLine();
			readInput();
		}		
	}

	private void stageLoop(int round){
		double angle,power;

		Wind roundWind = new Wind();		

		System.out.println("==========================");
		System.out.println("   Round #" + round);
		System.out.printf ("Wind Vector = (%5.2f, %5.2f)\n", roundWind.getDx(), roundWind.getDy());
		System.out.println("==========================");

		System.out.println();

		System.out.println("Giliran " + player1.getName());
		System.out.println("---------------------------------------");
		System.out.print("Masukkan sudut elevasi (dalam derajat ke arah " + player2.getName() + ") > ");
		angle = sc.nextDouble();
		System.out.print("Masukkan kecepatan awal (dalam m/s ke arah " + player2.getName() + ") > ");
		power = sc.nextDouble();

		if(player1.getPos().getX() > player2.getPos().getX() ){
			angle = 180 - angle;
		}

		player1.shoot(player2, power, angle, roundWind);

		System.out.println();

		System.out.println("Giliran " + player2.getName());
		System.out.println("---------------------------------------");
		System.out.print("Masukkan sudut elevasi (dalam derajat ke arah " + player1.getName() + ") > ");
		angle = sc.nextDouble();
		System.out.print("Masukkan kecepatan awal (dalam m/s ke arah " + player1.getName() + ") > ");
		power = sc.nextDouble();

		if(player2.getPos().getX() > player1.getPos().getX() ){
			angle = 180 - angle;
		}

		System.out.println(angle + " " + power);
		player2.shoot(player1, power, angle, roundWind);

		System.out.println();
	}
}
