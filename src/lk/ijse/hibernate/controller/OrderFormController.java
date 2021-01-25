package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jdk.internal.dynalink.linker.LinkerServices;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.BOType;
import lk.ijse.hibernate.bo.custom.CustomerBO;
import lk.ijse.hibernate.bo.custom.ItemBO;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.dto.ItemDTO;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderFormController {
    public Label lblOrderId;
    public JFXButton btnAdd;
    public JFXButton btnRemove;
    public TableView tblOrder;
    public TableColumn clmCode;
    public TableColumn clmDescription;
    public TableColumn clmUnitPrice;
    public TableColumn clmTotal;
    public TableColumn clmQty;
    public Label lblDate;
    public JFXComboBox cmbCustomers;
    public Label lblCustomerName;
    public JFXButton btnNewCustomer;
    public JFXComboBox cmbItemCode;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;
    public TextField txtQty;
    public Label lblTotal;
    public JFXButton btnPlaceOrder;

    CustomerBO customerBO = BOFactory.getInstance().getBO(BOType.CUSTOMER);
    ItemBO itemBO = BOFactory.getInstance().getBO(BOType.ITEM);

    public void initialize() throws Exception {
        generateDate();
        loadCustomersID();
        loadItemsCode();
    }

    //Date
    private void generateDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        lblDate.setText(dateFormat.format(date));
    }

    //Customers
    private void loadCustomersID() throws Exception {
        List<CustomerDTO> allCustomers = customerBO.getAll();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("");
        for (CustomerDTO c : allCustomers) {
            observableList.add(c.getId());
        }
        cmbCustomers.setItems(observableList);
    }

    //Items
    private void loadItemsCode() throws Exception {
        List<ItemDTO> allItems = itemBO.getAllitems();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("");
        for (ItemDTO i : allItems) {
            observableList.add(i.getCode());
        }
        cmbItemCode.setItems(observableList);
    }


    public void btnAddOnAction(ActionEvent actionEvent) {
        String code = (String) cmbItemCode.getSelectionModel().getSelectedItem();
        ObservableList<Object> selectItem = FXCollections.observableArrayList();
        selectItem.add(code);

        tblOrder.setItems(FXCollections.observableArrayList(selectItem));

        clmCode.setCellValueFactory(new PropertyValueFactory<>("code"));
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
    }

    public void btnNewCustomerOnAction(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk/ijse/hibernate/view/CustomersForm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
    }

    public void cmbCustomersOnAction(ActionEvent actionEvent) {
        try {
            String id = (String) cmbCustomers.getSelectionModel().getSelectedItem();
            CustomerDTO customerDTO = customerBO.searchCustomer(id);
            lblCustomerName.setText(customerDTO.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cmbItemCodeOnAction(ActionEvent actionEvent) {
        try {
            String code = (String) cmbItemCode.getSelectionModel().getSelectedItem();
            ItemDTO itemDTO = itemBO.searchItem(code);
            txtDescription.setText(itemDTO.getDescription());
            double unitPrice = itemDTO.getUnitPrice();
            txtUnitPrice.setText(String.valueOf(unitPrice));
            txtQtyOnHand.setText(String.valueOf(itemDTO.getQty()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
