package salon.menu;

import javax.swing.JFrame;
import javax.swing.JTextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import salon.entity.ClientRezervation;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class Rezervation extends JFrame {
	private JTextField fNameTextField;
	private JTextField lNameTextField;
	private JTextField pNumberbTextField;
	private JFrame frame;
	@SuppressWarnings("rawtypes")
	private JComboBox hairComboBox = new JComboBox();
	@SuppressWarnings("rawtypes")
	private JComboBox eyeComboBox = new JComboBox();
	@SuppressWarnings("rawtypes")
	private JComboBox manicureComboBox = new JComboBox();
	@SuppressWarnings("rawtypes")
	private JComboBox pedicureComboBox = new JComboBox();
	private JButton rezervationButton = new JButton();
	private JLabel hairRemovaLabel = new JLabel();
	private JLabel eyebrowsLabel = new JLabel();
	private JLabel manicureLabel = new JLabel();
	private JLabel phoneLabel = new JLabel();
	private JLabel lastNameLabel = new JLabel();
	private JLabel fNameLabel = new JLabel();
	private JLabel pedicureLabel = new JLabel();
	private JButton backButton = new JButton();

	public Rezervation() {
		createWindow();
		components();
		showComponents();
	}

	public void createWindow() {
		frame = new JFrame();
		frame.setTitle("APPOIIMENT");
		frame.setBounds(100, 100, 500, 500);
		frame.getContentPane().setBackground(Color.white);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setResizable(false);
	}

	public void showComponents() {
		frame.add(fNameLabel);
		frame.add(fNameTextField);
		frame.add(lastNameLabel);
		frame.add(lNameTextField);
		frame.add(phoneLabel);
		frame.add(pNumberbTextField);
		frame.add(backButton);
		frame.add(rezervationButton);
		frame.add(eyeComboBox);
		frame.add(eyebrowsLabel);
		frame.add(hairComboBox);
		frame.add(hairRemovaLabel);
		frame.add(manicureComboBox);
		frame.add(manicureLabel);
		frame.add(pedicureComboBox);
		frame.add(pedicureLabel);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void components() {
		getContentPane().setLayout(null);

		fNameTextField = new JTextField();
		fNameTextField.setBounds(158, 35, 222, 19);
		getContentPane().add(fNameTextField);
		fNameTextField.setColumns(10);

		lNameTextField = new JTextField();
		lNameTextField.setBounds(158, 64, 222, 19);
		getContentPane().add(lNameTextField);
		lNameTextField.setColumns(10);

		pNumberbTextField = new JTextField();
		pNumberbTextField.setBounds(158, 93, 222, 19);
		getContentPane().add(pNumberbTextField);
		pNumberbTextField.setColumns(10);

		hairComboBox = new JComboBox();
		hairComboBox.setModel(new DefaultComboBoxModel(new String[] { "", "LEGS", "HANDS", "PUBIAN", "LEGS AND HANDS",
				"LEGS AND PUBIAN", "HANDS AND PUBIAN", "ALL" }));
		hairComboBox.setBounds(158, 122, 190, 21);
		getContentPane().add(hairComboBox);

		eyeComboBox = new JComboBox();
		eyeComboBox.setModel(new DefaultComboBoxModel(new String[] { "", "PAINT", "THOUGHT ", "TRIMMED",
				"PAINT AND THOUGHT", "PAINT AND TRIMMED", "THOUGHT AND TRIMMED", "ALL" }));
		eyeComboBox.setBounds(158, 153, 190, 21);
		getContentPane().add(eyeComboBox);

		manicureComboBox = new JComboBox();
		manicureComboBox.setModel(
				new DefaultComboBoxModel(new String[] { "", "PERMANENT", "SEMIPERMANENT", "SIMPLE MANICURE" }));
		manicureComboBox.setBounds(158, 184, 190, 21);
		getContentPane().add(manicureComboBox);

		pedicureComboBox = new JComboBox();
		pedicureComboBox.setModel(
				new DefaultComboBoxModel(new String[] { "", "PERMANENT", "SEMIPERMANENT", "SIMPLE PEDICURE" }));
		pedicureComboBox.setBounds(158, 218, 190, 21);
		getContentPane().add(pedicureComboBox);

		rezervationButton = new JButton("DONE");
		rezervationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				if (ee.getSource() == rezervationButton) {
					Configuration cfg = new Configuration().configure();
					SessionFactory sf = cfg.buildSessionFactory();
					ClientRezervation clientRezervation = new ClientRezervation();

					clientRezervation.setfName(fNameTextField.getText());
					clientRezervation.setlName(lNameTextField.getText());
					clientRezervation.setpNumber(Integer.valueOf(pNumberbTextField.getText()));
					clientRezervation.setHairRemoval(hairComboBox.getSelectedItem().toString());
					clientRezervation.setEyebrows(eyeComboBox.getSelectedItem().toString());
					clientRezervation.setManicure(manicureComboBox.getSelectedItem().toString());
					clientRezervation.setPedicure(pedicureComboBox.getSelectedItem().toString());

					Session session = sf.openSession();
					session.beginTransaction();
					session.save(clientRezervation);
					session.getTransaction().commit();
					session.close();
					clearFields();
				}
			}
		});
		rezervationButton.setBounds(140, 270, 85, 21);
		getContentPane().add(rezervationButton);
		
		fNameLabel = new JLabel("FIRST  NAME");
		fNameLabel.setBounds(10, 38, 85, 13);
		getContentPane().add(fNameLabel);

		lastNameLabel = new JLabel("LAST  NAME");
		lastNameLabel.setBounds(10, 67, 85, 13);
		getContentPane().add(lastNameLabel);

		phoneLabel = new JLabel("PHONE  NUMBER");
		phoneLabel.setBounds(10, 96, 130, 13);
		getContentPane().add(phoneLabel);

		hairRemovaLabel = new JLabel("HAIR  REMOVAL");
		hairRemovaLabel.setBounds(10, 126, 130, 13);
		getContentPane().add(hairRemovaLabel);

		eyebrowsLabel = new JLabel("EYEBROWS");
		eyebrowsLabel.setBounds(10, 157, 130, 13);
		getContentPane().add(eyebrowsLabel);

		manicureLabel = new JLabel("MANICURE");
		manicureLabel.setBounds(10, 188, 130, 13);
		getContentPane().add(manicureLabel);

		pedicureLabel = new JLabel("PEDICURE");
		pedicureLabel.setBounds(10, 222, 130, 13);
		getContentPane().add(pedicureLabel);

		backButton = new JButton("BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		backButton.setBounds(10, 270, 85, 21);
		getContentPane().add(backButton);

	}

	private void clearFields() {
		fNameTextField.setText("");
		lNameTextField.setText("");
		pNumberbTextField.setText("");

	}
}
