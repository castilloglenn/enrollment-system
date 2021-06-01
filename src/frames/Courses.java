package frames;


import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
public class Courses extends JInternalFrame {

	private JTable tblCourseList;
	private JTabbedPane tabbedPane;
	private JPanel panelCourseList;
	private JButton btnCourseListDelete;
	private JLabel lblCourseCode;
	private JTextField txtCourseName;
	
	private final String[] COLUMNS = {"Subject Code", "Subject Name", "Units"};
	
	public Courses(Utility util, Database dtb) {

		setTitle("View Courses");
		setClosable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBorder(null);
		getContentPane().setLayout(null);
		setBounds(0, 0, 954, 558);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 964, 558);
		getContentPane().add(tabbedPane);
		
		panelCourseList = new JPanel();
		panelCourseList.setBackground(new Color(53, 134, 0));
		tabbedPane.addTab("Course List", null, panelCourseList, null);
		panelCourseList.setLayout(null);
		
		JScrollPane scrollPaneCourseList = new JScrollPane();
		scrollPaneCourseList.setBackground(new Color(53, 134, 0));
		scrollPaneCourseList.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPaneCourseList.setForeground(Color.BLACK);
		scrollPaneCourseList.setBounds(10, 130, 924, 369);
		scrollPaneCourseList.getViewport().setBackground(new Color(53, 134, 0));
		panelCourseList.add(scrollPaneCourseList);
		
		JComboBox<Object> comboCourseSelection = new JComboBox<>();
		comboCourseSelection.setModel(new DefaultComboBoxModel<Object>(dtb.fetchCourseIDs()));
		comboCourseSelection.setFont(new Font("Arial", Font.PLAIN, 16));
		comboCourseSelection.setBounds(138, 27, 290, 32);
		panelCourseList.add(comboCourseSelection);
		
		tblCourseList = new JTable();
		tblCourseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCourseList.setFont(new Font("Arial", Font.PLAIN, 16));
		tblCourseList.setRowHeight(20);
		tblCourseList.setModel(util.generateTable(
			dtb.fetchDataQuery("subject", "subject_id", 
				comboCourseSelection.getSelectedItem().toString().substring(2)), COLUMNS));
		util.tableCenter(tblCourseList);
		scrollPaneCourseList.setViewportView(tblCourseList);
		tblCourseList.getColumnModel().getColumn(1).setMinWidth(450);
		tblCourseList.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 16));
		
		btnCourseListDelete = new JButton("Delete Course");
		btnCourseListDelete.setBounds(820, 476, 114, 23);
		panelCourseList.add(btnCourseListDelete);
		
		lblCourseCode = new JLabel("Course Code");
		lblCourseCode.setForeground(Color.WHITE);
		lblCourseCode.setFont(new Font("Arial", Font.BOLD, 16));
		lblCourseCode.setBounds(14, 25, 114, 32);
		panelCourseList.add(lblCourseCode);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setForeground(Color.WHITE);
		lblCourseName.setFont(new Font("Arial", Font.BOLD, 16));
		lblCourseName.setBounds(15, 68, 113, 32);
		panelCourseList.add(lblCourseName);
		
		txtCourseName = new JTextField(dtb.fetchCourseName(comboCourseSelection.getSelectedItem().toString()));
		txtCourseName.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCourseName.setEditable(false);
		txtCourseName.setColumns(10);
		txtCourseName.setBounds(138, 67, 564, 32);
		panelCourseList.add(txtCourseName);

		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				dispose();
			}
		});
		comboCourseSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String courseID = comboCourseSelection.getSelectedItem().toString();
				txtCourseName.setText(dtb.fetchCourseName(courseID));
				
				tblCourseList.setModel(util.generateTable(
					dtb.fetchDataQuery("subject", "subject_id", 
						comboCourseSelection.getSelectedItem().toString().substring(2)), COLUMNS));
				util.tableCenter(tblCourseList);
				tblCourseList.getColumnModel().getColumn(1).setMinWidth(450);
			}
		});
	}

}
