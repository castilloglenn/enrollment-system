package util;

import java.io.IOException;
import java.util.Arrays;

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
	
	private Database dtb;
	
	public Utility(Database dtb) {
		this.dtb = dtb;
		if (!dtb.dataExistenceCheck()) setupXMLConfigurations();
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
