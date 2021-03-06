package com.dtucdio3.digitalshop2.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Product",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"id"}) })
public class Product {
	private int id;
	private String name;
	private int quantity;
	private long price;
	private String description;
	private Category category;
	private Set<Image> images = new HashSet<Image>();
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
	private Set<PromotionDetail> promotionDetails = new HashSet<>();
	

	public Product() {
		super();
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
	@Size(max = 60, min = 6, message = "Độ dài tối thiểu 6 đến 60 ký tự.")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "quantity")
	@Range(min = 1, message = "Số lượng phải lớn hơn 0.")
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Column(name = "price")
	@Range(min = 1, message = "Giá phải lớn hơn 0.")
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	@Column(name = "description")
	@Size(min = 6, message = "Độ dài tối thiểu 6 ký tự.")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "catId")
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.EAGER)
	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	@OneToMany
	@JoinColumn(name = "productId")
	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}


	@ManyToMany
	@JoinTable(
			name = "promotion",
			joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "promotion_detail_id")
	)
	public Set<PromotionDetail> getPromotionDetails() {
		return promotionDetails;
	}

	public void setPromotionDetails(Set<PromotionDetail> promotionDetails) {
		this.promotionDetails = promotionDetails;
	}

	public void addPromotionDetail(PromotionDetail promotionDetail) {
		promotionDetails.add(promotionDetail);
	}

	public void addImage(Image image) {
		if (images == null) {
			images = new HashSet<>();
		}
		images.add(image);
	}

	public Image firstImage() {
		if (images != null) {
			if (images.isEmpty()){
				return null;
			}
			return images.iterator().next();
		}
		return null;
	}

	public PromotionDetail firstPromotion() {
		if (promotionDetails != null) {
			if (promotionDetails.isEmpty()) {
				return null;
			}
			return getPromotionDetails().iterator().next();
		}
		return null;
	}


	public long discountPrice() {
		return (long) (price - price*firstPromotion().getDiscount()/100);
	}

}
