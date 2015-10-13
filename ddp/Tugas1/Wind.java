import java.util.Random;

/**
*	Kelas yang mengimplementasikan sebuah representasi
*	angin sederhana sebagai vektor kecepatan yang memiliki
*	nilai random yang unik untuk setiap Wind baru
*	@author Rakha Kanz Kautsar
*/
class Wind extends Vector2d{
	/**
	*	Random Number Generator untuk membangkitkan nilai random
	*/
	private Random generator;

	/**
	*	Kecepatan absolut maksimum dari angin
	*/
	public static final double MAX_WIND_SPEED = 2;

	/**
	*	Konstruktor class Wind, menjadikan dx dan dy pada vektor
	*	menjadi nilai random dalam range (-MAX_WIND_SPEED,MAX_WIND_SPEED)
	*/
	public Wind(){
		super();
		generator = new Random(System.currentTimeMillis());
		dx = generator.nextDouble() * 2 * MAX_WIND_SPEED - MAX_WIND_SPEED;
		dy = generator.nextDouble() * 2 * MAX_WIND_SPEED - MAX_WIND_SPEED;
	}
}
