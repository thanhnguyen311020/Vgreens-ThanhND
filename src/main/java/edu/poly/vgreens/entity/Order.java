package edu.poly.vgreens.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class Order  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 45, nullable = false )
    String first_name;
    @Column(length = 45, nullable = false )
    String last_name;
    @Column(length = 15, nullable = false )
    String phone_number;
    @Column(length =64, nullable = false )
    String address_line;
   
    @Column(length =45, nullable = true )
    String city;
    @Column(length =45, nullable = false )
    String state;
    @Column(length =45, nullable = false )
    String country;
    @Column(length =10, nullable = false )
    String postal_code;
   
    @Column(name = "Createdate")
    Date createDate = new Date();
    Float amount;
    Float discount;
    Integer status;
    @Column(length = 45, nullable = true )
    String payment_method;
    
    
    Integer deliver_days;
    Date deliver_date;
    
    Float sub_total;
    Float shipping_cost;
    @Column(length =1000, nullable = true )
    String description;
    
    
    @Column(name="payment_Id",length =100, nullable = true )
    String payment_Id;
    @Column(name="payment_status",length =50, nullable = false )
    String payment_status;
    
 
    @JoinColumn(name = "coupon_id", nullable = true)
    Integer coupon_id;

    @ManyToOne
    @JoinColumn(name = "username")
    Account account;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;
    
    @JsonIgnore
    @OneToMany(mappedBy = "order")
    Set<OrderTrack> orderTracks;
}