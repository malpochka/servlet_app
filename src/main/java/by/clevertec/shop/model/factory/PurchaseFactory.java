package by.clevertec.shop.model.factory;

import by.clevertec.shop.model.entity.Byn;
import by.clevertec.shop.model.entity.Item;
import by.clevertec.shop.model.entity.PromotionalPurchase;
import by.clevertec.shop.model.entity.Purchase;
import static by.clevertec.shop.util.Constant.*;

public class PurchaseFactory {
	
	public static Purchase getPurchaseFromFactory(String line) {
		String[] parts = line.split(SPLITTER);
		Purchase purchase = new Purchase();
		int itemId = Integer.parseInt(parts[0]);
		String title = parts[1];
		Byn price = new Byn(Integer.parseInt(parts[2]));
		int quantity = Integer.parseInt(parts[3]);

		if (parts.length == 3) {
			purchase = new Purchase(new Item(itemId, title, price), quantity);
		} else if (parts.length == 4) {
			double discount = (Double.parseDouble(parts[4]));
			purchase = new PromotionalPurchase(new Item(itemId, title, price), quantity, discount);
		}
		return purchase;
	}
}
