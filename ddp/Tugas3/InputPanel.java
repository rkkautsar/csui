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

class InputPanel extends JPanel {
	private static final long serialVersionUID = 1506688784L;

	public static final int PANEL_W = 200;

	private JPanel cards;
	private JPanel paneRadio;
	private ShootPanel paneShoot;
	private MovePanel paneMove;
	private JLabel lblTurn;
	private JRadioButton radShoot;
	private JRadioButton radMove;
	private ButtonGroup btnGroup;

	private Game game;
	
	public InputPanel(Game game){
		this.game = game;
		
		setPreferredSize(new Dimension(PANEL_W,GameGUI.FRAME_H - 50));
		setBorder(new EmptyBorder(10,10,10,10));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		lblTurn   = new JLabel(" - ");
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

		radShoot.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				CardLayout cl = (CardLayout)(cards.getLayout());
				cl.show(cards, e.getActionCommand());
			}
		});

		radMove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				CardLayout cl = (CardLayout)(cards.getLayout());
				cl.show(cards, e.getActionCommand());
			}
		});
	}

	public void disableAll(){
		paneShoot.disableButton();
		paneMove.disableButton();
	}

	public void enableAll(){
		paneShoot.enableButton();
		paneMove.enableButton();
	}

	public void setTurn(Player p){
		lblTurn.setText(p.getName() + "'s turn.");
	}


	class ShootPanel extends JPanel {
		private static final long serialVersionUID = 1506688784L;
		private JLabel lblPower;
		private JLabel lblAngle;
		private JSlider sldAngle;
		private JSlider sldPower;
		private JButton btnShoot;

		public ShootPanel(){
			setPreferredSize(new Dimension(PANEL_W,GameGUI.FRAME_H - 50));
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

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

			sldAngle.addChangeListener(new ChangeListener(){
				public void stateChanged(ChangeEvent e){
					lblAngle.setText("Angle : " + sldAngle.getValue());
				}
			});

			sldPower.addChangeListener(new ChangeListener(){
				public void stateChanged(ChangeEvent e){
					lblPower.setText("Power : " + sldPower.getValue());
				}
			});

			btnShoot.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					game.turnShoot(sldAngle.getValue(), sldPower.getValue());
				}
			});
		}

		protected void disableButton(){
			btnShoot.setEnabled(false);
		}

		protected void enableButton(){
			btnShoot.setEnabled(true);
		}
	}

	class MovePanel extends JPanel {
		private static final long serialVersionUID = 1506688784L;
		private JLabel lblMove;
		private JSlider sldMove;
		private JButton btnMove;
		public MovePanel(){
			setPreferredSize(new Dimension(PANEL_W,GameGUI.FRAME_H - 50));
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

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
			
			sldMove.addChangeListener(new ChangeListener(){
				public void stateChanged(ChangeEvent e){
					lblMove.setText("Move by : " + sldMove.getValue());
				}
			});

			btnMove.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					game.turnMove(sldMove.getValue());
				}
			});
		}

		protected void disableButton(){
			btnMove.setEnabled(false);
		}

		protected void enableButton(){
			btnMove.setEnabled(true);
		}
	}
}