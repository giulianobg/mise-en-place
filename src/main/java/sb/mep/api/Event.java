package sb.mep.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="events")
public class Event implements Serializable {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sq_events")
	@SequenceGenerator(name="sq_events",sequenceName="sq_events", allocationSize=1)
	private Long id;
	
	private String name;
	
	private Date date;
	
	@OneToMany(mappedBy="event")
	private List<Dish> dishes;
	
	public Event() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public List<Dish> getDishes() {
		return dishes;
	}
	
	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

}
