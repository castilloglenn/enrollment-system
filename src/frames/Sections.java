package frames;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import util.Database;
import util.Utility;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Sections extends JInternalFrame {

	private JPanel panel;
	private JTable tblCreateSection;
	private JLabel lblCourseCode,lblYearLevel, lblSection;
	private JScrollPane scrollPane;
	private JComboBox<Object> comboCourseCode;
	private JComboBox<String> comboYearLevel;
	private JComboBox<String> comboSection;
	private JLabel lblCourseName;
	private JTextField textField;
	private JTabbedPane tabbedPane;
	
	private final String[] COLUMNS = {
		"Subject Code", "Subject Name", "Units", "Time", "Day"
	};
	
	private Utility util;
	private Database dtb;
	
	public Sections(Utility util, Database dtb) {
		this.util = util;
		this.dtb = dtb;

		setBorder(null);
		setTitle("View Sections");
		setClosable(true);
		getContentPane().setLayout(null);
		setBounds(0, 0, 954, 558);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 964, 558);
		getContentPane().add(tabbedPane);
		
		panel = new JPanel();
		panel.setBackground(new Color(53, 134, 0));
		panel.setBounds(0, 0, 945, 535);
		tabbedPane.addTab("Section List", null, panel, null);
		panel.setLayout(null);
		
		lblCourseCode = new JLabel("Course Code");
		lblCourseCode.setBounds(10, 27, 111, 32);
		lblCourseCode.setForeground(Color.WHITE);
		lblCourseCode.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblCourseCode);
		
		lblYearLevel = new JLabel("Year Level");
		lblYearLevel.setBounds(322, 27, 81, 32);
		lblYearLevel.setForeground(Color.WHITE);
		lblYearLevel.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblYearLevel);
		
		lblSection = new JLabel("Section");
		lblSection.setBounds(506, 27, 79, 32);
		lblSection.setForeground(Color.WHITE);
		lblSection.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblSection);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 924, 369);
		scrollPane.getViewport().setBackground(new Color(53, 134, 0));
		panel.add(scrollPane);
		
		comboCourseCode = new JComboBox<>();
		comboCourseCode.setModel(new DefaultComboBoxModel<Object>(dtb.fetchCourseIDs()));
		comboCourseCode.setFont(new Font("Arial", Font.PLAIN, 16));
		comboCourseCode.setBounds(127, 27, 176, 32);
		panel.add(comboCourseCode);
		
		comboYearLevel = new JComboBox<>();
		comboYearLevel.setFont(new Font("Arial", Font.PLAIN, 16));
		comboYearLevel.setModel(new DefaultComboBoxModel<String>(util.yearLevels));
		comboYearLevel.setBounds(413, 27, 83, 32);
		panel.add(comboYearLevel);
		
		comboSection = new JComboBox<>();
		comboSection.setFont(new Font("Arial", Font.PLAIN, 16));
		comboSection.setModel(new DefaultComboBoxModel<String>(util.sections));
		comboSection.setBounds(590, 27, 83, 32);
		panel.add(comboSection);
		
		tblCreateSection = new JTable();
		tblCreateSection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCreateSection.setModel(util.generateTable(
			dtb.fetchSubjectSchedule(
				comboCourseCode.getSelectedItem().toString() + "-" +
				comboYearLevel.getSelectedItem().toString() +
				comboSection.getSelectedItem().toString()
			), COLUMNS));
		util.tableCenter(tblCreateSection);
		tblCreateSection.setRowHeight(20);
		tblCreateSection.setFont(new Font("Arial", Font.PLAIN, 16));
		tblCreateSection.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 16));
		tblCreateSection.getColumnModel().getColumn(0).setMinWidth(50);
		tblCreateSection.getColumnModel().getColumn(1).setMinWidth(450);
		util.tableCenter(tblCreateSection);
		
		scrollPane.setViewportView(tblCreateSection);
		
		lblCourseName = new JLabel("Course Name");
		lblCourseName.setForeground(Color.WHITE);
		lblCourseName.setFont(new Font("Arial", Font.BOLD, 16));
		lblCourseName.setBounds(10, 70, 111, 32);
		panel.add(lblCourseName);
		
		textField = new JTextField(dtb.fetchCourseName(comboCourseCode.getSelectedItem().toString()));
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(127, 67, 546, 32);
		panel.add(textField);
		
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				dispose();
			}
		});
		comboCourseCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String courseID = comboCourseCode.getSelectedItem().toString();
				textField.setText(dtb.fetchCourseName(courseID));

				updateSchedule();
			}
		});
		comboYearLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSchedule();
			}
		});
		comboSection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSchedule();
			}
		});
	}
	
	public void updateSchedule() {
		tblCreateSection.setModel(util.generateTable(
			dtb.fetchSubjectSchedule(
				comboCourseCode.getSelectedItem().toString() + "-" +
				comboYearLevel.getSelectedItem().toString() +
				comboSection.getSelectedItem().toString()
			), COLUMNS));
		util.tableCenter(tblCreateSection);
		tblCreateSection.getColumnModel().getColumn(0).setMinWidth(50);
		tblCreateSection.getColumnModel().getColumn(1).setMinWidth(450);
	}
}
