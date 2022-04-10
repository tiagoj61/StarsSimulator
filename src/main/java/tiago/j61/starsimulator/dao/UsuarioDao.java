package tiago.j61.starsimulator.dao;

import java.util.List;

import javax.persistence.EntityManager;

import tiago.j61.starsimulator.model.Usuario;

public class UsuarioDao {
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
