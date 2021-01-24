package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.ItemBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.DAOType;
import lk.ijse.hibernate.dao.custom.ItemDAO;
import lk.ijse.hibernate.dto.ItemDTO;
import lk.ijse.hibernate.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO{

    ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);

    @Override
    public boolean addItem(ItemDTO itemDTO) throws Exception {
        return itemDAO.add(new Item(
                itemDTO.getCode(),
                itemDTO.getDescription(),
                itemDTO.getUnitPrice(),
                itemDTO.getQty()
        ));
    }

    @Override
    public List getAllitems() throws Exception {
        List<Item> items = itemDAO.getAll();
        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : items) {
            ItemDTO itemDTO = new ItemDTO(
                    item.getCode(),
                    item.getDescription(),
                    item.getUnitPrice(),
                    item.getQty()
            );
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }

    @Override
    public String getNewItemCode() throws Exception {
        String code = itemDAO.getLastItemCode();

        int newCode = Integer.parseInt(code.substring(1, 4))+1;

        if(newCode < 10){
            return "P00"+newCode;
        }else if(newCode < 100){
            return "P0"+newCode;
        }else{
            return "P"+newCode;
        }
    }
}
