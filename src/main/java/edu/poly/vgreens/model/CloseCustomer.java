package edu.poly.vgreens.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import edu.poly.vgreens.entity.Account;
import edu.poly.vgreens.entity.Order;
import edu.poly.vgreens.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CloseCustomer implements Serializable{
	
	@Id
	String username;
	Double sumAmount;
//	Long CountOrder;
//	Long sumProduct;
	Account account;
}
