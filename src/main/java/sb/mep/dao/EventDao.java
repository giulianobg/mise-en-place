package sb.mep.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import sb.mep.api.Event;

import com.google.common.base.Optional;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class EventDao extends DefaultDao<Event> {
	
	public EventDao(SessionFactory factory) {
		super(factory);
	}

	public Optional<Event> findById(Long id) {
		Session s = getFactory().openSession();
		try {
			Event e = (Event) s.get(Event.class, id);
			return Optional.fromNullable(e);
		} finally {
			s.close();
		}
	}

	public List<Event> findAll() {
		Session s = getFactory().openSession();
		try {
			Criteria c = s.createCriteria(Event.class);
			return c.list();
		} finally {
			s.close();
		}
	}
	
}
