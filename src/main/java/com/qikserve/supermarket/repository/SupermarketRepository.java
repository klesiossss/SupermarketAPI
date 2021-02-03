package com.qikserve.supermarket.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.qikserve.supermarket.model.Products;

@Repository
public interface SupermarketRepository extends MongoRepository<Products, String> {
			
		Optional<Products>findById(String id);
}
