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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Consignments")
public class Consignment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100, nullable = false)
	private String name;
	@JoinColumn(name = "purchase_price", nullable = false)
	private Float purchase_price;

	@JoinColumn(name = "selling_price", nullable = false)
	private Float selling_price;

	@JoinColumn(name = "number_of_inputs", nullable = false)
	private Integer numberOfInputs;

	@JoinColumn(name = "number_of_sales", nullable = false)
	private Integer numberOfSales = 0;

	@JoinColumn(name = "number_of_returns", nullable = false)
	private Integer numberOfReturns = 0;

	@JoinColumn(name = "current_quantity", nullable = false)
	private Integer currentQuantity;
	
	  @Column( nullable = false)
	private Date created_time = new Date();
	
	private Date updated_time = new Date();
	 
	  @Column( nullable = false)
	private Date expiry_time;
	private Boolean status;

	@JsonIgnore
	@OneToMany(mappedBy = "consignment")
	Set<Product> products;
	

	@ManyToOne
	@JoinColumn(name = "supplier_id")
	Supplier supplier;
}
