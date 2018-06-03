package frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import data.Rezervation;

public class RezervationDay extends JFrame {

	private JPanel contentPane;
	private JTextField tf_id;
	private JTable table_rezervari;
	private JLabel lblDataddmmyyy;
	private JTextField tf_date;
	private JButton btnRezervaSala;
	private JLabel lbl;
	private JLabel lblOraSfarsit;
	private JTextField start_hour;
	private JTextField end_hour;
	private JTextField nr_tf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RezervationDay frame = new RezervationDay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*create a users list*/
	public ArrayList<Rezervation> getUsersList() 
	{
		ArrayList<Rezervation> userslist = new ArrayList<Rezervation>();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "select * from employee_db.rezervation where date = ?";
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db?autoReconnect=true&useSSL=false", "root", "root");
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, tf_date.getText());
			
			rs = psmt.executeQuery();
			Rezervation rezv;
			while(rs.next())
			{
				rezv = new Rezervation(rs.getInt("rezervation_id"),rs.getInt("hall_id"),rs.getString("Nume"),rs.getString("date"),rs.getString("start_hour"),rs.getString("end_hour"));
				userslist.add(rezv);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(psmt != null)
				psmt.close();
				if(rs != null)
					rs.close();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Resursele nu s-au inchis corect!");
			} 

		}
		return userslist;
	}

	//Display Data in JTable
	
	public void Show_Users_in_JTable()  {
			ArrayList<Rezervation> list = getUsersList(); 
			DefaultTableModel model = (DefaultTableModel)table_rezervari.getModel();
			Object [] row = new Object[4];
			for(int i = 0; i < list.size(); i++)
			{
				//row[0] = list.get(i).getRezervation_id();
				row[0] = list.get(i).getHall_id();
				row[1] = list.get(i).getNume();
				//row[3] = list.get(i).getDate();
				row[2] = list.get(i).getStart_hour();
				row[3] = list.get(i).getEnd_hour();
				model.addRow(row); 
			}
	}

	/**
	 * Create the frame.
	 */
	public RezervationDay() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 750, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table_rezervari = new JTable();
		table_rezervari.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Id Sala", "Nume", "Start", "Sfarsit"
			}
		));
		
		
		table_rezervari.getColumnModel().getColumn(0).setPreferredWidth(104);
		table_rezervari.getColumnModel().getColumn(1).setPreferredWidth(110);
		table_rezervari.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_rezervari.getColumnModel().getColumn(3).setPreferredWidth(118);
		table_rezervari.setBounds(23, 58, 332, 288);
		contentPane.add(table_rezervari);
		
		
		
		JButton btnNewButton = new JButton("Afiseaza");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userr = "root";
				String pass = "root";
				Connection mycon = null;
		        PreparedStatement psmt = null;
		        ResultSet rs = null;
		       
		        	try {
			    	mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db?autoReconnect=true&useSSL=false", userr, pass);
			    	mycon.setAutoCommit(true);
			    	String sql = "select * from employee_db.rezervation where date = ?";
			    	
			    	psmt = mycon.prepareStatement(sql);
			    	psmt.setString(1, tf_date.getText());
			    	rs = psmt.executeQuery();
					
			    	//DefaultTableModel model = (DefaultTableModel)table_rezervari.getModel();
			    	
			    	Show_Users_in_JTable();
			    	
					//mycon.commit();
					//model.setRowCount(0);
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Ceva nu a functionat!");
				} finally {
					
		                	try {
								if(psmt != null)
								  psmt.close();
								
								 if(mycon != null)
					                    mycon.close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "Fisierele nu s-au inchis corespunzator!");
							}
		
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 26));
		btnNewButton.setBounds(165, 375, 194, 47);
		contentPane.add(btnNewButton);
		
		JLabel lblIdSala = new JLabel("ID Sala");
		lblIdSala.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblIdSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdSala.setBounds(511, 58, 99, 35);
		contentPane.add(lblIdSala);
		
		tf_id = new JTextField();
		tf_id.setBounds(500, 106, 116, 22);
		contentPane.add(tf_id);
		tf_id.setColumns(10);
		
		
		
		JLabel lblRezervari = new JLabel("Rezervari");
		lblRezervari.setHorizontalAlignment(SwingConstants.CENTER);
		lblRezervari.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblRezervari.setBounds(137, 13, 107, 22);
		contentPane.add(lblRezervari);
		
		lblDataddmmyyy = new JLabel("Data(dd/mm/yyy)");
		lblDataddmmyyy.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDataddmmyyy.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataddmmyyy.setBounds(476, 155, 162, 47);
		contentPane.add(lblDataddmmyyy);
		
		tf_date = new JTextField();
		tf_date.setColumns(10);
		tf_date.setBounds(500, 204, 116, 22);
		contentPane.add(tf_date);
		
		btnRezervaSala = new JButton("Rezerva sala");
		btnRezervaSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String userr = "root";
				String pass = "root";
				Connection mycon = null;
		        PreparedStatement psmt = null;
		        PreparedStatement psmt1 = null;
		        ResultSet rs = null;
		       
		        	try {
			    	mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db?autoReconnect=true&useSSL=false", userr, pass);
			    	mycon.setAutoCommit(true);
			    	String sql = "insert into employee_db.rezervation(hall_id,date,start_hour,end_hour,Nume) values(?,?,?,?,?)";
			    	String sql1 = "select * from employee_db.rezervation";
			    	psmt1 = mycon.prepareStatement(sql1);
			    	rs = psmt1.executeQuery();
			    	String date_copy = null;

			    	int ok = 1;
			    	int start_h = Integer.parseInt(start_hour.getText().substring(0, 2));
			    	int start_min = Integer.parseInt(start_hour.getText().substring(3));
			    	int end_h = Integer.parseInt(end_hour.getText().substring(0,2));
			    	int end_min = Integer.parseInt(end_hour.getText().substring(3));
			    	int date_day = Integer.parseInt(tf_date.getText().substring(0, 2));
			    	int date_month = Integer.parseInt(tf_date.getText().substring(3, 5));
			    	int date_year = Integer.parseInt(tf_date.getText().substring(7));
			    	
			    	while(rs.next())
			    	{
			    		date_copy = rs.getString("date");
			    		int copy_date_day = Integer.parseInt(date_copy.substring(0, 2));
				    	int copy_date_month = Integer.parseInt(date_copy.substring(3, 5));
				    	int copy_date_year = Integer.parseInt(date_copy.substring(7));
				    	
				    	int copy_start_h = Integer.parseInt(rs.getString("start_hour").substring(0, 2));
				    	int copy_start_min = Integer.parseInt(rs.getString("start_hour").substring(3));
				    	int copy_end_h = Integer.parseInt(rs.getString("end_hour").substring(0,2));
				    	int copy_end_min = Integer.parseInt(rs.getString("end_hour").substring(3));
				    	
				    	if(copy_date_day == date_day && copy_date_month == date_month && copy_date_year == date_year)
				    	{
				    	   if((start_h >= copy_start_h && start_min >= copy_start_min && start_h <= copy_end_h && start_min <= copy_end_min) || (end_h >= copy_start_h && end_min >= copy_start_min && end_h <= copy_end_h && end_min <= copy_end_min))
				    	   ok = 0;
				    	}
			    	}
			    	
			    	if(ok == 1)
			    	{
			    		psmt = mycon.prepareStatement(sql);
			    		psmt.setInt(1, Integer.parseInt(tf_id.getText()));
			    		psmt.setString(2, tf_date.getText());
			    		psmt.setString(3, start_hour.getText());
			    		psmt.setString(4, end_hour.getText());
			    		psmt.setString(5,nr_tf.getText());
			    		psmt.executeUpdate();
			    		DefaultTableModel model = (DefaultTableModel)table_rezervari.getModel();
			    		Show_Users_in_JTable();
			    		JOptionPane.showMessageDialog(null, "Inregistrare incheiata cu succes!");
			    	}
			    	
			    	else {	
			    			JOptionPane.showMessageDialog(null, "Verificati inca o data ziua si orele libere salilor!");
			    	}
			    	
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Ceva nu a functionat!");
					e2.printStackTrace();
				} finally {
					
		                	try {
								if(psmt != null)
								  psmt.close();
								
								 if(mycon != null)
					                    mycon.close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "Resursele nu s-au inchis corespunzator!");
							}
		
				}
		        	
			}
		});
		btnRezervaSala.setFont(new Font("Times New Roman", Font.BOLD, 26));
		btnRezervaSala.setBounds(416, 375, 194, 47);
		contentPane.add(btnRezervaSala);
		
		lbl = new JLabel("Ora inceput");
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl.setBounds(409, 248, 133, 26);
		contentPane.add(lbl);
		
		lblOraSfarsit = new JLabel("Ora sfarsit");
		lblOraSfarsit.setHorizontalAlignment(SwingConstants.CENTER);
		lblOraSfarsit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblOraSfarsit.setBounds(568, 250, 133, 24);
		contentPane.add(lblOraSfarsit);
		
		start_hour = new JTextField();
		start_hour.setBounds(416, 289, 116, 22);
		contentPane.add(start_hour);
		start_hour.setColumns(10);
		
		end_hour = new JTextField();
		end_hour.setColumns(10);
		end_hour.setBounds(578, 287, 116, 22);
		contentPane.add(end_hour);
		
		nr_tf = new JTextField();
		nr_tf.setBounds(578, 340, 116, 22);
		contentPane.add(nr_tf);
		nr_tf.setColumns(10);
		
		JLabel lblNume = new JLabel("Nume");
		lblNume.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNume.setHorizontalAlignment(SwingConstants.CENTER);
		lblNume.setBounds(500, 343, 56, 16);
		contentPane.add(lblNume);
	}
}
