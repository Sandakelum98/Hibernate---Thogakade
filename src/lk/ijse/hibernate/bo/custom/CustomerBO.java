package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.entity.Customer;

public interface CustomerBO extends SuperBO {
    public boolean add(CustomerDTO customerDTO) throws Exception;
}
