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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import coffee.Coffee;
import coffee.CoffeePrices;

/**
 The CoffeeScreen class handles the user interface operations for coffee related activities.
 Capable of ordering coffee to send to the other screens. Coffee items can be customized
 and is able to throw errors upon faulty order confirmations.
 @author Jah C. Speed, Abe Vitangcol
 */
public class CoffeeScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String drinkSize = "";
    private int amount = 0;
    private TextView coffeeAmount;
    private CheckBox creamButton,milkButton,whippedCreamButton,caramelButton,syrupButton;

    /**
     Creates the CoffeeScreen and necessary buttons when called.
     @param savedInstanceState Data that was previously contained in other screens.
     */
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
        this.coffeeAmount = findViewById(R.id.coffeeAmt);
        createOrder.setOnClickListener(new View.OnClickListener() {
            /**
             Performs the confirm order button upon clicking this item.
             @param v The button to click to confirm the order.
             */
            @Override
            public void onClick(View v) {
                confirmOrder();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            /**
             Goes back one screen to the main menu.
             @param v The button to click to go back a screen.
             */
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

    /**
     Gets the item selected for the drink size and updates the size of this coffee.
     @param adapterView The view to change the size of coffee.
     @param view The view within the adapterview that was clicked.
     @param i The integer position the item is.
     @param l The row the item is in.
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.drinkSize = adapterView.getItemAtPosition(i).toString();
    }

    /**
     Goes back one screen, back to the main menu screen.
     */
    private void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     Confirms the coffee order and customizations made on it.
     Shows a message at the bottom if the order was successful.
     Throws an error if there was a problem with the order, such as invalid amount given.
     */
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

    /**
     Crates an alert box, acting as an error message for the coffee screen.
     @param message The message to be put into the box, generally an error message.
            title The title of the box.
            positiveMessageBtn The button message to close the box.
     */
    private void createAlertBox(String message,String title,String positiveMessageBtn){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton(positiveMessageBtn, new DialogInterface.OnClickListener() {
            /**
             Closes the alertbox upon clicking the close button.
             @param dialog The alert box that appeared.
             @param which The position of this alert box.
             */
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     Checks if the amount entered is a number greater than 0 to make an order.
     Throws an alert box if the number is not greater than 0.
     @return -1 if the number is not greater than 0 or not a number, 0 otherwise.
     */
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

    /**
     Changes the drink size to small as default.
     @param adapterView The view to change the size of the coffee cups.
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        this.drinkSize = adapterView.getItemAtPosition(0).toString();
    }

    /**
     Returns the list of addons that were checked for this coffee order.
     @return The list of addons for this coffee order.
     */
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