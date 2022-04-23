package donut;

/**
 The DonutPrices class stores information about the types of donuts being sold as well as their associated price.
 An enum class that says if the donut type is of a yeast, cake, or hole type and has a decimal formatted price
 in 2 decimal places associated with each donut type.
 @author Jah C. Speed
 */
public enum DonutPrices {
	YEAST("Yeast Donut",1.59),
	CAKE("Cake Donut",1.79),
	HOLE("Donut Hole",0.39);
	
	public final double price;
	public final String name;
	
	/**
	 Constructor for the donuts, giving the donuts a type and a cost associated with the said type of donut.
	 @param name The type of donut being sold in a string format.
	        price The amount of money it costs to buy this said type of donut.
	 */
	private DonutPrices(String name,double price) {
		this.name = name;
		this.price = price;
	}
}
