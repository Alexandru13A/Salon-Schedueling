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
import salon.entity.ClientRezervation;
import java.awt.Color;
import java.awt.SystemColor;

public class RezervationList extends JFrame {
	private JPanel contentPane;
	private JTextField idField;
	private DefaultTableModel dtm = new DefaultTableModel();
	private JTable table = new JTable(dtm);
	private List<ClientRezervation> result = new ArrayList<ClientRezervation>();

	private Session session = hibernateUtil.getSessionFactory().openSession();
	private Transaction transaction = session.beginTransaction();

	public RezervationList() {
		getContentPane().setForeground(Color.DARK_GRAY);
		setBackground(Color.WHITE);
		setForeground(new Color(64, 64, 64));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1005, 400);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);
		showClients();

		JButton backButton = new JButton("BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComponent comp = (JComponent) arg0.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		backButton.setBounds(859, 317, 122, 35);
		getContentPane().add(backButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 971, 179);
		getContentPane().add(scrollPane);

		table = new JTable(dtm);
		scrollPane.setViewportView(table);

		JButton loadClients = new JButton("LOAD  REZERVATIONS");
		loadClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);
				showClients();
			}
		});
		loadClients.setBounds(799, 199, 182, 35);
		getContentPane().add(loadClients);

		JButton searchButton = new JButton("SEARCH");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm = (DefaultTableModel) table.getModel();
				dtm.setRowCount(0);
				searchClientRezervation();
			}
		});
		searchButton.setBounds(121, 320, 101, 29);
		getContentPane().add(searchButton);

		idField = new JTextField();
		idField.setBounds(20, 279, 172, 31);
		getContentPane().add(idField);
		idField.setColumns(10);

		JButton deleteButton = new JButton("DELETE");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteClientRezervation();
			}
		});
		deleteButton.setBounds(10, 320, 101, 29);
		getContentPane().add(deleteButton);

		JLabel lblNewLabel = new JLabel("SEARCH BY NAME/DELETE BY ID");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel.setBounds(20, 239, 202, 30);
		getContentPane().add(lblNewLabel);

	}

	private void clearFields() {
		idField.setText("");
	}

	private void showClients() {
		String header[] = new String[] { "ID", "FIRST NAME", "LAST NAME", "PHONE", "HAIRD REMOVAL","EYEBROWS","MANICURE","PEDICURE" };
		dtm.setColumnIdentifiers(header);

		try {
			session = hibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from ClientRezervation");
			result = query.list();
			transaction.commit();
		} catch (HibernateException ee) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		for (int i = 0; i < result.size(); i++) {
			dtm.addRow(new Object[] { result.get(i).getId(),result.get(i).getfName(),result.get(i).getlName(),result.get(i).getpNumber(),result.get(i).getHairRemoval(),result.get(i).getEyebrows(),
					result.get(i).getManicure(),result.get(i).getPedicure()	});
		}
		dtm.fireTableDataChanged();

	}

	private void deleteClientRezervation() {
		try {

			session = hibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete ClientRezervation where id=:id");
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

	private void searchClientRezervation() {
		String header[] = new String[] { "ID", "FIRST NAME", "LAST NAME", "PHONE", "HAIRD REMOVAL","EYEBROWS","MANICURE","PEDICURE" };
		dtm.setColumnIdentifiers(header);

		try {
			session = hibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from ClientRezervation where lastName=:lastName");
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
			dtm.addRow(new Object[] { result.get(i).getId(),result.get(i).getfName(),result.get(i).getlName(),result.get(i).getpNumber(),result.get(i).getHairRemoval(),result.get(i).getEyebrows(),
					result.get(i).getManicure(),result.get(i).getPedicure() });

		}
	}
	
	

}
