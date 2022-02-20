package by.clevertec.shop.model.entity;

import java.util.Objects;
import static by.clevertec.shop.util.Constant.*;
public class DiscountCard {
	private int cardId;
	private String nameCard;
    public static final double PERCENT = 5;
    
	public DiscountCard() {
	
	}
	public DiscountCard(int cardId) {
		this.cardId = cardId;
		this.nameCard = "Card-"+cardId;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public String getNameCard() {
		return nameCard;
	}
	public void setNameCard(String nameCard) {
		this.nameCard = nameCard;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(cardId, nameCard);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiscountCard other = (DiscountCard) obj;
		return cardId == other.cardId && Objects.equals(nameCard, other.nameCard);
	}
	@Override
	public String toString() {
		return cardId + SPLITTER + nameCard;
	}
	
	

}
