//Nguyen Van Cuong - 20215006
package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public abstract class Media {
	private int id;
	private String title;
	private String category;
	private float cost;
	private static int nbMedia = 0;
	public static final Comparator<Media> COMPARE_BY_TITLE_COST =
			new MediaComparatorByTitleCost();
	public static final Comparator<Media> COMPARE_BY_COST_TITLE =
			new MediaComparatorByCostTitle();
	
	public Media(String title) {
		super();
		this.title = title;
		setId(++nbMedia);
	}

	public Media(String title, String category, float cost) {
		super();
		if (cost < 0) {
	        throw new IllegalArgumentException("Cost cannot be negative.");
	    }
		this.title = title;
		this.category = category;
		this.cost = cost;
		setId(++nbMedia);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public boolean equals(Object obj) {
	    if (obj == null || !(obj instanceof Media)) {
	        return false;
	    }

	    Media otherMedia = (Media) obj;

	    try {
	        return title.equals(otherMedia.title);
	    } catch (NullPointerException e) {
	        return false;
	    }
	}
	
	public boolean isMatch(String tit) {
		return getTitle().toLowerCase().contains(tit.toLowerCase());
		}
	
	public boolean isMatchID(int id) {
		return (getId() == id);
		}

	public Media() {
		
	}
}