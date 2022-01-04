package edu.poly.vgreens.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "Countries")
public class Country implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 45)
	private String name;
	
	@Column(nullable = false, length = 5)
	private String code;
	@JsonIgnore
	@OneToMany(mappedBy = "country")
	private Set<State> states;
	
	@JsonIgnore
	@OneToMany(mappedBy = "country")
	private Set<ShippingRate> shippingrates;
	
	@JsonIgnore
	@OneToMany(mappedBy = "country")
	List<Account> accounts;
}
