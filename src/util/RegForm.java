package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RegForm {
	
	// minimum width of monospaced texts
	private final int WIDTH = 107;
	private final int MARGIN = 6;
	// schedule table widths (percentage), short has three instances
	// formula = (short * 3) + medium + long = 100%
	private final long SCHEDULE_TABLE_RESERVED_SPACE = 6 + MARGIN;
	private final long SHORT = Math.round((WIDTH - SCHEDULE_TABLE_RESERVED_SPACE) * 0.10);
	private final long MEDIUM = Math.round((WIDTH - SCHEDULE_TABLE_RESERVED_SPACE) * 0.15);
	private long LONG = Math.round((WIDTH - SCHEDULE_TABLE_RESERVED_SPACE) * 0.55);
	// tuition table widths (percentage), shorter and longer has two instances
	// formula = (shorter * 2) + (longer * 2) = 100%
	private final long TUITION_TABLE_RESERVED_SPACE = 5 + MARGIN;
	private final long SHORTER = Math.round((WIDTH - TUITION_TABLE_RESERVED_SPACE) * 0.22);
	private final long LONGER = Math.round((WIDTH - TUITION_TABLE_RESERVED_SPACE) * 0.28);
	
	// student variables
	private String studentID, sectionID, courseID, fname, mname, lname, birthday, 
	gender, contactNumber, civilStatus, email, guardian;
	private Object[][] schedules;
	private StringBuilder form;
	private Object[] student;
	private int totalUnits = 0;
	private int totalHours = 0;
	private int tuition = 0;
	private int totalAmount = 0;
	private int perUnit = 165;
	
	// static values
	private int library = 650;
	private int medical = 85;
	private int publication = 115;
	private int registration = 100;
	private int guidance = 30;
	private int sfdf = 1800;
	private int srf = 2525;
	private int athletic = 100;
	private int scuaa = 100;
	private int internet = 185;
	private int ccl = 3200;
	
	private final String SPACE = " ";
	private final String BR = "\n";
    private String horizontalLine = "";
    private String tableHorizontalLine = "";

	private DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss aa");
	private String dateTime;
	
	private Utility util;
	
	
	public RegForm() {
		this.student = null;
		form = new StringBuilder();
		setupObjects();
	}
	
	public RegForm(Object[] student, Database dtb, Utility util) {
		this.student = student;
		this.util = util;
		form = new StringBuilder();
		setupObjects();
		
		studentID = student[0].toString();
		sectionID = student[1].toString();
		courseID = student[2].toString();
		fname = student[3].toString();
		mname = student[4].toString();
		lname = student[5].toString();
		birthday = student[6].toString();
		gender = student[7].toString();
		contactNumber = student[8].toString();
		civilStatus = student[9].toString();
		email = student[10].toString();
		guardian = student[11].toString();
		
		schedules = dtb.fetchSubjectSchedule(sectionID);
	}
	
	private void setupObjects() {
		dateTime = sdf.format(Calendar.getInstance().getTime());
        for (int space = 0; space < WIDTH; space++) {
            horizontalLine += "=";
        }
        for (int space = 0; space < WIDTH - MARGIN; space++) {
            tableHorizontalLine += "=";
        }
        
        if (((SHORT * 3) + MEDIUM + LONG + SCHEDULE_TABLE_RESERVED_SPACE) > WIDTH) {
        	long difference = ((SHORT * 3) + MEDIUM + LONG + SCHEDULE_TABLE_RESERVED_SPACE) - WIDTH;
        	LONG -= difference;
        }
    }
	
	private String center(String message) {
        int length = message.length();
        if (length == WIDTH - MARGIN) return "|  " + message + "  |";
        
        if (length > WIDTH - MARGIN) {
            return "|  " + message.substring(0, WIDTH - MARGIN) + "  |\n" + 
                center(message.substring(WIDTH - MARGIN));
        }
        
        int padding = (WIDTH - length) / 2 - 3;
        for (int space = 0; space < padding; space++) {
            message = " " + message;
        }
        int rightPadding = padding + ((WIDTH - length) % 2);
        for (int space = 0; space < rightPadding; space++) {
            message = message + " ";
        }
        
        return "|  " + message + "  |";
    }
	
	public String center(String message, long width) {
		int length = message.length();
		if (length == width) return message;
		
		String newMessage = "";
		if (length > width) {
			newMessage = message.substring(0, message.length() - 4);
			for (int dots = 0; dots < 3; dots++) newMessage += ".";
			return newMessage;
		}
		
		long padding = (width - length) / 2;
		long extra = (width - length) % 2;
		for (int left = 0; left < padding + extra; left++)
			newMessage += " ";
		newMessage += message;
		for (int right = 0; right < padding; right++)
			newMessage += " ";
		
		return newMessage;
	}
	
	private String justify(String message) {
        String newMessage = "";
		ArrayList<Integer> spaceIndexes = new ArrayList<>();

        for (int index = 0; index < message.length(); index++) {
            if (message.charAt(index) == ' ') spaceIndexes.add(index);
        }

        if (spaceIndexes.size() == 0) return center(message);
        int limit = WIDTH - MARGIN;
        int totalSpaces = limit - (message.length() - spaceIndexes.size());
        int equalSpaces = totalSpaces / spaceIndexes.size();
        int residual = totalSpaces % spaceIndexes.size();

        int previousIndex = 0;
        for (int index = 0; index < spaceIndexes.size(); index++) {
            for (int start = previousIndex; start < spaceIndexes.get(index); start++)
                if (message.charAt(start) != ' ') newMessage += message.charAt(start);
            for (int spaceIndex = 0; spaceIndex < equalSpaces; spaceIndex++)
                newMessage += " ";
            previousIndex = spaceIndexes.get(index);
        }
        for (int residualIndex = 0; residualIndex < residual; residualIndex++)
            newMessage += " ";
        for (int index = previousIndex; index < message.length(); index++)
            if (message.charAt(index) != ' ') newMessage += message.charAt(index);
        
		return decryptBullets("|  " + newMessage + "  |");
	}
	
	private String justify(String message, long width) {
        String newMessage = " ";
		ArrayList<Integer> spaceIndexes = new ArrayList<>();

        for (int index = 0; index < message.length(); index++) {
            if (message.charAt(index) == ' ') spaceIndexes.add(index);
        }

        if (spaceIndexes.size() == 0) return center(message);
        int limit = (int) width - 2;
        int totalSpaces = limit - (message.length() - spaceIndexes.size());
        int equalSpaces = totalSpaces / spaceIndexes.size();
        int residual = totalSpaces % spaceIndexes.size();

        int previousIndex = 0;
        for (int index = 0; index < spaceIndexes.size(); index++) {
            for (int start = previousIndex; start < spaceIndexes.get(index); start++)
                if (message.charAt(start) != ' ') newMessage += message.charAt(start);
            for (int spaceIndex = 0; spaceIndex < equalSpaces; spaceIndex++)
                newMessage += " ";
            previousIndex = spaceIndexes.get(index);
        }
        for (int residualIndex = 0; residualIndex < residual; residualIndex++)
            newMessage += " ";
        for (int index = previousIndex; index < message.length(); index++)
            if (message.charAt(index) != ' ') newMessage += message.charAt(index);
        
		return decryptBullets(newMessage) + " ";
	}
	
	public String encryptSpaces(String message) {
		String newMessage = "";
		for (int index = 0; index < message.length(); index++) {
			if (message.charAt(index) == ' ') newMessage += "*";
			else newMessage += message.charAt(index);
		}
		return newMessage;
	}
	
	public String decryptBullets(String message) {
		String newMessage = "";
		for (int index = 0; index < message.length(); index++) {
			if (message.charAt(index) == '*') newMessage += " ";
			else newMessage += message.charAt(index);
		}
		return newMessage;
	}
	
	public String scheduleHeader() {
		StringBuilder header = new StringBuilder();
		
		header.append("|  |");
		header.append(center("Code", SHORT));
		header.append("|");
		header.append(center("Description", LONG));
		header.append("|");
		header.append(center("Units", SHORT));
		header.append("|");
		header.append(center("Time", MEDIUM));
		header.append("|");
		header.append(center("Day", SHORT));
		header.append("|  |" + BR);
		
		return header.toString();
	}
	
	public String scheduleRows() {
		StringBuilder rows = new StringBuilder();
		
		for (Object[] schedule : schedules) {
			rows.append("|  |");
			rows.append(center(schedule[0].toString(), SHORT));
			rows.append("|");
			rows.append(center(schedule[1].toString(), LONG));
			rows.append("|");
			rows.append(center(schedule[2].toString(), SHORT));
			rows.append("|");
			rows.append(center(schedule[3].toString(), MEDIUM));
			rows.append("|");
			rows.append(center(schedule[4].toString(), SHORT));
			rows.append("|  |" + BR);
			
			totalUnits += (int) schedule[2];
			totalHours += parseSchedule(schedule[3].toString());
		}
		rows.append(scheduleEmptyRows());
		rows.append(scheduleEmptyRows());
		rows.append(scheduleEmptyRows());
		tuition = totalUnits * perUnit;
		
		totalAmount += tuition + library + medical + publication + 
			registration + guidance + sfdf + srf + athletic +
			scuaa + internet + ccl;
		
		return rows.toString();
	}
	
	public String scheduleEmptyRows() {
		StringBuilder rows = new StringBuilder();
		
		rows.append("|  |");
		rows.append(center("", SHORT));
		rows.append("|");
		rows.append(center("", LONG));
		rows.append("|");
		rows.append(center("", SHORT));
		rows.append("|");
		rows.append(center("", MEDIUM));
		rows.append("|");
		rows.append(center("", SHORT));
		rows.append("|  |" + BR);
		
		return rows.toString();
	}
	
	public int parseSchedule(String schedule) {
		int indexSeparator = -1;
		for (int index = 0; index < schedule.length(); index++) {
			if (schedule.charAt(index) == '-') {
				indexSeparator = index;
				break;
			}
		}
		String before = schedule.substring(0, indexSeparator);
		String after = schedule.substring(indexSeparator + 1);
		int beforeIndex = -1;
		int afterIndex = -1;
		
		for (int index = 0; index < util.schedules.length; index++) {
			if (before.equals(util.schedules[index])) beforeIndex = index;
			if (after.equals(util.schedules[index])) afterIndex = index;
		}
		
		return afterIndex - beforeIndex;
	}
	
	public String tuitionHeader() {
		StringBuilder header = new StringBuilder();
		
		header.append("|  |");
		header.append(center("Laboratory Fees", SHORTER));
		header.append("|");
		header.append(center("Other Fees", SHORTER));
		header.append("|");
		header.append(center("Assessment", LONGER));
		header.append("|");
		header.append(center("", LONGER));
		header.append("|  |" + BR);
		
		// gapped horizontal line
		header.append("|  ");
		long endHorizontal = (SHORTER * 2) + LONGER + (TUITION_TABLE_RESERVED_SPACE - MARGIN - 1);
		String gappedHorizontal = horizontalLine.substring(0, (int) endHorizontal);
		
		header.append(gappedHorizontal);
		header.append(justify(encryptSpaces("Total Units:") + String.format(" %d", totalUnits), LONGER));
		header.append("|  |" + BR);
		// end of horizontal line
		
		return header.toString();
	}
	
	public String tuitionRows() {
		StringBuilder rows = new StringBuilder();
		
		// LINE #1
		rows.append("|  |");
		rows.append(justify(encryptSpaces("CCL:") + String.format(" %,.2f", (float) ccl), SHORTER));
		rows.append("|");
		rows.append(justify(encryptSpaces("Internet:") + String.format(" %,.2f", (float) internet), SHORTER));
		rows.append("|");
		rows.append(justify(encryptSpaces("Tuition:") + String.format(" %,.2f", (float) tuition), LONGER));
		rows.append("|");
		rows.append(justify(encryptSpaces("Total Hours:") + String.format(" %d", totalHours), LONGER));
		rows.append("|  |" + BR);
		
		// LINE #2
		rows.append("|  |");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(justify(encryptSpaces("Library:") + String.format(" %,.2f", (float) library), LONGER));
		rows.append("|");
		rows.append(center("", LONGER));
		rows.append("|  |" + BR);
		
		// LINE #3
		rows.append("|  |");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(justify(encryptSpaces("Medical:") + String.format(" %,.2f", (float) medical), LONGER));
		rows.append("|");
		rows.append(justify(encryptSpaces("Total Amount:") + String.format(" %,.2f", (float) totalAmount), LONGER));
		rows.append("|  |" + BR);
		
		// LINE #4
		rows.append("|  |");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(justify(encryptSpaces("Publication:") + String.format(" %,.2f", (float) publication), LONGER));
		rows.append("|");
		rows.append(center("", LONGER));
		rows.append("|  |" + BR);
		
		// LINE #5
		rows.append("|  |");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(justify(encryptSpaces("Registration:") + String.format(" %,.2f", (float) registration), LONGER));
		rows.append("|");
		rows.append(center("Scholarship:", LONGER));
		rows.append("|  |" + BR);
		
		// LINE #6
		rows.append("|  |");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(justify(encryptSpaces("Guidance:") + String.format(" %,.2f", (float) guidance), LONGER));
		rows.append("|");
		rows.append(center("RA 10931", LONGER));
		rows.append("|  |" + BR);
		
		// LINE #7
		rows.append("|  |");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(justify(encryptSpaces("SFDF:") + String.format(" %,.2f", (float) sfdf), LONGER));
		rows.append("|");
		rows.append(justify(encryptSpaces("Tuition:") + " 0", LONGER));
		rows.append("|  |" + BR);
		
		// LINE #8
		rows.append("|  |");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(justify(encryptSpaces("SRF:") + String.format(" %,.2f", (float) srf), LONGER));
		rows.append("|");
		rows.append(justify(encryptSpaces("SFDF:") + " 0", LONGER));
		rows.append("|  |" + BR);
		
		// LINE #9
		rows.append("|  |");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(justify(encryptSpaces("Athletic:") + String.format(" %,.2f", (float) athletic), LONGER));
		rows.append("|");
		rows.append(justify(encryptSpaces("SRF:") + " 0", LONGER));
		rows.append("|  |" + BR);
		
		// LINE #10
		rows.append("|  |");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(justify(encryptSpaces("SCUAA:") + String.format(" %,.2f", (float) scuaa), LONGER));
		rows.append("|");
		rows.append(center("", LONGER));
		rows.append("|  |" + BR);
		
		// LINE #11
		rows.append("|  |");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", LONGER));
		rows.append("|");
		rows.append(center("Terms of Payment", LONGER));
		rows.append("|  |" + BR);
		
		// LINE #12
		rows.append("|  |");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", LONGER));
		rows.append("|");
		rows.append(justify(String.format("First: %,.2f", (float) totalAmount / 2.0), LONGER));
		rows.append("|  |" + BR);
		
		// LINE #13
		rows.append("|  |");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", LONGER));
		rows.append("|");
		rows.append(justify(String.format("Second: %,.2f", (float) totalAmount / 4.0), LONGER));
		rows.append("|  |" + BR);
		
		// LINE #14
		rows.append("|  |");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", LONGER));
		rows.append("|");
		rows.append(justify(String.format("Third: %,.2f", (float) totalAmount / 4.0), LONGER));
		rows.append("|  |" + BR);
		
		rows.append(tuitionEmptyRow());
		rows.append(tuitionEmptyRow());
		
		return rows.toString();
	}
	
	public String tuitionEmptyRow() {
		StringBuilder rows = new StringBuilder();
		
		rows.append("|  |");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", SHORTER));
		rows.append("|");
		rows.append(center("", LONGER));
		rows.append("|");
		rows.append(center("", LONGER));
		rows.append("|  |" + BR);
		
		return rows.toString();
	}
	
	public String get() {
		if (student == null) {
			form.append(BR + BR + BR + BR + BR + BR + BR);
			form.append(horizontalLine + BR);
			form.append(center("") + BR);
			form.append(center("Please select a row in the student's masterlist table and press \"Show Registration Form\" to continue.") + BR);
			form.append(center("") + BR);
			form.append(horizontalLine + BR);
			
		} else {
			form.append(horizontalLine + BR);
			form.append(center("") + BR);
			
	    	form.append(center("CAVITE STATE UNIVERSITY") + BR);
	    	form.append(center("Imus Campus") + BR);
	    	form.append(center("REGISTRATION FORM") + BR);

			form.append(center("") + BR);
			
			form.append(justify(
				encryptSpaces("Student Number: " + studentID) + SPACE + 
				encryptSpaces("Date: " + dateTime)
			) + BR);
			form.append(justify(
				encryptSpaces(String.format("Student Name: %s, %s %s", 
					lname.toUpperCase(), fname.toUpperCase(), mname.toUpperCase())) + SPACE + 
					encryptSpaces("Gender: " + gender) + SPACE + 
					encryptSpaces("Civil Status: " + civilStatus)
			) + BR);
			form.append(justify(
				encryptSpaces("Birthday: " + birthday) + SPACE + 
				encryptSpaces("E-mail: " + email)
			) + BR);
			form.append(justify(
				encryptSpaces("Guardian: " + guardian) + SPACE + 
				encryptSpaces("Contact: " + contactNumber)
			) + BR);
			form.append(justify(
				encryptSpaces("Course: " + courseID) + SPACE + 
				encryptSpaces("Section: " + sectionID.substring(5, 6)) + SPACE + 
				encryptSpaces("Year: " + sectionID.substring(6)) + SPACE + 
				encryptSpaces("School Year: 2020-2021")
			) + BR);

			form.append(center("") + BR);
			
			form.append(center(tableHorizontalLine) + BR);
			form.append(scheduleHeader());
			form.append(center(tableHorizontalLine) + BR);
			form.append(scheduleRows());
			form.append(center(tableHorizontalLine) + BR);
			form.append(tuitionHeader());
			form.append(tuitionRows());
			form.append(center(tableHorizontalLine) + BR);
			form.append(justify(encryptSpaces("Official Copy") + " " 
				+ encryptSpaces("Cavite State University | Imus Campus")
			) + BR);
	    	
	    	form.append(center("") + BR);
	    	form.append(horizontalLine + BR);
		}
    	return form.toString();
    }
	
	public Object[] getStudent() {return student;}
}
