package hust.soict.hedspi.test;
import hust.soict.hedspi.aims.media.*;
import java.util.ArrayList;
import java.util.List;
public class ToStringTest {
	public static void main(String[] args) {
		List<Media> mediae = new ArrayList<Media>();
		CompactDisc  cd = new CompactDisc("Michael Bay",8,"Michael Jackson");
		DigitalVideoDisc dvd = new DigitalVideoDisc("The Lion King",
                "Animation", "Roger Allers", 87, 19.95f );
		Book book = new Book("The Gutenberg Galaxy","Literary analysis", 5.6f);
		//Khởi tạo các media khác nhau
	    mediae.add(cd);
	    mediae.add(dvd);
	    mediae.add(book);
	    //Thêm chúng vào list
	    for(Media m: mediae) {
	    	System.out.println(m.toString());
	    	//Duyệt tuần tự và thử chúng với toString
	    }
        //Test kiểm tra tính đa hình với hàm toString
	}

}
