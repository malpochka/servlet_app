package by.clevertec.shop.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

import by.clevertec.shop.exception.DaoException;
import by.clevertec.shop.model.entity.Byn;
import by.clevertec.shop.model.entity.Item;
import by.clevertec.shop.model.entity.PromotionalPurchase;
import by.clevertec.shop.model.entity.Purchase;

import static by.clevertec.shop.util.Constant.*;

public class PurchaseCsvImplDao implements PurchaseDAO {
	private static String fileName;
	
	public static void init(ResourceBundle rb) {
		fileName = rb.getString(LOAD_FILE);
	}


	@Override
	public List<Purchase> getPurchases() throws DaoException {

		List<Purchase> purchases = new ArrayList<>();

		try (Scanner sc = new Scanner(new FileReader(fileName))) {
			Purchase purchase = new Purchase();

			while (sc.hasNext()) {
				String line = sc.next();
				String[] parts = line.split(SPLITTER);
				int itemId = Integer.parseInt(parts[0]);
				String title = parts[1];
				Byn price = new Byn(Integer.parseInt(parts[2]));
				int quantity = Integer.parseInt(parts[3]);

				if (parts.length == 4) {
					purchase = new Purchase(new Item(itemId, title, price), quantity);
					purchases.add(purchase);
				} else if (parts.length == 5) {
					double discount = (Double.parseDouble(parts[4]));
					purchase = new PromotionalPurchase(new Item(itemId, title, price), quantity, discount);
					purchases.add(purchase);
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println(NOT_FOUND);
		}
		return purchases;
	}

	public List<Purchase> getPurchaseById(Map<Integer,Integer> purchasesFinish ) {
		List<Purchase> purchases;
		List<Purchase> checkPurchase = new ArrayList<>();
		try {
			purchases = getPurchases();

			for (Map.Entry<Integer, Integer> entry : purchasesFinish.entrySet()) {
				for (Purchase pur : purchases) {
					if (pur.getProduct().getItemId() == entry.getKey()) {
						pur.setQuantity(entry.getValue());
						checkPurchase.add(pur);
					}
				}
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return checkPurchase;
	}
}
