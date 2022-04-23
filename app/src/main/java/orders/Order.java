package orders;

import java.util.ArrayList;

import coffee.Coffee;
import donut.Donut;
import mainMenu.MenuItem;

/**
 The Order class holds all the order information, ranging from donuts to coffee, and orders them based on when the order was made.
 Holds all the information of the coffee or donut order and can add or remove orders.
 @author Jah C. Speed, Abe Vitangcol
 */
public class Order implements Customizable{
	public int orderNumber;
	public ArrayList<MenuItem>orderList;
	
	/**
	 Constructs the order given a completed coffee or donut order and numbers it.
	 @param number The order number given to this order.
	 */
	public Order(int number) {
		this.orderList = new ArrayList<MenuItem>();
		this.orderNumber = number;
	}
	
	/**
	 Adds a coffee or donut order to the list of orders.
	 @param obj The coffee or donut order to add to the list of orders.
	 @return true if the addition of the said order was successful, false otherwise.
	 */
	@Override
	public boolean add(Object obj) {
		listInitilized();
		if(obj instanceof MenuItem) {
			this.orderList.add((MenuItem)obj);
			return true;
		}
		return false;
	}
	
	/**
	 Removes a coffee or donut order from the list of orders.
	 @param obj The coffee or donut order to be removed from the list of orders.
	 @return true if the removal of the said order was successful, false otherwise.
	 */
	@Override
	public boolean remove(Object obj) {
		listInitilized();
		if(obj instanceof MenuItem) {
			for(int i = 0; i < orderList.size(); i++) {
				if(this.orderList.get(i).isEqual((MenuItem)obj) == 0) {
					this.orderList.remove(orderList.get(i));
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 Initializes the order list to perform the addition or removal of orders to or from this list.
	 */
	private void listInitilized(){
		if(this.orderList == null) {
			this.orderList = new ArrayList<MenuItem>();
		}
	}
	
	/**
	 Checks if the order list is currently empty or not.
	 @return true if the order list is empty, false otherwise.
	 */
	public boolean isEmpty() {
		if(this.orderList.isEmpty())
			return true;
		else
			return false;
	}
	
	/**
	 Prints the order into a readable string format. Used for placing the order onto the user interface.
	 Prints it in "Order Number (ORDER NUMBER) - (ORDER TYPE AND CONTENTS)"
	 @return The order in string format.
	 */
	public String toString() {
		String returnString = ("Order Number: " + this.orderNumber + "  ");
		for(MenuItem e: this.orderList) {
			if(e instanceof Donut) {
				returnString+= "\t-Donut( ";
				returnString += e.toString();
				returnString += " )\n";
			}
			if(e instanceof Coffee) {
				returnString+= "\t-Coffee( ";
				returnString += e.toString();
				returnString += " )\n";
			}	
		}
		return returnString;
	}

}
