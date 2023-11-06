package com.Enitity;

import java.sql.Blob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pId;
	
	@Column(name = "product_name")
	private String pName;
	
	@Column(name = "product_desc")
	private String pDesc;
	
	@Column(name = "product_photo")
	@Lob
    private Blob pPhoto;
	
	@Column(name = "product_price")
	private String pPrice;
	
	@Column(name = "product_discount")
	private String pDiscount;
	
	@Column(name = "product_quantity") 
	private String pQuantity;
	
	@ManyToOne
	private Category category;
}
