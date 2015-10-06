import java.util.Random;

class Wind extends Vector2d{
	Random generator;

	public static final double MAX_WIND_SPEED = 2;

	public Wind(int round){
		super();
		generator = new Random(round);
		dx = generator.nextDouble() * 2 * MAX_WIND_SPEED - MAX_WIND_SPEED;
		dy = generator.nextDouble() * 2 * MAX_WIND_SPEED - MAX_WIND_SPEED;
	}
}
