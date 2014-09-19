package sb.mep.api;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="dish_kinds")
public class DishKind implements Serializable {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sq_dish_kinds")
	@SequenceGenerator(name="sq_dish_kinds",sequenceName="sq_dish_kinds", allocationSize=1)
	private Long id;
	
	private String name;
	
//	private List<Preparation> preparations;
	
//	@OneToMany(mappedBy="dish")
//	private List<Preparation> preparations;
	
	public DishKind() {
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
	
//	public List<Preparation> getPreparations() {
//		return preparations;
//	}
//	
//	public void setPreparations(List<Preparation> preparations) {
//		this.preparations = preparations;
//	}
	
	@Override
	public String toString() {
		return "[DISH KIND id=" + getId() + "] " + getName();
	}
	
}
