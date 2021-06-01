package frames;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import util.Database;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;


@SuppressWarnings("serial")
public class Dashboard extends JInternalFrame {

	
	public Dashboard(Database dtb) {
		setBorder(null);
		setTitle("Dashboard");
		setClosable(true);
		getContentPane().setLayout(null);
		setBounds(0, 0, 954, 558);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(53, 134, 0));
		panel.setBounds(0, 0, 964, 558);
		
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setFont(new Font("Arial", Font.BOLD, 40));
		lblDashboard.setForeground(new Color(255, 255, 255));
		lblDashboard.setBounds(93, 28, 216, 52);
		panel.add(lblDashboard);
		
		JLabel dashboardIcon = new JLabel("");
		dashboardIcon.setBounds(10, 29, 73, 51);
		Image dbIcon = new ImageIcon("images\\dashboard.png").getImage().getScaledInstance(73, 53, Image.SCALE_SMOOTH);
		ImageIcon dbIconScaled = new ImageIcon(dbIcon);
		dashboardIcon.setIcon(dbIconScaled);
		panel.add(dashboardIcon);
		
		JPanel bannerPanel = new JPanel();
		bannerPanel.setBackground(new Color(220, 220, 220));
		bannerPanel.setBounds(0, 104, 956, 52);
		panel.add(bannerPanel);
		bannerPanel.setLayout(null);
		
		JLabel lblAcademicYear = new JLabel("Academic Year:");
		lblAcademicYear.setBounds(10, 11, 155, 41);
		bannerPanel.add(lblAcademicYear);
		lblAcademicYear.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel lblYear = new JLabel("2020 - 2021");
		lblYear.setBounds(164, 11, 112, 41);
		bannerPanel.add(lblYear);
		lblYear.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Statistic Overview");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(319, 44, 191, 34);
		panel.add(lblNewLabel_1);
		
		JPanel totalStudentPanel = new JPanel();
		totalStudentPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		totalStudentPanel.setBackground(new Color(30, 144, 255));
		totalStudentPanel.setBounds(61, 257, 233, 159);
		panel.add(totalStudentPanel);
		totalStudentPanel.setLayout(null);
		
		JLabel totalStudentIcon = new JLabel("");
		totalStudentIcon.setBounds(20, 30, 66, 57);
		Image studentIcon = new ImageIcon("images\\Student.png").getImage().getScaledInstance(66, 57, Image.SCALE_SMOOTH);
		ImageIcon studentIconScaled = new ImageIcon(studentIcon);
		totalStudentIcon.setIcon(studentIconScaled);
		totalStudentPanel.add(totalStudentIcon);
		
		JLabel lblTotalStudentName = new JLabel("Total Students");
		lblTotalStudentName.setForeground(new Color(255, 255, 224));
		lblTotalStudentName.setFont(new Font("Arial", Font.BOLD, 18));
		lblTotalStudentName.setBounds(93, 110, 126, 26);
		totalStudentPanel.add(lblTotalStudentName);
		
		JLabel lblTotalStudentCount = new JLabel(Integer.toString(dtb.countStudents()));
		lblTotalStudentCount.setForeground(new Color(255, 255, 224));
		lblTotalStudentCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalStudentCount.setFont(new Font("Arial", Font.PLAIN, 50));
		lblTotalStudentCount.setBounds(129, 30, 93, 57);
		totalStudentPanel.add(lblTotalStudentCount);
		
		JPanel totalCoursePanel = new JPanel();
		totalCoursePanel.setBounds(355, 257, 233, 159);
		panel.add(totalCoursePanel);
		totalCoursePanel.setLayout(null);
		totalCoursePanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		totalCoursePanel.setBackground(new Color(30, 144, 255));
		
		JLabel totalCourseIcon = new JLabel("");
		totalCourseIcon.setBounds(22, 30, 66, 57);
		Image courseIcon = new ImageIcon("images\\Course.png").getImage().getScaledInstance(66, 57, Image.SCALE_SMOOTH);
		ImageIcon courseIconScaled = new ImageIcon(courseIcon);
		totalCourseIcon.setIcon(courseIconScaled);
		totalCoursePanel.add(totalCourseIcon);
		
		JLabel lblTotalCourse = new JLabel("Total Course");
		lblTotalCourse.setForeground(new Color(255, 255, 224));
		lblTotalCourse.setFont(new Font("Arial", Font.BOLD, 18));
		lblTotalCourse.setBounds(112, 109, 111, 26);
		totalCoursePanel.add(lblTotalCourse);

		JLabel lblTotalCourseCount = new JLabel(Integer.toString(dtb.countCourses()));
		lblTotalCourseCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalCourseCount.setForeground(new Color(255, 255, 224));
		lblTotalCourseCount.setFont(new Font("Arial", Font.PLAIN, 50));
		lblTotalCourseCount.setBounds(130, 30, 93, 57);
		totalCoursePanel.add(lblTotalCourseCount);
		
		JPanel totalSubjectPanel = new JPanel();
		totalSubjectPanel.setLayout(null);
		totalSubjectPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		totalSubjectPanel.setBackground(new Color(30, 144, 255));
		totalSubjectPanel.setBounds(649, 257, 233, 159);
		panel.add(totalSubjectPanel);
		
		JLabel totalSubjectIcon = new JLabel("");
		Image subjectIcon = new ImageIcon("images\\Books.png").getImage().getScaledInstance(66, 57, Image.SCALE_SMOOTH);
		ImageIcon subjectIconScaled = new ImageIcon(subjectIcon);
		totalSubjectIcon.setIcon(subjectIconScaled);
		totalSubjectIcon.setBounds(26, 30, 66, 57);
		totalSubjectPanel.add(totalSubjectIcon);
		
		JLabel lblTotalSubjects = new JLabel("Total Subjects");
		lblTotalSubjects.setForeground(new Color(255, 255, 224));
		lblTotalSubjects.setFont(new Font("Arial", Font.BOLD, 18));
		lblTotalSubjects.setBounds(99, 109, 124, 26);
		totalSubjectPanel.add(lblTotalSubjects);

		JLabel lblTotalSubjectsCount = new JLabel(Integer.toString(dtb.countSubjects()));
		lblTotalSubjectsCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalSubjectsCount.setForeground(new Color(255, 255, 224));
		lblTotalSubjectsCount.setFont(new Font("Arial", Font.PLAIN, 50));
		lblTotalSubjectsCount.setBounds(130, 30, 93, 57);
		totalSubjectPanel.add(lblTotalSubjectsCount);
		
		JButton btnRefresh = new JButton("Refresh Statistics");
		btnRefresh.setFocusable(false);
		btnRefresh.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setBackground(new Color(53, 134, 0));
		btnRefresh.setMargin(new Insets(2, 2, 2, 2));
		btnRefresh.setFont(new Font("Arial", Font.BOLD, 16));
		btnRefresh.setBounds(61, 178, 821, 44);
		panel.add(btnRefresh);

		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				dispose();
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTotalStudentCount.setText(Integer.toString(dtb.countStudents()));
				lblTotalCourseCount.setText(Integer.toString(dtb.countCourses()));
				lblTotalSubjectsCount.setText(Integer.toString(dtb.countSubjects()));
			}
		});
	}
}
