package tiago.j61.starsimulator.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tiago.j61.starsimulator.dao.UsuarioDao;
import tiago.j61.starsimulator.model.Usuario;

public class PrimaryController implements Initializable{
	private UsuarioDao usuarioDao;
private ObservableList<String> set= FXCollections.observableArrayList();
	@FXML
	private void switchToSecondary() throws IOException {
		usuarioDao = new UsuarioDao();
		System.out.println(usuarioDao);
		set.add("teste");
		//usuarioDao.salvarAtualizar(new Usuario());
		// App.setRoot("secondary");
	}

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
}
