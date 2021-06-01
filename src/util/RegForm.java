package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegForm {
	
	
	private StringBuilder form;
	private Object[] student;
	
	private final String BR = "\n";
	private final int WIDTH = 111;
    private String horizontalLine = "";

	private DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss aa");
	private String dateTime;
	
	private String studentID, sectionID, fname, mname, lname, birthday, 
		gender, contactNumber, civilStatus, email, guardian;
	
	public RegForm() {
		this.student = null;
		form = new StringBuilder();
		setupObjects();
	}
	
	public RegForm(Object[] student) {
		this.student = student;
		form = new StringBuilder();
		setupObjects();
		
		studentID = student[0].toString();
		sectionID = student[1].toString();
		fname = student[3].toString();
		mname = student[4].toString();
		lname = student[5].toString();
		birthday = student[6].toString();
		gender = student[7].toString();
		contactNumber = student[8].toString();
		civilStatus = student[9].toString();
		email = student[10].toString();
		guardian = student[11].toString();
	}
	
	private void setupObjects() {
		dateTime = sdf.format(Calendar.getInstance().getTime());
        for (int space = 0; space < WIDTH; space++) {
            horizontalLine += "=";
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
	
	private String justify(String message) {
		
		
		
		return message; 
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
//			form.append(horizontalLine + BR);
//	    	form.append(center("") + BR);
			form.append(horizontalLine + BR);
			form.append(center("") + BR);
			
	    	form.append(center("CAVITE STATE UNIVERSITY") + BR);
	    	form.append(center("Imus Campus") + BR);
	    	form.append(center("REGISTRATION FORM") + BR);

			form.append(center("") + BR);
			
			form.append(center(fname) + BR);
	    	
	    	form.append(center("") + BR);
	    	form.append(horizontalLine + BR);
		}
		
    	return form.toString();
    }
	
}
