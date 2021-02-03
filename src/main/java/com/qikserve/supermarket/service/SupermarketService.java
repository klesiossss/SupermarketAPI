package com.qikserve.supermarket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.qikserve.supermarket.model.Products;
import com.qikserve.supermarket.repository.SupermarketRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Service
public class SupermarketService {

	@Autowired
	private SupermarketRepository supermarketRepository;
	
	/**
	 * Serviço responsável por retornar os preços de produtos com base em seu id e quantidade,
	 * O backet no front end será responsável por listar esses itens solicitados
	 * 
	 * */
	public double priceProduct(String idProduct, int product_qty) {
		
		var prod = supermarketRepository.findById(idProduct);
		if(prod.get().getName().equals("Amazing Burger") && product_qty >= 2)
			return  prod.get().getPrice()*product_qty - prod.get().getPrice();
		else if (prod.get().getName().equals("Amazing Burger"))
			return prod.get().getPrice();
		else if(prod.get().getName().equals("Amazing Pizza") && product_qty >= 2)
			return  prod.get().getPrice() + (prod.get().getPromotions().getPrice()-prod.get().getPrice())*(product_qty-1) ;
		else if (prod.get().getName().equals("Amazing Pizza"))
			return prod.get().getPrice();	
		else if(prod.get().getName().equals("Amazing Salad"))
			return  (1-0.1)*(prod.get().getPrice())*product_qty;			
		else if (prod.get().getName().equals("Boring Fries"))
			return  prod.get().getPrice() * product_qty;		
						
		return 0;
		
	}
	

	public double savedMoney(String idProduct, int product_qty){
		var prod = supermarketRepository.findById(idProduct);
		if(prod.get().getName().equals("Amazing Burger")) 
			return prod.get().getPrice()*product_qty - prod.get().getPrice();
		else if(prod.get().getName().equals("Amazing Pizza"))
			return prod.get().getPrice()*product_qty-(prod.get().getPromotions().getPrice()-prod.get().getPrice())*product_qty;
		else if(prod.get().getName().equals("Amazing Salad"))
			return prod.get().getPrice()*product_qty - (1-0.1)*prod.get().getPrice()*product_qty;
		else if (prod.get().getName().equals("Boring Fries"))
			return prod.get().getPrice()-prod.get().getPrice();
		
		return 0;		
	}
	
	
	
	public Page<Products> findAll(Pageable pageable){
		return	supermarketRepository.findAll(pageable);
	}
	
	
	public Optional<Products> findById(String id){
		return supermarketRepository.findById(id);
		
	}
	
	
	/**
	 * Serviço responsável por cadastrar produtos e suas promoções
	 * */
	public Products save(Products product){
		return supermarketRepository.save(product);
	}
	
	public Products update(Products product){
		return supermarketRepository.save(product);
	}
	
	public void delete(String id) {
		supermarketRepository.deleteById(id);
		
	}
	
	
	
	
}
