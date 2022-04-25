package project5.rucafeapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import mainMenu.MenuItem;

public class OrderScreen extends AppCompatActivity {
    private ListView allOrders;
    private static TextView subtotalTxt,salesTaxTxt,totalTxt,orderNumberTxt;
    private Button backBtn,orderBtn;
    private static final double SALES_TAX = ((6.625)/100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        allOrders = findViewById(R.id.allOrderListView);
        subtotalTxt = findViewById(R.id.subtotalTxt);
        subtotalTxt.setMovementMethod(new ScrollingMovementMethod());
        salesTaxTxt = findViewById(R.id.salestaxTxt);
        salesTaxTxt.setMovementMethod(new ScrollingMovementMethod());
        totalTxt = findViewById(R.id.totalTxt);
        totalTxt.setMovementMethod(new ScrollingMovementMethod());
        orderNumberTxt = findViewById(R.id.orderNumber);
        backBtn = findViewById(R.id.orderBackBtn);
        orderBtn = findViewById(R.id.submitOrderBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });
        ListAdapter listAdapter = new ListAdapter(getApplicationContext(),MainActivity.currentOrder.orderList,-1);
        allOrders.setAdapter(listAdapter);
        allOrders.setClickable(true);
        updateTotals();

    }

    private void placeOrder() {
        if(MainActivity.currentOrder.isEmpty()){
            createAlertBox("Can't place an empty order","Error!","Ok");
            return;
        }
        MainActivity.storeOrders.add(MainActivity.currentOrder);
        MainActivity.resetOrder();
        Toast.makeText(getApplicationContext(), "Order Placed", Toast.LENGTH_LONG).show();
        updateTotals();
        ListAdapter listAdapter = new ListAdapter(getApplicationContext(),MainActivity.currentOrder.orderList,-1);
        allOrders.setAdapter(listAdapter);
    }
    private void createAlertBox(String message,String title,String positiveMessageBtn){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton(positiveMessageBtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public static void updateTotals(){
        DecimalFormat format = new DecimalFormat("###,##0.00");
        double subtotal = 0;
        double salesTax = 0;
        double finalTotal = 0;
        for(MenuItem e : MainActivity.currentOrder.orderList) {
            subtotal+= e.itemPrice();
        }
        salesTax = subtotal * SALES_TAX;
        finalTotal = subtotal + salesTax;
        try {
            subtotalTxt.setText("Subtotal: $" + format.format(subtotal));
            salesTaxTxt.setText("Sales Tax: $" + format.format(salesTax));
            totalTxt.setText("Total: $" + format.format(finalTotal));
            orderNumberTxt.setText("Order Number: " + MainActivity.currentOrder.orderNumber);
        }catch(NullPointerException e){
            return;

        }
    }
}
