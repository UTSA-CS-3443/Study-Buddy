package application.controller;

import application.Main;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label invalidLabel;
    
    public static User currUser;
    

    @FXML
    void createAccountButtonPress(ActionEvent event) {
    	try {
	    	FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(Main.class.getResource("./view/CreateNewUserView.fxml"));
	    	CreateNewUserController controller = new CreateNewUserController();
	    	loader.setController(controller);
	    	
	    	GridPane layout = (GridPane) loader.load();
	    	
	    	Scene scene = new Scene(layout);
	    	Main.stage.setScene(scene);
	    	Main.stage.setTitle("Create Account");
	    	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void loginButtonPress(ActionEvent event) {
    	if (User.validate(usernameField.getText(), passwordField.getText())) {
    		
    		currUser = User.loadUser(usernameField.getText());
    		
    		try {
    	    	FXMLLoader loader = new FXMLLoader();
    	    	loader.setLocation(Main.class.getResource("./view/MarketPlaceView.fxml"));
    	    	MarketPlaceController controller = new MarketPlaceController();
    	    	loader.setController(controller);
    	    	
    	    	BorderPane layout = (BorderPane) loader.load();
    	    	
    	    	Scene scene = new Scene(layout);
    	    	Main.stage.setScene(scene);
    	    	Main.stage.setTitle("MarketPlace");
    	    	
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
    	}
    	else {
    		invalidLabel.setTextFill(Color.web("#FF0000"));
			invalidLabel.setText("Invalid username or password");
    	}
    }

}