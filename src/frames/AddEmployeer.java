package frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import data.Employee;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddEmployeer extends JFrame {

	private JPanel panel;
	private final Action action = new SwingAction();
	private JTextField nume_angajat;
	private JTextField prenume_angajat;
	private JTextField user_angajat;
	private JTable table_employee;
	private JTextField parola_angajat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmployeer frame = new AddEmployeer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	/*create a users list*/
	public ArrayList<Employee> getUsersList() 
	{
		ArrayList<Employee> userslist = new ArrayList<Employee>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "select * from employee_db.employee";
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db?autoReconnect=true&useSSL=false", "root", "root");
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			Employee emp;
			while(rs.next())
			{
				emp = new Employee(rs.getInt("ID"),rs.getString("user"),rs.getString("password"),rs.getString("nume"),rs.getString("prenume"));
				userslist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Ceva este gresit!");
		}
		finally {
			try {
				if(psmt != null)
				psmt.close();
				if(rs != null)
					rs.close();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("Eroare la inchiderea resurselor!");
			}
		}
		return userslist;
	}

	//Display Data in JTable
	
	public void Show_Users_in_JTable()  {
			ArrayList<Employee> list = getUsersList(); 
			DefaultTableModel model = (DefaultTableModel)table_employee.getModel();
			Object [] row = new Object[5];
			for(int i = 0; i < list.size(); i++)
			{
				row[0] = list.get(i).getEmployee_id();
				row[1] = list.get(i).getUser();
				row[2] = list.get(i).getPassword();
				row[3] = list.get(i).getNume();
				row[4] = list.get(i).getPrenume();
				model.addRow(row); 
			}
	}
	
	
	/**
	 * Create the frame.
	 */	
	public AddEmployeer() {
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 751, 501);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		table_employee = new JTable();
		panel.add(table_employee);
		DefaultTableModel model = (DefaultTableModel)table_employee.getModel();
		table_employee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel mod = (DefaultTableModel)table_employee.getModel();
				int i = table_employee.getSelectedRow();
				user_angajat.setText(mod.getValueAt(i,1).toString());
				parola_angajat.setText(mod.getValueAt(i,2).toString());
				nume_angajat.setText(mod.getValueAt(i,3).toString());
				prenume_angajat.setText(mod.getValueAt(i,4).toString());
			}
		});
		table_employee.setModel(new DefaultTableModel(
			new Object[][] {
				{"ID", "USer", "Parola", "Nume", "Prenume"},
			},
			new String[] {
				"ID", "Nume", "Prenume", "User", "Email"
			}
		));
		table_employee.getColumnModel().getColumn(0).setPreferredWidth(66);
		table_employee.getColumnModel().getColumn(1).setPreferredWidth(119);
		table_employee.getColumnModel().getColumn(2).setPreferredWidth(110);
		table_employee.getColumnModel().getColumn(2).setMaxWidth(2147483635);
		table_employee.getColumnModel().getColumn(3).setPreferredWidth(113);
		table_employee.getColumnModel().getColumn(4).setPreferredWidth(107);
		table_employee.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table_employee.setBackground(Color.WHITE);
		table_employee.setFont(new Font("Times New Roman", Font.BOLD, 20));
		table_employee.setBounds(307, 13, 414, 370);
		
		
		Show_Users_in_JTable();
		JButton btnAdaugaAngajat = new JButton("Adauga angajat");
		btnAdaugaAngajat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				String userr = "root";
				String pass = "root";
				String sql = "insert into employee_db.employee(user,password,nume,prenume) values(?,?,?,?)";
				Connection mycon = null;
		        PreparedStatement psmt = null;
		        
				    try {
				    	
				    	mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db?autoReconnect=true&useSSL=false", userr, pass);
				    	mycon.setAutoCommit(true);
				    	
				    	psmt = mycon.prepareStatement(sql);
				    	
				    	psmt.setString(1,user_angajat.getText());
						psmt.setString(2,parola_angajat.getText());
						psmt.setString(3, nume_angajat.getText());
						psmt.setString(4,prenume_angajat.getText());
						
						psmt.executeUpdate();
						
						DefaultTableModel model1 = (DefaultTableModel)table_employee.getModel();
						model.addRow(new Object[]{user_angajat.getText(),parola_angajat.getText(), nume_angajat.getText(),prenume_angajat.getText()});
						
				    	
						//mycon.commit();
						//Show_Users_in_JTable();
						model.setRowCount(0);
						
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Ceva este in neregula!");
					} finally {
						
			                	try {
									if(psmt != null)
									  psmt.close();
									
									 if(mycon != null)
						                    mycon.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									System.out.println("Eroare la inchiderea resurselor!");
								}

					}
				  }
			});
		
		btnAdaugaAngajat.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnAdaugaAngajat.setBounds(55, 396, 224, 45);
		panel.add(btnAdaugaAngajat);
		
		JButton btnModificaAngajat = new JButton("Modifica Angajat");
		btnModificaAngajat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userr = "root";
				String pass = "root";
				String sql = "update employee_db.employee "
						+ "	set user=?,password=?,nume=?,prenume=? where user=?";
				String sql1 = "select * from employee_db.employee";
				Connection mycon = null;
		        PreparedStatement psmt = null;
		        PreparedStatement psmt1 = null;
		        ResultSet rs = null;
				    try {
				    	mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db?autoReconnect=true&useSSL=false", userr, pass);
				    	
				    	DefaultTableModel model1 = (DefaultTableModel)table_employee.getModel();
				    	
				    	psmt = mycon.prepareStatement(sql); 
				    	psmt1= mycon.prepareStatement(sql1);
				    	
				    	rs = psmt1.executeQuery();
				    	String copie_user = null;
				    	while(rs.next()) 
				    	{
				    		if(rs.getString("user").equals(user_angajat.getText()))
				    		  copie_user = user_angajat.getText();
				    	}
				    	
				    	JOptionPane.showMessageDialog(null, copie_user);
				    	
												
						//table_employee.revalidate();
						int i = table_employee.getSelectedRow();
				    	model1.setValueAt(user_angajat.getText(),i,1);
				    	model1.setValueAt(parola_angajat.getText(),i,2);
				    	model1.setValueAt(nume_angajat.getText(),i,3);
				    	model1.setValueAt(prenume_angajat.getText(),i,4);
				    	//model1.setRowCount(0);
				    	Show_Users_in_JTable();
				    	

				    	psmt.setString(1, user_angajat.getText());
				    	psmt.setString(2, parola_angajat.getText());
				    	psmt.setString(3, nume_angajat.getText());
				    	psmt.setString(4, prenume_angajat.getText());
				    	psmt.setString(5, copie_user);
						psmt.executeUpdate();
						
						
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						//e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Ceva este in neregula!");
					} finally {
						
						try
			            {
			                 
			                if(psmt != null)
			                    psmt.close();
			                
			                if(mycon != null)
			                    mycon.close();
			            }
			            catch (SQLException ex)
			            {
			                System.out.println("Eroare la Inchiderea conexiunii cu BD!");
			            }
						
					}
			}
		});
		btnModificaAngajat.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnModificaAngajat.setBounds(291, 396, 190, 45);
		panel.add(btnModificaAngajat);
		
		JButton btnStergeAngajat = new JButton("Sterge angajat");
		btnStergeAngajat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String userr = "root";
				String pass = "root";
				String sql = "delete from employee_db.employee where user=?";
				Connection mycon = null;
				
				try {
			    	mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db?autoReconnect=true&useSSL=false", userr, pass);
			    	
			    	DefaultTableModel model1 = (DefaultTableModel)table_employee.getModel();
			    	
			    	int i = table_employee.getSelectedRow();
			    	
			    	PreparedStatement psmt = mycon.prepareStatement(sql);
			    	
			    	psmt.setString(1,user_angajat.getText());
			    	psmt.executeUpdate();
			    	
			    	if(i >= 0) {
			    		model1.removeRow(i);	
			    		user_angajat.setText("");
			    		parola_angajat.setText("");
			    		nume_angajat.setText("");
			    		prenume_angajat.setText("");
			    	}
			    	
			    	else
			    	{
			    		System.out.println("Selectati un rand din tabel!s");
			    	}
					//mycon.commit();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Ceva este in neregula!");
					e2.printStackTrace();
				} finally {
					
					try
		            {
		                
		                if(mycon != null)
		                    mycon.close();
		            }
		            catch (SQLException ex)
		            {
		                System.out.println("Eroare la Inchiderea conexiunii cu BD!");
		            }
					
				}
				
				
			}
		});
		btnStergeAngajat.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnStergeAngajat.setBounds(493, 396, 173, 44);
		panel.add(btnStergeAngajat);
		
		
		JLabel lblNume = new JLabel("Nume");
		lblNume.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNume.setBounds(71, 59, 56, 16);
		panel.add(lblNume);
		
		nume_angajat = new JTextField();
		nume_angajat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)
					e.consume();
			}
		});
		nume_angajat.setBounds(179, 59, 116, 22);
		panel.add(nume_angajat);
		nume_angajat.setColumns(10);
		
		JLabel lblPrenume = new JLabel("Prenume");
		lblPrenume.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPrenume.setBounds(71, 96, 96, 16);
		panel.add(lblPrenume);
		
		prenume_angajat = new JTextField();
		prenume_angajat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)
					e.consume();
			}
		});
		prenume_angajat.setBounds(179, 94, 116, 22);
		panel.add(prenume_angajat);
		prenume_angajat.setColumns(10);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblUser.setBounds(71, 173, 56, 16);
		panel.add(lblUser);
		
		user_angajat = new JTextField();
		user_angajat.setBounds(179, 171, 116, 22);
		panel.add(user_angajat);
		user_angajat.setColumns(10);
		
		JLabel lblParola = new JLabel("Parola");
		lblParola.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblParola.setBounds(71, 216, 56, 16);
		panel.add(lblParola);
		
		parola_angajat = new JTextField();
		parola_angajat.setBounds(179, 214, 116, 22);
		panel.add(parola_angajat);
		parola_angajat.setColumns(10);
		
		
	
}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
