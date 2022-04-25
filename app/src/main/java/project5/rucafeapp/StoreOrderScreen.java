package project5.rucafeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class StoreOrderScreen extends AppCompatActivity {
    private Button backBtn;
    private ListView allOrders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_storeorders);
        allOrders = findViewById(R.id.allStoreOrderListView);
        backBtn = findViewById(R.id.storeOrderBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        ListAdapter listAdapter = new ListAdapter(getApplicationContext(),MainActivity.storeOrders.storeOrders);
        allOrders.setAdapter(listAdapter);
        allOrders.setClickable(true);
    }
    private void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
