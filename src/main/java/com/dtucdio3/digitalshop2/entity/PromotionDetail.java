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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "promotionDetail",
		uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class PromotionDetail {
	private int id;
	private LocalDate startingDate;
	private LocalDate endingDate;
	private float discount;
	private String gift;
	private Set<Promotion> promotion = new HashSet<Promotion>();
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "startingDate")
	public LocalDate getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(LocalDate startingDate) {
		this.startingDate = startingDate;
	}
	
	@Column(name = "endingDate")
	public LocalDate getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(LocalDate endingDate) {
		this.endingDate = endingDate;
	}
	
	@Column(name = "discount")
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	
	@Column(name = "gift")
	public String getGift() {
		return gift;
	}
	public void setGift(String gift) {
		this.gift = gift;
	}
	
	@OneToMany
	@JoinColumn(name = "promotionDetailId", referencedColumnName = "id")
	@JsonBackReference
	public Set<Promotion> getPromotion() {
		return promotion;
	}
	public void setPromotion(Set<Promotion> promotion) {
		this.promotion = promotion;
	}
}
