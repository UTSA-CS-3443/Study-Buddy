package application.controller;

import application.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	static User currUser;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label invalidLabel;

    @FXML
    void createAccountButtonPress(ActionEvent event) {
    	
    }

    @FXML
    void loginButtonPress(ActionEvent event) {
    	
    }

}
