package project5.rucafeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

/**
 The StoreOrderScreen shows the list of all finalized orders to view or delete.
 Capable of showing all the numbered orders as well as their contents.
 Orders can also be deleted.
 @author Jah C. Speed, Abe Vitangcol
 */
public class StoreOrderScreen extends AppCompatActivity {
    private Button backBtn;
    private ListView allOrders;
    /**
     Creates the StoreOrderScreen and necessary buttons when called.
     @param savedInstanceState data that was previously contained in other screens.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_storeorders);
        allOrders = findViewById(R.id.allStoreOrderListView);
        backBtn = findViewById(R.id.storeOrderBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            /**
             Calls the goBack function to go back to the main menu on click.
             @param v The go back button that was clicked.
             */
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        ListAdapter listAdapter = new ListAdapter(getApplicationContext(),MainActivity.storeOrders.storeOrders);
        allOrders.setAdapter(listAdapter);
        allOrders.setClickable(true);
    }

    /**
     Switches the screen to go back to the main menu, where other activities can be done.
     */
    private void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
