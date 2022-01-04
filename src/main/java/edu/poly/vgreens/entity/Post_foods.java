package edu.poly.vgreens.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "Post_foods")
public class Post_foods implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer postfoods_Id;
	
	@Column(nullable = true, length = 255)
	private String post_title;
	
	@Column(nullable = true, length = 255)
	private String post_url;
	
	@Column(nullable = false,columnDefinition = "TEXT")
	private String post_note;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String post_description;
	
	@Column(nullable = false, length = 255)
	private String post_image;
	
	@Column(nullable = false)
	private String post_view;
	
	@Column(nullable = false)
	Date created_time;
	Date updated_time;
	
	@JsonIgnore
    @OneToMany(mappedBy = "post_foods")
     Set<Product_foods_tag> Post_food_tag;
     
     
     @Transient // thuộc tính không liên quan đến database
     private String img;




}
