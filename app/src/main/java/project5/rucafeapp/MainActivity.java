package project5.rucafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import orders.Order;
import orders.OrderNumbers;
import orders.StoreOrder;

/**
 The MainActivity class handles the user interface operations for the main menu.
 Can go to the other screens to do other order activities and, from the other screens,
 come back to the main menu. The information regarding the current orders are kept throughout.
 @author Jah C. Speed, Abe Vitangcol
 */
public class MainActivity extends AppCompatActivity {
    public static Order currentOrder = new Order(OrderNumbers.getOrderNumber());
    public static StoreOrder storeOrders = new StoreOrder();

    /**
     Creates the main menu screen with the necessary buttons to go to the other screens.
     @param savedInstanceState data that was previously contained in other screens.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Button donutButton = findViewById(R.id.donutButton);
        Button coffeeButton = findViewById(R.id.coffeeButton);
        Button cartButton = findViewById(R.id.ordersButton);
        Button storeOrderButton = findViewById(R.id.storeordersButton);
        donutButton.setOnClickListener(new View.OnClickListener() {
            /**
             Upon clicking the donut store button, goes to the donut screen to order donuts.
             @param v
             */
            @Override
            public void onClick(View v) {
                openDonutScreen();
            }
        });
        coffeeButton.setOnClickListener(new View.OnClickListener() {
            /**
             Upon clicking the coffee store button, goes to the coffee screen to order coffee.
             @param v
             */
            @Override
            public void onClick(View v) {
                openCoffeeScreen();
            }
        });
        storeOrderButton.setOnClickListener(new View.OnClickListener() {
            /**
             Upon clicking the store order button, goes to the store order screen to see finalized orders.
             @param v
             */
            @Override
            public void onClick(View v) {
                openStoreScreen();
            }
        });
        cartButton.setOnClickListener(new View.OnClickListener() {
            /**
             Upon clicking the cart button, goes to the cart screen to remove orders before finalizing it.
             @param v
             */
            @Override
            public void onClick(View v) {
                openCartScreen();
            }
        });
    }

    /**
     Changes and opens the screen to the store screen, where finalized orders can be viewed and removed.
     */
    private void openStoreScreen() {
        Intent intent = new Intent(this, StoreOrderScreen.class);
        startActivity(intent);
    }

    /**
     Resets current orders stored on the cart when the order has been finalized.
     */
    public static void resetOrder(){
        currentOrder = new Order(OrderNumbers.getOrderNumber());
    }

    /**
     Changes and opens the screen to the donut screen, where donuts can be ordered.
     */
    public void openDonutScreen() {
        Intent intent = new Intent(this, DonutsScreen.class);
        startActivity(intent);
    }

    /**
     Changes and opens the screen to the coffee screen, where coffee can be customized to be ordered.
     */
    public void openCoffeeScreen() {
        Intent intent = new Intent(this, CoffeeScreen.class);
        startActivity(intent);
    }

    /**
     Changes and opens the screen to the cart screen, where orders can be removed or finalized.
     */
    public void openCartScreen() {
        Intent intent = new Intent(this, OrderScreen.class);
        startActivity(intent);
    }

}