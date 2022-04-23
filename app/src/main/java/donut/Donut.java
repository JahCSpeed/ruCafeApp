package donut;

import mainMenu.MenuItem;

/**
 The Donut class extends from the MenuItem class to hold information regarding a specific donut order.
 Holds information about the donut favorite name, the donut type, the amount of donuts of these parameters ordered,
 and the total price from these properties.
 @author Jah C. Speed, Abe Vitangcol
 */
public class Donut extends MenuItem {
	/**
	 Constructs a donut object, giving it a specific type and flavor as well as a number ordered and price associated with it.
	 @param donutName The type of donut: Yeast, Cake, or Hole.
	        donutType The flavor of this particular donut.
	        price The amount it costs to buy this donut.
	        amount The number of donuts wanted of these specific details.
	 */
	public Donut(String donutName, String donutType, double price, int amount ) {
		this.itemName = donutName;
		this.itemType = donutType;
		this.amount = amount;
		this.price = price;
	}

}
