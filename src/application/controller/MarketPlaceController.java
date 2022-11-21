package application.controller;
import application.model.StudySession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

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
    	//sort the sessions//if not already sorted.
    	studySessionsListView.setItems(StudySession.allSessions);
    	studySessionsListView.setCellFactory(new Callback<ListView<StudySession>, ListCell<StudySession>>() {
    	    @Override
    	    public ListCell<StudySession> call(ListView<StudySession> studySessionListView) {
    	        return new ListViewCellController();
    	    }
    	});
    	
    	//if the session is selected, then display a button
    }

}
