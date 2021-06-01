package frames;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import util.Database;
import util.Utility;

@SuppressWarnings("serial")
public class Sections extends JInternalFrame {

	private JPanel panel;
	private JTable tblCreateSection;
	private JLabel lblCourseCode,lblYearLevel, lblSection;
	private JScrollPane scrollPane;
	private JComboBox<String> comboCourseCode;
	private JComboBox<String> comboYearLevel;
	private JComboBox<String> comboSection;
	private JLabel lblCourseName;
	private JTextField textField;
	
	public Sections(Utility util, Database dtb) {
		setClosable(true);
		setBounds(100, 100, 949, 565);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(53, 134, 0));
		panel.setBounds(0, 0, 945, 535);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblCourseCode = new JLabel("Course Code");
		lblCourseCode.setBounds(10, 27, 111, 32);
		lblCourseCode.setForeground(Color.WHITE);
		lblCourseCode.setFont(new Font("Arial", Font.BOLD, 17));
		panel.add(lblCourseCode);
		
		lblYearLevel = new JLabel("Year Level");
		lblYearLevel.setBounds(322, 27, 81, 32);
		lblYearLevel.setForeground(Color.WHITE);
		lblYearLevel.setFont(new Font("Arial", Font.BOLD, 17));
		panel.add(lblYearLevel);
		
		lblSection = new JLabel("Section");
		lblSection.setBounds(506, 27, 79, 32);
		lblSection.setForeground(Color.WHITE);
		lblSection.setFont(new Font("Arial", Font.BOLD, 17));
		panel.add(lblSection);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 913, 383);
		panel.add(scrollPane);
		
		tblCreateSection = new JTable();
		tblCreateSection.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		tblCreateSection.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Subject Code", "Subject Name", "Units", "Time", "Day"
			}
		));
		tblCreateSection.getColumnModel().getColumn(0).setResizable(false);
		tblCreateSection.getColumnModel().getColumn(1).setResizable(false);
		tblCreateSection.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblCreateSection.getColumnModel().getColumn(1).setMinWidth(125);
		tblCreateSection.getColumnModel().getColumn(2).setResizable(false);
		tblCreateSection.getColumnModel().getColumn(2).setPreferredWidth(25);
		tblCreateSection.getColumnModel().getColumn(3).setResizable(false);
		tblCreateSection.getColumnModel().getColumn(3).setPreferredWidth(50);
		tblCreateSection.getColumnModel().getColumn(4).setResizable(false);
		tblCreateSection.getColumnModel().getColumn(4).setPreferredWidth(25);
		tblCreateSection.setFont(new Font("Arial", Font.BOLD, 10));
		tblCreateSection.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		tblCreateSection.setRowHeight(31);
		util.tableCenter(tblCreateSection);
		
		scrollPane.setViewportView(tblCreateSection);
		
		comboCourseCode = new JComboBox<>();
		comboCourseCode.setModel(new DefaultComboBoxModel<String>(new String[] {"BSIT", "BSCS", "BSBM", "BSHM"}));
		comboCourseCode.setFont(new Font("Arial", Font.BOLD, 20));
		comboCourseCode.setBounds(131, 27, 172, 29);
		panel.add(comboCourseCode);
		
		comboYearLevel = new JComboBox<>();
		comboYearLevel.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4"}));
		comboYearLevel.setBounds(413, 27, 83, 29);
		panel.add(comboYearLevel);
		
		comboSection = new JComboBox<>();
		comboSection.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "B", "C", "D"}));
		comboSection.setBounds(590, 27, 83, 29);
		panel.add(comboSection);
		
		lblCourseName = new JLabel("Course Name");
		lblCourseName.setForeground(Color.WHITE);
		lblCourseName.setFont(new Font("Arial", Font.BOLD, 17));
		lblCourseName.setBounds(10, 70, 111, 32);
		panel.add(lblCourseName);
		
		textField = new JTextField();
		textField.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 16));
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
	}

}
