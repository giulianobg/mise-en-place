package sb.mep.api;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="interval")
public class Interval implements Serializable {
	
	private Long minute;
	private String name;
	
	public Interval() {
	}
	
	public Long getMinute() {
		return minute;
	}
	
	public void setMinute(Long minute) {
		this.minute = minute;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}