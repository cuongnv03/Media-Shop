//Nguyen Van Cuong - 20215006
package hust.soict.hedspi.aims.screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import hust.soict.hedspi.aims.Cart;
import hust.soict.hedspi.aims.Store;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.screen.view.AddBookScreen;
import hust.soict.hedspi.aims.screen.view.AddCDScreen;
import hust.soict.hedspi.aims.screen.view.AddDVDScreen;
import hust.soict.hedspi.aims.screen.view.CartScreen;
import javafx.collections.ObservableList;

public class StoreScreen extends JFrame  {
    private Store store;
    private Cart cart;
    // khai báo các attribute cần thiết là store và cart
    public Store getStore() {
		return store;
	}

	public Cart getCart() {
		return cart;
	}
    //Getter lấy các attributes
	JPanel createNorth() {
    	JPanel north = new JPanel();
    	north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
    	north.add(createMenuBar()); 
    	north.add(createHeader());
    	return north;
    	//Xây dựng phần north của giao diện
    	}
     
    JMenuBar createMenuBar() {
    	//Tạo menubar với các lựa chọn
    	ButtonListener btnListener = new ButtonListener();
    	JMenu menu = new JMenu("Options");
    	
    	JMenu smUpdateStore = new JMenu("Update Store");
    	JMenuItem addBook = new JMenuItem("Add Book");
    	
    	smUpdateStore.add(addBook);
    	addBook.addActionListener(btnListener);
    	//Thêm actionlistener để chuyển sang screen addbook
    	
        JMenuItem addCD = new JMenuItem("Add CD");
    	
    	smUpdateStore.add(addCD);
    	addCD.addActionListener(btnListener);
    	//Thêm actionlistener để chuyển sang screen addcd
    	JMenuItem addDVD = new JMenuItem("Add DVD");
    	
    	smUpdateStore.add(addDVD);
    	addDVD.addActionListener(btnListener);
    	//Thêm actionlistener để chuyển sang screen adddvd
    	
    	menu.add(smUpdateStore);
    	menu.add(new JMenuItem("View store"));
    	JMenuItem cart = new JMenuItem("View cart");
    	
    	menu.add(cart);
    	cart.addActionListener(btnListener);
    	//Thêm actionlistener để chuyển sang srên view cảt
    	JMenuBar menuBar = new JMenuBar();
    	menuBar.setLayout(new FlowLayout(FlowLayout.LEFT)); 
    	menuBar.add(menu);
    	
    	return menuBar;
    	}
    
    JPanel createHeader() {
    	//Xây dựng phần header
    	ButtonListener btnListener = new ButtonListener();
    	JPanel header = new JPanel();
    	header.setLayout(new BoxLayout (header, BoxLayout.X_AXIS));
    	
    	JLabel title = new JLabel("AIMS");
    	title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
    	title.setForeground(Color.CYAN);
    	//Thiết kế tiêu để
    	JButton cart = new JButton("View cart");
    	cart.addActionListener(btnListener);
    	//Tạo nút view cart và add listener 
    	cart.setPreferredSize(new Dimension(100, 50)); 
    	cart.setMaximumSize(new Dimension(100, 50));
    	
    	header.add(Box.createRigidArea (new Dimension (10, 10))); 
    	header.add(title);
    	header.add(Box.createHorizontalGlue());
    	header.add(cart);
    	header.add(Box.createRigidArea (new Dimension(10, 10)));
    	return header;
    	} 
    
    JPanel createCenter() {
    	//Tạo phần trung tâm
        JPanel center = new JPanel();

        ObservableList<Media> mediaInStore = store.getItemsInStore();
        int size = mediaInStore.size();
        //Lấy các item đang có trong store
        // Lấy căn bậc hai của số lượng item đang có và + 1
        int rows = (int) Math.sqrt(size) + 1;
        //Ta được số hàng và cột
        center.setLayout(new GridLayout(rows, rows, 2, 2));
        //Tạo gridlayout theo số hàng và cột
        for (int i = 0; i < size; i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i));
            cell.setStoreScreen(this);
            center.add(cell);
            //Thêm từng item vào cell
        }

        return center;
    }
    
    public StoreScreen (Store store, Cart cart) {
    	this.store = store;
    	this.cart = cart;
    	Container cp = getContentPane();
    	cp.setLayout(new BorderLayout());
    	
    	cp.add(createNorth(), BorderLayout.NORTH);
    	cp.add(createCenter(), BorderLayout.CENTER);
    	
    	setVisible(true);
    	setTitle("Store");
    	setSize(1024, 768);
    	}
    // constructor khởi tạo storescreen
    private class ButtonListener implements ActionListener{ 
    	//Method thực hiện actionlistener
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		String button = e.getActionCommand();
    		if (button.equals("View cart"))
    		{
    			new CartScreen(cart, store);
    			dispose();
    			//Nếu là View cart chuyển sang screen cart
    			
    		}
    		if (button.equals("Add Book"))
    		{
    			new AddBookScreen(cart, store);
  
    			// Nếu là add book chuyển sang screen add book
    		}
    		if (button.equals("Add DVD"))
    		{
    			new AddDVDScreen(cart, store);
  
    			//Nếu là add dvd chuyển sang screen add dvd
    		}
    		
    		if (button.equals("Add CD"))
    		{
    			new AddCDScreen(cart, store);
    			
    		}
      }
    }
}