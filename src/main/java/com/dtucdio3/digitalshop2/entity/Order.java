package com.dtucdio3.digitalshop2.entity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Orders",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Order {
	private int id;
	private User user;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate createDay;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate deliveryDay;
	@Size(min = 6, message = "Độ dài tối thiểu 6 ký tự.")
	@NotBlank(message = "Trạng thái không được để trống")
	private String status;
	private Set<OrderDetail> detail = new HashSet<OrderDetail>();

	public Order() {
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Set<OrderDetail> getDetail() {
		return detail;
	}
	public void setDetail(Set<OrderDetail> detail) {
		this.detail = detail;
	}

	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addOrderDetail(OrderDetail orderDetail) {
		detail.add(orderDetail);
	}
}
