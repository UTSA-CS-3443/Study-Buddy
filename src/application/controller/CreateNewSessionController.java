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

    /**
     * TODO: untested
     * @param event
     */
    @FXML
    void cancelButtonPress(ActionEvent event) {
    	//load the new marketplace or load current session if current session is not loaded
    	try { 
    	
	    	if (StudySessionController.currSession != null) {
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
	    	else {
	    		FXMLLoader loader = new FXMLLoader();
	    		loader.setLocation(Main.class.getResource("./view/MarketPlaceView.fxml"));
	    		MarketPlaceController controller = new MarketPlaceController();
	    		loader.setController(controller);
	    		
	    		AnchorPane layout = (AnchorPane) loader.load();
	    		
	    		Scene scene = new Scene(layout);
	    		Main.stage.setScene(scene);
	    		Main.stage.setTitle(StudySessionController.currSession.getName());
	    		Main.stage.show();
	    	}  	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void createSessionButtonPress(ActionEvent event) {
    	int courseNum = Integer.parseInt(courseNumberField.getText());
    	
    	try { 
    		StudySession newSession = new StudySession(sessionNameField.getText(), LoginController.currUser, courseComboBox.getValue(), 
    				courseNum, locationComboBox.getValue(), locationDetailField.getText());
    		StudySessionController.currSession = newSession;
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
    	catch(IllegalArgumentException e) {
    		errorField.setText(e.getMessage());
    		errorField.setVisible(true);
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public void initialize() {
    	StudySession.loadSubjects();
       	StudySession.loadLocations();
       	
       	locationComboBox.setItems(StudySession.locations);
       	courseComboBox.setItems(StudySession.subjects);
    	
       	/*
       	//Keep the edit function??
    	//Setup for editing current session
    	if (StudySessionController.currSession != null) {
    		//Load the current Session data
    		
    		
    		createSessionButton.setText("Update Session");
    	}
    	*/
    }
}





