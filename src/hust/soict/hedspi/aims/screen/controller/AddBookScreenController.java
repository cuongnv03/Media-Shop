//Nguyen Van Cuong - 20215006
package hust.soict.hedspi.aims.screen.controller;

import java.util.ArrayList;
import java.util.List;

import hust.soict.hedspi.aims.Cart;
import hust.soict.hedspi.aims.Store;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.screen.StoreScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddBookScreenController {
    
	private Store store;
	private Cart cart;
	@FXML
    private TextField Category;

    @FXML
    private TextField Cost;

    @FXML
    private TextField Title;
    
    @FXML
    private TextField author1;
    
    private List<TextField> authorFields = new ArrayList<>();
    
    @FXML
    private VBox authorContainer;
    
    //Các attribute
	public AddBookScreenController(Cart cart, Store store) {
		super();
		this.store = store;
		this.cart = cart;
	}
    //Constructor
    @FXML
    void addAuthor(ActionEvent event) {
    	//Thưc hiện khi muốn thêm nhiều tác giả cho 1 book, tạo thêm các text field để nhập thêm
    	TextField newAuthorField = new TextField();
        newAuthorField.setPrefWidth(250.0);
        authorContainer.getChildren().add(newAuthorField);
        authorFields.add(newAuthorField);
    }

    @FXML
    void addBook(ActionEvent event) {  
        //Nhấn nút để addbook vào store
    	if (Title.getText().equals("")) {
            // Kiểm tra xem có hợp lệ
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a title for the book.");
            alert.showAndWait();
            return; // dừng thực hiện và báo lỗi nếu sách ko có title
        }
        
        float cost;
        try {
            cost = Float.parseFloat(Cost.getText());
        } catch (NumberFormatException e) {
            // Báo lỗi nếu cost không hợp lệ
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid cost for the book.");
            alert.showAndWait();
            return; 
        }
        Book book = new Book(Title.getText(), Category.getText(), cost);
        book.addAuthor(author1.getText());
        for (TextField author : authorFields)
        {
        	book.addAuthor(author.getText());
        	//Duyệt tuần tự qua các tác giả và thêm vào
        }
        store.addMedia(book);
        
       // Thông báo thành công
        showSuccessAlert("Book added successfully.");

        // Reset mọi thứ lại như cũ sau khi add xong
        resetFields();
        
    }
    
    private void showSuccessAlert(String message) {
    	//Method thông báo add thành công
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void resetFields() {
        //Method reset
    	Title.clear();
        Category.clear();
        Cost.clear();
        author1.clear();
        authorContainer.getChildren().clear();
        authorFields.clear();
        
        TextField firstAuthorField = new TextField();
        firstAuthorField.setPrefWidth(250.0);
        authorContainer.getChildren().add(firstAuthorField);
        authorFields.add(firstAuthorField);
    }
    
    @FXML
	void viewStore(ActionEvent event) {
		new StoreScreen(store,cart);
		
	}

}
