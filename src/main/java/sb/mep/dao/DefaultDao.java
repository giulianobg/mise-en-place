package sb.mep.dao;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.common.base.Optional;

public abstract class DefaultDao<T> extends AbstractDAO<T> {
	
	private SessionFactory factory;
	
	public DefaultDao(SessionFactory factory) {
		super(factory);
		this.factory = factory;
	}

	public abstract Optional<T> findById(Long id);
	
//	public Optional<T> findById(Long id) {
//		Session s = factory.openSession();
//		try {
//			T e = (T) s.get(T, id);
//			return Optional.fromNullable(e);
//		} finally {
//			s.close();
//		}
//	}

	public T create(T elm) {
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.save(elm);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}
		return elm;
	}

	public abstract List<T> findAll();
	
//	public List<T> findAll() {
//		Session s = factory.openSession();
//		try {
//			Criteria c = s.createCriteria(T.class);
//			return c.list();
//		} finally {
//			s.close();
//		}
//	}
	
	public T update(T elm) {
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.update(elm);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}
		return elm;
	}
	
	public T delete(T elm) {
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.delete(elm);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		} finally {
			s.close();
		}
		return elm;
	}
	
	protected SessionFactory getFactory() {
		return factory;
	}

}
