package edu.poly.vgreens.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class CheckoutInfo implements Serializable {
	
	private int deliverDays;
	private Date deliverDate;
	private boolean codSupported;
	private float rate;
	public int getDeliverDays() {
		return deliverDays;
	}
	public void setDeliverDays(int deliverDays) {
		this.deliverDays = deliverDays;
	}
	public Date getDeliverDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.DATE, deliverDays);
		return calendar.getTime();
	}
	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}
	public boolean isCodSupported() {
		return codSupported;
	}
	public void setCodSupported(boolean codSupported) {
		this.codSupported = codSupported;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	
	
	
	

}
