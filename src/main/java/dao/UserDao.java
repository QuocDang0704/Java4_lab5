package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entity.User;
import utils.JpaUtils;

public class UserDao {

	// add user DB
	public void insert(User user) {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();// handle add,up,dele

		try {
			trans.begin();

			em.persist(user);// save

			trans.commit();// complete the changes
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	// update user DB
	public void update(User user) {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();// handle add,up,dele

		try {
			trans.begin();

			em.merge(user);// save

			trans.commit();// complete the changes
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	// delete User DB
	public void delete(String userID) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();// handle add,up,dele

		try {
			trans.begin();

			User user = em.find(User.class, userID);
			if (user != null) {
				em.remove(user);
			} else {
				throw new Exception("User can not found");
			}

			trans.commit();// complete the changes
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	// find User by ID
	public User findById(String userID) {
		EntityManager em = JpaUtils.getEntityManager();
		User user = em.find(User.class, userID);
		return user;
	}

	// find All by User
	public List<User> findAll() {
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);

		return query.getResultList();
	}

	// find All by User page
	public List<User> findAll(int page, int pageSize) {
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		query.setFirstResult(page * pageSize);
		query.setMaxResults(pageSize);

		return query.getResultList();
	}

	public User checkLogin(String userId, String password) {
		EntityManager em = JpaUtils.getEntityManager();
		String jqpl = "select u from User u where u.id = :id and u.password = :Password";
		TypedQuery<User> query = em.createQuery(jqpl, User.class);
		query.setParameter("id", userId);
		query.setParameter("Password", password);

		return query.getSingleResult();
	}

	// find by Full name
	public List<User> findByFullName(String fullName) {
		EntityManager em = JpaUtils.getEntityManager();
		String jqpl = "select u from User u where u.fullname like :fullname";
		TypedQuery<User> query = em.createQuery(jqpl, User.class);

		query.setParameter("fullname", "%" + fullName + "%");

		return query.getResultList();
	}

	public int count(String fullName) {
		EntityManager em = JpaUtils.getEntityManager();
		String jqpl = "select count(u) from User u";
		Query query = em.createQuery(jqpl);

		return ((Long)query.getSingleResult()).intValue();
	}
}
