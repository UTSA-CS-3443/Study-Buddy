package application.controller;

import application.Main;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class CreateNewUserController{

    @FXML
    private Button backButton;

    @FXML
    private TextField firstNameInputText;

    @FXML
    private TextField lastNameInputText;

    @FXML
    private TextField userNameInputText;

    @FXML
    private TextField passwordInputText;

    @FXML
    private TextField confirmPasswordInputText;

    @FXML
    private Button createAccountButton;

    @FXML
    private Label errorLabel;

    @FXML
    void backButtonClicked(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource("view/loginView.fxml") );
			LoginController controller = new LoginController();
			loader.setController(controller);
			BorderPane layout = (BorderPane) loader.load();
			Scene scene = new Scene( layout );
			Main.stage.setScene(scene);
			Main.stage.setTitle("Welcome");
        	Main.stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void createAccountButtonClicked(ActionEvent event) {
    	if(passwordInputText.getText().equals(confirmPasswordInputText.getText())) {
    		if(passwordInputText.getText()!="") {
        		if(userNameInputText.getText()!="") {
        			if(lastNameInputText.getText()!="") {
        				if(firstNameInputText.getText()!="") {
        					//validate user name is not already in use 
        					//if its not in use 
        					 // create new user
        					 // add user to csv file 
        					try {
        						User newUser = new User(userNameInputText.getText(),
        								passwordInputText.getText(), firstNameInputText.getText(),
        								lastNameInputText.getText());
        						errorLabel.setTextFill(Color.web("#000000"));
        						errorLabel.setText("Account Created!");
        						User.updateUsers();
        			
        					} catch (Exception e) {
        						errorLabel.setTextFill(Color.web("#FF0000"));
        						errorLabel.setText("ERROR: Username is already taken!");
        						e.printStackTrace();
        					}
        					 
        				} else {
        					errorLabel.setTextFill(Color.web("#FF0000"));
                			errorLabel.setText("ERROR: First name field is empty!");
        				}
        			}else {
        				errorLabel.setTextFill(Color.web("#FF0000"));
            			errorLabel.setText("ERROR: Last name field is empty!");
        			}
        		}else {
        			errorLabel.setTextFill(Color.web("#FF0000"));
        			errorLabel.setText("ERROR: username field is empty!");
        		}
    		} else {
    			errorLabel.setTextFill(Color.web("#FF0000"));
    			errorLabel.setText("ERROR: password field is empty!");
    		}
    		
    	} else {
    		errorLabel.setTextFill(Color.web("#FF0000"));
    		errorLabel.setText("ERROR: password fields do not match!");
    	}
    }

}
