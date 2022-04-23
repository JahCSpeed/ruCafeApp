package orders;

import java.util.ArrayList;

/**
 The StoreOrder class deals with storing orders made from coffee or donut orders.
 The class is capable of adding and removing orders and knows when its list is empty to prevent
 invalid commands with an empty list.
 @author Jah C. Speed, Abe Vitangcol
 */
public class StoreOrder implements Customizable {
	public ArrayList<Order> storeOrders;
	
	/**
	 Constructor for the StoreOrder to create a new array list to store various orders from the other classes.
	 */
	public StoreOrder() {
		this.storeOrders = new ArrayList<Order>();
	}
	
	/**
	 Adds an order to the list of storeOrders.
	 @param obj The order object that is being added to the storeOrders.
	 @return true if the order has been successfully added, false otherwise.
	 */
	@Override
	public boolean add(Object obj) {
		listInitilized();
		if(obj instanceof Order) {
			this.storeOrders.add((Order)obj);
			return true;
		}
		return false;
	}
	
	/**
	 Removes an order from the list of storeOrders.
	 @param obj The order object that is being removed from the storeOrders.
	 @return true if the order has been successfully removed, false otherwise.
	 */
	@Override
	public boolean remove(Object obj) {
		listInitilized();
		if(obj instanceof Order) {
			this.storeOrders.remove((Order)obj);
			return true;
		}
		return false;
	}
	
	/**
	 Initializes the arraylist of storeOrders to enable addition or removal of orders into it.
	 */
	private void listInitilized(){
		if(this.storeOrders == null) {
			this.storeOrders = new ArrayList<Order>();
		}
	}
	
	/**
	 Checks if the storeOrder list is empty or not.
	 @return true if the storeOrder list is empty, false otherwise.
	 */
	public boolean isEmpty() {
		if(this.storeOrders.isEmpty())
			return true;
		else
			return false;
	}
}
