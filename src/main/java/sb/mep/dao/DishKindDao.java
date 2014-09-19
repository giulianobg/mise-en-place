package sb.mep.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import sb.mep.api.DishKind;

import com.google.common.base.Optional;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class DishKindDao extends DefaultDao<DishKind> {
	
	public DishKindDao(SessionFactory factory) {
		super(factory);
	}

	public Optional<DishKind> findById(Long id) {
		Session s = getFactory().openSession();
		try {
			DishKind dishKind = (DishKind) s.get(DishKind.class, id);
			return Optional.fromNullable(dishKind);
		} finally {
			s.close();
		}
	}

	public List<DishKind> findAll() {
		Session s = getFactory().openSession();
		try {
			Criteria c = s.createCriteria(DishKind.class);
			return c.list();
		} finally {
			s.close();
		}
	}
	
}
