package com.tutorialspoint.SpringBoot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductServiceController {
	private static Map<String, Product> productRepository = new HashMap<>();
	static {
		Product honey = new Product();
		honey.setId("1");
		honey.setName("Honey");
		productRepository.put(honey.getId(), honey);
		
		
		Product almond = new Product();
		almond.setId("2");
		almond.setName("Almond");
		productRepository.put(almond.getId(), almond);
	}
	
	@RequestMapping(value="/products/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Product> delete(@PathVariable("id") String id){
		productRepository.remove(id);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/products/{ìd}", method=RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
		if ( !productRepository.containsKey(id) )
			throw new ProductException();
		productRepository.remove(id);
		product.setId(id);
		productRepository.put(id, product);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		productRepository.put(product.getId(), product);
		return new ResponseEntity<Product>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/products")
	public ResponseEntity<Collection<Product>> getProduct(){
		return new ResponseEntity<Collection<Product>>(productRepository.values(), HttpStatus.OK);
	}

}
