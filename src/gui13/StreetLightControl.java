package gui13;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class StreetLightControl extends JFrame implements Runnable{

	private static final long serialVersionUID = 2716104666839809823L;
	private JPanel contentPane;
	private JStreetLightPanel pnStreetLight;
	
	private JRadioButton rdbtnRed;
	private JRadioButton rdbtnRY;
	private JRadioButton rdbtnGreen;
	private JRadioButton rdbtnYellow;
	private JRadioButton rdbtnOff;
	private JCheckBox chckbxAutomatic;
	
	private volatile Thread t = null;
	
	private int phaseRed = 3000;
	private int phaseRY = 500;
	private int phaseGreen = 3000;
	private int phaseYellow = 500;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(
							UIManager.getSystemLookAndFeelClassName());
					StreetLightControl frame = new StreetLightControl();
					frame.setLocationRelativeTo(null);
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
		setBounds(100, 100, 255, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout(
				"", "[73.00,grow][grow]", "[grow]"));
		
		pnStreetLight = new JStreetLightPanel();
		contentPane.add(pnStreetLight, "cell 0 0, grow");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 1 0,grow");
		panel.setLayout(new MigLayout("", "[125.00,grow]", "[grow][]"));
		
		JPanel pnControls = new JPanel();
		pnControls.setBorder(new TitledBorder(null, "manual control",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(pnControls, "cell 0 0,grow");
		pnControls.setLayout(new MigLayout("", "[]", "[][][][][][]"));
		
		rdbtnRed = new JRadioButton("red");
		rdbtnRed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnStreetLight.setPhase(1);
			}
		});
		pnControls.add(rdbtnRed, "cell 0 0");
		
		rdbtnRY = new JRadioButton("red/yellow");
		rdbtnRY.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnStreetLight.setPhase(2);
			}
		});
		pnControls.add(rdbtnRY, "cell 0 1");
		
		rdbtnGreen = new JRadioButton("green");
		rdbtnGreen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnStreetLight.setPhase(3);
			}
		});
		pnControls.add(rdbtnGreen, "cell 0 2");
		
		rdbtnYellow = new JRadioButton("yellow");
		rdbtnYellow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnStreetLight.setPhase(4);
			}
		});
		pnControls.add(rdbtnYellow, "cell 0 3");
		
		rdbtnOff = new JRadioButton("off");
		rdbtnOff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnStreetLight.setPhase(0);
			}
		});
		pnControls.add(rdbtnOff, "cell 0 4");
		
		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(rdbtnRed);
		bGroup.add(rdbtnRY);
		bGroup.add(rdbtnYellow);
		bGroup.add(rdbtnGreen);
		bGroup.add(rdbtnOff);
		
		chckbxAutomatic = new JCheckBox("auto");
		chckbxAutomatic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchAuto(e);
			}
		});
		pnControls.add(chckbxAutomatic, "cell 0 5");
	}
	
	private void setButtonEnabledState(boolean state) {
		rdbtnRed.setEnabled(state);
		rdbtnRY.setEnabled(state);
		rdbtnGreen.setEnabled(state);
		rdbtnYellow.setEnabled(state);
		rdbtnOff.setEnabled(state);
	}
	
	public void switchAuto(ActionEvent e) {
		boolean autoSelected = chckbxAutomatic.isSelected();
		if (autoSelected && t == null) {
			setButtonEnabledState(false);
			t = new Thread(this, "automatic");
			t.start();
		} else {
			t = null;
			rdbtnOff.setSelected(true);
			setButtonEnabledState(true);
			pnStreetLight.setPhase(0);
		}
	}
	@Override
	public void run() {
		while (chckbxAutomatic.isSelected()) {
			try {
				chckbxAutomatic.repaint();
				pnStreetLight.setPhase(1);
				Thread.sleep(phaseRed);
				pnStreetLight.setPhase(2);
				Thread.sleep(phaseRY);
				pnStreetLight.setPhase(3);
				Thread.sleep(phaseGreen);
				pnStreetLight.setPhase(4);
				Thread.sleep(phaseYellow);
			} catch (Exception e2) {
			}
		}
	}

}
