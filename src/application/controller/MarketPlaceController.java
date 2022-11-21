package application.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class MarketPlaceController{

    @FXML
    private ComboBox<?> subjectComboBox;

    @FXML
    private ComboBox<?> locationComboBox;

    @FXML
    private Button searchButton;

    @FXML
    private ListView<?> studySessionsListView;

    @FXML
    private Label userNameLabel;

    @FXML
    void searchButtonClicked(ActionEvent event) {

    }

}
