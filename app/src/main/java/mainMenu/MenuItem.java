package mainMenu;

import java.text.DecimalFormat;

/**
 The MenuItem class is an abstract class to work as a framework so other classes can hold details about the item's specific details.
 This class is the parent of both Donut and Coffee.
 @author Jah C. Speed, Abe Vitangcol
 */
public abstract class MenuItem {
	protected double price;
	protected int amount;
	protected String itemName;
	protected String itemType;
	protected final DecimalFormat format = new DecimalFormat("###,##0.00");

	/**
	 Returns the total price of the item.
	 @return The total price of the item, which is seen by doing individual price * amount in $##.## amount.
	 */
	public double itemPrice() {
		return price * amount;
	}
	
	/**
	 Returns the amount the order has for this specific order.
	 @return The amount the order has listed.
	 */
	public int getAmount() {
		return amount;
	}
	
	/**
	 Returns the unit price of the item.
	 @return The price of the item. Amount is not factored for this one, in $##.## amount.
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 Returns the name of the item, usually defined by the type of donut or coffee.
	 @return The name of the item, generally coffee or one of the three donut types, in string format.
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 Returns the type of this item, usually defined as a black coffee or a specific donut flavor.
	 @return The type of this item.
	 */
	public String getType() {
		return itemType;
	}
	
	/**
	 Checks if an object of interest is equal to this object.
	 @param obj The object of interest to see if it is the same as this one.
	 @return 0 if the objects are equal or the same, -1 otherwise.
	 */
	public int isEqual(Object obj) {
		if(obj instanceof MenuItem) {
			obj = (MenuItem)obj;
			if(((MenuItem) obj).getItemName() == getItemName()) {
				if(((MenuItem) obj).getType() == getType()) {
					return 0;
				}
			}
		}
		return -1;
		
	}
	
	/**
	 Returns this object into a readable string format.
	 @return A string in "Item: (ITEMNAME) | Flavor: (ITEMTYPE) | Quantity: (AMOUNT)" format.
	 */
	public String toString() {
		return "\n\tItem: " + this.itemName + "\n\tFlavor: " + this.itemType + "\n\tQuantity: " + this.amount
				+"\n\tPrice: $" + format.format(this.itemPrice());
	}
}
