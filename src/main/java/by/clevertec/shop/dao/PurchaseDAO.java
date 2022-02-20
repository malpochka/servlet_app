package by.clevertec.shop.dao;

import java.util.List;
import java.util.Map;


import by.clevertec.shop.exception.DaoException;
import by.clevertec.shop.model.entity.Purchase;


public interface PurchaseDAO {
 List<Purchase> getPurchases() throws DaoException;
 List<Purchase> getPurchaseById(Map<Integer,Integer> purchasesFinish);
}
