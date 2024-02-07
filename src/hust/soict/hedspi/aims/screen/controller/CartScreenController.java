//Nguyen Van Cuong - 20215006
package hust.soict.hedspi.aims.screen.controller;

import java.util.function.Predicate;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import hust.soict.hedspi.aims.*;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.screen.MediaPlayerDialog;
import hust.soict.hedspi.aims.screen.StoreScreen;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CartScreenController {

	private Cart cart;
	private Store store;
	
    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;
	
	@FXML
	private TableView<Media> tblMedia;
	
	@FXML
	private TableColumn<Media, String> colMediaTitle;
	
	@FXML
	private  TableColumn<Media, String> colMediaCategory;
	
	@FXML
	private  TableColumn<Media, Float> colMediaCost;
	
    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;
    
    @FXML
    private TextField tfFilter;
    
    private FilteredList<Media> filteredMediaList;
    
    @FXML
    private Label totalCost;
    
    @FXML
    private Button placeOrder;
    
   //Các thuộc tính
	
	public CartScreenController(Cart cart, Store store) {
		super();
		this.cart = cart;
		this.store = store;
		this.filteredMediaList = new FilteredList<>(cart.getItemOrdered());
	}
	//Constructor
	@FXML
	private void initialize() {
		
		colMediaTitle.setCellValueFactory(
				new PropertyValueFactory<Media, String>("title"));
		colMediaCategory.setCellValueFactory(
				new PropertyValueFactory<Media, String>("category"));
		colMediaCost.setCellValueFactory(
				new PropertyValueFactory<Media, Float>("cost")); 
		tblMedia.setItems(this.cart.getItemOrdered());
		//Lấy các item đang có trong giỏ hàng
		btnPlay.setVisible(false);
		btnRemove.setVisible(false);
		
		tblMedia.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Media>() {
				@Override
				public void changed(ObservableValue<? extends Media> observable, Media oldValue,				
						Media newValue) {
				if(newValue!=null) {
				//Tùy xem chọn item nào thì cập nhật các nút hiển thị
					updateButtonBar(newValue);
				}
				}
				});
		
        ToggleGroup toggleGroup = new ToggleGroup();
        radioBtnFilterId.setToggleGroup(toggleGroup);
        radioBtnFilterTitle.setToggleGroup(toggleGroup);
        //Tạo nhóm toggle cho nút Id và Title
        radioBtnFilterTitle.setSelected(true);
        //Mặc định là Title
        tfFilter.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue,
                String newValue) -> {
            if (toggleGroup.getSelectedToggle() == radioBtnFilterTitle) {
            	//Nếu chọn Title thì sử dụng filter tương ứng
                showFilteredMedia(item -> item.isMatch(newValue.toLowerCase()));
            } else if (toggleGroup.getSelectedToggle() == radioBtnFilterId) {
                try {
                    // Không thì chuyển string thành int để filter id
                    int id = Integer.parseInt(newValue);
                    showFilteredMedia(item -> item.isMatchID(id));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID format");
                }
            }
        });
        
        totalCost.textProperty().bind(Bindings.createStringBinding(() ->
        String.format("%.2f$", cart.getTotalCostProperty().get()),
        cart.getTotalCostProperty()));
        //Binding cost để giá trị trên giao diện tự cập nhật
	}
	
	@FXML
    void placeOrderPressed(ActionEvent event) {
        // Khi nhấn place order thì có thông báo đã order thành công
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText(null);
        alert.setContentText("Your order has been placed successfully!");
        alert.showAndWait();
    	cart.emptyCart();
   }
	
	private void showFilteredMedia(Predicate<Media> filter) {
	    //Hàm thực hiện việc filter
		FilteredList<Media> filteredList = new FilteredList<>(cart.getItemOrdered(), filter);
	    tblMedia.setItems(filteredList);
	}
	
	@FXML
	void btnRemovePressed(ActionEvent event) {
		Media media = tblMedia.getSelectionModel().getSelectedItem(); 
		cart.removeMedia(media);
		//Nếu ấn nút remove thì bỏ media khỏi cart
	}
	
	void updateButtonBar(Media media) {
		//Nếu chọn media có thể play thì hiển thị nút play
	    btnRemove.setVisible(true);
	    if (media instanceof Playable) {
	        btnPlay.setVisible(true);
	    } else {
	        btnPlay.setVisible(false);
	    }
	}
	@FXML
	void playMedia(ActionEvent event) {
		MediaPlayerDialog playDialog = new MediaPlayerDialog((JFrame) null, true, 
		        tblMedia.getSelectionModel().getSelectedItem());
		playDialog.setVisible(true);	
		//Khi play media gọi class hiển thị mediaplayerdialog
	}
	
	@FXML
	void viewStore(ActionEvent event) {
		
	    
	    // quay lại store
	    StoreScreen screen = new StoreScreen(store, cart);
	}

	

}
