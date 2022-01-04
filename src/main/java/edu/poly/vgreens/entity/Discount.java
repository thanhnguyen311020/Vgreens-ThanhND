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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Discounts")
public class Discount implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 255, nullable = false)
	private String name_discount;
	@Column(length = 255, nullable = true)
	private String discount_image;

	@Column(name = "percent_discount", nullable = false)
	private Integer percent_discount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	Date startDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	Date endDate = new Date();

//	 private Date days_discount;
//
//	private Date created_time;
	private Date updated_time;
	@Column(name = "status")
	private Boolean status;
	@Column(length = 1000, nullable = true)
	private String discount_description;

	@JsonIgnore
	@OneToMany(mappedBy = "discount")
	Set<Product> products;

	@Transient // thuộc tính không liên quan đến database
	private String img;

}
