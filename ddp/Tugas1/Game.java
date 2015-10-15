import java.util.Scanner;

/**
*	Kelas yang mengimplementasikan game itu sendiri,
*	mulai dari logic sederhana hingga interfacenya
*	@author Rakha Kanz Kautsar
*	@version 15.10.2015
*/
class Game
{	
	/**
	*	Nilai yang sangat kecil sebagai bantuan dalam
	*	membandingkan floating number
	*/
	public static final double EPS = 1e-9;

	/**
	*	Batasan kecepatan peluru
	*/
	public static final int MAX_POWER = 100;

	/**
	*	Batasan perpindahan maksimal
	*/
	public static final int MAX_MOVE  = 10;

	/**
	*	Player yang bermain
	*/
	private Player player1, player2;
	
	/**
	*	Bot yang menjadi lawan Player
	*/
	private Ai bot;

	/*
	*	Scanner untuk menerima input dari user
	*/
	private Scanner sc;

	/**
	*	Variabel untuk menentukan mode game,
	*	apakah solo atau versus
	*/
	private boolean solo;

	/**
	*	Validator untuk mengecek input user
	*/
	private Validator validator;

	/**
	*	Konstruktor Game yang akan dipanggil dari
	*	method lain, menerima input awal, lalu
	*	menjalankan loop hingga end game state terjadi
	*/
	public Game(){
		sc = new Scanner(System.in);
		validator = new Validator(sc);

		Interface.welcome();

		readInput();

		int round = 1;

		while(true) {
			stageLoop(round++);
		}
	}

	/**
	*	Method yang mengimplementasikan penerimaan
	*	input di awal game untuk menentukan mode
	*	dan menginisialisasi pemain
	*/
	private void readInput(){
		String name, mode;
		int x;

		do {
			try{
				System.out.print("[Solo] / [PvP]? ");
				mode = validator.getString("Solo", "PvP");
				
				solo = mode.equalsIgnoreCase("Solo");

				System.out.print("Masukkan nama Player 1 > ");
				name = sc.nextLine();

				if(name.length()<=0)
					name = "Player 1";

				System.out.print("Masukkan posisi x " + name + " (dalam m, -100 - 100) > ");
				x = validator.getInt(-100,100);
					
				player1 = new Player(name, new Point(x,0));

				if(solo){
					bot = new Ai();
				} else {
					System.out.print("Masukkan nama Player 2 > ");
					name = sc.nextLine();

					if(name.length()<=0)
						name = "Player 2";

					System.out.print("Masukkan posisi x " + name + " (dalam m, -100 - 100) > ");
					x = validator.getInt(-100,100);

					player2 = new Player(name, new Point(x,0));
				}

				break;

			} catch(Exception e){
				Interface.error(e);
			}		
		} while(true);
	}

	/**
	*	Method yang dijalankan untuk setiap round,
	*	merandom Wind, dan menjalankan turn pemain.
	*/
	private void stageLoop(int round){	

		System.out.println();
		System.out.println("==========================");
		System.out.println("   Round #" + round);
		System.out.println("==========================");
		System.out.println();
		Interface.delay(500);

		// ---------------------------------------------------------

		Wind roundWind = new Wind();	

		try {
			if(solo){
				turn(player1, bot, roundWind);
				turn(bot, player1, roundWind);
			} else {
				turn(player1, player2, roundWind);
				turn(player2, player1, roundWind);
			}
		} catch(Exception e){
			Interface.error(e);
		}		

	}

	/**
	*	Method untuk menjalankan logic turn untuk player ke enemy
	*	jika mode bermain versus.
	*	@param player Pemain yang mendapat turn
	*	@param enemy Musuh dari player
	*	@param roundWind angin yang berlangsung pada ronde ini
	*/
	private void turn(Player player, Player enemy, Wind roundWind){
		String command;

		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println("Giliran " + player.getName());
		System.out.println("Posisi " + player.getName() + ": " + player.getPos());
		System.out.println("Posisi " + enemy.getName() + ": " + enemy.getPos());
		System.out.printf ("Kecepatan angin dalam m/s: (%5.2f, %5.2f)\n", roundWind.getDx(), roundWind.getDy());
		System.out.println("-----------------------------------------");
		System.out.println();
		Interface.delay(500);

		do {
			try {
				System.out.print("[Move] / [Shoot]? ");
				command = validator.getString("move", "shoot");
				break;
			} catch(Exception e){
				Interface.error(e);
			}
		} while(true);

		if(command.equalsIgnoreCase("move"))
			turnMove(player);
		else
			turnShoot(player, enemy, roundWind);
	}

	/**
	*	Method overload dari turn(...) ketika bot mendapat turn pada mode solo
	*/
	private void turn(Ai bot, Player enemy, Wind roundWind){
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println("Giliran " + bot.getName());
		System.out.println("Posisi " + bot.getName() + ": " + bot.getPos());
		System.out.println("Posisi " + enemy.getName() + ": " + enemy.getPos());
		System.out.printf ("Kecepatan angin dalam m/s: (%5.2f, %5.2f)\n", roundWind.getDx(), roundWind.getDy());
		System.out.println("-----------------------------------------");
		System.out.println();
		Interface.delay(250);

		bot.turn(enemy, roundWind);
		Interface.delay(2000);
	}

	/**
	*	Method yang menanyakan input dan menjalankan player.move
	*	ketika pemain memilih untuk melakukan gerakan *move* di turn-nya
	*	@param player Pemain yang ingin *move*
	*/
	private void turnMove(Player player){
		int dx;

		do {
			try {
				System.out.println("Masukkan perpindahan dalam x integer,");
				System.out.println("Negatif ke kiri, positif ke kanan");
				System.out.print("(meter, [-" + MAX_MOVE + ", " + MAX_MOVE + "]) : ");

				dx = validator.getInt(-MAX_MOVE,MAX_MOVE);

				break;

			} catch(Exception e){
				Interface.error(e);
			}
		} while(true);

		player.move(dx);
	}

	/**
	*	Method yang menanyakan input dan menjalankan player.shooot()
	*	jika pemain yang mendapat turn memilih untuk melakukan gerakan *shoot*
	*	@param player Pemain yang ingin menembak
	*	@param enemy Lawan yang ingin ditembak
	*	@param roundWind Angin yang sedang berlangsung di ronde tersebut
	*/
	private void turnShoot(Player player, Player enemy, Wind roundWind){
		double angle,power;	
		do {
			try {
				System.out.print("Masukkan sudut elevasi (derajat, [0,180]) : ");
				angle = validator.getDouble(0,180);

				System.out.print("Masukkan kecepatan awal (m/s, [0," + MAX_POWER + "]) : ");
				power = validator.getDouble(0,MAX_POWER);

				break;

			} catch(Exception e) {
				Interface.error(e);
			}
		} while(true);

		if(player.getPos().getX() > enemy.getPos().getX() ){
			angle = 180 - angle;
		}

		player.shoot(enemy, power, angle, roundWind);
	}
}
