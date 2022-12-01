package application.controller;

import application.Main;
import application.model.StudySession;
import javafx.collections.FXCollections;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

/**
 * @author Ryan S, Ryan P, Ian Amaya
 *
 */
public class MarketPlaceController {

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

	/**
	 * @param event
	 * loads the create new session view
	 */
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

	/**
	 * @param arg0 follows click for session element.
	 */
	public void handleMouseClick(MouseEvent arg0) {
		// if the session is selected, then display a button to go to studysessionview.
		System.out.println("clicked on " + studySessionsListView.getSelectionModel().getSelectedItem().getName());
		selectButton.setVisible(true);

	}

	@FXML
	private Button selectButton;

	/**
	 * @param gets selected session and loads the view for that session.
	 */
	@FXML
	void selectButtonPressed(ActionEvent event) {
		if (studySessionsListView.getSelectionModel().getSelectedItem() != null) {
			// go to studysessionview.
			StudySessionController.currSession = studySessionsListView.getSelectionModel().getSelectedItem();
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("./view/StudySessionView.fxml"));
				StudySessionController controller = new StudySessionController();
				loader.setController(controller);

				AnchorPane layout = (AnchorPane) loader.load();

				Scene scene = new Scene(layout);
				Main.stage.setScene(scene);
				Main.stage.setTitle("Create a Session");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param event - search button clicked
	 * makes sure the filters are not null
	 * if not, it then actively searches session list for certain criteria
	 */
	@FXML
	void searchButtonClicked(ActionEvent event) {
		// search only by location
		// search only by subject
		// search by both

		ObservableList<StudySession> sessions = FXCollections.observableArrayList();
		if (subjectComboBox.getValue()!=null && locationComboBox.getValue()==null) {
			for (StudySession s : StudySession.allSessions) {
				if (s.getSubject().equals(subjectComboBox.getValue())) {
					sessions.add(s);
				}
			}

			// by location
		} else if (subjectComboBox.getValue()==null
				&& locationComboBox.getValue()!=null) {
			for (StudySession s : StudySession.allSessions) {
				if (s.getLocation().equals(subjectComboBox.getValue())) {
					sessions.add(s);
				}
			}
			// both
		} else if (subjectComboBox.getValue()!=null
				&& locationComboBox.getValue()!=null) {
			for (StudySession s : StudySession.allSessions) {
				if (s.getSubject().equals(subjectComboBox.getValue())
						&& s.getLocation().equals(locationComboBox.getValue())) {
					sessions.add(s);
				}
			}
			// they are null
		} else {
			for (StudySession s : StudySession.allSessions) {
				sessions.add(s);
			}
		}
		//if (sessions.size() > 0) {
			studySessionsListView.setItems(sessions);
			studySessionsListView.setCellFactory(new Callback<ListView<StudySession>, ListCell<StudySession>>() {
				@Override
				public ListCell<StudySession> call(ListView<StudySession> studySessionListView) {
					return new ListViewCellController();
				}
			});
		//}
		locationComboBox.getSelectionModel().clearSelection();
		subjectComboBox.getSelectionModel().clearSelection();
		
	}

	/**
	 * loads sessions and creates each individual fxml element for the list view.
	 * also creates a listener to tell if an element is clicked.
	 */
	public void initialize() {
		ObservableList<StudySession> sessions = StudySession.loadSessions();
		StudySession.loadSubjects();
		locationComboBox.setItems(StudySession.locations);
		subjectComboBox.setItems(StudySession.subjects);

		// sort the sessions//if not already sorted.
		studySessionsListView.setItems(sessions);
		studySessionsListView.setCellFactory(new Callback<ListView<StudySession>, ListCell<StudySession>>() {
			@Override
			public ListCell<StudySession> call(ListView<StudySession> studySessionListView) {
				return new ListViewCellController();
			}
		});

		studySessionsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(studySessionsListView.getSelectionModel().getSelectedItem() != null)
					selectButton.setVisible(true);
			}
		});
	}

}
