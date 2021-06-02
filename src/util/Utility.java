package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
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
	
	private Random rand = new Random();
	public String[] yearLevels = {"1", "2", "3", "4"};
	public String[] sections = {"A", "B", "C", "D"};
	public String[] schedules = {"8AM", "9AM", "10AM", "11AM", "12PM", 
		"1PM", "2PM", "3PM", "4PM", "5PM", "6PM", "7PM", "8PM"};
	private String[] days = {"M", "T", "W", "TH", "F", "S"};
	int maximumHours = 3;
	
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
	
	public void writeFile(String title, String message) {
		File f = new File("./public/" + title + ".txt");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(message.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private void generateData() {
		Object[] courses = dtb.fetchCourseIDs();
		Object[] minorSubjects = dtb.fetchSubjectIDs("GNED");
		
		for (int courseIndex = 0; courseIndex < courses.length; courseIndex++) {
			String course = courses[courseIndex].toString();
			String code = course.substring(2);
			Object[] subjects = dtb.fetchSubjectIDs(code);
			
			for (int subjectIndex = 0; subjectIndex < subjects.length; subjectIndex++) {
				dtb.insertContain(new String[] {course, subjects[subjectIndex].toString()});
			}
			
			for (int yearIndex = 0; yearIndex < yearLevels.length; yearIndex++) {
				for (int sectionIndex = 0; sectionIndex < sections.length; sectionIndex++) {
					String section_id = courses[courseIndex].toString() + "-" + yearLevels[yearIndex] + sections[sectionIndex];
					dtb.insertSection(new String[] {section_id, sections[sectionIndex], yearLevels[yearIndex]});
					
					if (yearIndex == 0) { // first year
						randomizeSchedule(section_id, subjects, new int[] {0, 1});
						randomizeSchedule(section_id, minorSubjects, new int[] {0, 1});
					} else if (yearIndex == 1) { // second year
						randomizeSchedule(section_id, subjects, new int[] {2, 3});
						randomizeSchedule(section_id, minorSubjects, new int[] {2, 3});
					} else if (yearIndex == 2) { // third year
						randomizeSchedule(section_id, subjects, new int[] {4, 5, 6});
						randomizeSchedule(section_id, minorSubjects, new int[] {4, 5, 6});
					} else if (yearIndex == 3) { // fourth year
						randomizeSchedule(section_id, subjects, new int[] {7, 8, 9});
						randomizeSchedule(section_id, minorSubjects, new int[] {7, 8, 9});
					}
				}
			}
		}
	}
	
	public void randomizeSchedule(String section_id, Object[] subjects, int[] range) {
		for (int index : range) {
			int randomStartOfSchedule = rand.nextInt(schedules.length - maximumHours);
			int randomHoursAllocated = rand.nextInt(maximumHours) + 1;
			int randomDay = rand.nextInt(days.length);
			String schedule = schedules[randomStartOfSchedule] + "-" + schedules[randomStartOfSchedule + randomHoursAllocated];
			
			dtb.insertSchedule(new String[] {section_id, subjects[index].toString(), schedule, days[randomDay]});
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
	
	public long generateStudentID() {
		long lastID = dtb.fetchLastStudentID();
		StringBuilder markup = new StringBuilder();
		
		Calendar c = Calendar.getInstance();
		markup.append(Integer.toString(c.get(Calendar.YEAR)));
		
		int month = c.get(Calendar.MONTH) + 1;
		if (month < 10) markup.append("0");
		markup.append(Integer.toString(month));
		
		long latest = lastID;
		if (latest == -1) {
			markup.append("001");
		} else {
			String lastNum = Long.toString(latest).substring(7);
			int increment = Integer.parseInt(lastNum) + 1;
			markup.append(String.format("%03d", increment));
		}
		return Long.parseLong(markup.toString());
	}
	
	public Object[][] setupStudentInformation(Object[][] students) {
		try {
			Object[][] arranged = new Object[students.length][7];
			for (int index = 0; index < students.length; index++) {
				Object[] row = new Object[7];
				String course = students[index][1].toString();
				
				row[0] = students[index][0];
				row[1] = students[index][5];
				row[2] = students[index][3];
				row[3] = students[index][4];
				row[4] = course.substring(0, 4);
				row[5] = course.substring(5, 6);
				row[6] = course.substring(6);
				
				arranged[index] = row;
			}
			return arranged;
		} catch (NullPointerException e) {}
		return null;
	}
}
