package application.controller;
import java.util.ArrayList;

import application.model.StudySession;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MarketPlaceController{

    @FXML
    private ComboBox<?> subjectComboBox;

    @FXML
    private ComboBox<?> locationComboBox;

    @FXML
    private Button searchButton;

    @FXML
    private ListView<StudySession> studySessionsListView;

    @FXML
    private Label userNameLabel;

    @FXML
    void searchButtonClicked(ActionEvent event) {

    }
    
    void initialize() {
    	//ObservableList<StudySession> sessions = StudySession.loadSessions();
    	//sort sessions
    	//write to the 
    	/*ObservableList<StudySession> arr = //observableList;
    	for(StudySession r : arr) {
    		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        	workContactsTable.getItems().add(r);
    	}*/
    	//listen for current selectd object // then show select button after element is selected.
    }

}
