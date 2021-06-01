package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RegForm {
	
	// minimum width of monospaced texts
	private final int WIDTH = 111;
	// schedule table widths (percentage)
	private final long TABLE_RESERVED_SPACE = 12;
	private final long SHORT = Math.round((WIDTH - TABLE_RESERVED_SPACE) * 0.1);
	private final long MEDIUM = Math.round((WIDTH - TABLE_RESERVED_SPACE) * 0.15);
	private final long LONG = Math.round((WIDTH - TABLE_RESERVED_SPACE) * 0.55);
	
	private StringBuilder form;
	private Object[] student;
	
	private final String SPACE = " ";
	private final String BR = "\n";
    private String horizontalLine = "";
    private String tableHorizontalLine = "";

	private DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss aa");
	private String dateTime;
	
	private String studentID, sectionID, courseID, fname, mname, lname, birthday, 
		gender, contactNumber, civilStatus, email, guardian;
	private Object[][] schedules;
	
	public RegForm() {
		this.student = null;
		form = new StringBuilder();
		setupObjects();
	}
	
	public RegForm(Object[] student, Database dtb) {
		this.student = student;
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
        for (int space = 0; space < WIDTH - 6; space++) {
            tableHorizontalLine += "=";
        }
    }
	
	private String center(String message) {
        int length = message.length();
        if (length == WIDTH - 6) return "|  " + message + "  |";
        
        if (length > WIDTH - 6) {
            return "|  " + message.substring(0, WIDTH - 6) + "  |\n" + 
                center(message.substring(WIDTH - 6));
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
        int limit = WIDTH - 6;
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
	
	public String encryptSpaces(String message) {
		String newMessage = "";
		for (int index = 0; index < message.length(); index++) {
			if (message.charAt(index) == ' ') newMessage += "•";
			else newMessage += message.charAt(index);
		}
		return newMessage;
	}
	
	public String decryptBullets(String message) {
		String newMessage = "";
		for (int index = 0; index < message.length(); index++) {
			if (message.charAt(index) == '•') newMessage += " ";
			else newMessage += message.charAt(index);
		}
		return newMessage;
	}
	
	public String tableHeader() {
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
	
	public String tableRows() {
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
		}
		// remove the final linebreak
//		rows.insert(rows.length() - 3, "");
		
		return rows.toString();
	}
	
	public String get() {
		if (student == null) {
			form.append(BR + BR + BR + BR + BR + BR + BR);
			form.append(horizontalLine + BR);
			form.append(center("") + BR);
			form.append(center("Please select a row in the student's masterlist table and press \"View Registration Form\" to continue.") + BR);
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
			form.append(tableHeader());
			form.append(center(tableHorizontalLine) + BR);
			form.append(tableRows());
			form.append(center(tableHorizontalLine) + BR);
	    	
	    	form.append(center("") + BR);
	    	form.append(horizontalLine + BR);
		}
    	return form.toString();
    }
	
}
