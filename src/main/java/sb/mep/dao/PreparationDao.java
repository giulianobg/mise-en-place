package sb.mep.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import sb.mep.api.Dish;
import sb.mep.api.Interval;
import sb.mep.api.Preparation;

import com.google.common.base.Optional;

public class PreparationDao extends DefaultDao<Preparation> {
	
	public PreparationDao(SessionFactory factory) {
		super(factory);
	}

	public Optional<Preparation> findById(Long id) {
		Session s = getFactory().openSession();
		try {
			Preparation prep = (Preparation) s.get(Preparation.class, id);
			return Optional.fromNullable(prep);
		} finally {
			s.close();
		}
	}

	public List<Preparation> findAll() {
		Session s = getFactory().openSession();
		try {
			Criteria c = s.createCriteria(Preparation.class);
			return c.list();
		} finally {
			s.close();
		}
	}
	
	@Override
	public Preparation create(Preparation elm) {
		System.out.println(elm);
		
		System.out.println(elm.getDish());
		System.out.println(elm.getInterval());
		
		Dish dish = Daos.get(DishDao.class).findById(elm.getDish().getId()).get();
		Interval interval = Daos.get(IntervalDao.class).findById(elm.getInterval().getMinute()).get();
		
		elm.setDish(dish);
		elm.setInterval(interval);
		
		return super.create(elm);
	}
	
}
