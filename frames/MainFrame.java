package frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBineAtiVenit = new JLabel("Bine ati venit!");
		lblBineAtiVenit.setHorizontalAlignment(SwingConstants.CENTER);
		lblBineAtiVenit.setFont(new Font("Times New Roman", Font.BOLD, 27));
		lblBineAtiVenit.setBounds(260, 13, 244, 43);
		contentPane.add(lblBineAtiVenit);
		
		JButton btnAdaugaOSala = new JButton("Adauga o sala");
		btnAdaugaOSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddHallFrame add = new AddHallFrame();
				add.setVisible(true);
			}
		});
		btnAdaugaOSala.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnAdaugaOSala.setBounds(12, 135, 194, 52);
		contentPane.add(btnAdaugaOSala);
		
		JButton btnRezervaOSala = new JButton("Rezerva o sala");
		btnRezervaOSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchFreeHall rez = new SearchFreeHall();
				rez.setVisible(true);
			}
		});
		btnRezervaOSala.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnRezervaOSala.setBounds(260, 135, 194, 52);
		contentPane.add(btnRezervaOSala);
		
		JButton btnRezervarileIntroZi = new JButton("Rezervarile intr-o zi");
		btnRezervarileIntroZi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RezervationDay rez = new RezervationDay();
				rez.setVisible(true);
			}
		});
		btnRezervarileIntroZi.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnRezervarileIntroZi.setBounds(499, 135, 211, 52);
		contentPane.add(btnRezervarileIntroZi);
		
		JButton btnAdaugaediteazaAngajati = new JButton("Adauga/Editeaza Angajati");
		btnAdaugaediteazaAngajati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEmployeer emp = new AddEmployeer();
				emp.setVisible(true);
			}
		});
		btnAdaugaediteazaAngajati.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnAdaugaediteazaAngajati.setBounds(225, 352, 267, 43);
		contentPane.add(btnAdaugaediteazaAngajati);
		
		JButton btnNewButton = new JButton("Afiseaza sali");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchFreeHall sfh = new SearchFreeHall();
				sfh.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.setBounds(225, 273, 267, 43);
		contentPane.add(btnNewButton);
	}

}
