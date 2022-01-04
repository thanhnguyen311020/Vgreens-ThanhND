package edu.poly.vgreens.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProductPostKey implements Serializable{

	 	@Column(name = "product_id")
	    Integer product_id;

	    @Column(name = "postfoods_Id")
	    Integer postfoods_Id;
}
