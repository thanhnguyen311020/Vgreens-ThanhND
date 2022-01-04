package edu.poly.vgreens.entity;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Product_foods_tag")
public class Product_foods_tag {

	@EmbeddedId
	private ProductPostKey id;

	@ManyToOne
	@MapsId("product_id")
	@JoinColumn(name = "id")
	private Product product;

	@ManyToOne
	@MapsId("postfoods_Id")
	@JoinColumn(name = "postfoods_Id")
	private Post_foods post_foods;

}
