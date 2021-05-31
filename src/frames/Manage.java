package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import util.Database;
import util.Utility;

@SuppressWarnings("serial")
public class Manage extends JInternalFrame {

	private JPanel formsPanel;
	private JTextField txtFormContactNumber, txtFormEmailAddress, txtFormGuardianName;
	private JTextField txtFormStudentNumber;
	private JLabel lblPersonalInformation, lblLastName, lblGender, lblCivilStatus, 
		lblContactNumber, lblEmailAddress, lblGuardianInformation, lblGuardianName;
	private JLabel lblGeneralInformation, lblCourse, lblFormYearLevel, lblSection;
	private JLabel lblFormStudentNumber;
	private JComboBox<Object> comboCourse;
	private JComboBox<String> comboYearLevel, comboFormGender, comboSection;
	private JComboBox<String> comboCivilStatus;
	private JComboBox<String> comboOperations;
	private JSpinner spinnerBirthday;
	private JButton btnFormAdd;
	private JTextField txtLastName;
	private JTextField txtFirstName;
	private JTextField txtMi;
	private JTextField textFormCourseName;
	private JLabel lblSelectOperation;
	private JLabel lblStudentImage;
	private JLabel lblBirthday;
	
	public Manage(Utility util, Database dtb) {
		setTitle("Manage Students");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		setSize(949,558);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 933, 528);
		
		getContentPane().add(tabbedPane);
		
		formsPanel = new JPanel();
		formsPanel.setBackground(new Color(54, 134, 0));
		tabbedPane.addTab("Forms", null, formsPanel, null);
		tabbedPane.setBackgroundAt(0, Color.GREEN);
		formsPanel.setLayout(null);
		
		lblPersonalInformation = new JLabel("Personal Information:\r\n");
		lblPersonalInformation.setBounds(10, 11, 169, 32);
		lblPersonalInformation.setForeground(Color.BLACK);
		lblPersonalInformation.setFont(new Font("Arial", Font.BOLD, 17));
		formsPanel.add(lblPersonalInformation);
		
		lblLastName = new JLabel("Full Name (Last, First, MI)");
		lblLastName.setBounds(10, 48, 214, 32);
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setFont(new Font("Arial", Font.BOLD, 17));
		formsPanel.add(lblLastName);
		
		lblGender = new JLabel("Gender");
		lblGender.setBounds(41, 91, 58, 32);
		lblGender.setForeground(Color.WHITE);
		lblGender.setFont(new Font("Arial", Font.BOLD, 17));
		formsPanel.add(lblGender);
		
		lblCivilStatus = new JLabel("Civil Status");
		lblCivilStatus.setBounds(425, 91, 89, 32);
		lblCivilStatus.setForeground(Color.WHITE);
		lblCivilStatus.setFont(new Font("Arial", Font.BOLD, 17));
		formsPanel.add(lblCivilStatus);
		
		lblContactNumber = new JLabel("Contact Number");
		lblContactNumber.setBounds(10, 134, 134, 32);
		lblContactNumber.setForeground(Color.WHITE);
		lblContactNumber.setFont(new Font("Arial", Font.BOLD, 17));
		formsPanel.add(lblContactNumber);
		
		txtFormContactNumber = new JTextField();
		txtFormContactNumber.setBounds(158, 134, 237, 32);
		txtFormContactNumber.setFont(new Font("Arial", Font.PLAIN, 16));
		txtFormContactNumber.setColumns(10);
		formsPanel.add(txtFormContactNumber);
		
		lblEmailAddress = new JLabel("Email Address");
		lblEmailAddress.setBounds(405, 134, 117, 32);
		lblEmailAddress.setForeground(Color.WHITE);
		lblEmailAddress.setFont(new Font("Arial", Font.BOLD, 17));
		formsPanel.add(lblEmailAddress);
		
		txtFormEmailAddress = new JTextField();
		txtFormEmailAddress.setBounds(532, 134, 185, 32);
		txtFormEmailAddress.setFont(new Font("Arial", Font.PLAIN, 16));
		txtFormEmailAddress.setColumns(10);
		formsPanel.add(txtFormEmailAddress);
		
		comboFormGender = new JComboBox<>();
		comboFormGender.setBounds(158, 93, 237, 29);
		comboFormGender.setModel(new DefaultComboBoxModel<String>(new String[] {"Male", "Female", "Other"}));
		comboFormGender.setFont(new Font("Arial", Font.PLAIN, 16));
		formsPanel.add(comboFormGender);
		
		lblGuardianInformation = new JLabel("Guardian Information:");
		lblGuardianInformation.setBounds(10, 227, 176, 32);
		lblGuardianInformation.setForeground(Color.BLACK);
		lblGuardianInformation.setFont(new Font("Arial", Font.BOLD, 17));
		formsPanel.add(lblGuardianInformation);
		
		lblGuardianName = new JLabel("Guardian Name");
		lblGuardianName.setBounds(10, 270, 124, 32);
		lblGuardianName.setForeground(Color.WHITE);
		lblGuardianName.setFont(new Font("Arial", Font.BOLD, 17));
		formsPanel.add(lblGuardianName);
		
		txtFormGuardianName = new JTextField();
		txtFormGuardianName.setBounds(158, 270, 559, 32);
		txtFormGuardianName.setFont(new Font("Arial", Font.PLAIN, 16));
		txtFormGuardianName.setColumns(10);
		formsPanel.add(txtFormGuardianName);
		
		lblGeneralInformation = new JLabel("General Information:");
		lblGeneralInformation.setBounds(10, 313, 176, 32);
		lblGeneralInformation.setForeground(Color.BLACK);
		lblGeneralInformation.setFont(new Font("Arial", Font.BOLD, 17));
		formsPanel.add(lblGeneralInformation);
		
		lblCourse = new JLabel("Course");
		lblCourse.setBounds(69, 385, 63, 32);
		lblCourse.setForeground(Color.WHITE);
		lblCourse.setFont(new Font("Arial", Font.BOLD, 17));
		formsPanel.add(lblCourse);
		
		lblFormYearLevel = new JLabel("Year Level");
		lblFormYearLevel.setBounds(454, 347, 89, 32);
		lblFormYearLevel.setForeground(Color.WHITE);
		lblFormYearLevel.setFont(new Font("Arial", Font.BOLD, 17));
		formsPanel.add(lblFormYearLevel);
		
		lblSection = new JLabel("Section");
		lblSection.setBounds(462, 385, 63, 32);
		lblSection.setForeground(Color.WHITE);
		lblSection.setFont(new Font("Arial", Font.BOLD, 17));
		formsPanel.add(lblSection);
		
		comboCourse = new JComboBox<>();
		comboCourse.setModel(new DefaultComboBoxModel<Object>(dtb.fetchCourseIDs()));
		comboCourse.setBounds(158, 387, 286, 29);
		comboCourse.setFont(new Font("Arial", Font.PLAIN, 16));
		formsPanel.add(comboCourse);
		
		lblFormStudentNumber = new JLabel("Student Number");
		lblFormStudentNumber.setForeground(Color.WHITE);
		lblFormStudentNumber.setFont(new Font("Arial", Font.BOLD, 17));
		lblFormStudentNumber.setBounds(10, 347, 134, 32);
		formsPanel.add(lblFormStudentNumber);
		
		txtFormStudentNumber = new JTextField(Long.toString(util.generateStudentID()));
		txtFormStudentNumber.setEditable(false);
		txtFormStudentNumber.setFont(new Font("Arial", Font.PLAIN, 16));
		txtFormStudentNumber.setColumns(10);
		txtFormStudentNumber.setBounds(158, 347, 286, 32);
		formsPanel.add(txtFormStudentNumber);
		
		btnFormAdd = new JButton("Confirm");
		btnFormAdd.setFocusable(false);
		btnFormAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnFormAdd.setBounds(739, 134, 173, 33);
		formsPanel.add(btnFormAdd);
		
		comboSection = new JComboBox<>();
		comboSection.setFont(new Font("Arial", Font.PLAIN, 16));
		comboSection.setModel(new DefaultComboBoxModel<String>(util.sections));
		comboSection.setBounds(543, 389, 174, 29);
		formsPanel.add(comboSection);
		
		comboYearLevel = new JComboBox<>();
		comboYearLevel.setFont(new Font("Arial", Font.PLAIN, 16));
		comboYearLevel.setModel(new DefaultComboBoxModel<String>(util.yearLevels));
		comboYearLevel.setBounds(543, 347, 174, 29);
		formsPanel.add(comboYearLevel);
		
		comboCivilStatus = new JComboBox<>();
		comboCivilStatus.setFont(new Font("Arial", Font.PLAIN, 16));
		comboCivilStatus.setModel(new DefaultComboBoxModel<String>(new String[] {"Single", "Married", "Widow"}));
		comboCivilStatus.setBounds(532, 91, 185, 29);
		formsPanel.add(comboCivilStatus);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Arial", Font.PLAIN, 16));
		txtLastName.setColumns(10);
		txtLastName.setBounds(219, 48, 175, 32);
		formsPanel.add(txtLastName);
		
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Arial", Font.PLAIN, 16));
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(405, 48, 226, 32);
		formsPanel.add(txtFirstName);
		
		txtMi = new JTextField();
		txtMi.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMi.setColumns(10);
		txtMi.setBounds(641, 48, 75, 32);
		formsPanel.add(txtMi);
		
		textFormCourseName = new JTextField(dtb.fetchCourseName(comboCourse.getSelectedItem().toString()));
		textFormCourseName.setEditable(false);
		textFormCourseName.setFont(new Font("Arial", Font.PLAIN, 16));
		textFormCourseName.setColumns(10);
		textFormCourseName.setBounds(158, 428, 559, 32);
		formsPanel.add(textFormCourseName);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setForeground(Color.WHITE);
		lblCourseName.setFont(new Font("Arial", Font.BOLD, 17));
		lblCourseName.setBounds(28, 428, 106, 32);
		formsPanel.add(lblCourseName);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(727, 48, 2, 420);
		formsPanel.add(separator);
		
		lblSelectOperation = new JLabel("Select Operation:");
		lblSelectOperation.setForeground(Color.WHITE);
		lblSelectOperation.setFont(new Font("Arial", Font.BOLD, 13));
		lblSelectOperation.setBounds(740, 48, 117, 32);
		formsPanel.add(lblSelectOperation);

		comboOperations = new JComboBox<>();
		comboOperations.setFocusable(false);
		comboOperations.setModel(new DefaultComboBoxModel<String>(new String[] {"Enroll", "Update", "Remove"}));
		comboOperations.setFont(new Font("Arial", Font.PLAIN, 16));
		comboOperations.setBounds(739, 91, 173, 32);
		formsPanel.add(comboOperations);
		
		lblStudentImage = new JLabel("");
		lblStudentImage.setBounds(739, 191, 173, 269);
		Image studentIcon = new ImageIcon("images\\studentIcon.png").getImage().getScaledInstance(lblStudentImage.getWidth(), lblStudentImage.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon studentIconScaled = new ImageIcon(studentIcon);
		lblStudentImage.setIcon(studentIconScaled);
		formsPanel.add(lblStudentImage);
		
		lblBirthday = new JLabel("Birthday");
		lblBirthday.setHorizontalAlignment(SwingConstants.CENTER);
		lblBirthday.setForeground(Color.WHITE);
		lblBirthday.setFont(new Font("Arial", Font.BOLD, 17));
		lblBirthday.setBounds(10, 177, 134, 32);
		formsPanel.add(lblBirthday);
		
		spinnerBirthday = new JSpinner();
		spinnerBirthday.setFont(new Font("Arial", Font.PLAIN, 16));
		spinnerBirthday.setModel(new SpinnerDateModel(new Date(946656000000L), new Date(-2196835200000L), new Date(1621612800000L), Calendar.DAY_OF_YEAR));
		spinnerBirthday.setBounds(157, 177, 238, 32);
		formsPanel.add(spinnerBirthday);

		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				dispose();
			}
		});
		comboOperations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = comboOperations.getSelectedIndex();
				if (selected == 0) {
					// enroll
					
				} else if (selected == 1) {
					// update
					
				} else if (selected == 2) {
					// remove
					
				}
			}
		});
		btnFormAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		comboCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String courseID = comboCourse.getSelectedItem().toString();
				textFormCourseName.setText(dtb.fetchCourseName(courseID));
			}
		});
	}

}
