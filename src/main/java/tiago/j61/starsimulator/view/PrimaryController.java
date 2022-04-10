package tiago.j61.starsimulator.view;

import java.io.IOException;

import javafx.fxml.FXML;
import tiago.j61.starsimulator.dao.UsuarioDao;
import tiago.j61.starsimulator.model.Usuario;

public class PrimaryController {
	private UsuarioDao usuarioDao;

	@FXML
	private void switchToSecondary() throws IOException {
		usuarioDao = new UsuarioDao();
		System.out.println(usuarioDao);
		usuarioDao.salvarAtualizar(new Usuario());
		// App.setRoot("secondary");
	}
}
