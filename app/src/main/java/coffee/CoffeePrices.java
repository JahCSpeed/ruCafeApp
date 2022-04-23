package coffee;

/**
 The CoffeePrices class stores information regarding how much each size of coffee costs.
 An enum class that gives each size a price in decimal format and only up to 2 decimal places.
 @author Jah C. Speed
 */
public enum CoffeePrices {
	SMALL(1.69),
	TALL(2.09),
	GRANDE(2.49),
	VENTI(2.89);
	
	public final double price;
	
	/**
	 Constructor for the coffee prices, giving each coffee size their respective price.
	 @param price The amount it costs to buy this specific size for coffee.
	 */
	private CoffeePrices(double price) {
		this.price = price;
	}
}
