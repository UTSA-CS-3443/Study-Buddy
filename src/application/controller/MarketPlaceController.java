package application.controller;
import application.model.StudySession;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class MarketPlaceController{

    @FXML
    private ComboBox<String> subjectComboBox;

    @FXML
    private ComboBox<String> locationComboBox;

    @FXML
    private Button searchButton;

    @FXML
    private ListView<StudySession> studySessionsListView;

    @FXML
    private Label userNameLabel;
    @FXML 
    public void handleMouseClick(MouseEvent arg0) {
    	//if the session is selected, then display a button to go to studysessionview.
        System.out.println("clicked on " + studySessionsListView.getSelectionModel().getSelectedItem().getName());
        selectButton.setVisible(true);
        
    }
    @FXML
    private Button selectButton;

    @FXML
    void selectButtonPressed(ActionEvent event) {
    	//go to studysessionview.
    }

    @FXML
    void searchButtonClicked(ActionEvent event) {

    }
    
    public void initialize() {
    	StudySession.loadSubjects();
       	StudySession.loadLocations();
       	
       	locationComboBox.setItems(StudySession.locations);
       	subjectComboBox.setItems(StudySession.subjects);
       	
    	ObservableList<StudySession> sessions = StudySession.loadSessions();
    	
    	//sort the sessions//if not already sorted.
    	//studySessionsListView.setItems(sessions);
    	//System.out.println(sessions.toString());
    	/*studySessionsListView.setCellFactory(new Callback<ListView<StudySession>, ListCell<StudySession>>() {
    	    @Override
    	    public ListCell<StudySession> call(ListView<StudySession> studySessionListView) {
    	        return new ListViewCellController();
    	    }
    	});*/
    }

}
