package tiago.j61.starsimulator.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {
	private static EntityManagerFactory emf;
	private static Conexao conexao;

	private Conexao() {
		emf = Persistence.createEntityManagerFactory("ConexaoJPA");
	}

	public synchronized static EntityManager getEntityManager() {
		if (conexao == null)
			conexao = new Conexao();
		return emf.createEntityManager();
	}
}
