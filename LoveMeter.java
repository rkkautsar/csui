import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoveMeter extends JFrame{
	private static final long serialVersionUID = 1506688784L;

	private static final int FRAME_WIDTH  = 300;
	private static final int FRAME_HEIGHT = 100;

	private JButton btnCheck;
	private JTextField txtNama1, txtNama2;
	private JLabel labelNama1, labelNama2;
	private JPanel panelMain;
	private JPanel panelInput;

	public LoveMeter(){
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Love Meter");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		createLabel();
		createTextField();
		createButton();
		createPanel();
	}

	private void createLabel(){
		labelNama1 = new JLabel("Nama Pertama");
		labelNama2 = new JLabel("Nama Kedua");
	}

	private void createTextField(){
		txtNama1 = new JTextField();
		txtNama2 = new JTextField();

		class TextFieldListener implements ActionListener {
			public void actionPerformed(ActionEvent e){
				btnCheck.doClick();
			}
		}

		txtNama1.addActionListener(new TextFieldListener());
		txtNama2.addActionListener(new TextFieldListener());
	}

	private void createButton(){
		btnCheck = new JButton("Check!");
		btnCheck.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String nama1 = txtNama1.getText();
				String nama2 = txtNama2.getText();

				int hasil = (nama1.hashCode() + nama2.hashCode()) % 100;

				JOptionPane.showMessageDialog(null, "Love Meter = " + hasil + "%");
			}
		});
	}

	private void createPanel(){
		panelInput = new JPanel();
		panelInput.setLayout(new GridLayout(2,2));
		
		panelInput.add(labelNama1);
		panelInput.add(txtNama1);

		panelInput.add(labelNama2);
		panelInput.add(txtNama2);

		panelMain = new JPanel();
		panelMain.setLayout(new GridLayout(2,1));

		panelMain.add(panelInput);
		panelMain.add(btnCheck);

		add(panelMain);
	}



	public static void main(String[] args){
		LoveMeter love = new LoveMeter();
		love.setVisible(true);
		love.setLocationRelativeTo(null);
	}

}