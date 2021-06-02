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

import main.Main;

import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import util.Database;
import util.Utility;
import util.RegForm;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class Masterlist extends JInternalFrame {
	
	private JTextField textField;
	private JTable tblStudentList;
	
	private String[] COLUMNS = {
		"Student Number", "Last Name", "First Name", "Middle Initial", "Course", "Year", "Section"
	};
	private Object[][] students;
	private RegForm regform;
	
	public Masterlist(Utility util, Database dtb) {
		students = dtb.fetchStudents("");
		regform = new RegForm();
		
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
		lblSearch.setFont(new Font("Arial", Font.BOLD, 18));
		lblSearch.setBounds(10, 13, 71, 33);
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
		
		JButton btnPrint = new JButton("Show Registration Form");
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
		scrollPane_1.setBounds(10, 10, 934, 491);
		panelRegForm.add(scrollPane_1);
		
		JTextArea txtRegForm = new JTextArea(regform.get());
		txtRegForm.setFont(new Font("Courier New", Font.PLAIN, 14));
		txtRegForm.setMargin(new Insets(20, 20, 20, 20));
		txtRegForm.setEditable(false);
		scrollPane_1.setViewportView(txtRegForm);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(txtRegForm, popupMenu);
		
		JMenuItem menuItemPrint = new JMenuItem("Print");
		popupMenu.add(menuItemPrint);
		

		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				dispose();
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getExtendedKeyCode() == 10) btnSubmit.doClick();
			}
		});
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				students = dtb.fetchStudents(textField.getText());
				tblStudentList.setModel(util.generateTable(util.setupStudentInformation(students), COLUMNS));
				util.tableCenter(tblStudentList);
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				students = dtb.fetchStudents("");
				tblStudentList.setModel(util.generateTable(util.setupStudentInformation(students), COLUMNS));
				util.tableCenter(tblStudentList);
				regform = new RegForm();
				txtRegForm.setText(regform.get());
			}
		});
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblStudentList.getSelectedRow() != -1) 
					regform = new RegForm(students[tblStudentList.getSelectedRow()], dtb, util);
				else regform = new RegForm();
				txtRegForm.setText(regform.get());
				txtRegForm.setCaretPosition(0);
				tabbedPane.setSelectedIndex(1);
			}
		});
		menuItemPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblStudentList.getSelectedRow() != -1 && regform.getStudent() != null) {
					util.writeFile(
						students[tblStudentList.getSelectedRow()][0] + "-" +
						Long.toString(Calendar.getInstance().getTimeInMillis()), 
						txtRegForm.getText()
					);
					JOptionPane.showMessageDialog(null, 
						"Registration Form successfully printed. View exported data via \"public\" folder." , 
						"Success | " + Main.SYSTEM_NAME, JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, 
						"Please select a row in the student's masterlist table and press \"View Registration Form\" to continue." , 
						"Invalid | " + Main.SYSTEM_NAME, JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
