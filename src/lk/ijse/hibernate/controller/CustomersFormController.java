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
import lk.ijse.hibernate.bo.custom.CustomerBO;
import lk.ijse.hibernate.dto.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

public class CustomersFormController {
    public JFXTextField txtName;
    public Label lblId;
    public JFXTextField txtAddress;
    public JFXTextField txtSalary;
    public JFXButton btnSave;
    public JFXButton btnCancel;
    public TableView tblCustomers;
    public TableColumn clmId;
    public TableColumn clmName;
    public TableColumn clmAddress;
    public TableColumn clmSalary;


    CustomerBO customerBo = BOFactory.getInstance().getBO(BOType.CUSTOMER);

    public void initialize() throws Exception {
        generateNewId();
        loadAll();
        lblId.requestFocus();
    }

    //Set ID Number
    private void generateNewId() {
        try {
            String newId = customerBo.getNewCustomerId();
            lblId.setText(newId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Load All customers
    public void loadAll() throws Exception {
        clmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        List<CustomerDTO> allCustomers = customerBo.getAll();
        tblCustomers.setItems(FXCollections.observableArrayList(allCustomers));
    }

    //Clear Text
    public void clearAll(){
        txtName.clear();
        txtAddress.clear();
        txtSalary.clear();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        try {
            String id = lblId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            double salary = Double.parseDouble(txtSalary.getText());

            boolean isAdded =customerBo.add(new CustomerDTO(id,name,address,salary));
            if(isAdded) {
                new Alert(Alert.AlertType.WARNING, "Saved !").show();
                clearAll();
                generateNewId();
                loadAll();
            }else {
                new Alert(Alert.AlertType.WARNING, "Failed !").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        clearAll();
    }




}
