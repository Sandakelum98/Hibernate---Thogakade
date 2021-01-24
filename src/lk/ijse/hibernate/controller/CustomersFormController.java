package lk.ijse.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.BOType;
import lk.ijse.hibernate.bo.custom.CustomerBO;
import lk.ijse.hibernate.dto.CustomerDTO;

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
    public JFXTextField txtId;

    CustomerBO customer = BOFactory.getInstance().getBO(BOType.CUSTOMER);

    public void btnSaveOnAction(ActionEvent actionEvent) {

        try {
            String id = txtId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            double salary = Double.parseDouble(txtSalary.getText());

            boolean isAdded =customer.add(new CustomerDTO(id,name,address,salary));
            if(isAdded) {
                new Alert(Alert.AlertType.WARNING, "Saved !").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Failed !").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
    }
}
