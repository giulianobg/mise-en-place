package sb.mep.api;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="interval")
public class Interval implements Serializable {
	
	@Id
	@Column(name="minutes")
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
	
	@JsonIgnore
	public String getName() {
		return name;
	}
	
	@JsonProperty(value="name")
	public void setName(String name) {
		this.name = name;
	}
	
}