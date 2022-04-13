package tiago.j61.starsimulator.bo;

import tiago.j61.starsimulator.dao.UsuarioDao;
import tiago.j61.starsimulator.model.Usuario;

public class UsuarioBo {
	private UsuarioDao usuarioDao;

	public boolean validUser(String user_name, String pass) {
		if (validUserName(user_name) && validPassWord(pass))
			return false;
		return true;
	}

	public static boolean validUserName(String user_name) {
		// Caso não encontre usuario, o username é valido
		System.out.println();
		if (UsuarioDao.getUsuarioDao().findByUserName(user_name) == null) {
			return true;
		}
		return false;
	}

	public static boolean validPassWord(String pass) {
		if (pass.length() > 8) {
			return true;
		}
		return false;
	}

	public Long insert(String user, String pass) {
		if (validUser(user, pass)) {
			Usuario entity= new Usuario();
			entity.setUserName(user);
			entity.setPassword(pass);
			return UsuarioDao.getUsuarioDao().insert(entity);
		}
		return null;
	}

}
