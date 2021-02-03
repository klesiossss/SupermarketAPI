package com.qikserve.supermarket.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "Supermarket")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Products {

	
    private String id;
    private String name;
    private double price;
    private Promotions promotions;
    
  
}