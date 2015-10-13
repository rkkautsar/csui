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
	*	Cannon yang dimiliki oleh pemain, default constructor
	*/
	protected Cannon cannon;

	/**
	*	Konstruktor default dari kelas Player,
	*	sama dengan Player("Player",new Point())
	*/
	public Player(){
		name = "Player";
		health = 100;
		pos = new Point();
		cannon = new Cannon();
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
		cannon = new Cannon();
	}

	/**
	*	Method yang dijalankan ketika ada peluru yang mengenai
	*	pemain dan mengurangi damage dari health Player, kemudian
	*	mengecek apakah health Player tersebut habis.
	*	@param damage Damage yang terjadi pada Player 
	*	@return Apakah nyawa Player telah habis
	*/
	public boolean hit(int damage){
		health -= damage;
		return health <= 0;
	}

	/**
	*	Method wrapper untuk menjalani method shoot pada objek Cannon 
	*	yang dimiliki oleh Player.
	*	@param other Player lain yang ingin ditembak
	*	@param v Kecepatan awal yang dikenakan pada peluru dalam m/s
	*	@param angle Sudut elevasi peluru, dalam derajat
	*	@param wind Kecepatan angin yang sedang terjadi pada stage
	*/
	public void shoot(Player other, double v, double angle, Wind wind){
		cannon.shoot(this, other, v, angle, wind);
	}


	/**
	*	Method untuk mensimulasikan gerakan Player pada sumbu x
	*	@param dx Besarnya gerakan Player pada sumbu x dalam meter
	*/
	public void move(int dx){
		pos.translate(dx, 0);
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
