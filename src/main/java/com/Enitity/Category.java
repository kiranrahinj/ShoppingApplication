package com.Enitity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	
	@Column(name = "category_title")
	private String categoryTitle;
	
	@Column(name = "category_description")
	private String categoryDescription;
	
	@OneToMany(mappedBy="category" )
	private List<Product>products=new ArrayList<>();
	
}
