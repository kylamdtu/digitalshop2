package com.dtucdio3.digitalshop2.entity;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Orders",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Order {
	private int id;
	private Customer customer;
	private LocalDate createDay;
	private LocalDate deliveryDay;
	private String status;
	private Set<OrderDetail> detail = new HashSet<OrderDetail>();
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	@JsonManagedReference
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Column(name = "createDay")
	public LocalDate getCreateDay() {
		return createDay;
	}
	public void setCreateDay(LocalDate createDay) {
		this.createDay = createDay;
	}
	
	@Column(name = "deliveryDay")
	public LocalDate getDeliveryDay() {
		return deliveryDay;
	}
	public void setDeliveryDay(LocalDate deliveryDay) {
		this.deliveryDay = deliveryDay;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@OneToMany
	@JoinColumn(name = "orderId", referencedColumnName = "id")
	@JsonBackReference
	public Set<OrderDetail> getDetail() {
		return detail;
	}
	public void setDetail(Set<OrderDetail> detail) {
		this.detail = detail;
	}
	
}
