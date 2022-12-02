/**
 * Object class for Study Session
 * 
 * @author Team 5
 * 
 */
package application.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Comparator;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudySession {
	public static ObservableList<String> locations = FXCollections.observableArrayList();
	public static ObservableList<String> subjects = FXCollections.observableArrayList();
	public static ObservableList<StudySession> allSessions = FXCollections.observableArrayList();
	private static final String sessionFilePath = "./data/SessionData.csv";
	private static final String locationsFilePath = "./data/LocationData.csv";
	private static final String subjectsFilePath = "./data/SubjectsData.csv";
	
	private String name;  //Serves as unique identifier
	private User owner;
	private String subject;
	private	int classNumber;
	private String location;
	private String locationDetail;
	private String description;
	private ObservableList<User> sessionMembers = FXCollections.observableArrayList();

	
	/**
	 * Constructor for the StudySession object.
	 * @param name String, serves a unique identifier for StudySession
	 * @param creator User, the creator user account 
	 * @param subject String identifying the subject
	 * @param classNumber int, 4 digit number (is validated to 0 <= n <= 9999)
	 * @param location String, building where study session is located
	 * @param locationDetail String, details on where exactly the class is located in the building
	 * @throws IllegalArgumentException thrown when a input is deemed invalid.
	 */
	public StudySession(String name, User creator, String subject, int classNumber,
			String location, String locationDetail) throws IllegalArgumentException {
		
		//Checking to see if loadSessions has already been run
		if (allSessions.isEmpty()) {
			//allSessions = loadSessions();
		}
		
		
		if (locations.size() == 0) {  // Confirms that locations have been loaded
			loadLocations(); 
		}
		
		if (locations.size() == 0) {  // Confirms Subjects have been loaded
			loadSubjects();
		}
		
		// Validates name of session, throw exception if invalid
		for (StudySession e : allSessions) {
			if (e.getName().compareTo(name) == 0) {
				throw new IllegalArgumentException("Name already in use");
			}
		}
		
		//Validates class number 0 <= classNumber <= 9999
		if (classNumber > 9999 || classNumber <= 0 ) {
			throw new IllegalArgumentException("Class Number invalid");
		}
		
		this.name = name;
		this.subject = subject;
		this.classNumber = classNumber;
		this.location = location;
		this.locationDetail = locationDetail;
		this.owner = creator;
		this.description = "";
		sessionMembers.add(creator);
	}
	
	/**
	 * Tested Working
	 * @return
	 */
	public static ObservableList<StudySession> loadSessions() {
		//Code to load sessions in from .csv file in records
		// checks to see if .csv file is not there, creates it if it does not exist
		
		// empties the list
		while (allSessions.size() != 0) {
			allSessions.remove(0);
		}
		
		try {
			File dataFile = new File(sessionFilePath);
			dataFile.createNewFile();  // Creates new file if and only if file does not exist
			Scanner s = new Scanner(dataFile);
			
			while (s.hasNext()) { 
				String records[] = s.nextLine().trim().split(",");
				
				//TODO: Do i need a specific catch for this????
				StudySession currRecord = new StudySession(records[0], User.loadUser(records[1]), records[2], Integer.parseInt(records[3]), records[4], records[5]); 
				currRecord.setDescription(records[6].replace('\u0c08', '\n'));  //adds new line characters back in
				if (records.length > 6 ) {
					for (int i = 7; i > (records.length - 1); i++) {
						currRecord.addSessionMember(User.loadUser(records[i]));
					}
				}
				allSessions.add(currRecord);
			}
			s.close();
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		
		return allSessions;
	}
	
	/**
	 * Tested working
	 */
	public static void updateRecords() {
		//updates CSV file by re-writing all the sessions in the allSessions list to the .csv file.
		try {
			File dataFile = new File(sessionFilePath);
			FileWriter writer = new FileWriter(dataFile, false);
			 
			for (StudySession e: allSessions) {
				
				//swap newline characters for some other character before saving description: using 0c08 hex code
				String csvFriendlyDescription = e.description.replace('\n', '\u0c08');
				
				String record = String.format("%s,%s,%s,%d,%s,%s,%s,", e.name, e.owner.getUsername(), e.subject, e.classNumber, e.location, e.locationDetail, csvFriendlyDescription);
				for (User u: e.sessionMembers) {
					record = record + u.getUsername() + ",";
				}
				record = record + "\n";
				writer.write(record);
			}
			writer.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sortSessionList() {
		Comparator<StudySession> comparator = Comparator.comparing(StudySession::getName);
		allSessions.sort(comparator);
	}
	
	/**
	 * Only loads locations if the locations file has a new entry
	 * Tested: Working
	 */
	public static void loadLocations() {
		int numLocations = 0;
		
		try {
			File locationFile = new File(locationsFilePath);
			Scanner s = new Scanner(locationFile);
			
			while (s.hasNext()) {
				numLocations++;
				s.nextLine();
			}
			
			s.close();
			
			//Loads locations if locations file has been updated
			if (locations.size() != numLocations) {
				while (locations.size() != 0) {
					locations.remove(0);
				}
				
				s = new Scanner(locationFile);
				
				while (s.hasNext()) {
					String record = s.nextLine().trim();
					locations.add(record);
				}
					
				s.close();	
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Very similar to loadLocations, functions the same filling in subjects list and using the subjects File Path
	 */
	public static void loadSubjects() {
		int numLocations = 0;
		
		try {
			File locationFile = new File(subjectsFilePath);
			Scanner s = new Scanner(locationFile);
			
			while (s.hasNext()) {
				numLocations++;
				s.nextLine();
			}
			
			s.close();
			
			//Loads subjects if subjects file has been updated
			if (subjects.size() != numLocations) {
				while (subjects.size() != 0) {
					subjects.remove(0);
				}
				
				s = new Scanner(locationFile);
				
				while (s.hasNext()) {
					String record = s.nextLine().trim();
					subjects.add(record);
				}
					
				s.close();	
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * adds a session member to the members list
	 * @param member User, user to be added to the session
	 */
	public void addSessionMember(User member) {
		sessionMembers.add(member);
		updateRecords();
	}
	
	/**
	 * gets name value
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * gets Owner object
	 * @return User
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * gets Subject String 
	 * @return String
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * sets Subject String
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * gets class number integer value
	 * @return int
	 */
	public int getClassNumber() {
		return classNumber;
	}

	/**
	 * sets the class number value 
	 * @param classNumber int 
	 */
	public void setClassNumber(int classNumber) throws IllegalArgumentException {
		if (classNumber > 9999 || classNumber <= 0 ) {
			throw new IllegalArgumentException("Class Number invalid");
		} else {
			this.classNumber = classNumber;
		}
	}

	/**
	 * gets Location string
	 * @return String
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * sets location String
	 * @param location String
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * gets Location Detail String
	 * @return String
	 */
	public String getLocationDetail() {
		return locationDetail;
	}

	/**
	 * sets location Detail String
	 * @param locationDetail String
	 */
	public void setLocationDetail(String locationDetail) {
		this.locationDetail = locationDetail;
	}

	/**
	 * gets Description string
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets description String
	 * @param description String
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * gets session Members list
	 * @return ObservableList<User> 
	 */
	public ObservableList<User> getSessionMembers() {
		return sessionMembers;
	}
	
	/**
	 * prints the record in .csv format
	 */
	public String toString() {
		String record = String.format("%s,%s,%s,%s,%s,%s,%s,", name, owner.getUsername(), subject, classNumber, location, locationDetail, description);
		for (User u: sessionMembers) {
			record = record + u.getUsername() + ",";
		}
		return record;
	}
}
