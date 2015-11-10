class Game {
	private GameType type;
	private GameGUI gui;

	public static final int ARENA_X_BOUNDARY = 100;
	public static final int ARENA_Y_BOUNDARY = 100;

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
	public static final int MAX_MOVE  = 10;

	/**
	*	Player yang bermain
	*/
	private Player player1, player2;

	/**
	*	Bot yang menjadi lawan Player
	*/
	private Ai bot;

	private Wind wind;

	private Ball ball;


	public Game(GameType type, GameGUI gui){
		this.type = type;
		this.gui = gui;

		String name;
		int pos;

		name = gui.ask("Player 1", "What is your name?", "Player 1");
		pos = gui.askInt(name, "Where will you stand at the start? (0.." + ARENA_X_BOUNDARY + ")", 0, ARENA_X_BOUNDARY);

		player1 = new Player(name, pos);

		gui.setPlayer1(player1);

		if(type == GameType.SOLO){
			bot = new Ai();
			gui.setPlayer2(bot);
		} else {
			name = gui.ask("Player 2", "What is your name?", "Player 1");
			pos = gui.askInt(name, "Where will you stand at the start? (0.." + ARENA_X_BOUNDARY + ")", 0, ARENA_X_BOUNDARY);

			player2 = new Player(name, pos);
			gui.setPlayer2(player2);
		}

		gui.update();

		int round = 0;
		while(!gameOver()){

			if(round%2==0)
				wind = new Wind();
			
			stageLoop(round++ , wind);
		}

		gui.info("Game Over!");
	}

	private void stageLoop(int round, Wind wind){
		double angle, speed;
		int displacement;

		gui.info("Current wind is " + wind);
		Object[] turnAction = {"Move", "Shoot"};
		String action;

		if(round%2 == 0){
			// Player 1 turn
			action = gui.select(player1.getName(), "Your turn.", turnAction);

			if(action.equals("Move")){
				displacement = gui.askInt(player1.getName(), 
					"Insert your displacement. (-" + MAX_MOVE + ".." + MAX_MOVE + ")", -MAX_MOVE, MAX_MOVE);
				player1.move(displacement);
				gui.update();
			} else {
				if(type == GameType.PVP)
					turnShoot(player1, player2, wind);
				else
					turnShoot(player1, bot, wind);
			}
		} else if(type == GameType.PVP){
			// Player 2 turn
			action = gui.select(player2.getName(), "Your turn.", turnAction);

			if(action.equals("Move")){
				displacement = gui.askInt(player1.getName(), 
					"Insert your displacement. (-" + MAX_MOVE + ".." + MAX_MOVE + ")", -MAX_MOVE, MAX_MOVE);
				player2.move(displacement);
				gui.update();
			} else {
				turnShoot(player2, player1, wind);
			}
		} else {
			// Bot turn
			action = bot.randomAction();
			gui.info(bot.getName(), "I choose to " + action);

			if(action.equalsIgnoreCase("Move")){
				// Bot moves
				displacement = bot.randomMove();
				gui.info("I moved by " + displacement);
				gui.update();
			} else {
				// Bot shoots
				speed = bot.getSpeed(player1, wind);
				angle = Ai.AI_DEFAULT_ANGLE;

				if(bot.getPos().getX() > player1.getPos().getX())
					angle = 180 - angle;

				ball = new Ball(bot.getPos(), speed, angle, wind);

				gui.setBall(ball);

				hitCheck(ball, player1);
				hitCheck(ball, bot);
			}

		}

		if(type == GameType.SOLO)
			endGameCheck(player1, bot);
		else
			endGameCheck(player1, player2);
	}

	public void turnShoot(Player shooter, Player target, Wind wind){
		double angle = gui.askDouble(player1.getName(), "Insert the angle. (0..180)", 0,180);
		double speed = gui.askDouble(player1.getName(), "Insert the speed. (0..100)", 0,100);

		if(shooter.getPos().getX() > target.getPos().getX())
			angle = 180 - angle;

		ball = new Ball(player1.getPos(), speed, angle, wind);
		
		gui.setBall(ball);
		
		hitCheck(ball, target);
		hitCheck(ball, shooter);
	}

	public void hitCheck(Ball ball, Player player){
		if(ball.hit(player))
			gui.info("The projectile hits " + player.getName() + 
				". " + player.getName() + " has " + player.getHealth() + " HP remaining.");
	}

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

	public boolean gameOver(){
		if(type == GameType.SOLO){
			return player1.dead() || bot.dead();
		} else {
			return player1.dead() || player2.dead();
		}
	}	
}
