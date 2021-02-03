package com.qikserve.supermarket.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qikserve.supermarket.model.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Basket {
	
	
	
	private String idCliente;
	private List<Item> itens;
	
	
	public void addItem(String idProduct, int quantity) {
		
		this.itens.add(new Item(idProduct,quantity));
		System.out.println("Novo item adicionado no Carrinho!");
		return;			
	}


	    
	public void removeItem(String idProduct) {
		for(int i = 0; i < this.itens.size(); i++) {
			if(this.itens.get(i).getIdProduct().equals(idProduct)) {
				System.out.println("Item removido com sucesso!");
				this.itens.remove(i);
			}
		}
	}


	public double getValorTotal() {
		
		double valueTotal = 0;
		for(Item item : itens)
			valueTotal+=  item.getValorItemTotal();	
		return valueTotal;
	}
	
	
	public double getValueSaved() {	
		
		double valueSaved = 0;
		for(Item item : itens) 
			valueSaved+= item.getValueSaved();  
		return valueSaved;
	}

	public void checkOut() {
		for(int i = 0; i < this.itens.size(); i++)
			 System.out.println(" O item "+ this.itens.get(i).getSupermarketService().findById(this.itens.get(i).getIdProduct()).get().getName()+" esta custando: "+ this.itens.get(i).getValorItemTotal()+ " e sua economia foi de "+ this.itens.get(i).getValueSaved()); 
		System.out.println("Total: You spent : "+ getValorTotal()+ " and saved: "+ getValueSaved()+"\n");
		
	}
}
