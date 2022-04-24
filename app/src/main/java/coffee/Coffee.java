package coffee;


import java.util.ArrayList;

import mainMenu.MenuItem;
import orders.Customizable;

/**
 The Coffee class extends from the MenuItem class to give specific details regarding coffee items.
 Holds information regarding the list of add-ins the coffee has, the size the coffee will be, as well
 as the end price of having these said properties.
 @author Jah C. Speed, Abe Vitangcol
 */
public class Coffee extends MenuItem implements Customizable {
	public ArrayList<String>addIns;
	
	/**
	 Constructor for the coffee class, giving each coffee a size, type of item, the quantity, and the price for the said coffee.
	 Capable of holding information about the add-ons for the coffee.
	 @param size The size of the coffee, either small, tall, grande, or venti.
	        coffee The type of item this object is.
	        price The amount of money it costs for this coffee.
	        amount The amount of coffee of this specifications that will be made in one order.
	 */
	public Coffee(String size, String coffee, double price, int amount) {
		this.itemName = size;
		this.itemType = coffee;
		this.amount = amount;
		this.price = price;
		this.addIns = new ArrayList<String>();
	}
	
	/**
	 Adds an addIn of the user's choice to the coffee.
	 @param obj The object to add in to the coffee.
	 @return true if the addition of the addIn was successful, false otherwise.
	 */
	@Override
	public boolean add(Object obj) {
		listInitilized();
		if(obj instanceof String) {
			this.addIns.add((String)obj);
			return true;
		}
		return false;
	}
	
	/**
	 Removes a said addIn from the current list of addIns for this coffee.
	 @param obj The object to remove from the coffee.
	 @return true if the removal of the addIn was successful, false otherwise.
	 */
	@Override
	public boolean remove(Object obj) {
		listInitilized();
		if(obj instanceof String) {
			this.addIns.remove((String)obj);
			return true;
			
		}
		return false;
	}
	
	/**
	 Initializes the addIns list to perform addition or removal of addIns for this coffee.
	 */
	private void listInitilized(){
		if(this.addIns == null) {
			this.addIns = new ArrayList<String>();
		}
	}
	
	/**
	 Turns this coffee object into a readable string. Useful for outputting the order onto the screen.
	 Prints it in "Size: (SIZE) | Flavor: (FLAVOR) | Addins: (LIST OF ADDINS) | Quantity: (AMOUNT)" format.
	 @return The coffee order in string format.
	 */
	@Override
	public String toString() {
		String returnString = ("\n\tSize: " + this.itemName + "\n\tFlavor: " + this.itemType + "\n\tAddins: ");
		if(addIns.size() == 0) {
			returnString+= "NONE";
		}else {
			for(int i = 0; i < addIns.size(); i++) {
				if(i != addIns.size()  -1)
					returnString+= addIns.get(i) + ", ";
				else
					returnString+= addIns.get(i);
			}
		}
		returnString+= "\n\tQuantity: " + this.amount + "\n\tPrice: $" + this.itemPrice();
		return returnString;
		
	}
}
