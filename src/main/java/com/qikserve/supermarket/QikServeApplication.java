package com.qikserve.supermarket;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


import com.qikserve.supermarket.model.Products;
import com.qikserve.supermarket.model.Promotions;
import com.qikserve.supermarket.model.Type;
import com.qikserve.supermarket.repository.SupermarketRepository;
import com.qikserve.supermarket.service.SupermarketService;
import com.qikserve.supermarket.service.Basket;
import com.qikserve.supermarket.service.Item;


@EnableMongoRepositories(basePackageClasses = SupermarketRepository.class)
@SpringBootApplication
public class QikServeApplication implements CommandLineRunner {

@Autowired 
private SupermarketRepository supermarketRepository;
@Autowired
private SupermarketService supermarketService;

public static void main(final String args[]) {
SpringApplication.run(QikServeApplication.class, args);
}


@Override
public void run(String... strings) throws Exception {
	supermarketRepository.deleteAll();
	

Type type = null;

final Promotions promoBurger = new Promotions("ZRAwbsO2qM",type.BUY_X_GET_Y_FREE,2,1);	
final Promotions promoPizza = new Promotions("ibt3EEYczW",type.QTY_BASED_PRICE_OVERRIDE,2,1799.0);
final Promotions promoSalad = new Promotions("Gm1piPn7Fg",type.FLAT_PERCENT,10);



final Products amazingBurger = new Products("PWWe3w1SDU","Amazing Burger",999.0,promoBurger);
final Products amazingPizza = new Products("Dwt5F7KAhi","Amazing Pizza",1099.0,promoPizza);
final Products amazingSalad = new Products("C8GDyLrHJb","Amazing Salad",499.0,promoSalad);
final Products boringFries = new Products("4MB7UfpTQs","Boring Fries",199.0,null);

supermarketService.save(amazingBurger);
supermarketService.save(amazingPizza);
supermarketService.save(amazingSalad);
supermarketService.save(boringFries);

System.out.println("Find all");
supermarketRepository.findAll().forEach(System.out::println);

System.out.println("Find by Id");
System.out.println(supermarketService.findById("PWWe3w1SDU"));




Item item1 = new Item(supermarketService,"PWWe3w1SDU",2);
Item item2 = new Item(supermarketService,"Dwt5F7KAhi",2);
Item item3 = new Item(supermarketService,"C8GDyLrHJb",2);
Item item4 = new Item(supermarketService,"4MB7UfpTQs",2);



List<Item> listaItem1 = new ArrayList<Item>();
List<Item> listaItem2 = new ArrayList<Item>();

listaItem1.add(item1);
listaItem1.add(item2);

listaItem2.add(item3);
listaItem2.add(item4);

/**
 * id dos Clientes e lista de itens
 * 
 * */
Basket basket1 = new Basket("123", listaItem1);
Basket basket2 = new Basket("321",listaItem2);


basket1.checkOut();
basket2.checkOut();


}
}