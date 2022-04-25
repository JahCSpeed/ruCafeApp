package project5.rucafeapp;

/**
 The Flavor class handles with holding and giving the flavor type of a donut order.
 It is capable of setting a donut flavor, returning a donut flavor, and changing flavors.
 @author Jah C. Speed, Abe Vitangcol
 */
public class Flavor {
    private String flavorType;

    /**
     Constructs a Flavor object for a specific donut.
     @param flavorType The flavor of the donut to be ordered.
     */
    public Flavor(String flavorType){
        this.flavorType = flavorType;
    }

    /**
     Returns the flavor of the current donut.
     @return The flavor of the donut in a string format.
     */
    public String getFlavorType(){
        return this.flavorType;
    }

    /**
     Changes the flavor type of the donut to a different one.
     @param flavorType The donut flavor to change to.
     */
    public void setFlavorType(String flavorType){
        this.flavorType = flavorType;
    }
}
