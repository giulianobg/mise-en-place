package sb.mep.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import sb.mep.api.Dish;

import com.google.common.base.Optional;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class DishDao extends DefaultDao<Dish> {
	
	public DishDao(SessionFactory factory) {
		super(factory);
	}

	public Optional<Dish> findById(Long id) {
		Session s = getFactory().openSession();
		try {
			return Optional.fromNullable(get(id));
		} finally {
			s.close();
		}
	}

	public List<Dish> findAll() {
		Session s = getFactory().openSession();
		try {
			Criteria c = s.createCriteria(Dish.class);
			return c.list();
		} finally {
			s.close();
		}
	}
	
}
