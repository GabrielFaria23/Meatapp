package br.com.meatapp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Restaurant implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(name="name")
	private String name;
	
	@NotNull
	@Column(name="category")
	private String category;
	
	@Column(name="deliveryEstimate")
	private String deliveryEstimate;
	
	@NotNull
	@Column(name="rating")
	private Double rating;
	
	@Column(name="imagePath")
	private String imagePath;
	
	@Column(name="hours")
	private String hours;
	
	@Column(name="about")
	private String about;
	
	@JsonIgnore
	@OneToMany(mappedBy="restaurant")
	private List<MenuItem> menuItems = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="restaurant")
	private List<Orders> orders = new ArrayList<>();
	
	public Restaurant() {
	}

	public Restaurant(Integer id, String name, String category, String deliveryEstimate,
			Double rating, String imagePath, String hours, String about) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.deliveryEstimate = deliveryEstimate;
		this.rating = rating;
		this.imagePath = imagePath;
		this.hours = hours;
		this.about = about;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDeliveryEstimate() {
		return deliveryEstimate;
	}

	public void setDeliveryEstimate(String deliveryEstimate) {
		this.deliveryEstimate = deliveryEstimate;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
