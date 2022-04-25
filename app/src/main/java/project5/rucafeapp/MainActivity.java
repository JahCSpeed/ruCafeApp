package project5.rucafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import orders.Order;
import orders.OrderNumbers;
import orders.StoreOrder;


public class MainActivity extends AppCompatActivity {
    public static Order currentOrder = new Order(OrderNumbers.getOrderNumber());
    public static StoreOrder storeOrders = new StoreOrder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Button donutButton = findViewById(R.id.donutButton);
        Button coffeeButton = findViewById(R.id.coffeeButton);
        Button cartButton = findViewById(R.id.ordersButton);
        Button storeOrderButton = findViewById(R.id.storeordersButton);
        donutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDonutScreen();
            }
        });
        coffeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCoffeeScreen();
            }
        });
        storeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStoreScreen();
            }
        });
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCartScreen();
            }
        });
    }

    private void openStoreScreen() {
        Intent intent = new Intent(this, StoreOrderScreen.class);
        startActivity(intent);
    }

    public static void resetOrder(){
        currentOrder = new Order(OrderNumbers.getOrderNumber());
    }
    public void openDonutScreen() {
        Intent intent = new Intent(this, DonutsScreen.class);
        startActivity(intent);

    }
    public void openCoffeeScreen() {
        Intent intent = new Intent(this, CoffeeScreen.class);
        startActivity(intent);
    }
    public void openCartScreen() {
        Intent intent = new Intent(this, OrderScreen.class);
        startActivity(intent);
    }

}