import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * Panel yang berisi komponen-komponen untuk input dari user
 * @author  Rakha Kanz Kautsar
 * @version 0.1-alpha
 */

class InputPanel extends JPanel {
	private static final long serialVersionUID = 1506688784L;

	/**
	 * Lebar total panel
	 */
	public static final int PANEL_W = 200;

	/**
	 * Panel untuk menyimpan "kartu-kartu" dari CardLayout
	 */
	private JPanel cards;
	/**
	 * Panel untuk menyimpan 
	 */
	private JPanel paneRadio;
	/**
	 * ShootPanel sebagai masukan jika ingin menembak
	 */
	private ShootPanel paneShoot;
	/**
	 * MovePanel sebagai masukan jika ingin berpindah tempat
	 */
	private MovePanel paneMove;
	/**
	 * Pilihan untuk menembak
	 */
	private JRadioButton radShoot;
	/**
	 * Pilihan untuk berpindah tempat
	 */
	private JRadioButton radMove;
	/**
	 * ButtonGroup untuk mengelompokkan JRadioButton
	 */
	private ButtonGroup btnGroup;
	/**
	 * Objek Game untuk mengarahkan input ke game
	 */
	private Game game;
	

	/**
	 * Konstruktor default
	 * @param  game game yang dimainkan
	 */
	public InputPanel(Game game){
		this.game = game;
		
		setPreferredSize(new Dimension(PANEL_W,GameGUI.FRAME_H - 50));
		setBorder(new EmptyBorder(10,10,10,10));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		paneShoot = new ShootPanel();
		paneMove  = new MovePanel();

		cards = new JPanel();
		cards.setLayout(new CardLayout());
		cards.add(paneShoot, "Shoot");
		cards.add(paneMove, "Move");

		paneRadio = new JPanel();
		radShoot  = new JRadioButton("Shoot");
		radMove   = new JRadioButton("Move");
		btnGroup  = new ButtonGroup();

		radShoot.setMnemonic(KeyEvent.VK_S);
		radMove.setMnemonic(KeyEvent.VK_M);

		radShoot.setSelected(true);
		btnGroup.add(radShoot);
		btnGroup.add(radMove);
		paneRadio.add(radShoot);
		paneRadio.add(radMove);

		add(paneRadio, BorderLayout.PAGE_START);
		add(cards, BorderLayout.CENTER);

		/**
		 * ActionListener untuk memindah "kartu" menjadi shoot
		 */
		radShoot.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				CardLayout cl = (CardLayout)(cards.getLayout());
				cl.show(cards, e.getActionCommand());
			}
		});

		/**
		 * ActionListener untuk memindah "kartu" menjadi move
		 */
		radMove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				CardLayout cl = (CardLayout)(cards.getLayout());
				cl.show(cards, e.getActionCommand());
			}
		});
	}

	/**
	 * Method untuk mengubah label turn pada panel
	 * @param p player yang mendapat turn
	 */
	public void setTurn(Player p){
		paneShoot.setTurn(p.getName() + "'s turn.");
		paneMove.setTurn(p.getName() + "'s turn.");
	}

	/**
	 * Inner class untuk menerima masukan untuk menembak
	 */
	class ShootPanel extends JPanel {
		private static final long serialVersionUID = 1506688784L;
		/**
		 * Label untuk slider power
		 */
		private JLabel lblPower;
		/**
		 * Label untuk slider angle
		 */
		private JLabel lblAngle;
		/**
		 * Label untuk turn
		 */
		private JLabel lblTurn;

		/**
		 * Slider untuk angle
		 */
		private JSlider sldAngle;
		/**
		 * Slider untuk power
		 */
		private JSlider sldPower;

		/**
		 * Button untuk memproses input
		 */
		private JButton btnShoot;

		public ShootPanel(){
			setPreferredSize(new Dimension(PANEL_W,GameGUI.FRAME_H - 50));
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

			lblTurn = new JLabel("-");
			add(lblTurn);

			lblAngle = new JLabel("Angle : " + Game.DEFAULT_ANGLE);
			sldAngle = new JSlider(0, 180, Game.DEFAULT_ANGLE);
			add(lblAngle);
			add(sldAngle);

			lblPower = new JLabel("Power : " + Game.DEFAULT_POWER);
			sldPower = new JSlider(0, 100, Game.DEFAULT_POWER);
			add(lblPower);
			add(sldPower);

			add(Box.createRigidArea(new Dimension(0,10)));

			btnShoot = new JButton("Shoot!");
			add(btnShoot);

			lblAngle.setAlignmentX(CENTER_ALIGNMENT);
			lblPower.setAlignmentX(CENTER_ALIGNMENT);
			btnShoot.setAlignmentX(CENTER_ALIGNMENT);

			/**
			 * ChangeListener untuk mengsinkronkan label dengan slider
			 */
			sldAngle.addChangeListener(new ChangeListener(){
				public void stateChanged(ChangeEvent e){
					lblAngle.setText("Angle : " + sldAngle.getValue());
				}
			});

			/**
			 * ChangeListener untuk mengsinkronkan label dengan slider
			 */
			sldPower.addChangeListener(new ChangeListener(){
				public void stateChanged(ChangeEvent e){
					lblPower.setText("Power : " + sldPower.getValue());
				}
			});

			/**
			 * ActionListener untuk menembak bola ketika button Shoot ditekan
			 */
			btnShoot.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					game.turnShoot(sldAngle.getValue(), sldPower.getValue());
				}
			});
		}

		/**
		 * Mutator lblTurn.
		 * @param s String untuk lblTurn
		 */
		public void setTurn(String s){
			lblTurn.setText(s);
		}
	}

	class MovePanel extends JPanel {
		private static final long serialVersionUID = 1506688784L;
		/**
		 * Label untuk perpindahan
		 */
		private JLabel lblMove;
		/**
		 * Label turn
		 */
		private JLabel lblTurn;
		/**
		 * Slider perpindahan
		 */
		private JSlider sldMove;
		/**
		 * Button untuk memproses input
		 */
		private JButton btnMove;

		public MovePanel(){
			setPreferredSize(new Dimension(PANEL_W,GameGUI.FRAME_H - 50));
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

			lblTurn = new JLabel("-");
			add(lblTurn);

			lblMove = new JLabel("Move by : " + Game.DEFAULT_MOVE);
			sldMove = new JSlider(- Game.MAX_MOVE, Game.MAX_MOVE, Game.DEFAULT_MOVE);
			add(lblMove);
			add(sldMove);

			add(Box.createRigidArea(new Dimension(0,10)));

			btnMove = new JButton("Move!");
			add(btnMove);

			lblMove.setAlignmentX(CENTER_ALIGNMENT);
			btnMove.setAlignmentX(CENTER_ALIGNMENT);
			
			/**
			 * ChangeListener untuk mengsinkronkan label dengan slider
			 */
			sldMove.addChangeListener(new ChangeListener(){
				public void stateChanged(ChangeEvent e){
					lblMove.setText("Move by : " + sldMove.getValue());
				}
			});

			/**
			 * ActionListener untuk melakukan perpindahan pada game
			 */
			btnMove.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					game.turnMove(sldMove.getValue());
				}
			});
		}

		/**
		 * Mutator lblTurn.
		 * @param s String untuk lblTurn
		 */
		public void setTurn(String s){
			lblTurn.setText(s);
		}
	}
}