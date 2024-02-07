//Nguyen Van Cuong - 20215006
package hust.soict.hedspi.aims.screen.controller;
import hust.soict.hedspi.aims.Cart;
import hust.soict.hedspi.aims.Store;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.screen.StoreScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddDVDScreenController {
    
	private Store store;
	private Cart cart;

	@FXML
    private TextField Category;

    @FXML
    private TextField Cost;

    @FXML
    private TextField Director;

    @FXML
    private TextField Length;

    @FXML
    private TextField Title;
    
    
  //Các attribute
	public AddDVDScreenController(Cart cart, Store store) {
		super();
		this.store = store;
		this.cart = cart;
	}
	//Constructor
    @FXML
    void addDVD(ActionEvent event) {
    	//Nhấn nút để adddvd vào store
    	if (Title.getText().equals("")) {
        	// Kiểm tra xem có hợp lệ
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a title for the dvd.");
            alert.showAndWait();
            return; 
        }
        
        float cost;
        try {
            cost = Float.parseFloat(Cost.getText());
        } catch (NumberFormatException e) {
        	// Báo lỗi nếu cost không hợp lệ
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid cost for the dvd.");
            alert.showAndWait();
            return; 
        }
        
        int length;
        try {
            length =Integer.parseInt(Length.getText());
        } catch (NumberFormatException e) {
        	// Báo lỗi nếu cost không hợp lệ
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid length for the dvd.");
            alert.showAndWait();
            return; 
        }
		DigitalVideoDisc dvd = new DigitalVideoDisc(Title.getText(),
                Category.getText(), Director.getText(), length, cost );
        store.addMedia(dvd);
     // báo thành công
        showSuccessAlert("DVD added successfully.");

     // reset về ban dầu
        resetFields();
    }

    
    private void showSuccessAlert(String message) {
    	 //Method báo thành công
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void resetFields() {
    	//Method reset sau khi add
        Title.clear();
        Category.clear();
        Cost.clear();
        Director.clear();
        Length.clear();
    }
    
    @FXML
	void viewStore(ActionEvent event) {
		StoreScreen Screen = new StoreScreen(store,cart);
		
	}

}
