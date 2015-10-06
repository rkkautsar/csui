class Player
{
	private String name;
	private int health;
	private Point pos;
	private Cannon cannon;

	public Player(){
		name = "Player";
		health = 100;
		pos = new Point();
		cannon = new Cannon();
	}

	public Player(String name, Point pos){
		this.name = name;
		this.pos  = pos;
		health = 100;
		cannon = new Cannon(pos);
	}

	public boolean hit(int damage){
		health -= damage;
		return health <= 0;
	}

	public void shoot(Player other, double v, double angle, Wind wind){
		cannon.shoot(this, other, v, angle, wind);
	}

	// accessor
	public String getName(){
		return name;
	}

	public int getHealth(){
		return health;
	}

	public Point getPos(){
		return pos;
	}
}
