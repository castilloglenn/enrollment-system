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
				+ "student_id BIGINT PRIMARY KEY,"
				+ "section_id VARCHAR(255) NOT NULL,"
				+ "course_id VARCHAR(255) NOT NULL,"
				+ "fname VARCHAR(255) NOT NULL,"
				+ "mname VARCHAR(255),"
				+ "lname VARCHAR(255) NOT NULL,"
				+ "birthday DATE NOT NULL,"
				+ "gender VARCHAR(255) NOT NULL,"
				+ "contact_number VARCHAR(255) NOT NULL,"
				+ "civil_status VARCHAR(255) NOT NULL,"
				+ "email VARCHAR(255) NOT NULL,"
				+ "guardian VARCHAR(255),"
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
			ps.setString(1, data[1]);
			ps.setString(2, data[0]);
			
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
			ps.setString(1, data[0]);
			ps.setString(2, data[1]);
			ps.setString(3, data[2]);
			
			ps.executeUpdate();
			subjects.add(data[0].toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertSection(String[] data) {
		try {
			ps = con.prepareStatement(
				  "INSERT INTO section "
				+ "VALUES (?, ?, ?);"
			);
			ps.setString(1, data[0].toString());
			ps.setString(2, data[1].toString());
			ps.setInt(3, Integer.parseInt(data[2]));
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertSchedule(String[] data) {
		try {
			ps = con.prepareStatement(
				  "INSERT INTO assigns "
				+ "VALUES (?, ?, ?, ?);"
			);
			ps.setString(1, data[0].toString());
			ps.setString(2, data[1].toString());
			ps.setString(3, data[2].toString());
			ps.setString(4, data[3].toString());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertContain(String[] data) {
		try {
			ps = con.prepareStatement(
				  "INSERT INTO contains "
				+ "VALUES (?, ?);"
			);
			ps.setString(1, data[0].toString());
			ps.setString(2, data[1].toString());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertStudent(Object[] data) {
		try {
			ps = con.prepareStatement(
				  "INSERT INTO student "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
			); // 12 values
			ps.setLong(1, (long) data[0]);
			ps.setString(2, data[1].toString());
			ps.setString(3, data[2].toString());
			ps.setString(4, data[3].toString());
			ps.setString(5, data[4].toString());
			ps.setString(6, data[5].toString());
			ps.setString(7, data[6].toString());
			ps.setString(8, data[7].toString());
			ps.setString(9, data[8].toString());
			ps.setString(10, data[9].toString());
			ps.setString(11, data[10].toString());
			ps.setString(12, data[11].toString());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateStudent(Object[] data) {
		try {
			ps = con.prepareStatement(
				  "UPDATE student SET "
				  + "section_id=?, "
				  + "course_id=?, "
				  + "fname=?, "
				  + "mname=?, "
				  + "lname=?, "
				  + "birthday=?, "
				  + "gender=?, "
				  + "contact_number=?, "
				  + "civil_status=?, "
				  + "email=?, "
				  + "guardian=? "
				  + "WHERE student_id=?;"
			); // 12 values
			
			ps.setString(1, data[1].toString());
			ps.setString(2, data[2].toString());
			ps.setString(3, data[3].toString());
			ps.setString(4, data[4].toString());
			ps.setString(5, data[5].toString());
			ps.setString(6, data[6].toString());
			ps.setString(7, data[7].toString());
			ps.setString(8, data[8].toString());
			ps.setString(9, data[9].toString());
			ps.setString(10, data[10].toString());
			ps.setString(11, data[11].toString());
			ps.setLong(12, (long) data[0]);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean deleteStudent(long studentID) {
		try {
			ps = con.prepareStatement(
				"DELETE FROM student "
				+ "WHERE student_id=?;"
			);
			ps.setLong(1, studentID);
			if (ps.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
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
	
	public Object[] fetchCourseIDs() {
		try {
			ps = con.prepareStatement(
				"SELECT course_id "
				+ "FROM course"
			);
			ResultSet courses = ps.executeQuery();
			ArrayList<String> coursesList = new ArrayList<>();
			while (courses.next()) {
				coursesList.add(courses.getString(1));
			}
			return coursesList.toArray();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object[] fetchSubjectIDs(String course) {
		try {
			ps = con.prepareStatement(
				"SELECT subject_id "
				+ "FROM subject "
				+ "WHERE subject_id "
				+ "LIKE '%" + course + "%'"
			);
			ResultSet subjects = ps.executeQuery();
			ArrayList<String> subjectList = new ArrayList<>();
			while (subjects.next()) {
				subjectList.add(subjects.getString(1));
			}
			return subjectList.toArray();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public long fetchLastStudentID() {
		try {
			ResultSet pid = stmt.executeQuery(
				"SELECT MAX(student_id) FROM student;"
			);
			pid.next();
			if (pid.getLong(1) != 0) return pid.getLong(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public String fetchCourseName(String course_id) {
		try {
			ps = con.prepareStatement(
				"SELECT name "
				+ "FROM course "
				+ "WHERE course_id='"
				+ course_id
				+ "';"
			);
			ResultSet courseName = ps.executeQuery();
			courseName.next();
			return courseName.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object[] fetchStudent(long studentID) {
		try {
			ps = con.prepareStatement(
				"SELECT * FROM student "
				+ "WHERE student_id=?"			
			);
			ps.setLong(1, studentID);
			ResultSet data = ps.executeQuery();
			
			Object[] student = new Object[12];
			while (data.next()) {
				student[0] = data.getLong(1);
				student[1] = data.getString(2);
				student[2] = data.getString(3);
				student[3] = data.getString(4);
				student[4] = data.getString(5);
				student[5] = data.getString(6);
				student[6] = data.getDate(7);
				student[7] = data.getString(8);
				student[8] = data.getString(9);
				student[9] = data.getString(10);
				student[10] = data.getString(11);
				student[11] = data.getString(12);
			}
			return (student[0] == null) ? null : student;
		} catch (SQLException e) {}
		return null;
	}
	
	public int fetchDataQueryCount(String table, String column, String query) {
		try {
			ResultSet fetchCount = stmt.executeQuery(
				  "SELECT COUNT(" + column + ") "
				+ "FROM " + table + " "
				+ "WHERE " + column + " "
				+ "LIKE \"%" + query + "%\";"
			);
			fetchCount.next();
			return fetchCount.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public Object[][] fetchDataQuery(String table, String column, String query) {
		try {
			int size = fetchDataQueryCount(table, column, query);
			if (size != 0) {
				Object[][] data = new Object[size][12];
				ResultSet fetchData = stmt.executeQuery(
					  "SELECT * "
					+ "FROM " + table + " "
					+ "WHERE " + column + " "
					+ "LIKE \"%" + query + "%\" "
				);
				
				int index = 0;
				while (fetchData.next()) {
					Object[] row = new Object[11];
					
					for (int index2 = 0; index2 < row.length; index2++) {
						try {
							row[index2] = fetchData.getObject(index2 + 1);
						} catch (SQLException e) {
							break;
						}
					}
					
					data[index] = row;
					index++;
				}
				return data;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object[][] fetchSubjectSchedule(String sectionID) {
		try {
			ps = con.prepareStatement(
				"SELECT COUNT(*) "
				+ "FROM subject s, assigns a "
				+ "WHERE s.subject_id=a.subject_id "
				+ "AND a.section_id=?;"
			);
			ps.setString(1, sectionID);
			ResultSet result = ps.executeQuery();
			result.next();
			int size = result.getInt(1);

			ps = con.prepareStatement(
				"SELECT s.subject_id, s.name, s.units, a.time, a.day "
				+ "FROM subject s, assigns a "
				+ "WHERE s.subject_id=a.subject_id "
				+ "AND a.section_id=?;"
			);
			ps.setString(1, sectionID);
			ResultSet result2 = ps.executeQuery();
			
			if (size != 0) {
				Object[][] data = new Object[size][5];
				int index = 0;
				while (result2.next()) {
					Object[] row = new Object[5];
					for (int index2 = 0; index2 < row.length; index2++) {
						row[index2] = result2.getObject(index2 + 1);
					}
					data[index] = row;
					index++;
				}
				return data;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public Object[][] fetchStudents(String code) {
		try {
			ps = con.prepareStatement(
				"SELECT COUNT(*) "
				+ "FROM student "
				+ "WHERE student_id LIKE '%"+code+"%' "
				+ "OR section_id LIKE '%"+code+"%' "
				+ "OR course_id LIKE '%"+code+"%' "
				+ "OR fname LIKE '%"+code+"%' "
				+ "OR mname LIKE '%"+code+"%' "
				+ "OR lname LIKE '%"+code+"%' "
				+ "OR birthday LIKE '%"+code+"%' "
				+ "OR gender LIKE '%"+code+"%' "
				+ "OR contact_number LIKE '%"+code+"%' "
				+ "OR civil_status LIKE '%"+code+"%' "
				+ "OR email LIKE '%"+code+"%' "
				+ "OR guardian LIKE '%"+code+"%';"
			);
			ResultSet result = ps.executeQuery();
			result.next();
			int size = result.getInt(1);

			ps = con.prepareStatement(
				"SELECT * FROM student "
				+ "WHERE student_id LIKE '%"+code+"%' "
				+ "OR section_id LIKE '%"+code+"%' "
				+ "OR course_id LIKE '%"+code+"%' "
				+ "OR fname LIKE '%"+code+"%' "
				+ "OR mname LIKE '%"+code+"%' "
				+ "OR lname LIKE '%"+code+"%' "
				+ "OR birthday LIKE '%"+code+"%' "
				+ "OR gender LIKE '%"+code+"%' "
				+ "OR contact_number LIKE '%"+code+"%' "
				+ "OR civil_status LIKE '%"+code+"%' "
				+ "OR email LIKE '%"+code+"%' "
				+ "OR guardian LIKE '%"+code+"%';"
			);
			ResultSet result2 = ps.executeQuery();
			
			if (size != 0) {
				Object[][] data = new Object[size][12];
				int index = 0;
				while (result2.next()) {
					Object[] row = new Object[12];
					for (int index2 = 0; index2 < row.length; index2++) {
						row[index2] = result2.getObject(index2 + 1);
					}
					data[index] = row;
					index++;
				}
				return data;
			}
		} catch (SQLException e) {}
		return null;
	}
}
