package by.clevertec.shop.model.logic;

import java.util.ArrayList;
import java.util.List;

import by.clevertec.shop.model.entity.Byn;
import by.clevertec.shop.model.entity.DiscountCard;
import by.clevertec.shop.model.entity.Purchase;
import by.clevertec.shop.model.entity.RoundMethod;

public class PurchaseLogic {
	public static Byn getTotalCost(List<Purchase> purchases) {
		Byn totalCost = new Byn();
		for (Purchase pur : purchases) {
			totalCost = totalCost.add(pur.getCost());
		}
		return totalCost;
	}

	public static Byn getTotalCostAfterDiscount(List<Purchase> purchases, DiscountCard card) {
		Byn totalFinalCost = new Byn();

		if (card.getCardId() == 0) {
			totalFinalCost = getTotalCost(purchases);
		} else {
			totalFinalCost = getTotalCost(purchases)
					.multiplication(1 - DiscountCard.PERCENT / 100, RoundMethod.ROUND,0);
		}
		return totalFinalCost;

	}

	public static Byn getDiscount(List<Purchase> purchases, DiscountCard card) {
		return getTotalCost(purchases).subtract(getTotalCostAfterDiscount(purchases, card));

	}

}
