package tiago.j61.starsimulator.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import tiago.j61.starsimulator.controller.App;
import tiago.j61.starsimulator.dao.UsuarioDao;

public class MainController implements Initializable {
	private UsuarioDao usuarioDao;
	private ObservableList<String> set = FXCollections.observableArrayList();

	@FXML
	private Button login_but;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		set.addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable observable) {
				System.out.println("aQUIIi");
			}
		});
		// TODO Auto-generated method stub
		
	}
	@FXML
	private void switchToLogin() throws IOException {
		//usuarioDao = new UsuarioDao();
		System.out.println(usuarioDao);
		set.add("teste");
		App.setRoot("/tiago/j61/login_page");
	}
	@FXML
	private void switchToSign() throws IOException {
		usuarioDao = new UsuarioDao();
		System.out.println(usuarioDao);
		set.add("teste");
		App.setRoot("/tiago/j61/sign_page");
	}

}
