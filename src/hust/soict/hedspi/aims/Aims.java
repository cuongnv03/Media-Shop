//Nguyen Van Cuong - 20215006
package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.screen.StoreScreen;

import java.util.Scanner;

public class Aims {
	private static String mediaTitle;
	public static Scanner scanner = new Scanner(System.in);
	public static Store aStore; 
	public static Cart aCart = new Cart();
	private static Media foundMedia;
	public static void main(String[] args){   
		aStore = new Store();
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
		                 "Animation", "Roger Allers", 87, 19.95f );
	    aStore.addMedia(dvd1);
	    
	    DigitalVideoDisc dvd2 = new  DigitalVideoDisc("Star Wars",
                "Science Fiction", "George Lucas", 87, 24.95f );
	    aStore.addMedia(dvd2);
	    DigitalVideoDisc dvd3 = new  DigitalVideoDisc("Aladin",
                "Animation", 18.99f );
	    aStore.addMedia(dvd3);
	    CompactDisc cd1 = new CompactDisc("The Eras Tour", "Music", 19.89f);
        CompactDisc cd2 = new CompactDisc("Money Talk", "Podcast", 12f);
        aStore.addMedia(cd1);
        aStore.addMedia(cd2);
        Book book1 = new Book("Harry Potter and the Half-Blood Prince", "Fiction", 30f);
        Book book2 = new Book("Deep Work", "Skill", 20f);
        aStore.addMedia(book1);
        aStore.addMedia(book2);
        
        new StoreScreen(aStore, aCart);
	}
   
	public static void showMenu() {
	   System.out.println("AIMS: ");
	   System.out.println("--------------------------------");
	   System.out.println("1. View store");
	   System.out.println("2. Update store");
	   System.out.println("3. See current cart");
	   System.out.println("0. Exit");
	   System.out.println("--------------------------------");
	   System.out.println("Please choose a number: 0-1-2-3");

	   int choice = scanner.nextInt();

       switch (choice){
           case 1: storeMenu(); break;
           case 2: updateStore(); break;
           case 3: cartMenu(); break;
           case 0: return;
           default:
               System.out.println("Error, choose again!");
               showMenu();
	   }
   }
   
   public static void storeMenu() {
	   aStore.showStore();
	   System.out.println("Options: ");
	   System.out.println("--------------------------------");
	   System.out.println("1. See a media’s details");
	   System.out.println("2. Add a media to cart");
	   System.out.println("3. Play a media");
	   System.out.println("4. See current cart");
	   System.out.println("0. Back");
	   System.out.println("--------------------------------");
	   System.out.println("Please choose a number: 0-1-2-3-4");
	   
	   int choice = scanner.nextInt();

       switch (choice){
           case 0: showMenu(); break;
           case 1:  
	           System.out.println("Enter media's title: ");
	           scanner.nextLine();
	           mediaTitle = scanner.nextLine();
	           foundMedia = aStore.searchByTitle(mediaTitle);
	           if (foundMedia != null) {
	        	   mediaDetailsMenu();
	           }
	           storeMenu();
	           break;
           case 2:
	    	   System.out.println("Enter media's title");
	    	   scanner.nextLine();
	    	   String mediaTitleAdd = scanner.nextLine();
	    	   foundMedia = aStore.searchByTitle(mediaTitleAdd);
	    	   if (foundMedia != null) {
	    	        aCart.addMedia(foundMedia);
	    	        aStore.removeMedia(foundMedia);
	    	        System.out.println("Media added to cart successfully");
	    	    } else {
	    	        System.out.println("Media not found in the store");
	    	    }
	    	   storeMenu();
	           break;
           case 3:
               System.out.println("Enter media's title");
               scanner.nextLine();
               String mediaTitlePlay = scanner.nextLine();
               foundMedia = aStore.searchByTitle(mediaTitlePlay);
               if (foundMedia instanceof CompactDisc) {
            	    CompactDisc cd = (CompactDisc) foundMedia;
            	    try {
            	        cd.play();
            	    } catch (PlayerException e) {
            	        System.err.println("Error playing media: " + e.getMessage());
            	        e.printStackTrace(); // Print the stack trace
            	    }
            	} else if (foundMedia instanceof DigitalVideoDisc) {
            	    DigitalVideoDisc dvd = (DigitalVideoDisc) foundMedia;
            	    try {
            	        // Call the play() method on a Media object
            	    	dvd.play();
            	    } catch (PlayerException e) {
            	        // Handle the PlayerException
            	        System.err.println("Error playing media: " + e.getMessage());
            	        e.printStackTrace(); // Print the stack trace
            	        // You can display a dialog box to the user with the content of the exception here
            	    }
            	}
               storeMenu();
               break;
           case 4:
               cartMenu();
               break;
           default:
               System.out.println("Error, choose again!");
               storeMenu();
	   }
   }
   
   public static void mediaDetailsMenu() {
	   int check = 0;
	   System.out.println("Options: ");
	   System.out.println("--------------------------------");
	   System.out.println("1. Add to cart");
	   if (foundMedia instanceof CompactDisc || foundMedia instanceof DigitalVideoDisc) {
	        System.out.println("2. Play");
	        check = 1;
	    }
	   System.out.println("0. Back");
	   System.out.println("--------------------------------");
	   System.out.println("Please choose a number: 0-1-(2)");
	   int detailMenuChoice = scanner.nextInt();
	   switch (detailMenuChoice){
       case 1:
	        aCart.addMedia(foundMedia);
	        aStore.removeMedia(foundMedia);
	        System.out.println("Media added to cart successfully");
	        storeMenu();
	        break;
       case 2:
           if (check == 0)
           {
        	   System.out.println("Error, choose again!");
               mediaDetailsMenu();
           }
           else{
        	   if (foundMedia instanceof CompactDisc) {
           	    CompactDisc cd = (CompactDisc) foundMedia;
           	    try {
           	        // Call the play() method on a Media object
           	        cd.play();
           	    } catch (PlayerException e) {
           	        // Handle the PlayerException
           	        System.err.println("Error playing media: " + e.getMessage());
           	        e.printStackTrace(); // Print the stack trace
           	        // You can display a dialog box to the user with the content of the exception here
           	    }
           	} else if (foundMedia instanceof DigitalVideoDisc) {
           	    DigitalVideoDisc dvd = (DigitalVideoDisc) foundMedia;
           	    try {
           	        // Call the play() method on a Media object
           	    	dvd.play();
           	    } catch (PlayerException e) {
           	        // Handle the PlayerException
           	        System.err.println("Error playing media: " + e.getMessage());
           	        e.printStackTrace(); // Print the stack trace
           	        // You can display a dialog box to the user with the content of the exception here
           	    }
           	}
           }
           break;
       case 0:
           storeMenu();
           break;
       default:
           System.out.println("Error, choose again!");
           mediaDetailsMenu();
	   }
   }
   
   public static void updateStore(){
       Media item;

	   System.out.println("Options: ");
       System.out.println("--------------------------------");
       System.out.println("1. Add new media.");
       System.out.println("2. Remove media.");
       System.out.println("0. Back");
       System.out.println("---------------------------------");
       System.out.println("Please choose a number 0-1-2");
       int updateStoreChoice = scanner.nextInt();
       switch (updateStoreChoice){
           case 0: showMenu(); break;
           case 1:
               AddMedia();
               break;
           case 2:
               System.out.println("Removed media");
               scanner.nextLine();
               String removeFromStore = scanner.nextLine();
               item = aStore.searchByTitle(removeFromStore);

               if (item != null) {
       	        System.out.println("Media removed successfully");
       	        aStore.removeMedia(item);
       	    } else {
       	        System.out.println("Can't remove");
       	    }
               updateStore();
               break;
           default:
               System.out.println("Error, choose again!");
               updateStore();
       }
   }
   
   public static void AddMedia(){
       String Title;
	   System.out.println("Options: ");
       System.out.println("--------------------------------");
       System.out.println("1. Add a book.");
       System.out.println("2. Add a DVD.");
       System.out.println("3. Add a CD.");
       System.out.println("0. Back");
       System.out.println("---------------------------------");
       System.out.println("Please choose a number 0-1-2-3");
       int updateStoreChoice = scanner.nextInt();
       scanner.nextLine();
       switch (updateStoreChoice){
           case 0: updateStore(); break;
           case 1:
        	   System.out.println("Title");
        	   Title = scanner.nextLine();
        	   Book book = new Book(Title);
        	   aStore.addMedia(book);
        	   updateStore();
               break;
           case 2:
        	   System.out.println("Title");
        	   Title = scanner.nextLine();
        	   DigitalVideoDisc dvd = new DigitalVideoDisc(Title);
        	   aStore.addMedia(dvd);
               updateStore();
               break;
           case 3:
        	   System.out.println("Title");
        	   Title = scanner.nextLine();
        	   CompactDisc cd = new CompactDisc(Title);
        	   aStore.addMedia(cd);
        	   updateStore();
               break;   
           default:
               System.out.println("Error, choose again!");
               updateStore();
       }
   }
   
   public static void cartMenu() {
	   Media item;
	   aCart.showCart();

	   System.out.println("Options: ");
       System.out.println("--------------------------------");
       System.out.println("1. Filter media in cart");
       System.out.println("2. Sort media in cart");
       System.out.println("3. Remove media from cart");
       System.out.println("4. Play a media");
       System.out.println("5. Place order");
       System.out.println("0. Back");
       System.out.println("--------------------------------");
       System.out.println("Please choose a number: 0-1-2-3-4-5");
       int cartMenuChoice = scanner.nextInt();
       switch (cartMenuChoice){
           case 1:
               filterMenu();
               break;
           case 2:
               sortMenu();
               break;
           case 3:
               System.out.println("Enter media's title: ");
               scanner.nextLine();
               String removeMediaTitle = scanner.nextLine();
               item = aCart.searchByTitle(removeMediaTitle);
               if (item != null) {
          	        System.out.println("Media removed successfully");
          	        aCart.removeMedia(item);
          	    } else {
          	        System.out.println("Can't removed");
          	    }
               cartMenu();
               break;
           case 4:
               System.out.println("Enter media's title");
               scanner.nextLine();
               String mediaTitlePlay = scanner.nextLine();
               item =  aCart.searchByTitle(mediaTitlePlay);

               if (foundMedia instanceof CompactDisc) {
           	    CompactDisc cd = (CompactDisc) foundMedia;
           	    try {
           	        // Call the play() method on a Media object
           	        cd.play();
           	    } catch (PlayerException e) {
           	        // Handle the PlayerException
           	        System.err.println("Error playing media: " + e.getMessage());
           	        e.printStackTrace(); // Print the stack trace
           	        // You can display a dialog box to the user with the content of the exception here
           	    }
           	} else if (foundMedia instanceof DigitalVideoDisc) {
           	    DigitalVideoDisc dvd = (DigitalVideoDisc) foundMedia;
           	    try {
           	        // Call the play() method on a Media object
           	    	dvd.play();
           	    } catch (PlayerException e) {
           	        // Handle the PlayerException
           	        System.err.println("Error playing media: " + e.getMessage());
           	        e.printStackTrace(); // Print the stack trace
           	        // You can display a dialog box to the user with the content of the exception here
           	    }
           	}
      	        else {
      	        System.out.println("Media not found in the store");
      	    }
               cartMenu();
               break;
           case 5:
        	   System.out.println("An order is created");
               cartMenu();
               break;
           case 0:
               storeMenu();
               break;
           default:
               System.out.println("Error, choose again");
               cartMenu();
       }
   }
   
   public static void filterMenu(){
       System.out.println("Options: ");
       System.out.println("-------------------------------");
       System.out.println("1. By ID");
       System.out.println("2. By Title");
       System.out.println("0. Back");
       System.out.println("-------------------------------");
       System.out.println("Please choose a number: 0-1-2");
       int filterChoice = scanner.nextInt();
       switch (filterChoice){
           case 1:
               System.out.println("Enter media's ID: ");
               int filterID = scanner.nextInt();
               aCart.searchById(filterID);
               filterMenu();
               break;
           case 2:
               System.out.println("Enter media's title: ");
               scanner.nextLine();
               String filterTitle = scanner.nextLine();
               aCart.searchByTitle(filterTitle);
               filterMenu();
               break;
           case 0:
               cartMenu();
               break;
           default:
               System.out.println("Error, choose again");
               filterMenu();
       }
   }

   public static void sortMenu(){
       System.out.println("Options: ");
       System.out.println("-------------------------------");
       System.out.println("1. By Title");
       System.out.println("2. By Cost");
       System.out.println("0. Back");
       System.out.println("-------------------------------");
       System.out.println("Please choose a number: 0-1-2");
       int sortChoice = scanner.nextInt();
       switch (sortChoice){
           case 1:
               aCart.sortByTitle();
               aCart.showCart();
               sortMenu();
               break;
           case 2:
               aCart.sortByCost();
               aCart.showCart();
               sortMenu();
               break;
           case 0:
               cartMenu();
               break;
           default:
               System.out.println("Error, choose again");
               sortMenu();
       }
   }
}