package edu.poly.vgreens.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipping_rates")
public class ShippingRate implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column( nullable = false)
	private float rate;
	
	@Column(nullable = false)
	private int days;
	
	@Column(name = "cod_supported")
	private boolean codSupported;
	
	@ManyToOne
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;
	
	@Column(nullable = false,length = 45)
	private String state;
}
