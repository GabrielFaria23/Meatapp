package br.com.meatapp.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Orders implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	@Column(name="data")
	private LocalDateTime data;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="restaurant_id")
	private Restaurant restaurant;
	
	@NotNull
	@Column(name="address")
	private String address;
	
	@NotNull
	@Column(name="number")
	private String number;
	
	@Column(name="optionalAddress")
	private String optionalAddress;
	
	@NotNull
	@Column(name="paymentOption")
	private String paymentOption;
	
	@OneToMany(mappedBy="id.orders", cascade=CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();

	public Orders() {
		
	}

	public Orders(Integer id, LocalDateTime data, User user, Restaurant restaurant, String address,
			 String number, String optionalAddress, String paymentOption) {
		super();
		this.id = id;
		this.data = data;
		this.user = user;
		this.restaurant = restaurant;
		this.address = address;
		this.number = number;
		this.optionalAddress = optionalAddress;
		this.paymentOption = paymentOption;
	}
	
	public double getTotal() {
		double sum = 0;
		for (OrderItem orderItem : orderItems) {
			sum = sum+ orderItem.getSubTotal();
		}
		return sum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getOptionalAddress() {
		return optionalAddress;
	}

	public void setOptionalAddress(String optionalAddress) {
		this.optionalAddress = optionalAddress;
	}

	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
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
		Orders other = (Orders) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
