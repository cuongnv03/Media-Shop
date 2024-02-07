//Nguyen Van Cuong - 20215006
package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hust.soict.hedspi.aims.exception.PlayerException;

public class CompactDisc extends Disc implements Playable {
	private String artist;
	private List<Track> tracks = new ArrayList<Track>();

	public CompactDisc(String title) {
        super(title);
    }
	
	public CompactDisc(String title, String category, float cost) {
		super(title,category,cost);
	}
	
	public CompactDisc(String director, int length, String artist) {
		super(director, length);
		this.artist = artist;
	}
	
    public CompactDisc(String title, String category, String director, int length, float cost, String artist) {
    	super(title, category, cost);
        setDirector(director);
    	setLength(length);
    	setArtist(artist);
    }
	
	public String getArtist() {
		return artist;
	}

	public void addTrack(Track trackName)
	{   // Method cho việc thêm track
		if ( tracks.contains(trackName))
			{ //Kiểm tra xem track có trong list chưa
			throw new CompactDiscException("The track is already presented.");
			}
		else 
		   {
			// Nếu không thì thêm vào và thông báo thêm thành công
			tracks.add(trackName);
			System.out.println("Track has been added successfully");
		   }
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void removeTrack(Track trackName)
	{   // Method cho việc xoa track
		if ( tracks.contains(trackName))
			{ //Kiểm tra xem tên có trong list chưa
			 tracks.remove(trackName);
			 System.out.println("The track has been removed successfully");
			 return;
			}
		else 
		   {
			// Nếu không thì báo
			throw new CompactDiscException("This track is not presented, can't remove it");
		   }
	}
	
	public int getLength ()
	{   //Method tính length
		int total =0;
		for (Track track : tracks) {
             total += track.getLength();
        }
		// Duyệt tuần tự qua tracks và tính tổng thời gian
		return total;
	}
     
	public StringBuilder play() throws PlayerException {
		// method thực hiện interface play() có bắt exception, sử dụng string builder cho tiện
	    StringBuilder result = new StringBuilder();
	    result.append("Total tracks: ").append(tracks.size()).append("\n");
	    result.append("Total runtime: ").append(getLength()).append("\n");

	    if (this.getLength() > 0) {
	        // Nếu thời gian > 0 thì không có lỗi
	        Iterator<Track> iter = tracks.iterator();
	        Track nextTrack;
	        while (iter.hasNext()) {
	            nextTrack = iter.next();
	            try {
	                result.append(nextTrack.play()).append("\n");
	                // Duyệt tuần tự và chơi từng tracks
	            } catch (PlayerException e) {
	                // Xử lý exception tạo bởi Track
	                System.err.println("Error playing track: " + e.getMessage());
	                throw e;
	            }
	        }
	        return result;
	    } else {
	        throw new PlayerException("ERROR: CD length is non-positive!");
	    }
	}

	   public String toString() 
		{// Phương thức chuyển các thông tin của CD thành string rồi trả lại
			String cd;
			cd = 	getTitle()+ " - " + getCategory() + " - " + getDirector()+ " - "  +getArtist() + " - "  +getLength();
			return cd;
		}
		
	   public class CompactDiscException extends RuntimeException {
		   //method exception tổng quát
		    public CompactDiscException(String message) {
		        super(message);
		    }
		}
}