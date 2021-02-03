package com.qikserve.supermarket.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qikserve.supermarket.model.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
	
	@Autowired
	SupermarketService supermarketService;
	
	  private String idProduct;
	  private int quantity;

	  
	    public Item(String idProduct, int quantity) {
	    	this.idProduct = idProduct;
	    	this.quantity = quantity;
	    }
	    
	   
	    public double getValorItemTotal() {
	    	
	    	return supermarketService.priceProduct(this.idProduct, this.quantity); 
	    }

	    public double getValueSaved() {
	    	return supermarketService.savedMoney(this.idProduct, this.quantity);
	    }
	    
	    public Optional<Products> ProductById(String id) {
	    	return supermarketService.findById(id);
	    }

}
