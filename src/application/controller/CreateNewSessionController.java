package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateNewSessionController {

    @FXML
    private TextField sessionNameField;

    @FXML
    private MenuButton subjectMenuButton;

    @FXML
    private TextField courseNumberField;

    @FXML
    private MenuButton locationMenuButton;

    @FXML
    private TextArea locationDetailField;

    @FXML
    private Label errorField;

    @FXML
    void cancelButtonPress(ActionEvent event) {
    	
    }

    @FXML
    void createSessionButtonPress(ActionEvent event) {

    }

}
