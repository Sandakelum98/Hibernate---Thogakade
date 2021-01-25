package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.entity.Customer;

import java.util.List;

public interface CustomerBO extends SuperBO {
    public boolean add(CustomerDTO customerDTO) throws Exception;
    public String getNewCustomerId() throws Exception;
    public List getAll() throws Exception;
    public CustomerDTO searchCustomer(String id) throws Exception;
}
