//Nguyen Van Cuong - 20215006
package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
	private List<String> authors = new ArrayList<String>();
	public Book(String title) {
        super(title);
    }
	
	public Book(String title, String category, float cost) {
		super(title,category,cost);
	}

	public void addAuthor(String authorName){
		if ( authors.contains(authorName)){
			throw new BookException("The author name is already presented.");
		}else {
			authors.add(authorName);
			System.out.println("Author's name has been added successfully");
		}
	}
	
	public void removeAuthor(String authorName){
		if ( authors.contains(authorName)){
			 authors.remove(authorName);
			 throw new BookException("This author's name is not presented, can't remove it");
		}else {
			System.out.println("This author's name is not presented, can't remove it");
		}
	}
	
	public String toString() {
		String cd;
		cd = getTitle() + " - " + getCategory() + " - "  + getCost();
		return cd;
	}
	
	public Book() {
		
	}
	
	public class BookException extends RuntimeException {
	    public BookException(String message) {
	        super(message);
	    }
	}
}