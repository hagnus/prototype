package org.jboss.html5.model;
// Generated 25-Sep-2016 11:42:16 by Hibernate Tools 4.0.1.Final

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Buyer generated by hbm2java
 */
@Entity
@Table(name = "buyer", catalog = "4fashion")
public class Buyer implements Serializable {
	private static final long serialVersionUID = 2724499279479400062L;
	
	private long buyerId;
	private Person person;
	private Set<Order> orders = new HashSet<Order>(0);

	public Buyer() {
	}

	public Buyer(long buyerId, Person person) {
		this.buyerId = buyerId;
		this.person = person;
	}

	public Buyer(long buyerId, Person person, Set<Order> orders) {
		this.buyerId = buyerId;
		this.person = person;
		this.orders = orders;
	}

	@Id
	@Column(name = "BUYER_ID", unique = true, nullable = false)
	public long getBuyerId() {
		return this.buyerId;
	}

	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_FK", nullable = false)
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "buyer")
	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}
