package com.dtucdio3.digitalshop2.entity;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "Category",
	uniqueConstraints =  {@UniqueConstraint(columnNames = {"id"}) })
public class Category {
	
	private int id;
	private String name;
	private String description;
	private Set<Product> products = new HashSet<Product>();
	
	protected Category() {
		super();
	}

	protected Category(int id) {
		super();
		this.id = id;
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

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	                                                                                    
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name = "catId", referencedColumnName="id")
	@JsonBackReference
	public Set<Product> getProducts(){
		return products;
	}
	
	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
