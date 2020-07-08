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
@Table(name = "OrderDetail")
public class OrderDetail {
	
	@Embeddable
	public class OrderDetailIdentity implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Column(name = "orderId")
		private int orderId;
		@Column(name = "productId")
		private int productId;
		
		
		
		public int getOrderId() {
			return orderId;
		}
		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}
		public int getProductId() {
			return productId;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + orderId;
			result = prime * result + productId;
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
			OrderDetailIdentity other = (OrderDetailIdentity) obj;
			if (orderId != other.orderId)
				return false;
			if (productId != other.productId)
				return false;
			return true;
		}
		
		
	}
	
	@EmbeddedId
	private OrderDetailIdentity id;
	private int quantity;
	
	@Column(name = "quantity")
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@ManyToOne
	@JoinColumns({@JoinColumn(name = "orderId"),
					@JoinColumn(name = "productId")})
	@JsonManagedReference
	public OrderDetailIdentity getId() {
		return id;
	}
	public void setId(OrderDetailIdentity id) {
		this.id = id;
	}
	
}
