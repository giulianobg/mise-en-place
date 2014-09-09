package sb.mep.api;

import java.io.Serializable;
import java.util.List;

public class DishKind implements Serializable {
	
	private Long id;
	private String name;
	private List<Preparation> preparations;
	
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
	
	public List<Preparation> getPreparations() {
		return preparations;
	}
	
	public void setPreparations(List<Preparation> preparations) {
		this.preparations = preparations;
	}
	
}
