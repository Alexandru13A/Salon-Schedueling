package salon.menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import salon.entity.Employee;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EmployeeInsert extends JFrame implements ActionListener {

	private JFrame frame;
	private JLabel firstNameLabel = new JLabel("FIRST  NAME");
	private JLabel lastNameLabel = new JLabel("LAST  NAME");
	private JLabel phoneNumbeLabel = new JLabel("PHONE  NUMBER");
	private JLabel genderBoxLabel = new JLabel("GENDER");
	private JTextField firstNameText = new JTextField();
	private JTextField lastNameText = new JTextField();
	private JTextField phoneNumberText = new JTextField();
	private JTextField genderBoxText = new JTextField();
	private JButton insertEmployee = new JButton("INSERT");
	private JButton backButton = new JButton("BACK");
	private JButton showAllAppoiments = new JButton("SHOW EMPLOYEE");

	public EmployeeInsert() {
		createWindow();
		setLocationAndSize();
		addComponentsToFrame();
		actionEvent();

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

	public void setLocationAndSize() {
		firstNameLabel.setBounds(20, 40, 100, 35);
		lastNameLabel.setBounds(20, 80, 100, 35);
		phoneNumbeLabel.setBounds(20, 120, 120, 35);
		firstNameText.setBounds(150, 48, 120, 20);
		lastNameText.setBounds(150, 88, 120, 20);
		phoneNumberText.setBounds(150, 128, 120, 20);
		genderBoxText.setBounds(150, 160, 100, 20);
		genderBoxLabel.setBounds(20, 160, 100, 20);
		insertEmployee.setBounds(40, 210, 100, 30);
		backButton.setBounds(160, 210, 100, 30);
		showAllAppoiments.setBounds(280, 210, 170, 30);
	}

	public void addComponentsToFrame() {
		frame.add(firstNameLabel);
		frame.add(lastNameLabel);
		frame.add(phoneNumbeLabel);
		frame.add(firstNameText);
		frame.add(lastNameText);
		frame.add(phoneNumberText);
		frame.add(genderBoxText);
		frame.add(genderBoxLabel);
		frame.add(insertEmployee);
		frame.add(backButton);
		frame.add(showAllAppoiments);
	}

	public void actionEvent() {
		insertEmployee.addActionListener(this);
		backButton.addActionListener(this);
		showAllAppoiments.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ee) {
		if (ee.getSource() == insertEmployee) {
			Configuration cfg = new Configuration().configure();
			SessionFactory sf = cfg.buildSessionFactory();
			Employee employee=new Employee();

			employee.setFirstName(firstNameText.getText());
			employee.setLastName(lastNameText.getText());
			employee.setPhoneNumber(phoneNumberText.getText());
			employee.setGender(genderBoxText.getText());

			Session session = sf.openSession();
			session.beginTransaction();
			session.save(employee);
			session.getTransaction().commit();
			session.close();
			clearFields();
		}
		if(ee.getSource()==showAllAppoiments) {
			new EmployeeList();
		}
		if(ee.getSource()==backButton) {
			frame.dispose();
			
			
			
		}
		

	}

	private void clearFields() {
		firstNameText.setText("");
		lastNameText.setText("");
		phoneNumberText.setText("");
		genderBoxText.setText("");

	}

}
