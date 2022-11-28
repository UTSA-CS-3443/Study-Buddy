package application.controller;
import application.Main;
import application.model.StudySession;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class MarketPlaceController{

    @FXML
    private Button createNewSessionButton;
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
    void CreateNewSessionButtonClicked(ActionEvent event) {
    	try {
	    	FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(Main.class.getResource("./view/CreateSessionView.fxml"));
	    	CreateNewSessionController controller = new CreateNewSessionController();
	    	loader.setController(controller);
	    	
	    	BorderPane layout = (BorderPane) loader.load();
	    	
	    	Scene scene = new Scene(layout);
	    	Main.stage.setScene(scene);
	    	Main.stage.setTitle("Create a Session");
	    	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
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
    	ObservableList<StudySession> sessions = StudySession.loadSessions();
       	locationComboBox.setItems(StudySession.locations);
       	subjectComboBox.setItems(StudySession.subjects);
       	
    	//sort the sessions//if not already sorted.
       	studySessionsListView.setItems(sessions);
    	studySessionsListView.setCellFactory(new Callback<ListView<StudySession>, ListCell<StudySession>>() {
    	    @Override
    	    public ListCell<StudySession> call(ListView<StudySession> studySessionListView) {
    	        return new ListViewCellController();
    	    }
    	});
    	
    studySessionsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
    	public void handle(MouseEvent event) {
            System.out.println("clicked on " + studySessionsListView.getSelectionModel().getSelectedItem().getName());
            selectButton.setVisible(true);
        }
    });
    }

}
