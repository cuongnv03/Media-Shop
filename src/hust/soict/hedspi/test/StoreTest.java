package hust.soict.hedspi.test;
import hust.soict.hedspi.aims.*;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
public class StoreTest {

	public static void main(String[] args) {
		 Store aStore = new Store();
		    // Tạo store mới với tên aStore
			DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
			                 "Animation", "Roger Allers", 87, 19.95f );
		    aStore.addMedia(dvd1);
		    
		    DigitalVideoDisc dvd2 = new  DigitalVideoDisc("Star Wars",
	                "Science Fiction", "George Lucas", 87, 24.95f );
		    aStore.addMedia(dvd2);
		    
		    DigitalVideoDisc dvd3 = new  DigitalVideoDisc("Aladin",
	                "Animation", 18.99f );
		    aStore.addMedia(dvd3);
		 // Tạo các dvd và thêm vào store
		    aStore.removeMedia(dvd3);
		    aStore.removeMedia(dvd3);
		 // Xóa dvd khỏi store
		    

	}

}