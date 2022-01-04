package edu.poly.vgreens.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import edu.poly.vgreens.entity.Order;
import edu.poly.vgreens.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StatisticalProduct implements Serializable{
	
	@Id
	private Product product;
	private Long countProduct;
	private Double sumUnitPrice;
	private Order order;
}
