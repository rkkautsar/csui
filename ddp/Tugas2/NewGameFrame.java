import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
*	Kelas dari Frame yang akan ditampilkan pada awal sebelum
*	game dimulai, berisi pilihan jenis game dan tombol exit.
*	@author Rakha Kanz Kautsar
*	@version 10-11-2015
*/
class NewGameFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1506688784L;

	/**
	*	Lebar frame
	*/
	private final int FRAME_W = 200;
	/**
	*	Tinggi frame
	*/
	private final int FRAME_H = 150;

	/**
	*	GUI yang akan dibuat dan dipass ke obyek Game
	*/
	private GameGUI gui;
	/**
	*	Obyek Game yang akan dimainkan
	*/
	private Game game;

	/**
	*	Konstruktor yang akan memanggil superclass constructor dengan parameter
	*	"Cannon War v0.2" kemudian mengatur ukuran dan pengaturan lainnya
	*/
	public NewGameFrame(){
		super("Cannon War v0.2");
		setSize(FRAME_W, FRAME_H);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initUI();
	}

	/**
	*	Method helper untuk memberikan beberapa button pada frame
	*/
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

	/**
	*	Aksi yang akan dilakukan ketika button ditekan,
	*	mengimplementasikan ActionListener
	*	@param e ActionEvent yang ter-trigger
	*/
	@Override
	public void actionPerformed(ActionEvent e){
		String command = e.getActionCommand();
		
		if(command.equals("New Game")){
			setVisible(false);
			gui = new GameGUI(); 
			gui.setVisible(true);
			game = new Game(GameType.SOLO, gui);

			while(!game.gameOver());

			setVisible(true);
		} else if(command.equals("Versus")){
			setVisible(false);
			gui = new GameGUI();
			gui.setVisible(true);
			game = new Game(GameType.PVP, gui);

			while(!game.gameOver());

			setVisible(true);
		} else if(command.equals("Exit")){
			dispose();
			gui.dispose();
			System.exit(0);
		}
	}
}
