package edu.poly.vgreens.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Coupons")
public class Coupon implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coupon_id;

    @Column(length = 255,nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer coupon_percent;

    @Column(length = 500,nullable = true)
    private String note;

    private Date created_time;

    private Date updated_time;
    
//    @JsonIgnore
//    @OneToMany(mappedBy = "coupon")
//    List<Order> orders;
}
