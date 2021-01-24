package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainFormController {
    public AnchorPane context;
    public JFXButton btnCustomers;
    public JFXButton btnItems;
    public JFXButton btnOrders;

    //START-------------------------------------------------------------------------------------------------------------
    public void initialize() throws IOException {
        loadDefault();
    }

    private void loadDefault() throws IOException {
        setUi("CustomersForm");
    }

    //Method FOR ALL
    public void setUi(String location) throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().getResource("/lk/ijse/hibernate/view/" + location + ".fxml")));
    }

    public void btnCustomersOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomersForm");
    }

    public void btnItemsOnAction(ActionEvent actionEvent) throws IOException {
        setUi("ItemForm");
    }

    public void btnOrdersOnAction(ActionEvent actionEvent) {
    }
}
