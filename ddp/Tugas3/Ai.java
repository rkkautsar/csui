import java.util.Random;

/**
*	Kelas yang mengimplentasikan sebuah AI sederhana
*	yang melakukan keputusan untuk setiap turn dan
*	melakukan perhitungan sederhana setiap kali menembak
*	@author Rakha Kanz Kautsar
*	@version 13.10.2015
*/
class Ai extends Player
{
	/**
	*	Batasan maksimum posisi AI
	*/
	public static final int AI_POS_BOUND = Game.ARENA_X_BOUNDARY;

	/**
	*	Nilai yang menunjukkan kecendrungan AI
	*	untuk melakukan gerakan *move*
	*/
	public static final double AI_MOVE_PROBABILITY = 0.25;

	/**
	*	Nilai angle default
	*	yang akan digunakan pada perhitungan penembakan
	*/
	public static final double AI_DEFAULT_ANGLE = 45;

	/**
	*	Banyaknya tahapan binary search yang dilakukan AI,
	*	variabel ini akan memengaruhi aproksimasi tembakan yang
	*	dilakukan AI
	*/
	public static final int AI_BINARY_SEARCH_STEPS = 7;

	/**
	*	Random number generator untuk membangkitkan nilai
	*	random yang unik untuk setiap pemanggilan
	*/
	private Random generator;

	/**
	*	Akses ke kelas ParabolicMotion memungkinkan AI
	*	melakukan aproksimasi tembakan yang tepat
	*/
	private ParabolicMotion motion;

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
	}

	/**
	*	Method yang meminta AI untuk memilih untuk move atau shoot
	*	@return Pilihan AI
	*/
	public String randomAction(){
		if(generator.nextDouble() < AI_MOVE_PROBABILITY)
			return "move";
		else
			return "shoot";
	}

	/**
	*	Method yang meminta AI untuk melakukan move dengan random
	*	@return Perpindahan AI
	*/
	public int randomMove(){
		return generator.nextInt(2 * Game.MAX_MOVE) - Game.MAX_MOVE;
	}

	/**
	*	Method yang meminta AI untuk menghitung kecepatan optimal
	*	untuk sudut AI_DEFAULT_ANGLE jika ingin menembak musuh,
	*	menggunakan binary search sebanyak AI_BINARY_SEARCH_STEPS
	*	@param enemy Pemain lawan
	*	@param wind Angin yang berlangsung
	*	@return kecepatan optimal
	*/
	public double getSpeed(Player enemy, Wind wind){
		double angle = AI_DEFAULT_ANGLE;
		double left = 0, right = 100, mid = 50;

		if(pos.getX() > enemy.getPos().getX())
			angle = 180 - angle;

		// binary search the velocity
		for(int i = 0; i < AI_BINARY_SEARCH_STEPS; ++i){
			mid = (left + right) / 2.0;
			motion = new ParabolicMotion(pos, mid, angle, wind);
			if(motion.max().getX() < enemy.getPos().getX()){
				if(pos.getX() < enemy.getPos().getX()){
					left = mid - Game.EPS;
				} else {
					right = mid + Game.EPS;
				}
			} else {
				if(pos.getX() < enemy.getPos().getX()){
					right = mid + Game.EPS;
				} else {
					left = mid - Game.EPS;
				}
			}
		}

		return mid;
	}

}
