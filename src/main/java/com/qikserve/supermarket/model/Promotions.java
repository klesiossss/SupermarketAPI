package com.qikserve.supermarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Promotions {
	
	private String id;
	private Type type;
	private double price;
	private int required_qty;
	private int amount;
	private int free_qty;
	
	public Promotions(String id, Type type, int required_qty,int free_qty) {
		this.id = id;
		this.type = type;
		this.required_qty = required_qty;
		this.free_qty = free_qty;
	}
	
	public Promotions(String id, Type type, int required_qty, double price) {
		this.id = id;
		this.type = type;
		this.required_qty = required_qty;
		this.price = price;
	}

	public Promotions(String id, Type type, int amount) {
		this.id = id;
		this.type = type;
		this.amount = amount;
	}

}
