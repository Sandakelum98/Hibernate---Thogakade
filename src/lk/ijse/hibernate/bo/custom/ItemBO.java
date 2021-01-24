package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.ItemDTO;

import java.util.List;

public interface ItemBO extends SuperBO {
    public boolean addItem(ItemDTO itemDTO) throws Exception;
    public List getAllitems() throws Exception;
    public String getNewItemCode() throws Exception;
}
