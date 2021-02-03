package com.qikserve.supermarket.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.qikserve.supermarket.model.Products;
import com.qikserve.supermarket.service.SupermarketService;




@RestController
@RequestMapping("/products")
public class SupermarketController {
	
	@Autowired
	private SupermarketService supermarketService;
	
	ResponseEntity<Page<Products>> findAll(@PageableDefault(sort = "sort", size = 20) Pageable pageable){
		var products  = supermarketService.findAll(pageable);
		return ResponseEntity.ok(products);
	}
	
	
	
	@GetMapping("/{id}")
	ResponseEntity<Optional<Products>> findAllById(@PathVariable String id){
		var product = supermarketService.findById(id);
		return ResponseEntity.ok(product);		
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Products> save(@RequestBody Products product) {
		var productSaved = supermarketService.save(product);
		var location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(productSaved.getId()).toUri();
		return ResponseEntity.created(location).body(productSaved);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Products> update(@RequestBody Products product) {
		var colaboradorSaved = supermarketService.save(product);
		var location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(colaboradorSaved.getId()).toUri();
		return ResponseEntity.created(location).body(colaboradorSaved);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Products> delete(@PathVariable String id) {
		supermarketService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	

}
