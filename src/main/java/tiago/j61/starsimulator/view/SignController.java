package tiago.j61.starsimulator.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import tiago.j61.starsimulator.bo.UsuarioBo;
import tiago.j61.starsimulator.controller.App;

public class SignController implements Initializable {
	@FXML
	TextField usuario_input;
	@FXML
	TextField senha_input;
	UsuarioBo usuarioBo;

	@FXML
	private void registerUser() throws IOException {
		if (usuarioBo.validUser(usuario_input.getText(), senha_input.getText()))
			App.setRoot("primary");
		
	}

	@FXML
	private void switchToMain() throws IOException {
		App.setRoot("primary");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		usuarioBo = new UsuarioBo();
		// TODO Auto-generated method stub

	}
}