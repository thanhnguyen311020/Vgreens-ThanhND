 package edu.poly.vgreens.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SuppressWarnings("serial")

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Products")
public class Product  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(length = 64,nullable = false)
    String name;
    @Column(nullable = false)
    String image;
    @Column(nullable = false, length = 256) 
    String description;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    Date createDate = new Date();
    
    @Temporal(TemporalType.DATE)
    @Column(name = "update_date")
    Date updateDate = new Date();
    
    @Column(nullable = false)
    Boolean available;
    @Column(nullable = true)
    Boolean available_discount;
    
    @ManyToOne
    @JoinColumn(name = "Categoryid", nullable = false)
    Category category;
    
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<OrderDetail> orderDetails;
    
   
    @ManyToOne
    @JoinColumn(name = "consignment_id", nullable = false) 
    Consignment consignment;
    
    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false) 
    Unit unit;
    
    @JsonIgnore
    @OneToMany(mappedBy = "product")
     Set<Review> reviews;
    
    @JsonIgnore
    @OneToMany(mappedBy = "product")
     Set<Product_foods_tag> Post_food_tag;
    
    
	@ManyToOne
	@JoinColumn(name = "discount_id",nullable = true)
	Discount discount;
	
	@Transient
	String img;
}

