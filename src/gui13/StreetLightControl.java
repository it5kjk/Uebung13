package gui13;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

public class StreetLightControl extends JFrame {

	private static final long serialVersionUID = 2716104666839809823L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					StreetLightControl frame = new StreetLightControl();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StreetLightControl() {
		setTitle("StreetLight control");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 268, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[73.00,grow][grow]", "[grow]"));
		
		JStreetLightPanel pnStreetLight = new JStreetLightPanel();
		contentPane.add(pnStreetLight, "cell 0 0, grow");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 1 0,grow");
		panel.setLayout(new MigLayout("", "[125.00,grow]", "[grow][]"));
		
		JPanel pnControls = new JPanel();
		pnControls.setBorder(new TitledBorder(null, "manual control",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(pnControls, "cell 0 0,grow");
		pnControls.setLayout(new MigLayout("", "[]", "[][][][][]"));
		
		JRadioButton rdbtnRed = new JRadioButton("red");
		pnControls.add(rdbtnRed, "cell 0 0");
		
		JRadioButton rdbtnRY = new JRadioButton("red/yellow");
		pnControls.add(rdbtnRY, "cell 0 1");
		
		JRadioButton rdbtnGreen = new JRadioButton("green");
		pnControls.add(rdbtnGreen, "cell 0 2");
		
		JRadioButton rdbtnYellow = new JRadioButton("yellow");
		pnControls.add(rdbtnYellow, "cell 0 3");
		
		JRadioButton rdbtnOff = new JRadioButton("off");
		pnControls.add(rdbtnOff, "cell 0 4");
		
		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(rdbtnRed);
		bGroup.add(rdbtnRY);
		bGroup.add(rdbtnYellow);
		bGroup.add(rdbtnGreen);
		bGroup.add(rdbtnOff);
	}

}
