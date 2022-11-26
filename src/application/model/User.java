package application.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class User {

	private static ArrayList<User> allUsers;
	private static final String userFilePath = "./data/UserData.csv";
	private String userName;
	private String lastName;
	private String firstName;
	private String password;
	
	public User(String userName, String password, String firstName, String lastName) throws IllegalArgumentException {
		
		if (allUsers.isEmpty()) {
			loadUsers();
		}
		
		for (User e: allUsers) {
			if (e.getUsername().compareTo(userName) == 0) {
				throw new IllegalArgumentException("Username already exists");
			}
		}
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		
		allUsers.add(this);
		
	}
	
	public String getUsername() {
		return userName;
	}
	
	public static void loadUsers() {
		
		System.out.println("Load Users was called");
		
		//empties the list
		while (allUsers.size() != 0) {
			allUsers.remove(0);
		}
		
		try {
			File dataFile = new File(userFilePath);
			dataFile.createNewFile(); // creates new file if and only if file does not exist
			Scanner s = new Scanner(dataFile);
			
			while (s.hasNext()) {
				String records[] = s.nextLine().trim().split(",");
				
				User currRecord = new User(records[0], records[1], records[2], records[3]);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static User loadUser(String userName) {
		
		if (allUsers.isEmpty()) {
			loadUsers();
		}
		
		for (User e: allUsers) {
			if (e.getUsername().compareTo(userName) == 0) {
				return e;
			}
		}
		
		return null;
	}

}
