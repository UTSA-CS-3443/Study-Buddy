package application.controller;
import java.io.IOException;

import application.Main;
import application.model.StudySession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

public class ListViewCellController extends ListCell<StudySession>{

    @FXML
    private GridPane mainCellPane;

    @FXML
    private Label sessionName;

    @FXML
    private Label sessionLocation;

    @FXML
    private Label numOfMembers;

    @FXML
    private Label sessionSubject;
    
    private FXMLLoader loader;
    @Override
	protected
    void updateItem(StudySession s,boolean empty) {
    	super.updateItem(s,empty);
    	loader = new FXMLLoader(Main.class.getResource("view/CreateSessionView.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	sessionName.setText(s.getName());
    	sessionLocation.setText(s.getLocation());
    	sessionSubject.setText(s.getSubject());
    	numOfMembers.setText(Integer.toString(s.getSessionMembers().size()));
    }

}
