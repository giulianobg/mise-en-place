package sb.mep.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import sb.mep.api.Interval;

import com.google.common.base.Optional;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class IntervalDao extends DefaultDao<Interval> {
	
	public IntervalDao(SessionFactory factory) {
		super(factory);
	}

	public Optional<Interval> findById(Long id) {
		Session s = getFactory().openSession();
		try {
			Interval interval = (Interval) s.get(Interval.class, id);
			return Optional.fromNullable(interval);
		} finally {
			s.close();
		}
	}

	public List<Interval> findAll() {
		Session s = getFactory().openSession();
		try {
			Criteria c = s.createCriteria(Interval.class);
			return c.list();
		} finally {
			s.close();
		}
	}
	
}
