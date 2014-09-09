package sb.mep.dao;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import sb.mep.api.Dish;
import sb.mep.api.Event;

import com.google.common.base.Optional;

public class DishDao extends AbstractDAO<Dish> {
	
	private SessionFactory factory;
	
	public DishDao(SessionFactory factory) {
		super(factory);
		this.factory = factory;
	}

	public Optional<Dish> findById(Long id) {
		Session s = factory.openSession();
		try {
			return Optional.fromNullable(get(id));
		} finally {
			s.close();
		}
	}

	public Dish create(Dish dish) {
		Session s = factory.openSession();
		try {
			return persist(dish);
		} finally {
			s.close();
		}
	}

	public List<Dish> findAll() {
		Session s = factory.openSession();
		try {
			Criteria c = s.createCriteria(Dish.class);
			return c.list();
		} finally {
			s.close();
		}
	}
	
}
