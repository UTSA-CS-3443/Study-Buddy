/**
 * Object class for User
 * 
 * @author Team 5
 */
package application.model;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class User {

	private static ArrayList<User> allUsers;
	private static final String userFilePath = "./data/UserData.csv";
	private String userName;
	private String lastName;
	private String firstName;
	private String password;
	
	/**
	 * gets list of all Users
	 * @return ArrayList<User>
	 */
	public static ArrayList<User> getAllUsers() {
		return allUsers;
	}

	/**
	 * Sets list of all Users
	 * @param allUsers ArrayList<User>
	 */
	public static void setAllUsers(ArrayList<User> allUsers) {
		User.allUsers = allUsers;
	}
	
	/**
	 * gets userName String
	 * @return String
	 */
	public String getUsername() {
		return userName;
	}

	/**
	 * sets userName String
	 * @param userName String
	 */
	public void setUsername(String userName) {
		this.userName = userName;
	}
	
	/**
	 * gets password String
	 * @return String
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * sets password String
	 * @param password String
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * gets firstName String
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * sets firstName String
	 * @param firstName String
	 */
	public void setFirstName(String firstName) {
			this.firstName = firstName;
	}
	
	/**
	 * gets lastName String
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * sets lastName String
	 * @param lastName String
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * Constructor for User object.
	 * @param userName String, serves as a unique identifier for User
	 * @param password String, serves as a unique identifier for User
	 * @param firstName string, user's first name
	 * @param lastName String, user's last name
	 * @throws IllegalArgumentException, thrown when a input is deemed invalid
	 */
	public User(String userName, String password, String firstName, String lastName) throws IllegalArgumentException {
		
		for (User e: allUsers) {
			if (e.getUsername().compareTo(userName) == 0) {
				System.out.println(e.getUsername());
				throw new IllegalArgumentException("Username already exists");
			}
		}
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		
		allUsers.add(this);
	}
	
	
	/**
	 * Loads Users and if file does not exist, one will be created
	 */
	public static void loadUsers() {
		
		//empties the list
		if (allUsers == null) {
			allUsers = new ArrayList<>();
		}
		
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
	
	/**
	 * Loads the User record for the userName being passed in
	 * @param userName String, username associated to record being loaded
	 * @return User
	 */
	public static User loadUser(String userName) {
		
		for (User e: allUsers) {
			if (e.getUsername().compareTo(userName) == 0) {
				return e;
			}
		}
		
		return null;
	}
	
	/**
	 * Validates that the password entered is matched to correct username
	 * @param userName, username entered by user
	 * @param password, password entered by user
	 * @return boolean
	 */
	public static boolean validate(String userName, String password) {
		for (User e: allUsers) {
			if (e.getUsername().compareTo(userName) == 0) {
				if (e.password.equals(password)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * updates CSV file by re-writing all the users in allUsers list to the .csv file
	 */
	public static void updateUsers() {
		try {
			File dataFile = new File(userFilePath);
			FileWriter writer = new FileWriter(dataFile, false);
			 
			for (User e: allUsers) {
				String record = String.format("%s,%s,%s,%s,", e.userName, e.password, e.firstName, e.lastName);
				record = record + "\n";
				writer.write(record);
			}
			writer.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * prints the users first and last name
	 */
	public String toString() {
		return firstName + " " + lastName;
	}
}


