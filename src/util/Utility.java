package util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Utility {

	private String parentNode = "course";
	private String[] course = new String[2];
	private String[] subject = new String[3];
	private int courseIterator = 0, subjectIterator = 0;

	String[] yearLevels = {"1", "2", "3", "4"};
	String[] sections = {"A", "B", "C", "D"};
	
	private Database dtb;
	
	public Utility(Database dtb) {
		this.dtb = dtb;
		if (!dtb.dataExistenceCheck()) {
			setupXMLConfigurations();
			generateData();
		}
	}

	private void setupXMLConfigurations() {
		try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document dom = db.parse("./config/data.xml");
            Element doc = dom.getDocumentElement();
            NodeList nl = doc.getElementsByTagName(parentNode);
            parseXML(nl);
        } catch (ParserConfigurationException | 
                 SAXException | IOException e) {
            e.printStackTrace();
        }
	}
	
	public void parseXML(NodeList node) {
		for (int index = 0; index < node.getLength(); index++) {
	        if (node.item(index).getChildNodes().getLength() == 1) {
	        	String parent = node.item(index).getParentNode().getNodeName();
	        	String value = node.item(index).getFirstChild().getNodeValue();
    			if (parent.equals("course")) {
    				course[courseIterator] = value;
    				if (courseIterator == course.length - 1) {
    					dtb.insertCourse(course);
    					courseIterator = 0;
    				} else courseIterator++;
    			} else if (parent.equals("subject")) {
    				subject[subjectIterator] = value;
    				if (subjectIterator == subject.length - 1) {
    					dtb.insertSubject(subject);
    					subjectIterator = 0;
    				} else subjectIterator++;
    			}
	        } else parseXML(node.item(index).getChildNodes());
		}
	}
	
	private void generateData() {
		Object[] courses = dtb.fetchCourseIDs();
		String[] schedules = {"8AM", "9AM", "10AM", "11AM", "12PM", 
			"1PM", "2PM", "3PM", "4PM", "5PM", "6PM", "7PM", "8PM"};
		String[] days = {"M", "T", "W", "TH", "F", "S"};
		Random rand = new Random();
		
		for (int courseIndex = 0; courseIndex < courses.length; courseIndex++) {
			String course = courses[courseIndex].toString();
			String code = course.substring(2);
			Object[] subjects = dtb.fetchSubjectIDs(code);
			
			for (int yearIndex = 0; yearIndex < yearLevels.length; yearIndex++) {
				for (int sectionIndex = 0; sectionIndex < sections.length; sectionIndex++) {
					String section_id = courses[courseIndex].toString() + "-" + yearLevels[yearIndex] + sections[sectionIndex];
					dtb.insertSection(new String[] {section_id, sections[sectionIndex], yearLevels[yearIndex]});
					
					for (int subjectIndex = 0; subjectIndex < subjects.length; subjectIndex++) {
						dtb.insertContain(new String[] {course, subjects[subjectIndex].toString()});
						
						
						dtb.insertSchedule(new String[] {section_id, subjects[subjectIndex].toString()});
					}
				}
			}
		}
	}
	
	public void tableCenter(JTable table) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for(int col=0; col < table.getColumnCount(); col++){
			table.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
	    };
	}
	
	@SuppressWarnings("serial")
	public DefaultTableModel generateTable(Object[][] rows, Object[] column) {
		return new DefaultTableModel(rows, column) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}
	
	
}
