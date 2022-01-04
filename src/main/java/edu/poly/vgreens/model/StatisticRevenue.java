package edu.poly.vgreens.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StatisticRevenue {

	@Id
	private Integer year;
	private Integer mouth;
	private Double revenue;
	private Long countOrder;
	private Double discount;
}
