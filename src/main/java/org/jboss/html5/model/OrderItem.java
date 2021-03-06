package org.jboss.html5.model;
// Generated 25-Sep-2016 11:42:16 by Hibernate Tools 4.0.1.Final

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OrderItem generated by hbm2java
 */
@Entity
@Table(name = "order_item", catalog = "4fashion")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 348631842007728509L;
	
	private long orderItemId;
	private Order order;
	private Product product;

	public OrderItem() {
	}

	public OrderItem(long orderItemId, Order order, Product product) {
		this.orderItemId = orderItemId;
		this.order = order;
		this.product = product;
	}

	@Id
	@Column(name = "ORDER_ITEM_ID", unique = true, nullable = false)
	public long getOrderItemId() {
		return this.orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_FK", nullable = false)
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
