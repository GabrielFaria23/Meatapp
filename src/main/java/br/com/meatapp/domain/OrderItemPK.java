package br.com.meatapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class OrderItemPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="orders_id")
	private Orders orders;
	
	@NotNull
	@Column(name="order_item_id")
	private Integer orderItemId;

	public OrderItemPK() {
		
	}
	
	public OrderItemPK(Orders orders, Integer orderItemId) {
		super();
		this.orders = orders;
		this.orderItemId = orderItemId;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	
	
}
