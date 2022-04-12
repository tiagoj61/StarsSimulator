package tiago.j61.starsimulator.bo;

import tiago.j61.starsimulator.dao.UsuarioDao;

public class UsuarioBo {
	private UsuarioDao usuarioDao;

	public boolean validUser(String user_name, String pass) {
		if(validUserName(user_name)&&validPassWord(pass));
		return false;
	}

	public static boolean validUserName(String user_name) {
		//Caso não encontre usuario, o username é valido
		if (UsuarioDao.getUsuarioDao().findByUserName(user_name) == null) {
			return true;
		}
		return false;
	}

	public static boolean validPassWord(String pass) {
		if(pass.length()>8) {
			return true;
		}
		return false;
	}

}
