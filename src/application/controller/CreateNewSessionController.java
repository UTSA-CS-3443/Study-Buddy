/**
 * Controls the createNewSession FXML view
 * 
 * @author Team 5
 */
package application.controller;

import java.io.IOException;

import application.Main;
import application.model.StudySession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class CreateNewSessionController {

    @FXML
    private TextField sessionNameField;

    @FXML
    private ComboBox<String> courseComboBox;

    @FXML
    private TextField courseNumberField;

    @FXML
    private ComboBox<String> locationComboBox;

    @FXML
    private TextArea locationDetailField;

    @FXML
    private Button createSessionButton;

    @FXML
    private Label errorField;
    
    @FXML
    private Button deleteSessionButton;

    /**
     * Controls the actions taken when the cancel button is pressed. 
     * Loads the studySessionView if no active session, otherwise loads MarketPlaceView
     * @param event ActionEvent, loaded by FXML
     */
    @FXML
    void cancelButtonPress(ActionEvent event) {
    	try { 
    	
	    	if (StudySessionController.currSession != null) { //Loads the study session view if there is an active session
	    		FXMLLoader loader = new FXMLLoader();
	    		loader.setLocation(Main.class.getResource("./view/StudySessionView.fxml"));
	    		StudySessionController controller = new StudySessionController();
	    		loader.setController(controller);
	    		
	    		AnchorPane layout = (AnchorPane) loader.load();
	    		
	    		Scene scene = new Scene(layout);
	    		Main.stage.setScene(scene);
	    		Main.stage.setTitle(StudySessionController.currSession.getName());
	    		Main.stage.show();
	    	} else {    // Loads marketplace view if no active session
	    		FXMLLoader loader = new FXMLLoader();
	    		loader.setLocation(Main.class.getResource("./view/MarketPlaceView.fxml"));
	    		MarketPlaceController controller = new MarketPlaceController();
	    		loader.setController(controller);
	    		
	    		BorderPane layout = (BorderPane) loader.load();
	    		
	    		Scene scene = new Scene(layout);
	    		Main.stage.setScene(scene);
	    		Main.stage.setTitle("MarketPlace");
	    		Main.stage.show();
	    	}  	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    /**
     * Controls functions when the create new Session or the Update session button is pressed.
     * If a Active Session is set, the function will update the record, if not a new session is created. 
     * @param event ActionEvent, loaded by FXML
     */
    @FXML
    void createSessionButtonPress(ActionEvent event) {
    	int courseNum = Integer.parseInt(courseNumberField.getText());
    	
    	if (StudySessionController.currSession != null) { //If there is an active Session
    		//checking for differences, updating if different
    		if (courseComboBox.getValue().compareTo(StudySessionController.currSession.getSubject()) != 0) {
    			StudySessionController.currSession.setSubject(courseComboBox.getValue());
    		}
    		
    		if (Integer.parseInt(courseNumberField.getText()) != StudySessionController.currSession.getClassNumber() ) {
    			StudySessionController.currSession.setClassNumber(Integer.parseInt(courseNumberField.getText()));
    		}
    		
    		if (locationComboBox.getValue().compareTo(StudySessionController.currSession.getLocation()) != 0) {
    			StudySessionController.currSession.setLocation(locationComboBox.getValue());
    		}
    		
    		if (locationDetailField.getText().compareTo(StudySessionController.currSession.getLocationDetail()) != 0) {
    			StudySessionController.currSession.setLocationDetail(locationDetailField.getText());
    		}
    		
    		//Loads the updated session in SessionView
    		try {
	    		FXMLLoader loader = new FXMLLoader();
		    	loader.setLocation(Main.class.getResource("./view/StudySessionView.fxml"));
		    	StudySessionController controller = new StudySessionController();
		    	loader.setController(controller);
		    	
		    	AnchorPane layout = (AnchorPane) loader.load();
		    	
		    	Scene scene = new Scene(layout);
		    	Main.stage.setScene(scene);
		    	Main.stage.setTitle(StudySessionController.currSession.getName());
		    	Main.stage.show();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	else { //if there is no active Session
	    	try { 
		    	StudySession newSession = new StudySession(sessionNameField.getText(), LoginController.currUser, courseComboBox.getValue(), 
		    			courseNum, locationComboBox.getValue(), locationDetailField.getText());
		    	StudySessionController.currSession = newSession;
		    	StudySession.allSessions.add(newSession);
		    	StudySession.updateRecords();
		    	
		    	FXMLLoader loader = new FXMLLoader();
		    	loader.setLocation(Main.class.getResource("./view/StudySessionView.fxml"));
		    	StudySessionController controller = new StudySessionController();
		    	loader.setController(controller);
		    	
		    	AnchorPane layout = (AnchorPane) loader.load();
		    	
		    	Scene scene = new Scene(layout);
		    	Main.stage.setScene(scene);
		    	Main.stage.setTitle(StudySessionController.currSession.getName());
		    	Main.stage.show();
		    }
	    	catch(IllegalArgumentException e) { // this is thrown by the StudySession Constructor when invalid input is given
	    		errorField.setText(e.getMessage());
	    		errorField.setVisible(true);
	    	}
	    	catch (IOException e) {
	    		e.getStackTrace();
	    	}
    	}
    }
    
    /**
     * Controls events when hitting the delete button 
     * removes the session from the all Sessions master list, removes from the active session slot, 
     * updates CSV records to reflect change, loads marketplace.
     * @param event
     */
    @FXML
    void deleteSessionButton(ActionEvent event) {
    	for (StudySession e: StudySession.allSessions) {
    		if (e.getName().compareTo(StudySessionController.currSession.getName()) == 0) {
    			StudySession.allSessions.remove(e);
    			break;
    		}
    	}
    	
	    StudySessionController.currSession = null;
	    StudySession.updateRecords();
	    	
	    try { 
	    	FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./view/MarketPlaceView.fxml"));
			MarketPlaceController controller = new MarketPlaceController();
			loader.setController(controller);
			
			BorderPane layout = (BorderPane) loader.load();
			
			Scene scene = new Scene(layout);
			Main.stage.setScene(scene);
			Main.stage.setTitle("MarketPlace");
			Main.stage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Sets up the menu in either edit mode (if there is an active session) or loads menu for creating new session.
     */
    public void initialize() {
    	StudySession.loadSubjects();
       	StudySession.loadLocations();
       	
       	locationComboBox.setItems(StudySession.locations);
       	courseComboBox.setItems(StudySession.subjects);
    	
    	//Setup for editing current session
    	if (StudySessionController.currSession != null) {
    		createSessionButton.setText("Update Session");
    		sessionNameField.setText(StudySessionController.currSession.getName());
    		sessionNameField.setEditable(false);
    		courseComboBox.setValue(StudySessionController.currSession.getSubject());
    		courseNumberField.setText(String.format("%d", StudySessionController.currSession.getClassNumber()));
    		locationComboBox.setValue(StudySessionController.currSession.getLocation());
    		locationDetailField.setText(StudySessionController.currSession.getLocationDetail());
    		deleteSessionButton.setVisible(true);
    	}
    }
}





