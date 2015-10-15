import java.util.Random;

/**
*	Kelas yang mengimplementasikan sebuah representasi
*	angin sederhana sebagai vektor percepatan yang memiliki
*	nilai random yang unik untuk setiap Wind baru
*	@author Rakha Kanz Kautsar
*	@version 06.10.2015
*/
class Wind extends Vector2d
{
	/**
	*	Random Number Generator untuk membangkitkan nilai random
	*/
	private Random generator;

	/**
	*	Percepatan absolut maksimum dari angin
	*/
	public static final double MAX_WIND_ACCEL = 1;

	/**
	*	Konstruktor class Wind, menjadikan dx dan dy pada vektor
	*	menjadi nilai random dalam range (-MAX_WIND_ACCEL,MAX_WIND_ACCEL)
	*/
	public Wind(){
		super();
		generator = new Random(System.currentTimeMillis());
		dx = generator.nextDouble() * 2 * MAX_WIND_ACCEL - MAX_WIND_ACCEL;
		dy = generator.nextDouble() * 2 * MAX_WIND_ACCEL - MAX_WIND_ACCEL;
	}
}
