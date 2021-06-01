package main;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import util.Utility;
import util.Database;
import frames.Dashboard;
import frames.Manage;
import frames.Courses;
import frames.Sections;
import frames.Masterlist;


@SuppressWarnings("serial")
public class Main extends JFrame {
	
	public static final String SYSTEM_NAME = "Cavite State University Enrollment System";

	private JPanel contentPane, menuPanel, displayPanel;
	private JButton btnDashboard, btnCourseList, btnViewSection, btnManageStudents, btnStudentMasterList, btnLogout;
	private JLabel adminIcon, lblWelcomeAdmin, menuButton, banner, lblBackgroundImage, lblVerticalBanner;
	
	private boolean x = false;
	private Utility util;

	public Main() {
		Database dtb = new Database();
		util = new Utility(dtb);

		Sections sl = new Sections(util, dtb);
		Courses cl = new Courses(util, dtb);
		Dashboard db = new Dashboard(dtb);
		Manage as = new Manage(util, dtb);
		Masterlist su = new Masterlist(util, dtb);
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images\\logo.png"));
		setTitle(Main.SYSTEM_NAME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1227, 724);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(10, 163, 35));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		menuPanel = new JPanel();	//Eto ung sliding menu lods
		menuPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		menuPanel.setBackground(new Color(34, 139, 34));
		menuPanel.setBounds(0, 126, 0, 558); //0 default width, 247 width pag clinick
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);
		
		displayPanel = new JPanel();  				//Dito ilalabas yung ibang mga frames pag clinick mo mga buttons
		displayPanel.setOpaque(false);
		displayPanel.setBounds(257, 126, 954, 558);
		contentPane.add(displayPanel);
		displayPanel.setLayout(null);
		displayPanel.add(db);
		displayPanel.add(cl);
		displayPanel.add(sl);
		displayPanel.add(as);
		displayPanel.add(su);
		
		adminIcon = new JLabel("");	//Admin Icon lods
		Image adminPicture = new ImageIcon("images\\admin.png").getImage().getScaledInstance(76, 65, Image.SCALE_SMOOTH);
		ImageIcon adminPictureScaled = new ImageIcon(adminPicture);
		adminIcon.setIcon(adminPictureScaled);
		adminIcon.setBounds(77, 11, 87, 68);
		menuPanel.add(adminIcon);
		
		lblWelcomeAdmin = new JLabel("Welcome Administrator");
		lblWelcomeAdmin.setForeground(new Color(245, 222, 179));
		lblWelcomeAdmin.setFont(new Font("Arial", Font.PLAIN, 18));
		lblWelcomeAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeAdmin.setBounds(0, 90, 0, 26);
		menuPanel.add(lblWelcomeAdmin);
		
		btnLogout = new JButton("Sign Out");
		btnLogout.setFocusable(false);
		btnLogout.setBackground(Color.WHITE);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Image logoutIcon = new ImageIcon("images\\check.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);  //Icon lang to dong
				ImageIcon logoutIconScaled = new ImageIcon(logoutIcon);
			    
				int response = JOptionPane.showConfirmDialog(null, "Do you want to Sign Out?", "Confirm Sign Out", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, logoutIconScaled); // eto reposnse dong kung lologout
				if(response==JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "You have successfully logout!", "Message", JOptionPane.INFORMATION_MESSAGE, logoutIconScaled);
					dispose();
					Login login = new Login();
					login.setVisible(true);
				}
			}
		});
		btnLogout.setFont(new Font("Rockwell", Font.BOLD, 16));
		Image logoutIcon = new ImageIcon("images\\logout.png").getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon logoutIconScaled = new ImageIcon(logoutIcon);
		btnLogout.setIcon(logoutIconScaled);
		btnLogout.setBounds(10, 499, 227, 34);
		menuPanel.add(btnLogout);
		
		btnDashboard = new JButton("Dashboard");			//Dashboard button
		btnDashboard.setFocusable(false);
		btnDashboard.setBackground(Color.WHITE);
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//function ng dashboard
				as.setVisible(false);
				sl.setVisible(false);
				cl.setVisible(false);
				su.setVisible(false);
				db.setVisible(true);
				slidingMenu();
			}
		});
		btnDashboard.setFont(new Font("Rockwell", Font.BOLD, 16));
		btnDashboard.setBounds(10, 138, 227, 34);
		menuPanel.add(btnDashboard);
		
		btnCourseList = new JButton("View Courses");					//create course button
		btnCourseList.setFocusable(false);
		btnCourseList.setBackground(Color.WHITE);
		btnCourseList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				//create course function
				as.setVisible(false);
				db.setVisible(false);
				sl.setVisible(false);
				su.setVisible(false);
				cl.setVisible(true);
				slidingMenu();
			}
		});
		btnCourseList.setFont(new Font("Rockwell", Font.BOLD, 16));
		btnCourseList.setBounds(10, 263, 227, 34);
		menuPanel.add(btnCourseList);
		
		btnViewSection = new JButton("View Sections");
		btnViewSection.setFocusable(false);
		btnViewSection.setBackground(Color.WHITE);
		btnViewSection.addActionListener(new ActionListener() {			//function ng create section
			public void actionPerformed(ActionEvent e) {
				as.setVisible(false);
				db.setVisible(false);
				cl.setVisible(false);
				su.setVisible(false);
				sl.setVisible(true);
				slidingMenu();	

			}
		});
		btnViewSection.setFont(new Font("Rockwell", Font.BOLD, 16));
		btnViewSection.setBounds(10, 308, 227, 34);
		menuPanel.add(btnViewSection);
		
		btnManageStudents = new JButton("Manage Students");
		btnManageStudents.setFocusable(false);
		btnManageStudents.setBackground(Color.WHITE);
		btnManageStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.setVisible(false);
				db.setVisible(false);
				sl.setVisible(false);
				su.setVisible(false);
				as.setVisible(true);
				slidingMenu();
								
			}
		});
		btnManageStudents.setFont(new Font("Rockwell", Font.BOLD, 16));
		btnManageStudents.setBounds(10, 183, 227, 34);
		menuPanel.add(btnManageStudents);
		
		btnStudentMasterList = new JButton("Student Masterlist");
		btnStudentMasterList.setFocusable(false);
		btnStudentMasterList.setBackground(Color.WHITE);
		btnStudentMasterList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				as.setVisible(false);
				db.setVisible(false);
				cl.setVisible(false);
				sl.setVisible(false);
				su.setVisible(true);
				slidingMenu();	
			}
		});
		btnStudentMasterList.setFont(new Font("Rockwell", Font.BOLD, 16));
		btnStudentMasterList.setBounds(10, 380, 227, 34);
		menuPanel.add(btnStudentMasterList);
		
		
		menuButton = new JLabel("");
		menuButton.setBackground(Color.GREEN);
		menuButton.setForeground(Color.YELLOW);
		menuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// Action ng menu button
		menuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				slidingMenu();
			}	
			
		});
		
		menuButton.setBounds(10, 60, 61, 59);
		contentPane.add(menuButton);
		Image menuIcon = new ImageIcon("images\\menu.png").getImage().getScaledInstance(61, 59, Image.SCALE_SMOOTH);
		ImageIcon menuIconScaled = new ImageIcon(menuIcon);
		menuButton.setIcon(menuIconScaled);
		
		banner = new JLabel("");						//Banner lods
		banner.setBounds(0, 0, 1221, 59);
		Image cvsuBanner = new ImageIcon("images\\banner.png").getImage().getScaledInstance(1221, 59, Image.SCALE_SMOOTH);
		ImageIcon cvsuBannerScaled = new ImageIcon(cvsuBanner);
		banner.setIcon(cvsuBannerScaled);
		contentPane.add(banner);
		
		lblBackgroundImage = new JLabel("");
		lblBackgroundImage.setBounds(257, 126, 954, 558);
		Image bgImg = new ImageIcon("images\\aerialView.png").getImage().getScaledInstance(lblBackgroundImage.getWidth(), lblBackgroundImage.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon bgImgScaled = new ImageIcon(bgImg);
		lblBackgroundImage.setIcon(bgImgScaled);
		contentPane.add(lblBackgroundImage);
		
		lblVerticalBanner = new JLabel("");
		lblVerticalBanner.setBounds(0, 126, 247, 558);
		Image bannerVertical = new ImageIcon("images\\bannerVertical.png").getImage().getScaledInstance(lblVerticalBanner.getWidth(), lblVerticalBanner.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon bannerVerticalScaled = new ImageIcon(bannerVertical);
		lblVerticalBanner.setIcon(bannerVerticalScaled);
		contentPane.add(lblVerticalBanner);

		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public void slidingMenu() {
		if (x == false) {
//			Thread th = new Thread() {
//				public void run() {
//					try {
//						for(int a=0; a<=247; a++) {
//							Thread.sleep(5);
//							a++; a++;
//							menuPanel.setBounds(0, 126, a, 558);
//							x = true;
//						}
//					} catch(Exception e) {
//						System.out.print(e);
//					}		
//				}		
//			};
//			th.start();
			menuPanel.setBounds(0, 126, 247, 558);
			x = true;
		} else {
			x = false;
//				Thread th = new Thread() {
//				public void run() {
//					try {
//						for(int a=247; a>=0; a--) {
//							Thread.sleep(5);
//							a--; a--;
//							menuPanel.setBounds(0, 126, a, 558);
//						}
//					} catch(Exception e) {
//						System.out.print(e);
//					}		
//				}		
//			};
//			th.start();
			menuPanel.setBounds(0, 126, 0, 558);
		}
	}

}
