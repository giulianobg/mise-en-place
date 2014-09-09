package sb.mep.dao;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import sb.mep.api.Event;

import com.google.common.base.Optional;

public class PreparationDao extends AbstractDAO<Event> {
	
	private SessionFactory factory;
	
	public PreparationDao(SessionFactory factory) {
		super(factory);
		this.factory = factory;
	}

	public Optional<Event> findById(Long id) {
		Session s = factory.openSession();
		try {
			return Optional.fromNullable(get(id));
		} finally {
			s.close();
		}
	}

	public Event create(Event event) {
		Session s = factory.openSession();
		try {
			return persist(event);
		} finally {
			s.close();
		}
	}

	public List<Event> findAll() {
		Session s = factory.openSession();
		try {
			Criteria c = s.createCriteria(Event.class);
			return c.list();
		} finally {
			s.close();
		}
	}
	
}
