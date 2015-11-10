class Ball extends ParabolicMotion{
	public static final double HIT_RADIUS = 5;
	public static final double BASE_DAMAGE = 40;

	private double time;

	public Ball(Point origin, double v, double angle, Wind wind){
		super(origin, v, angle, wind);
		time = 0;
	}

	public void next(double dt){
		time += dt;
	}

	public boolean hit(Player player){
		double radius  = Math.abs(max().getX() - player.getPos().getX());

		if(radius + Game.EPS < HIT_RADIUS)
			player.hit((int) ((1-(radius/HIT_RADIUS)) * BASE_DAMAGE));

		return radius + Game.EPS < HIT_RADIUS;
	}

	public Point getPos(){
		return at(time);
	}

	public boolean flying(){
		return getPos().getY() > - Game.EPS;
	}
}
