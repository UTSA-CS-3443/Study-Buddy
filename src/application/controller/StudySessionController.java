package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class StudySessionController {

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
    private ListView<?> participantListView;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    void backButtonPressed(ActionEvent event) {

    }

    @FXML
    void editSessionButtonPressed(ActionEvent event) {

    }

}
