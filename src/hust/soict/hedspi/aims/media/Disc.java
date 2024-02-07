//Nguyen Van Cuong - 20215006
package hust.soict.hedspi.aims.media;
public class Disc extends Media {
	private String director;
	private int length;

	public String getDirector() {
		return director;
	}
	
	public int getLength() {
		return length;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
	public void setLength(int length) {
		this.length = length;
	}

	public Disc(String title) {
        super(title);
    }
	
	public Disc(String title, String category, float cost) {
		super(title,category,cost);
	}
	
	public Disc(String director, int length) {
		super();
		this.director = director;
		this.length = length;
	}
	
	public Disc() {
        super();
    }
}