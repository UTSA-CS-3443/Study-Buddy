package application.model;

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
	
	public static ObservableList<StudySession> loadSessions() {
		//Code to load sessions in from .csv file in records
		// checks to see if .csv file is not there, creates it if it does not exist
		
		return null;
	}

	public void updateRecords() {
		//updates CSV file by re-writing all the session in the all sessions list to the .csv file.
	}
	
	public StudySession(String name, User creator, String subject, int classNumber,
			String location, String locationDetail) {
		
		//Checking to see if loadSessions has already been run
		if (allSessions.isEmpty()) {
			allSessions = loadSessions();
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
}
