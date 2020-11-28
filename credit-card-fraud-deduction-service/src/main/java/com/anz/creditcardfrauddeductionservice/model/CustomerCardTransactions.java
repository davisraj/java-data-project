package com.anz.creditcardfrauddeductionservice.model;

import org.springframework.stereotype.Component;

/**
 * @author rdavis
 * 
 *         This model class written to define card transaction message
 *         structure.
 *
 */
@Component
public class CustomerCardTransactions {

	public CustomerCardTransactions() {
		super();
	}

	@Override
	public String toString() {

		return "CustomerCardTransactions{" + "customerId='" + customerId + '\'' + ", date='" + date + '\''
				+ ", transactionDesciption='" + transactionDesciption + '\'' + ", city='" + city + '\'' + ", amount='"
				+ amount + '\'' + '}';

	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTransactionDesciption() {
		return transactionDesciption;
	}

	public void setTransactionDesciption(String transactionDesciption) {
		this.transactionDesciption = transactionDesciption;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	private String customerId;
	private String date;
	private String transactionDesciption;
	private String city;
	private double amount;

}
