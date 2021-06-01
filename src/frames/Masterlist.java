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
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import util.Database;


@SuppressWarnings("serial")
public class Masterlist extends JInternalFrame {
	
	private JTextField textField;
	private JTable tblStudentList;
	
	public Masterlist(Database dtb) {
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
		textField.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 16));
		textField.setColumns(10);
		textField.setBounds(83, 11, 389, 32);
		panel.add(textField);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(Color.WHITE);
		lblSearch.setFont(new Font("Arial", Font.BOLD, 17));
		lblSearch.setBounds(10, 11, 63, 32);
		panel.add(lblSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(new Color(53, 134, 0));
		scrollPane.setBounds(10, 54, 927, 444);
		panel.add(scrollPane);
		
		tblStudentList = new JTable();
		tblStudentList.setFont(new Font("Arial", Font.PLAIN, 16));
		tblStudentList.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Student Number", "Last Name", "First Name", "Middle Initial", "Course", "Year", "Section"
			}
		) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tblStudentList.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 16));
		tblStudentList.getColumnModel().getColumn(0).setResizable(false);
		tblStudentList.setRowHeight(20);
		scrollPane.setViewportView(tblStudentList);
		
		JButton btnPrint = new JButton("Print Registration Form");
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPrint.setBounds(482, 11, 173, 35);
		panel.add(btnPrint);
		
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
