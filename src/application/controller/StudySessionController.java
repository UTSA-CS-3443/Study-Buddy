/**
 * Controls the Study Session View
 * @author Team 5
 */
package application.controller;

import application.Main;
import application.model.StudySession;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class StudySessionController {
	public static StudySession currSession;

    @FXML
    private Button editSessionButton;

    @FXML
    private Button backButton;

    @FXML
    private Label sessionNameLabel;

    @FXML
    private Label ownerNameLabel;

    @FXML
    private Label subjectClassLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private ListView<User> participantListView;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Button joinSessionButton;

    @FXML
    private Button leaveSessionButton;
    
    @FXML
    private Label locationDetailLabel;

    /**
     * loads the previous view, saving the data from the description box
     * @param event ActionEvent, loaded by FXML
     */
    @FXML
    void backButtonPressed(ActionEvent event) {
    	
    	currSession.setDescription(descriptionTextArea.getText());
    	//TODO: Add code to update record in all Sessions data
    	StudySession.updateRecords();
    	
    	try {
	    	FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./view/MarketPlaceView.fxml"));
			MarketPlaceController controller = new MarketPlaceController();
			loader.setController(controller);
			
			BorderPane layout = (BorderPane) loader.load();
			
			Scene scene = new Scene(layout);
			Main.stage.setScene(scene);
			Main.stage.setTitle(StudySessionController.currSession.getName());
			currSession = null;
			Main.stage.show();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }	

    /**
     * loads the edit Session view, saving the current description data. 
     * @param event ActionEvent, loaded by FXML
     */
    @FXML
    void editSessionButtonPressed(ActionEvent event) {
    	
    	currSession.setDescription(descriptionTextArea.getText());
    	//TODO: Add code to update record in all Sessions data
    	StudySession.updateRecords();
    	
      	try {
	       	
	    	FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./view/CreateSessionView.fxml"));
			CreateNewSessionController controller = new CreateNewSessionController();
			loader.setController(controller);
			
			BorderPane layout = (BorderPane) loader.load();
			
			Scene scene = new Scene(layout);
			Main.stage.setScene(scene);
			Main.stage.setTitle(StudySessionController.currSession.getName());
			Main.stage.show();
	   	}
	   	catch (Exception e) {
	   		e.printStackTrace();
	   	}
    }
    
    /**
     * adds user to session Member list, validates and prevent user from joining multiple times
     * @param event ActionEvent, loaded by FXML
     */
    @FXML
    void joinSessionButtonPress(ActionEvent event) {
    	//If current user is not already in the class
    	boolean isInClass = false;
    	for (User u: currSession.getSessionMembers()) {
    		if (LoginController.currUser.getUsername().compareTo(u.getUsername()) == 0) {
    			isInClass = true;
    		}
    	}
    	
    	if (!isInClass) {
    		currSession.addSessionMember(LoginController.currUser);
    	}
    }

    /**
     * removes user from sessionMembers list, validates that user is in the class.
     * @param event ActionEvent, loaded by FXML
     */
    @FXML
    void leaveSessionButtonPress(ActionEvent event) {
    	boolean isInClass = false;
    	for (User u: currSession.getSessionMembers()) {
    		if (LoginController.currUser.getUsername().compareTo(u.getUsername()) == 0) {
    			isInClass = true;
    		}
    	}
    	
    	if (isInClass) {
    		currSession.getSessionMembers().remove(LoginController.currUser);
    	}
    }
    
    /**
     * loads in all the various data, setting labels and filling in user list, also sets the various button visibility based on user's access. 
     */
    public void initialize() {
    	sessionNameLabel.setText(currSession.getName());
    	ownerNameLabel.setText(currSession.getOwner().getFirstName() + " " + currSession.getOwner().getLastName()); // need getters for user name
    	subjectClassLabel.setText(currSession.getSubject() + " " + currSession.getClassNumber());
    	locationLabel.setText(currSession.getLocation());
    	locationDetailLabel.setText(currSession.getLocationDetail());
    	descriptionTextArea.setText(currSession.getDescription());
    	
    	participantListView.setItems(currSession.getSessionMembers());
    	
    	//is not owner disable edit button
    	if (currSession.getOwner().getUsername().compareTo(LoginController.currUser.getUsername()) != 0) {
    		editSessionButton.setVisible(false);
    	}
    	else { // if owner, disable join and leave buttons
    		joinSessionButton.setVisible(false);
    		leaveSessionButton.setVisible(false);
    	}
    }

}

