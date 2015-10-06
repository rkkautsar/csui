import java.util.Random;

class Wind extends Vector2d{
	Random generator;

	public static final double MAX_WIND_SPEED = 2;

	public Wind(){
		super();
		generator = new Random(System.currentTimeMillis());
		dx = generator.nextDouble() * 2 * MAX_WIND_SPEED - MAX_WIND_SPEED;
		dy = generator.nextDouble() * 2 * MAX_WIND_SPEED - MAX_WIND_SPEED;
	}
}
