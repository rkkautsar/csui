import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.event.WindowEvent;

class Game {
	private GameType type;
	private GameGUI gui;

	public static final int ARENA_X_BOUNDARY = 750;
	public static final int ARENA_Y_BOUNDARY = 150;

	public static final int DEFAULT_ANGLE = 45;
	public static final int DEFAULT_POWER = 50;
	public static final int DEFAULT_MOVE  = 5;

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
	public static final int MAX_MOVE  = 20;

	/**
	*	Player yang bermain
	*/
	private Player player1, player2;

	/**
	*	Bot yang menjadi lawan Player
	*/
	private Ai bot;

	/**
	*	Obyek angin yang akan dirandom pada setiap ronde
	*/
	private Wind wind;

	/**
	*	Obyek bola yang akan dilempar ketika ada yang menembak
	*/
	private Ball ball;

	/**
	 * Timer untuk terus mengecek end condition
	 */
	private Timer endTimer;

	/**
	 * Panel untuk menerima input
	 */
	private InputPanel inputPanel;

	/**
	 * Berapa ronde telah berjalan
	 */
	private int round;


	/**
	*	Konstruktor kelas Game yang berisi sebagian besar flow dari game
	*	@param type Tipe game yang akan dimainkan
	*	@param gui GUI yang akan dijadikan interface permainan
	*/
	public Game(GameType type){
		// TODO add variable arena boundary

		this.type = type;
		
		gui        = new GameGUI();
		inputPanel = new InputPanel(this);

		GridBagConstraints cons = new GridBagConstraints();
		cons.anchor = GridBagConstraints.PAGE_START;
		cons.fill   = GridBagConstraints.VERTICAL;
		cons.gridx  = 0;
		gui.add(inputPanel, cons);
		gui.addOthers();

		gui.setVisible(true);

		String name;
		int pos;

		name = gui.ask("Player 1", "What is your name?", "Blue");
		pos  = gui.askInt(name, "Where will you stand at the start? (0.." + ARENA_X_BOUNDARY + ")", 0, ARENA_X_BOUNDARY);

		player1 = new Player(name, pos);

		gui.setPlayer1(player1);

		if(type == GameType.SOLO){
			bot = new Ai();
			gui.setPlayer2(bot);
		} else {
			name = gui.ask("Player 2", "What is your name?", "Red");
			pos  = gui.askInt(name, "Where will you stand at the start? (0.." + ARENA_X_BOUNDARY + ")", 0, ARENA_X_BOUNDARY);

			player2 = new Player(name, pos);
			gui.setPlayer2(player2);
		}


		gui.update();

		/**
		 * Timer untuk mengecek end condition dengan delay 500ms
		 */
		endTimer = new Timer(500, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(gameOver()){
					endTimer.stop();
					try {
						endGameCheck(player1, player2);
					} catch (Exception ignore){
						endGameCheck(player1, bot);
					} finally {
						end();
						gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
						NewGameFrame ng = new NewGameFrame();
						ng.setVisible(true);
					}
				}
			}
		});

		endTimer.start();

		// Initialization
		round = 0;
		inputPanel.setTurn(player1);
		wind = new Wind();
		gui.showWind(wind);
		gui.update();
	}

	/**
	 * Melakukan perpindahan terhadap pemain di ronde ini
	 * @param displacement besar perpindahan
	 */
	public void turnMove(int displacement){
		if(round % 2 == 0){
			player1.move(displacement);
		} else if(type == GameType.PVP){
			player2.move(displacement);
		} else {
			// Bot
			// Seharusnya gak kesini.
		}

		gui.update();
		++round;

		if(round % 2 == 0){
			wind = new Wind();
			gui.showWind(wind);
			inputPanel.setTurn(player1);
		}

		if(round % 2 == 1 && type == GameType.SOLO){
			inputPanel.setTurn(bot);
			botTurn();
		}
		else if(round % 2 == 1 && type == GameType.PVP)
			inputPanel.setTurn(player2);
	}

	/**
	 * Melakukan penembakan pada pemain yang mendapat turn
	 * @param angle sudut dalam derajat ke arah lawan
	 * @param power kecepatan (m/s)
	 */
	public void turnShoot(double angle, double power){
		

		if(round % 2 == 0){
			
			if(type == GameType.PVP){
				if(player1.getPos().getX() > player2.getPos().getX())
					angle = 180 - angle;
			} else {
				if(player1.getPos().getX() > bot.getPos().getX())
					angle = 180 - angle;
			}

			ball = new Ball(player1.getPos(), power, angle, wind);
			gui.addBall(ball);
		} else if(type == GameType.PVP) {
			if(player2.getPos().getX() > player1.getPos().getX())
				angle = 180 - angle;

			ball = new Ball(player2.getPos(), power, angle, wind);
			gui.addBall(ball);
		} else {
			// Bot
			// Seharusnya gak kesini.
		}

		gui.update();
		++round;

		if(round % 2 == 0){
			wind = new Wind();
			gui.showWind(wind);
			inputPanel.setTurn(player1);
		}

		if(round % 2 == 1 && type == GameType.SOLO){
			inputPanel.setTurn(bot);
			botTurn();
		} else if(round % 2 == 1 && type == GameType.PVP){
			inputPanel.setTurn(player2);
		}
	}

	/**
	 * Rutin ketika bot mendapat turn
	 */
	public void botTurn(){
		String action = bot.randomAction();
		
		if(action.equalsIgnoreCase("move")){
			bot.move(bot.randomMove());
		} else {
			double speed = bot.getSpeed(player1, wind);
			double angle = Ai.AI_DEFAULT_ANGLE;
			
			if(bot.getPos().getX() > player1.getPos().getX())
					angle = 180 - angle;

			ball = new Ball(bot.getPos(), speed, angle, wind);

			gui.addBall(ball);
		}	

		gui.update();
		++round;

		if(round % 2 == 0){
			wind = new Wind();
			gui.showWind(wind);
			inputPanel.setTurn(player1);
		}

	}

	/**
	*	Rutin yang mengecek apakah game telah selesai
	*	dan akan menghapus frame jika iya
	*	@param a Pemain 1
	*	@Param b Lawan Pemain 1
	*/
	public void endGameCheck(Player a, Player b){
		if(a.dead() && b.dead()){
			gui.info("Ties!");
			gui.dispose();
		}
		else if(b.dead()){
			gui.info(a.getName() + " wins!");
			gui.dispose();
		}
		else if(a.dead()){
			gui.info(b.getName() + " wins!");
			gui.dispose();
		}
	}

	/**
	*	Rutin yang akan mengembalikan apakah game telah selesai,
	*	yaitu ketika salah satu hp pemain telah habis
	*	@return apakah game telah selesai
	*/
	public boolean gameOver(){
		if(type == GameType.SOLO){
			return player1.dead() || bot.dead();
		} else {
			return player1.dead() || player2.dead();
		}
	}	

	/**
	 * Game telah berakhir.
	 */
	public void end(){
		gui.info("Game Over!");
	}
}
