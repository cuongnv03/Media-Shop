package hust.soict.hedspi.test;
import hust.soict.hedspi.aims.*;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
public class CartTest {
  public static void main(String[] args)
  {  //Tạo cart mới
	  Cart cart = new Cart();
	  
	  //Tạo các đối tượng dvd mới và thêm chúng vào cart
	  DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
              "Animation", "Roger Allers", 87, 19.95f );
cart.addMedia(dvd1);

DigitalVideoDisc dvd2 = new  DigitalVideoDisc("Star Wars",
     "Science Fiction", "George Lucas", 87, 24.95f );
cart.addMedia(dvd2);

DigitalVideoDisc dvd3 = new  DigitalVideoDisc("Aladin",
     "Animation", 18.99f );
cart.addMedia(dvd3);
DigitalVideoDisc dvd4 = new  DigitalVideoDisc("Star Wars 2: Hello there",
	     "Animation", 15.39f );
	cart.addMedia(dvd4);
      //Test method để print
	  cart.showCart();
	  cart.searchById(1);
	  cart.searchById(10);
	  cart.searchByTitle("Star");
	  cart.searchByTitle("Lion");
	  cart.searchByTitle("Alalala");
	  //Test method để tìm
  }
}