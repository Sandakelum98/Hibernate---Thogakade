package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.ItemDAO;
import lk.ijse.hibernate.entity.Customer;
import lk.ijse.hibernate.entity.Item;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public boolean update(Item entity) throws Exception {
        return false;
    }

    @Override
    public Item search(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Item where id = ?1");
        query.setParameter(1, s);
        Item item = (Item) query.uniqueResult();

        transaction.commit();
        session.close();

        return item;
    }

    @Override
    public List<Item> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Item");
        List<Item> list = query.list();

        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public String getLastItemCode() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("select code from Item order by code desc limit 1");
        String code = (String) sqlQuery.uniqueResult();

        transaction.commit();
        session.close();
        return code;
    }
}
