package com.dtucdio3.digitalshop2.entity;
import javax.persistence.*;

@Entity
@Table(name = "Image",
	uniqueConstraints =  {@UniqueConstraint(columnNames = {"id"}) })
public class Image {
	private int id;
	private Product product;
	private String imageUrl;
	
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
	@JoinColumn(name = "productId")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Column(name = "imageUrl")
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}
