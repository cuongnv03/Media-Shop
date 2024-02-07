//Nguyen Van Cuong - 20215006
package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class Track implements Playable {
	private String title;
	private int length;
	
	public String getTitle() {
		return title;
	}

	public int getLength() {
		return length;
	}

	public Track(String title, int length) {
		super();
		this.title = title;
		this.length = length;
	}

	public boolean equals(Track o){
		if (title.equals(o.title)){  
			if (length == o.length ){
				return true;
			}
			else
			{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public String toString() {
		String track;
		track = getTitle() + " - " +getLength() ;
		return track;
	}
		
	
    public StringBuilder play() throws PlayerException  {
        StringBuilder result = new StringBuilder();
        if (this.getLength() > 0) {
        	result.append("Playing Track: ").append(getTitle()).append("\nTrack length: ").append(getLength());
	        return result;
    	} else {
    	throw new PlayerException("ERROR: DVD length is non-positive!");
    	}
    }    
}