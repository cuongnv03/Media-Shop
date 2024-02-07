//Nguyen Van Cuong - 20215006
package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {
    
    // Khởi tạo thuộc tính của DigitalVideoDisc
    public DigitalVideoDisc(String title) {
        super(title);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super(title, category, cost);
        setDirector(director);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
    	super(title, category, cost);
        setDirector(director);
    	setLength(length);
    }
    //Các phương thức constructor cho DVD, dùng super tới Media
    public String toString() {
    	// Phương thức chuyển các thông tin của DVD thành string rồi trả lại
        String dvd = getTitle() + " - " + getCategory() + " - " +
                getDirector() + " - "  + getLength() + " - " + getCost();
        return dvd;
    }

    public StringBuilder play() throws PlayerException {
        // Method play choPlayable interface
        StringBuilder result = new StringBuilder();
        if (this.getLength() > 0) {
        	//Nếu length > thì không có lỗi play như thường
        	result.append("Playing DVD: ").append(getTitle()).append("\nDVD length: ").append(getLength());
            return result;
        	} else {
        	throw new PlayerException("ERROR: DVD length is non-positive!");
        	}
    }
}