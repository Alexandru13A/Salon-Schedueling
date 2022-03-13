package salon.menu;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.util.hibernateUtil;
import salon.entity.Employee;

public class EmployeeList extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private DefaultTableModel dtm = new DefaultTableModel();
	private JTable table = new JTable(dtm);
	private List<Employee> result = new ArrayList<Employee>();

	private Session session = hibernateUtil.getSessionFactory().openSession();
	private Transaction transaction = session.beginTransaction();

	public EmployeeList() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);
		showEmployee();

		JButton backButton = new JButton("BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComponent comp = (JComponent) arg0.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		backButton.setBounds(10, 532, 101, 21);
		getContentPane().add(backButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 566, 355);
		getContentPane().add(scrollPane);

		table = new JTable(dtm);
		scrollPane.setViewportView(table);

		JButton loadClients = new JButton("LOAD CLIENTS");
		loadClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);
				showEmployee();
			}
		});
		loadClients.setBounds(435, 375, 123, 21);
		getContentPane().add(loadClients);

		JButton searchButton = new JButton("SEARCH");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);
				searchClient();
			}
		});
		searchButton.setBounds(0, 431, 101, 21);
		getContentPane().add(searchButton);

		idField = new JTextField();
		idField.setBounds(52, 402, 96, 19);
		getContentPane().add(idField);
		idField.setColumns(10);

		JButton deleteButton = new JButton("DELETE");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteClient();
			}
		});
		deleteButton.setBounds(111, 431, 101, 21);
		getContentPane().add(deleteButton);

		JLabel lblNewLabel = new JLabel("SEARCH BY NAME/DELETE BY ID");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 9));
		lblNewLabel.setBounds(40, 380, 172, 13);
		getContentPane().add(lblNewLabel);

	}

	private void clearFields() {
		idField.setText("");
	}

	private void showEmployee() {
		String header[] = new String[] { "ID", "FIRST NAME", "LAST NAME", "PHONE", "GENDER" };
		dtm.setColumnIdentifiers(header);

		try {
			session = hibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Employee");
			result = query.list();
			transaction.commit();
		} catch (HibernateException ee) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		for (int i = 0; i < result.size(); i++) {
			dtm.addRow(new Object[] { result.get(i).getId(), result.get(i).getFirstName(), result.get(i).getLastName(),
					result.get(i).getPhoneNumber(), result.get(i).getGender() });
		}
		dtm.fireTableDataChanged();

	}

	private void deleteClient() {
		try {

			session = hibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete Employee where id=:id");
			query.setParameter("id", Long.valueOf(idField.getText()));
			query.executeUpdate();
			transaction.commit();
			clearFields();
		} catch (HibernateException ee) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		clearFields();

	}

	private void searchClient() {
		String header[] = new String[] { "ID", "FIRST NAME", "LAST NAME", "PHONE", "GENDER" };
		dtm.setColumnIdentifiers(header);

		try {
			session = hibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Employee where lastName=:lastName");
			query.setParameter("lastName", idField.getText());
			result = query.list();
			transaction.commit();
			clearFields();
		} catch (HibernateException ee) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		for (int i = 0; i < result.size(); i++) {
			dtm.addRow(new Object[] { result.get(i).getId(), result.get(i).getFirstName(), result.get(i).getLastName(),
					result.get(i).getPhoneNumber(), result.get(i).getGender() });

		}
	}
}
