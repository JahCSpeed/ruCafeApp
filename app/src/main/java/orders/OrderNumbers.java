package orders;

/**
 The OrderNumber class keeps track of the current order number in the list of orders and increments by 1 with each new order.
 @author Jah C. Speed, Abe Vitangcol
 */
public class OrderNumbers {
	private static int number = 0;
	
	/**
	 Gets the order number of this order.
	 @return The current order number plus one to show this order number for a new order.
	 */
	public static int getOrderNumber() {
		return ++number;
	}
}
