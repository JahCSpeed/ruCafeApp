package project5.rucafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import coffee.Coffee;
import coffee.CoffeePrices;
import donut.Donut;
import donut.DonutPrices;

public class CoffeeScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String drinkSize = "";
    private int amount = 0;
    private TextView coffeeAmount;
    private CheckBox creamButton,milkButton,whippedCreamButton,caramelButton,syrupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        Button backBtn = findViewById(R.id.coffeeBackBtn);
        Button createOrder = findViewById(R.id.createOrderCoffee);
        creamButton = findViewById(R.id.creamBtn);
        milkButton = findViewById(R.id.milkBtn);
        whippedCreamButton = findViewById(R.id.whippedCreamBtn);
        caramelButton = findViewById(R.id.caramelBtn);
        syrupButton = findViewById(R.id.syrupBtn);
        this.coffeeAmount = findViewById(R.id.coffeAmt);
        createOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmOrder();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        Spinner sizeSpinner = findViewById(R.id.sizeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,R.array.drinkSizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapter);
        sizeSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.drinkSize = adapterView.getItemAtPosition(i).toString();
    }
    private void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void confirmOrder() {
        if(checkAmount() == -1){
            return;
        }
        Coffee orderCoffee = null;
        switch(this.drinkSize){
            case "Small":
                orderCoffee = new Coffee(this.drinkSize,"Black Coffee", CoffeePrices.SMALL.price, amount);
            case "Tall":
                orderCoffee = new Coffee(this.drinkSize,"Black Coffee", CoffeePrices.TALL.price, amount);
            case "Grande":
                orderCoffee = new Coffee(this.drinkSize,"Black Coffee", CoffeePrices.GRANDE.price, amount);
            case "Venti":
                orderCoffee = new Coffee(this.drinkSize,"Black Coffee", CoffeePrices.VENTI.price, amount);
            default:
                orderCoffee = new Coffee(this.drinkSize,"Black Coffee", CoffeePrices.SMALL.price, amount);
        }
        for(String addin : getAddons()){
            orderCoffee.add(addin);
        }
        MainActivity.currentOrder.add(orderCoffee);
        Toast.makeText(getApplicationContext(), "Order Added Successfully", Toast.LENGTH_SHORT).show();
        startActivity(getIntent());
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
    private int checkAmount(){
        try{
            this.amount = Integer.parseInt(coffeeAmount.getText().toString());
            if(this.amount <= 0){
                createAlertBox("Please enter a number greater then 0!","Hello Friend","Ok");
                return -1;
            }
            return 0;
        }catch(Exception NumberFormatException){
            createAlertBox("Please enter a valid number greater then 0!","Hello Friend","Ok");
            return -1;
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        this.drinkSize = adapterView.getItemAtPosition(0).toString();
    }

    private ArrayList<String> getAddons(){
        ArrayList<String> addons = new ArrayList<>();
        if(creamButton.isChecked())
            addons.add(creamButton.getText().toString());
        if(milkButton.isChecked())
            addons.add(milkButton.getText().toString());
        if(whippedCreamButton.isChecked())
            addons.add(whippedCreamButton.getText().toString());
        if(caramelButton.isChecked())
            addons.add(caramelButton.getText().toString());
        if(syrupButton.isChecked())
            addons.add(syrupButton.getText().toString());
        return addons;
    }
}