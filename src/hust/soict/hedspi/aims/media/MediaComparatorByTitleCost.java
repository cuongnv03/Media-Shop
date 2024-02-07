//Nguyen Van Cuong - 20215006
package hust.soict.hedspi.aims.media;
import java.util.Comparator;

public class MediaComparatorByTitleCost implements Comparator<Media> {
	//Class Comparator theo title rồi đến cost
	private Comparator<Media> costComparator = Comparator.comparingDouble(Media::getCost);
    private Comparator<Media> titleComparator = Comparator.comparing(Media::getTitle);
 // Dùng hàm comparing theo cost và title
    private Comparator<Media> compoundComparator = titleComparator.thenComparing(costComparator);
  //Dùng thenComparing để compare theo cost rồi đến title
    
    public int compare(Media media1, Media media2) {
        return compoundComparator.compare(media1, media2);
    }
}