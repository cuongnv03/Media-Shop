package hust.soict.hedspi.aims.screen;

import java.awt.Font;

import javax.swing.*;

import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;

public class MediaPlayerDialog extends JDialog {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	
	public MediaPlayerDialog(JFrame parent, boolean modal, Media media) {
		super(parent, modal);
		//Sử dụng stringbuilder để lấy kết quả của việc play
		StringBuilder playResult = new StringBuilder();
		JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);
        if (media instanceof CompactDisc) {
    	    CompactDisc cd = (CompactDisc) media;
    	    try {
    	        // nếu media là playable thì ép kiểu và chạy play rồi gán kết quả vào playResult
    	    	playResult.append(cd.play().toString());
    	    	//
    	    	
    	    } catch (PlayerException e) {
    	        // Xử lý PlayerException
    	        System.err.println( e.getMessage());
    	        playResult.append(e.getMessage());
    	        e.printStackTrace(); 
    	        // Đặt tiêu đề là lỗi nếu khi play lỗi
    	        setTitle("Error: " + e.getClass().getSimpleName());
    	    }
    	} else if (media instanceof DigitalVideoDisc) {
    	    DigitalVideoDisc dvd = (DigitalVideoDisc) media;
    	    try {
    	        //  // nếu media là playable thì ép kiểu và chạy play rồi gán kết quả vào playResult
    	    	playResult.append(dvd.play().toString());
    	    
    	    } catch (PlayerException e) {
    	        // Xử lý PlayerException
    	        System.err.println(e.getMessage());
    	        playResult.append(e.getMessage());
    	        e.printStackTrace(); 
    	        // Đặt tiêu đề là lỗi nếu khi play lỗi
    	        setTitle("Error: " + e.getClass().getSimpleName());
    	    }
    	}
        JTextArea playResultArea = new JTextArea(playResult.toString());
        playResultArea.setEditable(false);
        playResultArea.setLineWrap(true);
        playResultArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(playResultArea);

        
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(title);
        getContentPane().add(scrollPane);

        // Nếu tiêu đề chưa có thì đặt là media player
        if (getTitle() == null) {
			setTitle("Media Player");
		}
        
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	


	}
	
	
}