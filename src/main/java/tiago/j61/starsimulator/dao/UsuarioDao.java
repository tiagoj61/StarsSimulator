package tiago.j61.starsimulator.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tiago.j61.starsimulator.model.Usuario;

public class UsuarioDao {
	private static UsuarioDao usuarioDao;
	private UsuarioDao() {
	}
	public synchronized static UsuarioDao getUsuarioDao() {
		if (usuarioDao == null)
			usuarioDao = new UsuarioDao();
		return usuarioDao;
	}
	
	public Usuario findByUserName(String userName) {
		EntityManager em = Conexao.getEntityManager();
		StringBuilder sql = new StringBuilder("select aggkind from pg_aggregate");
		System.out.println(em.createNativeQuery(sql.toString()).getResultList().size());
		return (Usuario) new Usuario();
	}

	public void salvarAtualizar(Usuario usuario) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();

		if (usuario.getId() == null) {
			usuario = em.merge(usuario);
		}
		em.persist(usuario);

		em.getTransaction().commit();
		em.close();
	}

	public void excluir(Usuario usuario) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();

		usuario = em.merge(usuario);

		em.remove(usuario);

		em.getTransaction().commit();
		em.close();
	}

	public List<Usuario> pesquisar(Usuario usuario) {
		EntityManager em = Conexao.getEntityManager();
		StringBuilder sql = new StringBuilder("from usuario where 1=1 ");
		usuario = em.merge(usuario);

		if (usuario.getId() != null) {
			sql.append("and usuario.id=? ");
		}
		return em.createQuery(sql.toString()).setParameter(1, usuario.getId()).getResultList();
	}
}
