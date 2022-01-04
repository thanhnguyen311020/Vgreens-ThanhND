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
@Table(name = "Suppliers")
public class Supplier implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 200)
	private String name;
	@Column(nullable = false, length = 12)
	private String phone;
	@Column(nullable = false, length = 256)
	private String address;
	
	@JoinColumn(name = "created_time")
	private Date created_time = new Date();
	
	@JoinColumn(name = "updated_time")
	private Date update_time = new Date();
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "supplier")
	private Set<Consignment> consignments;

}
