package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.BOType;
import lk.ijse.hibernate.bo.custom.ItemBO;
import lk.ijse.hibernate.dto.ItemDTO;

import java.util.List;

public class ItemFormController {
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public Label lblId;
    public JFXButton btnSave;
    public JFXButton btnCancel;
    public TableView tblItems;
    public TableColumn clmCode;
    public TableColumn clmDescription;
    public TableColumn clmUnitPrice;
    public TableColumn clmQty;

    ItemBO itemBO = BOFactory.getInstance().getBO(BOType.ITEM);

    public void initialize() throws Exception {
        generateNewCode();
        loadItems();
    }

    private void generateNewCode() throws Exception {
        String code = itemBO.getNewItemCode();
        lblId.setText(code);
    }

    //Load Items
    public void loadItems() throws Exception {
        clmCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        clmUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        clmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        List<ItemDTO> items = itemBO.getAllitems();
        tblItems.setItems(FXCollections.observableArrayList(items));
    }

    //Clear text
    public void clearAll() {
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQty.clear();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        try {
            String code = lblId.getText();
            String description = txtDescription.getText();
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            int qty = Integer.parseInt(txtQty.getText());

            boolean isAdded = itemBO.addItem(new ItemDTO(code,description,unitPrice,qty));
            if(isAdded) {
                new Alert(Alert.AlertType.WARNING, "Saved !").show();
                clearAll();
                loadItems();
            } else {
                new Alert(Alert.AlertType.WARNING, "Failed !").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
    }
}
