package application.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.collections.ObservableList;

public class StudySession {
	public static ObservableList<String> Locations;
	public static ObservableList<StudySession> allSessions;
	private static final String dataFilePath = "./data/SessionData.csv";
	
	private String name;  //Serves as unique identifier
	private User owner;
	private String subject;
	private	int classNumber;
	private String location;
	private String locationDetail;
	private String description;
	private ObservableList<User> sessionMembers;

	public void updateRecords() {
		//updates CSV file by re-writing all the session in the all sessions list to the .csv file.
	}
	
	public StudySession(String name, User creator, String subject, int classNumber,
			String location, String locationDetail) {
		
		//Checking to see if loadSessions has already been run
		if (allSessions.isEmpty()) {
			allSessions = loadSessions();
		}
		
		loadLocations();  //Confirms that locations have been loaded
		
		//Validates name of session, throw exception if invalid
		for (StudySession e : allSessions) {
			if (e.getName().compareTo(name) == 0) {
				throw new IllegalArgumentException("Name already in use");
			}
		}
		
		this.name = name;
		this.subject = subject;
		this.classNumber = classNumber;
		this.location = location;
		this.locationDetail = locationDetail;
		this.owner = creator;
		sessionMembers.add(creator);
		
		allSessions.add(this);
		updateRecords();
	}
	
	public static ObservableList<StudySession> loadSessions() {
		//Code to load sessions in from .csv file in records
		// checks to see if .csv file is not there, creates it if it does not exist
		try {
		
			File dataFile = new File(dataFilePath);
			dataFile.createNewFile();  // Creates new file if and only if file does not exist
			Scanner s = new Scanner(dataFile);
			
			while (s.hasNext()) {
				//name,ownerUsername,subject,classNumber,location,locationDetail,description,sessionMember1,sessionMember2,sessionMember3, . . . ,sessionMember(n)
				
				String records[] = s.next().trim().split(",");
				
				//Do i need a specific catch for this????
				StudySession currRecord = new StudySession(records[0], User.loadUser(records[1]), records[2], Integer.parseInt(records[3]), records[4], records[5]); 
				currRecord.setDescription(records[6]);
				if (records.length > 6 ) {
					for (int i = 7; i > (records.length - 1); i++) {
						currRecord.addSessionMember(User.loadUser(records[i]));
					}
				}
				
				allSessions.add(currRecord);
			}
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		
		return allSessions;
	}
	
	public void sortSessionList() {
		
	}
	
	public void loadLocations() {
		
	}
	
	public void addSessionMember(User member) {
		sessionMembers.add(member);
	}
	
	
	
	//Setters and Getters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocationDetail() {
		return locationDetail;
	}

	public void setLocationDetail(String locationDetail) {
		this.locationDetail = locationDetail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ObservableList<User> getSessionMembers() {
		return sessionMembers;
	}
}
