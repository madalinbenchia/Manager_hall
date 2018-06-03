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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cs.CapitalizeClient;

import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField user_TF;
	private JPasswordField pass_TF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Login() {
		
		
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 752, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIntraInCont = new JLabel("Intra in cont");
		lblIntraInCont.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntraInCont.setFont(new Font("Times New Roman", Font.BOLD, 27));
		lblIntraInCont.setBounds(269, 31, 215, 39);
		contentPane.add(lblIntraInCont);
		
		user_TF = new JTextField();
		user_TF.setBounds(322, 155, 116, 22);
		contentPane.add(user_TF);
		user_TF.setColumns(10);
		
		JLabel user = new JLabel("User");
		user.setFont(new Font("Times New Roman", Font.BOLD, 23));
		user.setBounds(115, 158, 138, 16);
		contentPane.add(user);
		
		JLabel password = new JLabel("Password");
		password.setFont(new Font("Times New Roman", Font.BOLD, 23));
		password.setBounds(115, 212, 138, 16);
		contentPane.add(password);
		
		JLabel control_LB = new JLabel("");
		control_LB.setBounds(215, 437, 277, 16);
		contentPane.add(control_LB);
		
		
		
		JButton btn_register = new JButton("Inregistreaza-te");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEmployeer emp_temp = new AddEmployeer();
				emp_temp.setVisible(true);
			}
		});
		btn_register.setFont(new Font("Times New Roman", Font.BOLD, 24));
		btn_register.setBounds(403, 359, 209, 39);
		contentPane.add(btn_register);	
		
		pass_TF = new JPasswordField();
		pass_TF.setBounds(322, 212, 116, 22);
		contentPane.add(pass_TF);
		
		JButton btn_login = new JButton("LOG IN");

		btn_login.setFont(new Font("Times New Roman", Font.BOLD, 24));
		btn_login.setBounds(196, 359, 138, 39);
		contentPane.add(btn_login);
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = user_TF.getText();
				String pass = pass_TF.getText();
				try {
					String message = CapitalizeClient.getInstance().sendLoginToServer(user, pass);
				} catch ( Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	}
	
		
		
		public static String serverLogin(String u, String pas) 
		
		{
			String qrySQL = "select * from employee_db.employee where user = ? and password = ?";
			System.out.println("reusit");
			String userr = "root";
			String pass = "root";
			String message = "";
			ResultSet rs = null;
			try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db?autoReconnect=true&useSSL=false", userr, pass);
					PreparedStatement psmt = con.prepareStatement(qrySQL);) {
				 psmt.setString(1,u);
				 psmt.setString(2,pas);
				 rs = psmt.executeQuery();
				
				if(rs.next()) {
					
					//if(rs.getString("user").equals(user_TF.getText()))  ;
					//if(rs.getString("user").equals(user_TF.getText()) && rs.getString("password").equals(pass_TF.getText())) {
						//JOptionPane.showMessageDialog(null, "Conectare reusita. Bine ai venit " + rs.getString("nume") + " " + rs.getString("prenume"));
						message = "Conectare reusita. Bine ai venit " + rs.getString("nume") + " " + rs.getString("prenume");
						//MainFrame main_table = new MainFrame();
						//main_table.setVisible(true);
						//break;
					}
					else {
						//JOptionPane.showMessageDialog(null, "Conectare nereusita! User sau parola gresite!");
						//user_TF.setText("");
						//pass_TF.setText("");
						message = "Conectare nereusita! User sau parola gresite!";
					}
				psmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return message;
		}
}
