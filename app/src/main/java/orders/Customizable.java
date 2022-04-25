package orders;

/**
 The Customizable class enables menu items to be customized by adding in different add ins to make the order unique.
 @author Jah C. Speed, Abe Vitangcol
 */
public interface Customizable {
	/**
	 Adds an item to a specific object.
	 @param obj The object to be added.
	 @return True if successful, false otherwise.
	 */
	boolean add(Object obj);

	/**
	 Removes an item to a specific object.
	 @param obj The object to be removed.
	 @return True if successful, false otherwise.
	 */
	boolean remove(Object obj);
}
