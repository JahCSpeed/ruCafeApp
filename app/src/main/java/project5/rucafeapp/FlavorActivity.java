package project5.rucafeapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import donut.Donut;
import donut.DonutPrices;

/**
 The FlavorActivity is capable for customizing the given donut flavor for an order.
 Has 3 donut types: Cake, Yeast, and Donut Holes. The quantity of the donuts can also
 be changed in this activity.
 @author Jah C. Speed, Abe Vitangcol
 */
public class FlavorActivity extends AppCompatActivity {
    private Button createOrder,backBtn;
    private RadioButton yeastButton,cakeButton,holeButton;
    private TextView donutAmount,flavorTxt;

    private int amount;
    private final String keyForFlavor = "Flavors";

    /**
     Creates the FlavorActivity screen with the necessary fields and buttons to do its job.
     @param savedInstanceState The data that was previously contained in other screens (like orders).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flavor_choice_specs);
        this.flavorTxt = findViewById(R.id.flavorChoice);
        this.createOrder = findViewById(R.id.createOrder);
        this.yeastButton = findViewById(R.id.yeastBtn);
        this.cakeButton = findViewById(R.id.cakeBtn);
        this.holeButton = findViewById(R.id.holeBtn);
        this.donutAmount = findViewById(R.id.donutAmt);
        this.backBtn = findViewById(R.id.flavorBackBtn);
        this.backBtn.setOnClickListener(new View.OnClickListener() {
            /**
             Goes back to the flavor selection screen upon clicking the go back button.
             @param v The go back button that was clicked on.
             */
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        createOrder.setOnClickListener(new View.OnClickListener() {
            /**
             Confirms the current order of donuts upon clicking the confirm order button.
             @param v The confirm order button that was clicked.
             */
            @Override
            public void onClick(View v) {
                confirmOrder();
            }
        });
        String flavorString = "NULL";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            flavorString = extras.getString(keyForFlavor);
        }
        flavorTxt.setText(flavorString);

    }

    /**
     Goes back one screen, back to the DonutsScreen to select a flavor.
     Refer more about what it does in DonutsScreen.java.
     */
    private void goBack() {
        Intent intent = new Intent(this, DonutsScreen.class);
        startActivity(intent);
    }

    /**
     Confirms the order of donuts given the type of donuts and the amount.
     Notifies the user if the order was successful and returns them back to the flavor selection.
     Gives an error message if the amount given was invalid.
     */
    public void confirmOrder() {
        String type = getDonutType();
        String flavor = String.valueOf(this.flavorTxt.getText());
        if(type == null || flavor == null || checkAmount() == -1) {
            return;
        }
        switch(type) {
            case "Yeast Donut":
                MainActivity.currentOrder.add(new Donut(DonutPrices.YEAST.name, flavor, DonutPrices.YEAST.price, amount));
                break;
            case "Cake Donut":
                MainActivity.currentOrder.add(new Donut(DonutPrices.CAKE.name, flavor, DonutPrices.CAKE.price, amount));
                break;
            case "Donut Hole":
                MainActivity.currentOrder.add(new Donut(DonutPrices.HOLE.name, flavor, DonutPrices.HOLE.price, amount));
                break;
            default:
                return;
        }

        openDonutScreen();
    }

    /**
     Goes back to the DonutScreen upon a successful order being placed.
     */
    public void openDonutScreen() {
        Intent intent = new Intent(this, DonutsScreen.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Order Added Successfully", Toast.LENGTH_SHORT).show();
    }

    /**
     Returns the type of donut being selected.
     Throws an error if there is no type of donut selected.
     @return The type of donut selected in a string format, null otherwise.
     */
    private String getDonutType() {
        if (this.yeastButton.isChecked())
            return this.yeastButton.getText().toString();
        if (this.cakeButton.isChecked())
            return this.cakeButton.getText().toString();
        if (this.holeButton.isChecked())
            return this.holeButton.getText().toString();
        createAlertBox("Please select a donut type!","Hello Friend","Ok");
        return null;

    }

    /**
     Crates an alert box, acting as an error message for the donuts (flavor) screen.
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
            this.amount = Integer.parseInt(donutAmount.getText().toString());
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

}
