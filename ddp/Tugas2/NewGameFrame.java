import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
class NewGameFrame extends JFrame implements ActionListener{

	private final int FRAME_W = 250;
	private final int FRAME_H = 200;

	private GameGUI gui;

	public NewGameFrame(){
		super("Cannon War v0.2");
		setSize(FRAME_W, FRAME_H);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initUI();
	}

	private void initUI(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,1));

		JButton btnNewGame 	= new JButton("New Game");
		JButton btnVersus	= new JButton("Versus");
		JButton btnExit		= new JButton("Exit");

		btnNewGame.addActionListener(this);
		btnVersus.addActionListener(this);
		btnExit.addActionListener(this);

		panel.add(btnNewGame);
		panel.add(btnVersus);
		panel.add(btnExit);

		add(panel);

		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		String command = e.getActionCommand();
		
		if(command.equals("New Game")){
			setVisible(false);
			gui = new GameGUI(); 
			gui.setVisible(true);
			Game game = new Game(GameType.SOLO, gui);

			while(!game.gameOver());

			setVisible(true);
		} else if(command.equals("Versus")){
			setVisible(false);
			gui = new GameGUI();
			gui.setVisible(true);
			Game game = new Game(GameType.PVP, gui);

			while(!game.gameOver());

			setVisible(true);
		} else if(command.equals("Exit")){
			dispose();
			gui.dispose();
			System.exit(0);
		}
	}
}
