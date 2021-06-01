package main;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtPassword;
	
	private String[] sampleLogin = {"admin", "admin"};
	private Main main;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Login window = new Login();
				window.setVisible(true);
			}
		});
	}

	
	public Login() {
		main = new Main();
		
		UIManager.put("OptionPane.background", new Color(54, 134, 0));
		UIManager.put("OptionPane.messageForeground", new Color(255, 255, 255));
		UIManager.put("Panel.background", new Color(54, 134, 0));
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("images\\logo.png"));
		setTitle(Main.SYSTEM_NAME + " | Login");
		setBounds(100, 100, 884, 377);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 878, 348);
		mainPanel.setBackground(new Color(10, 163, 35));
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		JPanel peachPanel = new JPanel();
		peachPanel.setBounds(0, 41, 878, 46);
		peachPanel.setBackground(new Color(243, 201, 105));
		mainPanel.add(peachPanel);
		
		Image logo = new ImageIcon("images\\logo.png").getImage().getScaledInstance(161, 127, Image.SCALE_SMOOTH);
		JLabel cvsuIcon = new JLabel(new ImageIcon(logo));
		cvsuIcon.setBounds(10, 123, 161, 127);
		mainPanel.add(cvsuIcon);
		
		JLabel schoolName = new JLabel("Cavite State University");
		schoolName.setForeground(new Color(255, 245, 178));
		schoolName.setFont(new Font("Arial", Font.BOLD, 20));
		schoolName.setBounds(181, 157, 215, 35);
		mainPanel.add(schoolName);
		
		JLabel enrollmentSystem = new JLabel("Enrollment System");
		enrollmentSystem.setForeground(new Color(255, 245, 178));
		enrollmentSystem.setFont(new Font("Arial", Font.BOLD, 16));
		enrollmentSystem.setBounds(181, 203, 144, 27);
		mainPanel.add(enrollmentSystem);
		
		JPanel formPanel = new JPanel();
		formPanel.setBorder(new LineBorder(new Color(192, 192, 192), 5, true));
		formPanel.setBackground(new Color(232, 174, 104));
		formPanel.setBounds(427, 98, 428, 228);
		mainPanel.add(formPanel);
		formPanel.setLayout(null);
		
		JLabel passwordIcon = new JLabel("");
		Image passwordlogo = new ImageIcon("images\\pw.png").getImage().getScaledInstance(62, 56, Image.SCALE_SMOOTH);
		ImageIcon passwordlogoScaled = new ImageIcon(passwordlogo);
		passwordIcon.setIcon(passwordlogoScaled);
		passwordIcon.setBounds(10, 123, 62, 56);
		formPanel.add(passwordIcon);
		
		JLabel usernameIcon = new JLabel("");
		Image usrnameLogo = new ImageIcon("images\\uname.png").getImage().getScaledInstance(62, 56, Image.SCALE_SMOOTH);
		ImageIcon usrnameLogoScaled = new ImageIcon(usrnameLogo);
		usernameIcon.setIcon(usrnameLogoScaled);
		usernameIcon.setBounds(10, 56, 62, 56);
		formPanel.add(usernameIcon);
		
		JLabel password = new JLabel("Password");
		password.setFont(new Font("Arial Black", Font.PLAIN, 16));
		password.setBounds(82, 131, 90, 30);
		formPanel.add(password);
		
		JLabel username = new JLabel("Username");
		username.setFont(new Font("Arial Black", Font.PLAIN, 16));
		username.setBounds(82, 71, 90, 30);
		formPanel.add(username);
		
		JTextField txtUsername = new JTextField();
		txtUsername.setFont(new Font("Arial", Font.PLAIN, 19));
		txtUsername.setBounds(182, 71, 212, 28);
		formPanel.add(txtUsername);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 19));
		txtPassword.setBounds(182, 129, 212, 30);
		formPanel.add(txtPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBackground(Color.WHITE);
		Image loginIcon = new ImageIcon("images\\lg.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon loginIconScaled = new ImageIcon(loginIcon);
		btnLogin.setIcon(loginIconScaled);
		btnLogin.setBounds(300, 181, 94, 36);
		formPanel.add(btnLogin);
		
		JLabel welcome = new JLabel("Welcome Administrator");
		welcome.setFont(new Font("Segoe UI Semilight", Font.BOLD, 18));
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setBounds(49, 11, 345, 34);
		formPanel.add(welcome);
		
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = txtUsername.getText();
				String pass = new String(txtPassword.getPassword());
				
				if (user.equals(sampleLogin[0]) && pass.equals(sampleLogin[1])) {
					main.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, 
						"Incorrect username or password", 
						Main.SYSTEM_NAME, 
						JOptionPane.WARNING_MESSAGE);
					txtUsername.requestFocus();
				}
			}
		});
		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					txtPassword.requestFocus();
				}
			}
		});
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					btnLogin.doClick();
				}
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	

}
