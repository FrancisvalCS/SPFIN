package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Usuario;

public class UsuarioDAO {
	
	private static UsuarioDAO instance;
	protected EntityManager entityManager;
	
	public static UsuarioDAO getInstance() {
		if(instance == null) {
			instance = new UsuarioDAO();			
		}
		return instance;
	}
	
	public UsuarioDAO() {
		entityManager = getEntityManager();
	}
	
	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("gpfin");
		if(entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		
		return entityManager;
	}
	
	public Usuario getById(final int id) {
		return entityManager.find(Usuario.class, id);
	}
	
	public Usuario getUsuario(String usuario, String senha) {
		try {
			Usuario user = (Usuario) entityManager.createQuery(
		             "FROM "+Usuario.class.getName())
		         .setParameter("name", usuario)
		         .setParameter("senha", senha).getSingleResult();
		             
		          return user;
		}catch(Exception e) {
			return null;
		}
		
	}
	
	
	//Seleciona e armazena os dados em uma lista
	@SuppressWarnings("unchecked")
	public List<Usuario> findAll(){
		return entityManager.createQuery("FROM "+Usuario.class.getName()).getResultList();
	}
	
	
	//Persiste os dados no banco
	public void persist(Usuario user) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.getTransaction().commit();
		}catch(Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void merge(Usuario user) {
        try {
           entityManager.getTransaction().begin();
           entityManager.merge(user);
           entityManager.getTransaction().commit();
        } catch (Exception ex) {
           ex.printStackTrace();
           entityManager.getTransaction().rollback();
        }
     }
	
	public void remove(Usuario user) {
		try {
			entityManager.getTransaction().begin();
			user = entityManager.find(Usuario.class, user.getId());
			entityManager.remove(user);
			entityManager.getTransaction().commit();
		}catch(Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void removeById(final int id) {
		try {
			Usuario user = getById(id);
			remove(user);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
