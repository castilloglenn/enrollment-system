package frames;


import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import util.Database;
import util.Utility;
import java.awt.Insets;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class Masterlist extends JInternalFrame {
	
	private JTextField textField;
	private JTable tblStudentList;
	
	private String[] COLUMNS = {
		"Student Number", "Last Name", "First Name", "Middle Initial", "Course", "Year", "Section"
	};
	private Object[][] students;
	
	public Masterlist(Utility util, Database dtb) {
		students = dtb.fetchStudents();
		
		setBorder(null);
		setTitle("Student Masterlist");
		setClosable(true);
		getContentPane().setLayout(null);
		setBounds(0, 0, 954, 558);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 964, 558);
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(53, 134, 0));
		tabbedPane.addTab("Student Masterlist", null, panel, null);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(83, 11, 389, 35);
		panel.add(textField);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setForeground(Color.WHITE);
		lblSearch.setFont(new Font("Arial", Font.BOLD, 17));
		lblSearch.setBounds(10, 11, 63, 35);
		panel.add(lblSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(new Color(53, 134, 0));
		scrollPane.setBounds(10, 54, 927, 444);
		panel.add(scrollPane);
		
		tblStudentList = new JTable();
		tblStudentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblStudentList.setFont(new Font("Arial", Font.PLAIN, 16));
		tblStudentList.setModel(util.generateTable(util.setupStudentInformation(students), COLUMNS));
		util.tableCenter(tblStudentList);
		tblStudentList.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 16));
		tblStudentList.setRowHeight(20);
		scrollPane.setViewportView(tblStudentList);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setMargin(new Insets(2, 5, 2, 5));
		btnSubmit.setBackground(Color.WHITE);
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 16));
		btnSubmit.setBounds(482, 11, 110, 35);
		panel.add(btnSubmit);
		
		JButton btnPrint = new JButton("Print Registration Form");
		btnPrint.setFont(new Font("Arial", Font.PLAIN, 16));
		btnPrint.setBackground(Color.WHITE);
		btnPrint.setBounds(720, 11, 217, 35);
		panel.add(btnPrint);
		
		JButton btnRefresh = new JButton("Refresh List");
		btnRefresh.setMargin(new Insets(2, 5, 2, 5));
		btnRefresh.setFont(new Font("Arial", Font.PLAIN, 16));
		btnRefresh.setBackground(Color.WHITE);
		btnRefresh.setBounds(598, 11, 116, 35);
		panel.add(btnRefresh);
		
		JPanel panelRegForm = new JPanel();
		panelRegForm.setBackground(new Color(53, 134, 0));
		tabbedPane.addTab("View Registration Form", null, panelRegForm, null);
		panelRegForm.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 10, 908, 480);
		panelRegForm.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane_1.setViewportView(textArea);
		

		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				dispose();
			}
		});
	}
}
