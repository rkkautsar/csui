/**
*	Kelas yang mengimplementasikan pemain dalam game ini
* 	@author Rakha Kanz Kautsar
*/
class Player
{
	/**
	* 	Nama pemain, default "Player"
	*/
	protected String name;
	/**
	*	Nyawa pemain, default 100
	*/
	protected int health;
	/**
	*	Posisi pemain, default (0,0)
	*/
	protected Point pos;

	/**
	*	Konstruktor default dari kelas Player,
	*	sama dengan Player("Player",new Point())
	*/
	public Player(){
		name = "Player";
		health = 100;
		pos = new Point();
	}

	/**
	*	Konstruktor dari kelas Player
	*	@param name Nama pemain
	*	@param pos Posisi pemain berupa objek Point
	*/
	public Player(String name, Point pos){
		this.name = name;
		this.pos  = pos;
		health = 100;
	}

	public Player(String name, int posX){
		this.name = name;
		this.pos = new Point(posX, 0);
		health = 100;
	}

	/**
	*	Method yang dijalankan ketika ada peluru yang mengenai
	*	pemain dan mengurangi damage dari health Player, kemudian
	*	mengecek apakah health Player tersebut habis.
	*	@param damage Damage yang terjadi pada Player 
	*/
	public void hit(int damage){
		health -= damage;
	}

	/**
	*	Method untuk mensimulasikan gerakan Player pada sumbu x
	*	@param dx Besarnya gerakan Player pada sumbu x dalam meter
	*/
	public void move(int dx){
		pos.translate(dx, 0);
		if(pos.getX() < 0)
			pos.translate(-pos.getX(), 0);

		if(pos.getY() > Game.ARENA_X_BOUNDARY)
			pos.translate(Game.ARENA_X_BOUNDARY - pos.getX(), 0);
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

	public boolean dead(){
		return health <= 0;
	}
}
