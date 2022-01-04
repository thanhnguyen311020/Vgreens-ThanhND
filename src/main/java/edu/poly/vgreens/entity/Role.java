package edu.poly.vgreens.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "roles")
public class Role implements Serializable{
	 	@Id
	    private String id;
	 	
	 	@Column(nullable = false)
	    private String name;
	 	
	    @JsonIgnore
	    @OneToMany(mappedBy = "role")
	    List<Authority> authorities;

}
