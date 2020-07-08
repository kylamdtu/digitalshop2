package com.dtucdio3.digitalshop2.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "promotion")
public class Promotion {

	@Embeddable
	public class PromotionIdentify implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		@Column(name = "productId")
		private int productId;
		@Column(name = "promotionDetailId")
		private int promotionDetailId;
		
		@ManyToOne
		@JoinColumn(name = "productId")
		@JsonManagedReference
		public int getProductId() {
			return productId;
		}
		
		public void setProductId(int productId) {
			this.productId = productId;
		}
		
		@ManyToOne
		@JoinColumn(name = "promotionDetailId")
		@JsonManagedReference
		public int getPromotionDetailId() {
			return promotionDetailId;
		}
		public void setPromotionDetailId(int promotionDetailId) {
			this.promotionDetailId = promotionDetailId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + productId;
			result = prime * result + promotionDetailId;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PromotionIdentify other = (PromotionIdentify) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (productId != other.productId)
				return false;
			if (promotionDetailId != other.promotionDetailId)
				return false;
			return true;
		}

		private Promotion getEnclosingInstance() {
			return Promotion.this;
		}

	}
	
	@EmbeddedId
	private PromotionIdentify id;

	@ManyToOne
	@JoinColumns({@JoinColumn(name = "productId"), @JoinColumn(name = "promotionDetailId")})
	@JsonManagedReference
	public PromotionIdentify getId() {
		return id;
	}

	public void setId(PromotionIdentify id) {
		this.id = id;
	}
	
	
}
