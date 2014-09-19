package sb.mep.api;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="preparations")
public class Preparation {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sq_preparations")
	@SequenceGenerator(name="sq_preparations",sequenceName="sq_preparations", allocationSize=1)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="interval")
	private Interval interval;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="dish")
	private Dish dish;
	
	private String description;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Interval getInterval() {
		return interval;
	}
	
	public void setInterval(Interval interval) {
		this.interval = interval;
	}
	
	@JsonIgnore
	public Dish getDish() {
		return dish;
	}
	
	@JsonProperty(value="dish")
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
