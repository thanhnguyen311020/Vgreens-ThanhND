package edu.poly.vgreens.model;

import java.io.Serializable;
import java.util.Date;

import edu.poly.vgreens.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO implements Serializable{
	
	private String username;
	private String address_line;
	private String city;
	private String email;
	private Boolean enable;
	private String first_name;
	private String last_name;
	private String password;
	private String phone_number;
	private String photo;
	private String postal_code ="123";
	private Date register_date = new Date();
	private String state = "DN";
	private String verification_code;
	private Country conCountry = new Country();
}
