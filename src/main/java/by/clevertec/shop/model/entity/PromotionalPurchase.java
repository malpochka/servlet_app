package by.clevertec.shop.model.entity;

import static by.clevertec.shop.util.Constant.*;

public class PromotionalPurchase extends Purchase {
	private static final double NUMBER_DISCOUNT = 5;
	private static final double PERCENT = 10;
	private double discount;

	public PromotionalPurchase(Item product, int quantity, double discount) {
		super(product, quantity);
		this.discount = discount;

	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public Byn getCost() {
		Byn finalCost = getProduct().getPrice().multiplication(1 - discount / 100, RoundMethod.FLOOR, 0)
				.multiplication(getQuantity());
		if (getQuantity() > NUMBER_DISCOUNT) {
			finalCost = finalCost.multiplication(1 - PERCENT / 100, RoundMethod.FLOOR, 0);
		}
		return finalCost;
	}

	@Override
	public String toString() {
		return super.fieldsToString() + SPLITTER + discount;
	}

}
