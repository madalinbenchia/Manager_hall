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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SearchFreeHall extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchFreeHall frame = new SearchFreeHall();
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
	public SearchFreeHall() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 751, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCauta = new JButton("Afiseaza sali");
		btnCauta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userr = "root";
				String pass = "root";
				String sql = "select * from employee_db.hall";
				Connection mycon = null;
		        PreparedStatement psmt = null;
				    try {
				    	mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db?autoReconnect=true&useSSL=false", userr, pass);
				    	
				    	DefaultTableModel model1 = (DefaultTableModel)table.getModel();
				    	
				    	psmt = mycon.prepareStatement(sql); 
				    	int i = 0;
				    	ResultSet rs = psmt.executeQuery();
				    	while(rs.next())
				    	{
				    		model1.addRow(new Object[] {rs.getInt("id"),rs.getString("nume_sala"), rs.getString("proiector"),rs.getInt("nr_persoane")});
				    		model1.setValueAt(rs.getInt("id"),i,0);
				    		model1.setValueAt(rs.getString("nume_sala"),i,1);
				    		model1.setValueAt(rs.getString("proiector"),i,2);
				    		model1.setValueAt(rs.getInt("nr_persoane"),i,3);
				    	}
						
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
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
		btnCauta.setFont(new Font("Times New Roman", Font.BOLD, 28));
		btnCauta.setBounds(222, 375, 286, 41);
		contentPane.add(btnCauta);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				" ID Sala", "Nume Sala", "Videoproiector", "Numar persoane"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(114);
		table.getColumnModel().getColumn(1).setPreferredWidth(105);
		table.getColumnModel().getColumn(2).setPreferredWidth(112);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.setToolTipText("");
		table.setBounds(174, 13, 385, 338);
		contentPane.add(table);
	}
}
