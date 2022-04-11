package tiago.j61.starsimulator.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tiago.j61.starsimulator.controller.App;

public class SignController implements Initializable {

	@FXML
	private void switchToMain() throws IOException {
		App.setRoot("primary");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}