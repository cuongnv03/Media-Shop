//Nguyen Van Cuong - 20215006
package hust.soict.hedspi.aims.screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import hust.soict.hedspi.aims.media.*;

public class MediaStore extends JPanel{
	private StoreScreen storeScreen;
	private Media media;
	//Các attributes
	public void setStoreScreen(StoreScreen storeScreen) {
        this.storeScreen = storeScreen;
    }
	//setter
	public MediaStore (Media media){
		ButtonListener btnListener = new ButtonListener();
		this.media = media;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel(""+media.getCost()+" $");
        cost.setAlignmentX(CENTER_ALIGNMENT);
        //Xây dựng giao diện
        JPanel container = new JPanel(); container.setLayout(new FlowLayout(FlowLayout.CENTER));        
        JButton btnAdd = new JButton("Add to cart");
        btnAdd.addActionListener(btnListener);
        container.add(btnAdd);
        if(media instanceof Playable) {
        	// nếu media thực hiện playable thì làm xuất hiện nút play
        	JButton btnPlay = new JButton("Play");
        	btnPlay.addActionListener(btnListener);
             container.add(btnPlay);
        }
        //Thêm các button và listerner
        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);
        
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
   }
	
	private class ButtonListener implements ActionListener{ 
    	@Override
    	//method thực hiện actionlistener
    	public void actionPerformed(ActionEvent e) {
    		String button = e.getActionCommand();
    		if (button.equals("Add to cart"))
    		{
    			storeScreen.getCart().addMedia(media);
    			//Nếu là add to cart thì thêm vào cart
    		}
    		if (button.equals("Play"))
    		{    
    			MediaPlayerDialog playDialog = new MediaPlayerDialog((JFrame) SwingUtilities.getWindowAncestor(MediaStore.this), true, media);
    			playDialog.setVisible(true);
    			//Nếu là play thì gọi một cửa sổ mới và chạy
    		}
      }
    }
}