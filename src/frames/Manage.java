package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import main.Main;
import util.Database;
import util.Utility;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	
	private Utility util;
	
	public Manage(Utility util, Database dtb) {
		setBorder(null);
		this.util = util;
		
		setTitle("Manage Students");
		setClosable(true);
		getContentPane().setLayout(null);
		setBounds(0, 0, 954, 558);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 964, 558);
		
		getContentPane().add(tabbedPane);
		
		formsPanel = new JPanel();
		formsPanel.setBackground(new Color(54, 134, 0));
		tabbedPane.addTab("Forms", null, formsPanel, null);
		tabbedPane.setBackgroundAt(0, Color.GREEN);
		formsPanel.setLayout(null);
		
		lblPersonalInformation = new JLabel("Personal Information:\r\n");
		lblPersonalInformation.setBounds(10, 11, 169, 32);
		lblPersonalInformation.setForeground(Color.BLACK);
		lblPersonalInformation.setFont(new Font("Arial", Font.BOLD, 16));
		formsPanel.add(lblPersonalInformation);
		
		lblLastName = new JLabel("Full Name (Last, First, MI)");
		lblLastName.setBounds(10, 48, 214, 32);
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setFont(new Font("Arial", Font.BOLD, 16));
		formsPanel.add(lblLastName);
		
		lblGender = new JLabel("Gender");
		lblGender.setBounds(41, 91, 58, 32);
		lblGender.setForeground(Color.WHITE);
		lblGender.setFont(new Font("Arial", Font.BOLD, 16));
		formsPanel.add(lblGender);
		
		lblCivilStatus = new JLabel("Civil Status");
		lblCivilStatus.setBounds(425, 91, 89, 32);
		lblCivilStatus.setForeground(Color.WHITE);
		lblCivilStatus.setFont(new Font("Arial", Font.BOLD, 16));
		formsPanel.add(lblCivilStatus);
		
		lblContactNumber = new JLabel("Contact Number");
		lblContactNumber.setBounds(10, 134, 134, 32);
		lblContactNumber.setForeground(Color.WHITE);
		lblContactNumber.setFont(new Font("Arial", Font.BOLD, 16));
		formsPanel.add(lblContactNumber);
		
		txtFormContactNumber = new JTextField();
		txtFormContactNumber.setBounds(158, 134, 237, 32);
		txtFormContactNumber.setFont(new Font("Arial", Font.PLAIN, 16));
		txtFormContactNumber.setColumns(10);
		formsPanel.add(txtFormContactNumber);
		
		lblEmailAddress = new JLabel("Email Address");
		lblEmailAddress.setBounds(405, 134, 117, 32);
		lblEmailAddress.setForeground(Color.WHITE);
		lblEmailAddress.setFont(new Font("Arial", Font.BOLD, 16));
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
		lblGuardianInformation.setFont(new Font("Arial", Font.BOLD, 16));
		formsPanel.add(lblGuardianInformation);
		
		lblGuardianName = new JLabel("Guardian Name");
		lblGuardianName.setBounds(10, 270, 124, 32);
		lblGuardianName.setForeground(Color.WHITE);
		lblGuardianName.setFont(new Font("Arial", Font.BOLD, 16));
		formsPanel.add(lblGuardianName);
		
		txtFormGuardianName = new JTextField();
		txtFormGuardianName.setBounds(158, 270, 559, 32);
		txtFormGuardianName.setFont(new Font("Arial", Font.PLAIN, 16));
		txtFormGuardianName.setColumns(10);
		formsPanel.add(txtFormGuardianName);
		
		lblGeneralInformation = new JLabel("General Information:");
		lblGeneralInformation.setBounds(10, 313, 176, 32);
		lblGeneralInformation.setForeground(Color.BLACK);
		lblGeneralInformation.setFont(new Font("Arial", Font.BOLD, 16));
		formsPanel.add(lblGeneralInformation);
		
		lblCourse = new JLabel("Course");
		lblCourse.setBounds(69, 385, 63, 32);
		lblCourse.setForeground(Color.WHITE);
		lblCourse.setFont(new Font("Arial", Font.BOLD, 16));
		formsPanel.add(lblCourse);
		
		lblFormYearLevel = new JLabel("Year Level");
		lblFormYearLevel.setBounds(454, 347, 89, 32);
		lblFormYearLevel.setForeground(Color.WHITE);
		lblFormYearLevel.setFont(new Font("Arial", Font.BOLD, 16));
		formsPanel.add(lblFormYearLevel);
		
		lblSection = new JLabel("Section");
		lblSection.setBounds(462, 385, 63, 32);
		lblSection.setForeground(Color.WHITE);
		lblSection.setFont(new Font("Arial", Font.BOLD, 16));
		formsPanel.add(lblSection);
		
		comboCourse = new JComboBox<>();
		comboCourse.setModel(new DefaultComboBoxModel<Object>(dtb.fetchCourseIDs()));
		comboCourse.setBounds(158, 387, 286, 29);
		comboCourse.setFont(new Font("Arial", Font.PLAIN, 16));
		formsPanel.add(comboCourse);
		
		lblFormStudentNumber = new JLabel("Student Number");
		lblFormStudentNumber.setForeground(Color.WHITE);
		lblFormStudentNumber.setFont(new Font("Arial", Font.BOLD, 16));
		lblFormStudentNumber.setBounds(10, 347, 134, 32);
		formsPanel.add(lblFormStudentNumber);
		
		txtFormStudentNumber = new JTextField(Long.toString(util.generateStudentID()));
		txtFormStudentNumber.setEditable(false);
		txtFormStudentNumber.setFont(new Font("Arial", Font.PLAIN, 16));
		txtFormStudentNumber.setColumns(10);
		txtFormStudentNumber.setBounds(158, 347, 286, 32);
		formsPanel.add(txtFormStudentNumber);
		
		btnFormAdd = new JButton("Confirm");
		btnFormAdd.setBackground(Color.WHITE);
		btnFormAdd.setFocusable(false);
		btnFormAdd.setFont(new Font("Arial", Font.PLAIN, 16));
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
		txtLastName.setBounds(219, 48, 200, 32);
		formsPanel.add(txtLastName);
		
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Arial", Font.PLAIN, 16));
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(423, 48, 208, 32);
		formsPanel.add(txtFirstName);
		
		txtMi = new JTextField();
		txtMi.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMi.setColumns(10);
		txtMi.setBounds(635, 48, 82, 32);
		formsPanel.add(txtMi);
		
		textFormCourseName = new JTextField(dtb.fetchCourseName(comboCourse.getSelectedItem().toString()));
		textFormCourseName.setEditable(false);
		textFormCourseName.setFont(new Font("Arial", Font.PLAIN, 16));
		textFormCourseName.setColumns(10);
		textFormCourseName.setBounds(158, 428, 559, 32);
		formsPanel.add(textFormCourseName);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setForeground(Color.WHITE);
		lblCourseName.setFont(new Font("Arial", Font.BOLD, 16));
		lblCourseName.setBounds(28, 428, 106, 32);
		formsPanel.add(lblCourseName);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(727, 48, 2, 420);
		formsPanel.add(separator);
		
		lblSelectOperation = new JLabel("Select Operation:");
		lblSelectOperation.setForeground(Color.WHITE);
		lblSelectOperation.setFont(new Font("Arial", Font.BOLD, 16));
		lblSelectOperation.setBounds(740, 48, 172, 32);
		formsPanel.add(lblSelectOperation);

		comboOperations = new JComboBox<>();
		comboOperations.setFocusable(false);
		comboOperations.setModel(new DefaultComboBoxModel<String>(new String[] {"Enroll", "Update", "Remove"}));
		comboOperations.setFont(new Font("Arial", Font.PLAIN, 16));
		comboOperations.setBounds(739, 91, 173, 32);
		formsPanel.add(comboOperations);
		
		lblStudentImage = new JLabel("");
		lblStudentImage.setFont(new Font("Arial", Font.BOLD, 16));
		lblStudentImage.setBounds(739, 191, 173, 269);
		Image studentIcon = new ImageIcon("images\\studentIcon.png").getImage().getScaledInstance(lblStudentImage.getWidth(), lblStudentImage.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon studentIconScaled = new ImageIcon(studentIcon);
		lblStudentImage.setIcon(studentIconScaled);
		formsPanel.add(lblStudentImage);
		
		lblBirthday = new JLabel("Birthday");
		lblBirthday.setHorizontalAlignment(SwingConstants.CENTER);
		lblBirthday.setForeground(Color.WHITE);
		lblBirthday.setFont(new Font("Arial", Font.BOLD, 16));
		lblBirthday.setBounds(10, 177, 134, 32);
		formsPanel.add(lblBirthday);
		
		spinnerBirthday = new JSpinner();
		spinnerBirthday.setFont(new Font("Arial", Font.PLAIN, 16));
		spinnerBirthday.setModel(new SpinnerDateModel(new Date(946656000000L), new Date(-2196835200000L), new Date(1621612800000L), Calendar.DAY_OF_YEAR));
		spinnerBirthday.setBounds(157, 177, 238, 32);
		SimpleDateFormat model = new SimpleDateFormat("MM/dd/yyyy");
		spinnerBirthday.setEditor(new JSpinner.DateEditor(spinnerBirthday, model.toPattern()));
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
					clearFields(true);
					txtFormStudentNumber.setEditable(false);
					txtFormStudentNumber.setFocusable(false);
					txtLastName.setEditable(true);
					txtLastName.requestFocus();
					txtFirstName.setEditable(true);
					txtMi.setEditable(true);
					txtFormContactNumber.setEditable(true);
					txtFormEmailAddress.setEditable(true);
					txtFormGuardianName.setEditable(true);
					spinnerBirthday.setEnabled(true);
					comboCivilStatus.setEnabled(true);
					comboFormGender.setEnabled(true);
					comboYearLevel.setEnabled(true);
					comboCourse.setEnabled(true);
					comboSection.setEnabled(true);
				} else if (selected == 1) {
					// update
					clearFields(false);
					txtFormStudentNumber.setText("");
					txtFormStudentNumber.setEditable(true);
					txtFormStudentNumber.setFocusable(true);
					txtLastName.setEditable(false);
					txtFirstName.setEditable(false);
					txtMi.setEditable(false);
					txtFormContactNumber.setEditable(false);
					txtFormEmailAddress.setEditable(false);
					txtFormGuardianName.setEditable(false);
					spinnerBirthday.setEnabled(false);
					comboCivilStatus.setEnabled(false);
					comboFormGender.setEnabled(false);
					comboYearLevel.setEnabled(false);
					comboCourse.setEnabled(false);
					comboSection.setEnabled(false);
				} else if (selected == 2) {
					// remove
					clearFields(false);
					txtFormStudentNumber.setText("");
					txtFormStudentNumber.setEditable(true);
					txtFormStudentNumber.setFocusable(true);
					txtLastName.setEditable(false);
					txtFirstName.setEditable(false);
					txtMi.setEditable(false);
					txtFormContactNumber.setEditable(false);
					txtFormEmailAddress.setEditable(false);
					txtFormGuardianName.setEditable(false);
					spinnerBirthday.setEnabled(false);
					comboCivilStatus.setEnabled(false);
					comboFormGender.setEnabled(false);
					comboYearLevel.setEnabled(false);
					comboCourse.setEnabled(false);
					comboSection.setEnabled(false);
				}
			}
		});
		btnFormAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int selected = comboOperations.getSelectedIndex();
					long studentID = Long.parseLong(txtFormStudentNumber.getText());
					String courseID = comboCourse.getSelectedItem().toString();
					String sectionID = courseID + "-"
						+ comboYearLevel.getSelectedItem().toString()
						+ comboSection.getSelectedItem().toString();
					String lastName = txtLastName.getText();
					String firstName = txtFirstName.getText();
					String middleName = txtMi.getText();
					DateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
					Date parsedBirthday = parser.parse(spinnerBirthday.getValue().toString());
					DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String birthday = sdf.format(parsedBirthday);
					String gender = comboFormGender.getSelectedItem().toString();
					String contactNo = txtFormContactNumber.getText();
					String civilStatus = comboCivilStatus.getSelectedItem().toString();
					String email = txtFormEmailAddress.getText();
					String guardian = txtFormGuardianName.getText();
					
					if (selected == 0) {
						// enroll
						if (checkFields()) {
							dtb.insertStudent(new Object[] {
								studentID, sectionID, courseID, firstName, middleName, lastName,
								birthday, gender, contactNo, civilStatus, email, guardian
							});
							JOptionPane.showMessageDialog(null, 
								"Student (" + lastName + ") has been enrolled!", 
								"Success | " + Main.SYSTEM_NAME, 
								JOptionPane.INFORMATION_MESSAGE);
							clearFields(true);
						}
					} else if (selected == 1) {
						// update
						if (checkFields()) {
							dtb.updateStudent(new Object[] {
								studentID, sectionID, courseID, firstName, middleName, lastName,
								birthday, gender, contactNo, civilStatus, email, guardian
							});
							JOptionPane.showMessageDialog(null, 
								"Student (" + lastName + ")'s information has been updated!", 
								"Success | " + Main.SYSTEM_NAME, 
								JOptionPane.INFORMATION_MESSAGE);
							clearFields(false);
							txtFormStudentNumber.setText("");
						}
					} else if (selected == 2) {
						// remove
						int confirmation = JOptionPane.showConfirmDialog(null, 
							"This process is irreversible, are you sure you want to delete student (" + lastName + ")?",
							"Confirmation | " + Main.SYSTEM_NAME, JOptionPane.YES_NO_OPTION, 
							JOptionPane.WARNING_MESSAGE);
						if (confirmation == JOptionPane.YES_OPTION) {
							if (dtb.deleteStudent(studentID)) {
								JOptionPane.showMessageDialog(null, 
									"Student (" + lastName + ") has been deleted from the database.", 
									"Success | " + Main.SYSTEM_NAME, 
									JOptionPane.INFORMATION_MESSAGE);
								clearFields(false);
								txtFormStudentNumber.setText("");
							}
						}
					}
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
			}
		});
		comboCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String courseID = comboCourse.getSelectedItem().toString();
				textFormCourseName.setText(dtb.fetchCourseName(courseID));
			}
		});
		txtFormStudentNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getExtendedKeyCode() > 0)
					getStudentDetails();
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getExtendedKeyCode() > 0)
					getStudentDetails();
			}
			
			public void getStudentDetails() {
				try {
					Object[] student = dtb.fetchStudent(Long.parseLong(txtFormStudentNumber.getText()));
					if (student != null) {
						txtLastName.setText(student[5].toString());
						txtFirstName.setText(student[3].toString());
						txtMi.setText(student[4].toString());
						txtFormContactNumber.setText(student[8].toString());
						txtFormEmailAddress.setText(student[10].toString());
						txtFormGuardianName.setText(student[11].toString());
						comboCivilStatus.setSelectedItem(student[9]);
						comboFormGender.setSelectedItem(student[7]);
						
						Date birthday =new SimpleDateFormat("yyyy-MM-dd").parse(student[6].toString());
						spinnerBirthday.setModel(new SpinnerDateModel(birthday, new Date(-2196835200000L), new Date(1621612800000L), Calendar.DAY_OF_YEAR));
						SimpleDateFormat model = new SimpleDateFormat("MM/dd/yyyy");
						spinnerBirthday.setEditor(new JSpinner.DateEditor(spinnerBirthday, model.toPattern()));
						
						String section = student[1].toString();
						comboCourse.setSelectedItem(section.substring(0, 4));
						comboYearLevel.setSelectedItem(section.substring(5, 6));
						comboSection.setSelectedItem(section.substring(6));
						
						// opening fields
						if (comboOperations.getSelectedIndex() != 2) {
							txtLastName.setEditable(true);
							txtFirstName.setEditable(true);
							txtMi.setEditable(true);
							txtFormContactNumber.setEditable(true);
							txtFormEmailAddress.setEditable(true);
							txtFormGuardianName.setEditable(true);
							spinnerBirthday.setEnabled(true);
							comboCivilStatus.setEnabled(true);
							comboFormGender.setEnabled(true);
							comboYearLevel.setEnabled(true);
							comboCourse.setEnabled(true);
							comboSection.setEnabled(true);
						}
					} else throw new Exception();
				} catch (Exception e2) {
					clearFields(false);
				}
			}
		});
	}

	private boolean checkFields() {
		StringBuilder errors = new StringBuilder();
		
		if (txtLastName.getText().isBlank()) errors.append("• Last name field cannot be empty.\n");
		if (txtFirstName.getText().isBlank()) errors.append("• First name field cannot be empty.\n");
		if (txtFormContactNumber.getText().isBlank()) errors.append("• Contact field cannot be empty.\n");
		if (txtFormEmailAddress.getText().isBlank()) errors.append("• Email field cannot be empty.\n");
		
		try {
			DateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
			Date parsedBirthday = parser.parse(spinnerBirthday.getValue().toString());
			Calendar now = Calendar.getInstance();
			Calendar birthday = Calendar.getInstance();
			birthday.setTime(parsedBirthday);
			
			if (now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR) < 12) {
				errors.append("• You are very young to enter college (Minimum age is atleast 12).\n");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (errors.length() != 0) {
			JOptionPane.showMessageDialog(null, 
				"Please check the following:\n" + errors.toString(), 
				"Invalid Fields | " + Main.SYSTEM_NAME, 
				JOptionPane.WARNING_MESSAGE);
			return false;
		} else return true;
	}
	
	private void clearFields(boolean setID) {
		if (setID) txtFormStudentNumber.setText(Long.toString(util.generateStudentID()));
		txtLastName.setText("");
		txtFirstName.setText("");
		txtMi.setText("");
		txtFormContactNumber.setText("");
		txtFormEmailAddress.setText("");
		txtFormGuardianName.setText("");
		spinnerBirthday.setModel(new SpinnerDateModel(new Date(946656000000L), new Date(-2196835200000L), new Date(1621612800000L), Calendar.DAY_OF_YEAR));
		SimpleDateFormat model = new SimpleDateFormat("MM/dd/yyyy");
		spinnerBirthday.setEditor(new JSpinner.DateEditor(spinnerBirthday, model.toPattern()));
		comboCivilStatus.setSelectedIndex(0);
		comboFormGender.setSelectedIndex(0);
		comboYearLevel.setSelectedIndex(0);
		comboCourse.setSelectedIndex(0);
		comboSection.setSelectedIndex(0);
		
		if (!setID) {
			txtLastName.setEditable(false);
			txtFirstName.setEditable(false);
			txtMi.setEditable(false);
			txtFormContactNumber.setEditable(false);
			txtFormEmailAddress.setEditable(false);
			txtFormGuardianName.setEditable(false);
			spinnerBirthday.setEnabled(false);
			comboCivilStatus.setEnabled(false);
			comboFormGender.setEnabled(false);
			comboYearLevel.setEnabled(false);
			comboCourse.setEnabled(false);
			comboSection.setEnabled(false);
		}
	}
}
