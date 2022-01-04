package edu.poly.vgreens.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "Advertisements")
public class Advertisement implements Serializable{
	@Id
	String id ;
	String title;
	String title1;
	@Column(name = "title_btn")
	String titleBtn;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	Date startDate = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	Date endDate = new Date();
	
	String decrepsiton;
	String image;
	Integer discount;
	
	@Transient
	String img;
}
