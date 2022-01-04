package edu.poly.vgreens.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import edu.poly.vgreens.entity.Product;
import edu.poly.vgreens.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductReviewGroupByProductID implements Serializable{
	
	@Id
	Product product;
	Double ratingCount;
	
	 
	
}
