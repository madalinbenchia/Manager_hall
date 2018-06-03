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
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddHallFrame extends JFrame {

	
	private JPanel contentPane;
	private JTextField tF_ID;
	private JTextField tF_nr;
	private JTextField tf_proiector;
	private JTextField ft_name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddHallFrame frame = new AddHallFrame();
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
	public AddHallFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 747, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdaugaSala = new JButton("Adauga sala");
		btnAdaugaSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection mycon = null;
				PreparedStatement psmt = null;
				ResultSet rs = null;
				String userr = "root";
				String pass = "root";
				String sql ="insert into employee_db.hall(nr_persoane,proiector,nume_sala,id) values(?,?,?,?)";
				try {
					mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db?autoReconnect=true&useSSL=false", userr, pass);
					psmt = mycon.prepareStatement(sql);
					
					String id_err = "ok";
					String nr_err = "ok";
					String proiector_err = "ok";
					String total_error = null;
					
					if(Integer.parseInt(tF_ID.getText()) > 20) id_err = "ID-ul trebuie sa fie mai mic decat 20! ";
					if(Integer.parseInt(tF_nr.getText()) > 150) nr_err = "Nu exista sali cu mai mult de 150 persoane! ";
					if(!tf_proiector.getText().equals("da") && !tf_proiector.getText().equals("nu")) proiector_err = "Specificati daca sala are video proiector!";
					total_error = id_err + nr_err + proiector_err;
					
					if(total_error.equals("okokok")) {
						psmt.setString(1,tF_nr.getText());
						psmt.setString(2,tf_proiector.getText());
						psmt.setString(3,ft_name.getText());
						psmt.setInt(4,Integer.parseInt(tF_ID.getText()));
						psmt.executeUpdate();
						//mycon.commit();
						JOptionPane.showMessageDialog(null, "Inregistrare incheiata cu succes!");
					}
					else {
						JOptionPane.showMessageDialog(null, total_error);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Verificati daca sala cu acest id  exista deja!");
				}
			}
		});
		
		JLabel show_validation_id = new JLabel("");
		show_validation_id.setForeground(Color.RED);
		show_validation_id.setBounds(497, 76, 138, 16);
		contentPane.add(show_validation_id);
		
		btnAdaugaSala.setFont(new Font("Times New Roman", Font.BOLD, 30));
		btnAdaugaSala.setBounds(264, 403, 221, 38);
		contentPane.add(btnAdaugaSala);
		
		tF_ID = new JTextField();
		tF_ID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE))
					e.consume();
			}
		});
		
		tF_nr = new JTextField();
		tF_nr.setBounds(369, 240, 56, 22);
		contentPane.add(tF_nr);
		tF_nr.setColumns(10);
		
		tF_nr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE))
					e.consume();
			}
		});
		
		
		tF_ID.setBounds(369, 73, 116, 22);
		contentPane.add(tF_ID);
		tF_ID.setColumns(10);
		
		JLabel lblIdSala = new JLabel("ID Sala:");
		lblIdSala.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblIdSala.setBounds(226, 75, 94, 16);
		contentPane.add(lblIdSala);
		
		JLabel lblProiector = new JLabel("Proiector:");
		lblProiector.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblProiector.setBounds(226, 129, 94, 16);
		contentPane.add(lblProiector);
		
		JLabel lblNumarPersoane = new JLabel("Numar Persoane:");
		lblNumarPersoane.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNumarPersoane.setBounds(226, 242, 144, 16);
		contentPane.add(lblNumarPersoane);
		
		
		tf_proiector = new JTextField();
		tf_proiector.setBounds(369, 127, 116, 22);
		contentPane.add(tf_proiector);
		tf_proiector.setColumns(10);
		
		JLabel lblNumeSala = new JLabel("Nume Sala");
		lblNumeSala.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNumeSala.setBounds(226, 190, 123, 16);
		contentPane.add(lblNumeSala);
		
		ft_name = new JTextField();
		ft_name.setBounds(369, 189, 116, 22);
		contentPane.add(ft_name);
		ft_name.setColumns(10);
		
	}
}