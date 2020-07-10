package com.dtucdio3.digitalshop2.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "promotionDetail",
		uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class PromotionDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startingDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endingDate;
	private float discount;
	@NotBlank(message = "Quà tặng không được để trống.")
	@Size(min = 6, message = "Độ dài tối thiểu 6 ký tự.")
	private String gift;
	@ManyToMany(mappedBy = "promotionDetails")
	private Set<Product> products;

	public PromotionDetail() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(LocalDate startingDate) {
		this.startingDate = startingDate;
	}

	public LocalDate getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(LocalDate endingDate) {
		this.endingDate = endingDate;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public String getGift() {
		return gift;
	}

	public void setGift(String gift) {
		this.gift = gift;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
