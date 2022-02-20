package by.clevertec.shop.model.entity;

import java.util.Objects;
import static by.clevertec.shop.util.Constant.*;

public class Purchase {
	private Item product;
	private int quantity;
	public Purchase() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Purchase(Item product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}
	public Item getProduct() {
		return product;
	}
	public void setProduct(Item product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Byn getCost() {
		return product.getPrice().multiplication(quantity);
	}
	
	protected String fieldsToString() {
        return product + SPLITTER + quantity;
    }
	@Override
	public int hashCode() {
		return Objects.hash(product, quantity);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Purchase other = (Purchase) obj;
		return Objects.equals(product, other.product) && quantity == other.quantity;
	}
	@Override
	public String toString() {
		return fieldsToString()+SPLITTER + getCost();
	}
	

}
