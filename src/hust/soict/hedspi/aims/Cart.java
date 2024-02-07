//Nguyen Van Cuong - 20215006
package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.media.*;
import javafx.beans.property.SimpleFloatProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {
    
	public static final int MAX_NUMBERS_ORDERED = 20;
	private ObservableList<Media> itemOrdered = FXCollections.observableArrayList(); 

	private SimpleFloatProperty totalCostProperty = new SimpleFloatProperty(0);

	public void addMedia (Media item){
		if (itemOrdered.size() == MAX_NUMBERS_ORDERED){
			throw new CartException("The cart is full. Cannot add more items.");
		} else {
			itemOrdered.add(item);
			totalCostProperty.set(item.getCost()+totalCostProperty.get());
			System.out.println("The item has been added");
		}
	}
	
	public void addMedia (Media ... item){
		if ( itemOrdered.size() == MAX_NUMBERS_ORDERED){
			throw new CartException("The cart is full. Cannot add more items.");
		}else {
			if ( itemOrdered.size() + item.length == MAX_NUMBERS_ORDERED){
				throw new CartException("There are not enough spots in the cart");
			}else{
				for (Media it : item) {
					itemOrdered.add(it);
					totalCostProperty.set(it.getCost()+totalCostProperty.get());
		        }
				System.out.println("The list of items has been added successfully");
			} 
		}
	}
	
	public void addMedia (Media item1, Media item2 ){
		if ( itemOrdered.size() + 2 >  MAX_NUMBERS_ORDERED){ 
			throw new CartException("The cart is full. Cannot add more items.");
		}else {
			itemOrdered.add(item1);
			itemOrdered.add(item2);
			totalCostProperty.set(item1.getCost()+totalCostProperty.get());
			totalCostProperty.set(item2.getCost()+totalCostProperty.get());
			System.out.println("Both items have been added");
		}
	}
	
	public void removeMedia (Media item){
        if (itemOrdered.isEmpty()){
        	throw new CartException("The cart is empty. Cannot remove items.");
        }
        
        if ( itemOrdered.remove(item)){
        	System.out.println("The item is removed successfully");
        	totalCostProperty.set(totalCostProperty.get() - item.getCost());
        }
        else{   
        	throw new CartException("The item is not in the cart.");
        }	
	}
	
	public void showCart (){
		int i = 1;
		System.out.println("***********************CART***********************");
		for (Media item : itemOrdered) {
			 System.out.println(i + ".item - " + item.toString());
			 i++;
        }
	    System.out.print("Total Cost: ");
	    System.out.println(totalCostProperty.get());
	    System.out.println("***************************************************");
	}
	
	public void emptyCart() {
	    itemOrdered.clear();
	    totalCostProperty.set(0);
	    System.out.println("The cart has been emptied.");
	}
	
	public ObservableList<Media> getItemOrdered() {
		return itemOrdered;
	}
	
	public SimpleFloatProperty getTotalCostProperty() {
		return totalCostProperty;
	}
    
	public Media searchByTitle(String Title){
		int count = 1;
		Media result = null;
		for (Media item : itemOrdered){
            if (item.isMatch(Title)){
            	System.out.println(count + ".item -" + item.toString());
            	result = item;
            	count ++;
            }
		}
		if (count == 1){
			System.out.println("No results found");
			return null;
		}
		return result;
	}
	
	
	public void searchById(int Id){   
		int count = 1;
		for (Media item : itemOrdered) {
            if (Id == item.getId()){
            	System.out.println(count + ".item -" + item.toString());
            	count ++;
            	break;
            } 
		}
		if (count == 1){
			System.out.println("No results found");
		}
	}
	
	public void sortByTitle(){
		java.util.Collections.sort(itemOrdered,Media.COMPARE_BY_TITLE_COST);
	}
	
	public void sortByCost(){
		java.util.Collections.sort(itemOrdered, Media.COMPARE_BY_COST_TITLE);
	}
	
	public class CartException extends RuntimeException {
	    public CartException(String message) {
	        super(message);
	    }
	}
}