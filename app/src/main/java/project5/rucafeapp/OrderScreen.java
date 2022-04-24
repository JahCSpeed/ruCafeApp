package project5.rucafeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import mainMenu.MenuItem;

public class OrderScreen extends AppCompatActivity {
    private ListView allOrders;
    private TextView subtotalTxt,salesTaxTxt,totalTxt,orderNumberTxt;
    private Button backBtn;
    private final double SALES_TAX = ((6.625)/100);
    private final DecimalFormat format = new DecimalFormat("###,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        allOrders = findViewById(R.id.allOrderListView);
        subtotalTxt = findViewById(R.id.subtotalTxt);
        salesTaxTxt = findViewById(R.id.salestaxTxt);
        totalTxt = findViewById(R.id.totalTxt);
        orderNumberTxt = findViewById(R.id.orderNumber);
        backBtn = findViewById(R.id.orderBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        ListAdapter listAdapter = new ListAdapter(getApplicationContext(),MainActivity.currentOrder.orderList,0);
        allOrders.setAdapter(listAdapter);
        allOrders.setClickable(true);
        onContentChanged();
        updateTotals();
    }
    private void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void updateTotals(){
        double subtotal = 0;
        double salesTax = 0;
        double finalTotal = 0;
        for(MenuItem e : MainActivity.currentOrder.orderList) {
            subtotal+= e.itemPrice();
        }
        salesTax = subtotal * SALES_TAX;
        finalTotal = subtotal + salesTax;
        subtotalTxt.setText("Subtotal: $" + format.format(subtotal));
        salesTaxTxt.setText("Sales Tax: $" + format.format(salesTax));
        totalTxt.setText("Total: $" + format.format(finalTotal));
        orderNumberTxt.setText("Order Number: " + MainActivity.currentOrder.orderNumber);
    }
}
