package salon.menu;

import javax.swing.*;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalonWindow implements ActionListener {

	private static final int EXIT_ON_CLOSE = 0;
	private JButton rezervaionButton = new JButton("RESERVATION");
	private JButton employeeButton = new JButton("EMPLOYEE");
	private JButton rezervationListButton = new JButton("REZERVATION  LIST");
	private JFrame frame;
	private final JButton exitButton = new JButton("EXIT");
	
		

	
	
	public SalonWindow() {

		createWindow();
		setLocationAndSize();
		addComponentsToFrame();
		actionEvent();

	}

	public void createWindow() {
		frame = new JFrame();
		frame.setTitle("SALON ANDREEA");
		frame.setBounds(100, 100, 400, 400);
		frame.getContentPane().setBackground(Color.white);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(true);

	}

	public void setLocationAndSize() {
		
		rezervaionButton.setBounds(102, 132, 170, 35);
		employeeButton.setBounds(102, 68, 170, 35);
		rezervationListButton.setBounds(102, 193, 170, 35);
		exitButton.setBounds(10, 318, 100, 35);

	}

	public void addComponentsToFrame() {
		frame.getContentPane().add(rezervaionButton);
		frame.getContentPane().add(employeeButton);
		frame.getContentPane().add(rezervationListButton);
		frame.getContentPane().add(exitButton);
		
		
	}

	public void actionEvent() {
		rezervaionButton.addActionListener(this);
		employeeButton.addActionListener(this);
		rezervationListButton.addActionListener(this);
		exitButton.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==rezervaionButton) {
			new Rezervation();
		}
		if(e.getSource()==employeeButton) {
			new EmployeeInsert();
		}
		if(e.getSource()==rezervationListButton) {
			new RezervationList();
		}
		if(e.getSource()==exitButton) {
			System.exit(0);
		}
		
	}
}
