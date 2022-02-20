package by.clevertec.shop.model.entity;

import java.util.Objects;
import static by.clevertec.shop.util.Constant.*;

public class Item {
	private int itemId;
	private String title;
	private Byn price;
	
	public Item() {
	}

	public Item(int itemId, String title, Byn price) {
		super();
		this.itemId = itemId;
		this.title = title;
		this.price = price;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Byn getPrice() {
        return price;
    }

	public void setPrice(Byn price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(price, itemId, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(price, other.price) && itemId == other.itemId && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return  itemId + SPLITTER + title + SPLITTER + price ;
	}

}
