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
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import util.Database;
import util.Utility;


@SuppressWarnings("serial")
public class Courses extends JInternalFrame {

	private JTable tblCourseList;
	private JTabbedPane tabbedPane;
	private JPanel panelCourseList;
	private JButton btnCourseListDelete;
	private JLabel lblCourseCode;
	private JTextField txtCourseName;
	
	public Courses(Utility util, Database dtb) {
		setClosable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBorder(null);
		setBounds(-5, 0, 949, 565);
		getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 949, 538);
		getContentPane().add(tabbedPane);
		
		panelCourseList = new JPanel();
		panelCourseList.setBackground(new Color(53, 134, 0));
		tabbedPane.addTab("Course List", null, panelCourseList, null);
		panelCourseList.setLayout(null);
		
		JScrollPane scrollPaneCourseList = new JScrollPane();
		scrollPaneCourseList.setBounds(10, 130, 924, 369);
		panelCourseList.add(scrollPaneCourseList);
		
		tblCourseList = new JTable();
		tblCourseList.setFont(new Font("Arial", Font.BOLD, 15));
		tblCourseList.setRowHeight(20);
		tblCourseList.setModel(new DefaultTableModel(
			new Object[][] {
				{"wqeqweqwe", "qwe", "wqew"},
			},
			new String[] {
				"Subject Code", "Subject Name", "Units"
			}
		));
		tblCourseList.getColumnModel().getColumn(0).setResizable(false);
		tblCourseList.getColumnModel().getColumn(1).setResizable(false);
		tblCourseList.getColumnModel().getColumn(2).setResizable(false);
		util.tableCenter(tblCourseList);
		scrollPaneCourseList.setViewportView(tblCourseList);
		
		btnCourseListDelete = new JButton("Delete Course");
		btnCourseListDelete.setBounds(820, 476, 114, 23);
		panelCourseList.add(btnCourseListDelete);
		
		lblCourseCode = new JLabel("Course Code");
		lblCourseCode.setForeground(Color.WHITE);
		lblCourseCode.setFont(new Font("Arial", Font.BOLD, 17));
		lblCourseCode.setBounds(25, 25, 103, 32);
		panelCourseList.add(lblCourseCode);
		
		JComboBox<String> comboCourseSelection = new JComboBox<>();
		comboCourseSelection.setModel(new DefaultComboBoxModel<String>(new String[] {"BSIT", "BSCS", "BSBM", "BSHM"}));
		comboCourseSelection.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		comboCourseSelection.setBounds(138, 27, 290, 29);
		panelCourseList.add(comboCourseSelection);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setForeground(Color.WHITE);
		lblCourseName.setFont(new Font("Arial", Font.BOLD, 17));
		lblCourseName.setBounds(10, 68, 118, 32);
		panelCourseList.add(lblCourseName);
		
		txtCourseName = new JTextField();
		txtCourseName.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 16));
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
	}

}
