//Nguyen Van Cuong - 20215006
package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Store {
	private ObservableList<Media> itemsInStore = FXCollections.observableArrayList();  
	
	public void addMedia (Media item){
		itemsInStore.add(item);
		System.out.println("The item has been added");
	}
	
	public void removeMedia (Media item){ 
        if (itemsInStore.isEmpty()){
        	throw new StoreException("The shop is empty. Cannot remove items.");
        }
        if (itemsInStore.remove(item)){
        	System.out.println("The item is removed successfully");
        }
        else{
        	throw new StoreException("The item is not in the shop.");
        }
	}
	
	public void showStore(){
		int i = 1;
		System.out.println("***********************Store***********************");
		for (Media item : itemsInStore) {
			 System.out.println(i + ".item - " + item.toString());
			 i++;
        }
	    System.out.println("***************************************************");
	}
	
	public ObservableList<Media> getItemsInStore() {
		return itemsInStore;
	}

	public Media searchByTitle(String Title){
		int count = 1;
		Media result = null;
		for (Media item : itemsInStore) {
            if (item.isMatch(Title)){
            	System.out.println("Item -" + item.toString());
            	result = item;
            	count++;
            	}
		}
		if (count == 1) {
	        throw new StoreException("No results found for the given title.");
	    }
		return result;
	}
	public class StoreException extends RuntimeException {
	    public StoreException(String message) {
	        super(message);
	    }
	}
}