//Nguyen Van Cuong - 20215006
package hust.soict.hedspi.aims.screen.controller;

import java.util.ArrayList;
import java.util.List;

import hust.soict.hedspi.aims.Cart;
import hust.soict.hedspi.aims.Store;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.screen.StoreScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AddCDScreenController {

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

    @FXML
    private TextField Artist;

    @FXML
    private TextField Track1;

    @FXML
    private VBox trackContainer;

    @FXML
    private TextField Length1;

    @FXML
    private VBox lengthContainer;

    @FXML
    private Label header1;

    @FXML
    private Label header2;
  //Các attribute
    public AddCDScreenController(Cart cart, Store store) {
        super();
        this.store = store;
        this.cart = cart;
    }
  //Constructor
    private List<TextField> trackFields = new ArrayList<>();
    private List<TextField> lengthFields = new ArrayList<>();
    //List lưu trữ các text field đê điền nhiều tracks
    @FXML
    void addTrack(ActionEvent event) {
        // Tạo track title field mới để thêm track
        TextField newTrackField = new TextField();
        newTrackField.setPrefWidth(250.0);
        VBox.setMargin(newTrackField, new Insets(0, 0, 0, 35.0));
        trackContainer.getChildren().add(newTrackField);
        trackFields.add(newTrackField);

        // Tạo length field mới để thêm length
        TextField newLengthField = new TextField();
        newLengthField.setPrefWidth(40.0);
        VBox.setMargin(newLengthField, new Insets(0, 0, 0, 35.0));
        lengthContainer.getChildren().add(newLengthField);
        lengthFields.add(newLengthField);
    }

    @FXML
    void addCD(ActionEvent event) {
    	//Nhấn nút để addcd vào store
        if (Title.getText().equals("")) {
        	 // Kiểm tra xem có hợp lệ
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a title for the cd.");
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
            alert.setContentText("Please enter a valid cost for the cd.");
            alert.showAndWait();
            return; 
        }

        int length;
        try {
            length = Integer.parseInt(Length.getText());
        } catch (NumberFormatException e) {
            // Báo lỗi nếu length không hợp lệ
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid length for the cd.");
            alert.showAndWait();
            return; 
        }
        CompactDisc cd = new CompactDisc(Title.getText(), Category.getText(), Director.getText(), length, cost,
                Artist.getText());

        Track firstTrack = new Track(Track1.getText(), Integer.parseInt(Length1.getText()));
        cd.addTrack(firstTrack);

        int i = 0;
        for (TextField track : trackFields) {
            Track trackAdd = new Track(track.getText(), Integer.parseInt(lengthFields.get(i).getText()));
            cd.addTrack(trackAdd);
            i++;
          //Duyệt tuần tự qua các track và thêm vào cd
        }

        store.addMedia(cd);
         // thêm cd vào cửa hàng
        // báo thành công
        showSuccessAlert("CD added successfully.");

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
        Artist.clear();
        Track1.clear();
        Length1.clear();
        trackContainer.getChildren().clear();
        lengthContainer.getChildren().clear();
        trackFields.clear();
        lengthFields.clear();

        // tạo lại header labels
        header1 = new Label("Title");
        header1.setTextFill(javafx.scene.paint.Color.web("#9e0f3f"));
        header1.setFont(new Font(14.0));
        header1.setPadding(new Insets(35.0, 0, 0, 35.0));

        header2 = new Label("Length");
        header2.setTextFill(javafx.scene.paint.Color.web("#9e0f3f"));
        header2.setFont(new Font(14.0));
        header2.setPadding(new Insets(35.0, 0, 0, 35.0));

        trackContainer.getChildren().add(header1);
        lengthContainer.getChildren().add(header2);

        // thêm first track và length fields lại
        TextField newTrackField = new TextField();
        newTrackField.setPrefWidth(250.0);
        VBox.setMargin(newTrackField, new Insets(0, 0, 0, 35.0));
        trackContainer.getChildren().add(newTrackField);
        trackFields.add(newTrackField);

        TextField newLengthField = new TextField();
        newLengthField.setPrefWidth(40.0);
        VBox.setMargin(newLengthField, new Insets(0, 0, 0, 35.0));
        lengthContainer.getChildren().add(newLengthField);
        lengthFields.add(newLengthField);
    }

    @FXML
    void viewStore(ActionEvent event) {
        new StoreScreen(store, cart);
        
    }

}
