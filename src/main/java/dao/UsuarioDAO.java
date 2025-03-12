package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Usuario;

public class UsuarioDAO {
	private final EntityManager em;
	
	public UsuarioDAO(EntityManager em) {
		this.em = em;
	}
	
	public Usuario insert(Usuario user) {
		em.persist(user);
		return user;
	}
	
	public Usuario update(Usuario user) {
		em.merge(user);
		em.persist(user);
		return user;
	}
	
	public Usuario insertOrUpdate(Usuario user) {
		if (user.getId() > 0) {
			return this.update(user);
		} 
		return this.insert(user);
	}
	
	public void delete(Usuario user) {
		em.merge(user);
		em.remove(user);
	}
	
	public Usuario selectPorId(Usuario user) {
		 /* String jpql = "select u from Usuario as u " + "where u.id = :pId";
		Query query = em.createQuery(jpql);
		query.setParameter("pId", user.getId());
		return retornarListaComBaseNaConsulta(query).get(0); */
		return em.find(Usuario.class, user.getId());
	}
	
	public List<Usuario> selectAll() {
		String jpql = "select u from Usuario u";
		TypedQuery<Usuario> cq = em.createQuery(jpql, Usuario.class);
		return retornarListaComBaseNaConsulta(cq);
	}

	private List<Usuario> retornarListaComBaseNaConsulta(TypedQuery<Usuario> cq) {
		List<Usuario> usuarios;
		try {
			usuarios = cq.getResultList();
		} catch (NoResultException e) {
			usuarios = new ArrayList<Usuario>();
		}
		return usuarios;
	}
	
	public boolean existeNoBancoPorUsuarioESenha(Usuario user) {
		String jpql = "select u from Usuario as u " + "where u.usuario = :pUsuario and u.senha = :pSenha";
		TypedQuery<Usuario> cq = em.createQuery(jpql, Usuario.class);
		cq.setParameter("pUsuario", user.getUsuario());
		cq.setParameter("pSenha", user.getSenha());
		
		return !retornarListaComBaseNaConsulta(cq).isEmpty();
	}
	
}
