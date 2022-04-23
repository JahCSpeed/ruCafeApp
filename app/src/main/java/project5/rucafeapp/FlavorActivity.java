package project5.rucafeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import donut.Donut;
import donut.DonutPrices;

public class FlavorActivity extends AppCompatActivity {
    private Button createOrder,backBtn;
    private RadioButton yeastButton,cakeButton,holeButton;
    private TextView donutAmount,flavorTxt;

    private int amount;
    private final String keyForFlavor = "Flavors";
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
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        createOrder.setOnClickListener(new View.OnClickListener() {
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
    private void goBack() {
        Intent intent = new Intent(this, DonutsScreen.class);
        startActivity(intent);
    }
    public void confirmOrder() {
        if(MainActivity.currentOrder.orderList == null){
            System.out.println("Its Null");
            return;
        }else{
            System.out.println("Its  NOT !!!!! !! Null");

        }
        String type = getDonutType();
        String flavor = String.valueOf(this.flavorTxt.getText());
        if(checkDonutType() == -1 || flavor == null || checkAmount() == -1) {
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
    public void openDonutScreen() {
        Intent intent = new Intent(this, DonutsScreen.class);
        startActivity(intent);
    }
    private String getDonutType(){
        if(this.yeastButton.isChecked())
            return this.yeastButton.getText().toString();
        if(this.cakeButton.isChecked())
            return this.cakeButton.getText().toString();
        if(this.holeButton.isChecked())
            return this.holeButton.getText().toString();
        return null;

    }
    private int checkDonutType(){
        if(!this.yeastButton.isChecked() && !this.cakeButton.isChecked() && !this.holeButton.isChecked()){
            System.out.println("No Flavor Selected");
            return -1;
        }
        return 0;
    }
    private int checkAmount(){
        try{
            this.amount = Integer.parseInt(donutAmount.getText().toString());
            if(this.amount <= 0){
                System.out.println("No Amount Selected");
                return -1;
            }
            return 0;
        }catch(Exception NumberFormatException){
            System.out.println("Must input an valid amount");
            return -1;
        }

    }

}
