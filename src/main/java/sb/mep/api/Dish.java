package sb.mep.api;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="dishes")
public class Dish implements Serializable {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sq_dishes")
	@SequenceGenerator(name="sq_dishes",sequenceName="sq_dishes", allocationSize=1)
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy="dish")
	private List<Preparation> preparations;
	
	@ManyToOne
	@JoinColumn(name="id_event")
	private Event event;
	
	@ManyToOne
	@JoinColumn(name="kind")
	private DishKind kind;
	
	public Dish() {
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
	
	public List<Preparation> getPreparations() {
		return preparations;
	}
	
	public void setPreparations(List<Preparation> preparations) {
		this.preparations = preparations;
	}
	
	public DishKind getKind() {
		return kind;
	}
	
	public void setKind(DishKind kind) {
		this.kind = kind;
	}
	
	public Event getEvent() {
		return event;
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}
	
}
