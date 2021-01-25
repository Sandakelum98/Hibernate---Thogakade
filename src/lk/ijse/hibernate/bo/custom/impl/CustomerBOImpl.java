package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.CustomerBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.DAOType;
import lk.ijse.hibernate.dao.custom.CustomerDAO;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);

    @Override
    public boolean add(CustomerDTO customerDTO) throws Exception {
        return customerDAO.add(new Customer(
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getSalary()
        ));
    }

    @Override
    public String getNewCustomerId() throws Exception {
        String lastId = customerDAO.getLastCustomerId();

        int newId = Integer.parseInt(lastId.substring(1, 4))+1;

        if(newId < 10){
            return "C00"+newId;
        }else if(newId < 100){
            return "C0"+newId;
        }else{
            return "C"+newId;
        }
    }

    @Override
    public List getAll() throws Exception {
        List<Customer> allCustomers = customerDAO.getAll();
        List<CustomerDTO> allCustomerDTOS = new ArrayList<>();
        for (Customer c : allCustomers) {
            CustomerDTO customerDTO = new CustomerDTO(
                    c.getId(),
                    c.getName(),
                    c.getAddress(),
                    c.getSalary()
            );
            allCustomerDTOS.add(customerDTO);
        }
        return allCustomerDTOS;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws Exception {
        Customer customer = customerDAO.search(id);
        CustomerDTO customerDTO = new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getSalary()
        );
        return customerDTO;
    }
}
