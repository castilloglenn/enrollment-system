package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import main.Main;

public class Database {
	
	private final String db_url = "jdbc:mysql://localhost/?serverTimezone=UTC";
	private final String db_name = "enrollment";
	private final String db_user = "root";
	private final String db_pass = "";
	
	private ArrayList<String> courses = new ArrayList<>();
	private ArrayList<String> subjects = new ArrayList<>();
	
	private Connection con;
	private PreparedStatement ps;
	private Statement stmt;
	
	public Database() {
		try {
			con = DriverManager.getConnection(
				db_url, db_user, db_pass
			);
			stmt = con.createStatement();
			createDatabase();
			createTables();
			dataExistenceCheck();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(
				null, 
				"Communications with the server is unreachable.\n"
				+ "Error message:\n" + e.getMessage(), 
				"Error | " + Main.SYSTEM_NAME, 
				JOptionPane.WARNING_MESSAGE
			);
			System.exit(0);
		}
	}
	
	public void createDatabase() throws SQLException {
		stmt.execute(String.format("CREATE DATABASE IF NOT EXISTS %s;", db_name));
		stmt.execute(String.format("USE %s;", db_name));
	}
	
	public void createTables() throws SQLException {
		stmt.execute(
			"CREATE TABLE IF NOT EXISTS subject ("
					+ "subject_id VARCHAR(255) PRIMARY KEY,"
					+ "name VARCHAR(255) NOT NULL,"
					+ "units INT NOT NULL"
			+ ");"
		);
		stmt.execute(
			"CREATE TABLE IF NOT EXISTS section ("
					+ "section_id VARCHAR(255) PRIMARY KEY,"
					+ "name VARCHAR(255) NOT NULL,"
					+ "year_level INT NOT NULL"
			+ ");"
		);
		stmt.execute(
			"CREATE TABLE IF NOT EXISTS course ("
					+ "course_id VARCHAR(255) PRIMARY KEY,"
					+ "name VARCHAR(255) NOT NULL"
			+ ");"
		);
		stmt.execute(
			"CREATE TABLE IF NOT EXISTS student ("
					+ "student_id VARCHAR(255) PRIMARY KEY,"
					+ "section_id VARCHAR(255) NOT NULL,"
					+ "course_id VARCHAR(255) NOT NULL,"
					+ "fname VARCHAR(255) NOT NULL,"
					+ "mname VARCHAR(255) DEFAULT \"\","
					+ "lname VARCHAR(255) NOT NULL,"
					+ "birthday DATE NOT NULL,"
					+ "gender VARCHAR(255) NOT NULL,"
					+ "contact_number VARCHAR(255) NOT NULL,"
					+ "civil_status VARCHAR(255) NOT NULL,"
					+ "email VARCHAR(255) NOT NULL,"
					+ "guardian VARCHAR(255) NOT NULL,"
					+ "FOREIGN KEY (section_id) "
					+ "REFERENCES section(section_id),"
					+ "FOREIGN KEY (course_id) "
					+ "REFERENCES course(course_id)"
			+ ");"
		);
		stmt.execute(
			"CREATE TABLE IF NOT EXISTS contains ("
					+ "course_id VARCHAR(255) NOT NULL, "
					+ "subject_id VARCHAR(255) NOT NULL, "
					+ "FOREIGN KEY (course_id)"
					+ "REFERENCES course(course_id),"
					+ "FOREIGN KEY (subject_id)"
					+ "REFERENCES subject(subject_id)"
			+ ");"
		);
		stmt.execute(
			"CREATE TABLE IF NOT EXISTS assigns ("
					+ "section_id VARCHAR(255) NOT NULL, "
					+ "subject_id VARCHAR(255) NOT NULL, "
					+ "time VARCHAR(255) NOT NULL, "
					+ "day VARCHAR(255) NOT NULL, "
					+ "FOREIGN KEY (section_id)"
					+ "REFERENCES section(section_id), "
					+ "FOREIGN KEY (subject_id)"
					+ "REFERENCES subject(subject_id)"
			+ ");"
		);
	}
	
	public void insertCourse(String[] data) {
		try {
			ps = con.prepareStatement(
				  "INSERT INTO course "
				+ "VALUES (?, ?);"
			);
			ps.setString(1, data[1].toString());
			ps.setString(2, data[0].toString());
			
			ps.executeUpdate();
			courses.add(data[1].toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertSubject(String[] data) {
		try {
			ps = con.prepareStatement(
				  "INSERT INTO subject "
				+ "VALUES (?, ?, ?);"
			);
			ps.setString(1, data[0].toString());
			ps.setString(2, data[1].toString());
			ps.setString(3, data[2].toString());
			
			ps.executeUpdate();
			subjects.add(data[0].toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean dataExistenceCheck() {
		try {
			ps = con.prepareStatement(
				  "SELECT COUNT(course_id) "
				+ "FROM course;"
			);
			ResultSet count = ps.executeQuery();
			count.next();
			int courseCount = count.getInt(1);
			ps = con.prepareStatement(
				  "SELECT COUNT(subject_id) "
				+ "FROM subject;"
			);
			ResultSet count2 = ps.executeQuery();
			count2.next();
			int subjectCount = count2.getInt(1);
			return courseCount != 0 && subjectCount != 0;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public int countStudents() {
		try {
			ps = con.prepareStatement(
				"SELECT COUNT(student_id) "
				+ "FROM student;"
			);
			ResultSet count = ps.executeQuery();
			count.next();
			return count.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int countCourses() {
		try {
			ps = con.prepareStatement(
				"SELECT COUNT(course_id) "
				+ "FROM course;"
			);
			ResultSet count = ps.executeQuery();
			count.next();
			return count.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int countSubjects() {
		try {
			ps = con.prepareStatement(
				"SELECT COUNT(subject_id) "
				+ "FROM subject;"
			);
			ResultSet count = ps.executeQuery();
			count.next();
			return count.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
