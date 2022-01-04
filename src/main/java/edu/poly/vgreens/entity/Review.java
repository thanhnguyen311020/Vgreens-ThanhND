package edu.poly.vgreens.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Product_reviews")
public class Review implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
	
	@ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
	
	 @Column(length = 24, nullable = false)
	 private String username;
	 
	 @Column(length = 100, nullable = false)
	 private String title;
	 
	 @Column(length = 500, nullable = false)
	 private String comment;
	 
	 @Column( nullable = false)
	 private Float rating;
	 
	 @Column(name = "order_id")
	 private Integer order_id;
	 
	 @Column( nullable = false)
	 private Date created_time;
		private Date updated_time;
}
