package tiago.j61.starsimulator.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;

import tiago.j61.starsimulator.database.TableName;
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

	/*
	 * Query:
	 * 
	 * SELECT * FROM USUARIO WHERE NOME=?
	 * 
	 */
	public Usuario findByUserName(String userName) {
		try {
			EntityManager em = Conexao.getEntityManager();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("* ");
			sql.append("FROM ");
			sql.append(TableName.getJPAName(Usuario.class));
			sql.append("WHERE ");
			sql.append(TableName.getNamedField(Usuario.class, 2)).append("=? ");
			sql.append("LIMIT 1");
			System.out.println(sql.toString());

			Usuario usuario = (Usuario) em.createNativeQuery(sql.toString()).setParameter(1, userName)
					.getSingleResult();
			return usuario;
		} catch (NoResultException e) {
			return null;
		}
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

	public Long insert(Usuario entity) {
		EntityManager em = Conexao.getEntityManager();

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ");
		sql.append(TableName.getJPAName(Usuario.class));
		sql.append("(");
		sql.append(TableName.getNamedField(Usuario.class, 2)).append(",");
		sql.append(TableName.getNamedField(Usuario.class, 3));
		sql.append(")");
		sql.append("VALUES");
		sql.append("(");
		sql.append("?").append(",");
		sql.append("?");
		sql.append(")");
		sql.append("RETURNING ").append(TableName.getNamedField(Usuario.class, 1));
		Long id = ((BigInteger) em.createNativeQuery(sql.toString()).setParameter(1, entity.getUserName())
				.setParameter(2, entity.getPassword()).getSingleResult()).longValue();
		return id;
	}
}
