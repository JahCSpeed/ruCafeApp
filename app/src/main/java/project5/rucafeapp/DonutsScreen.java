package project5.rucafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 The DonutsScreen has a list of flavors to select to select the donut flavor.
 This is only part 1 of 2 of the donut process. The actual ordering and customization
 is shown in the FlavorActivity.java.
 @author Jah C. Speed, Abe Vitangcol
 */
public class DonutsScreen extends AppCompatActivity {
    private ArrayList<Flavor> flavorList;
    private RecyclerView flavorView;
    private RecyclerAdaptor.RecyclerViewClickListener listener;
    private ListView donutOrders;
    private Button backBtn;
    private final String keyForFlavor = "Flavors";

    /**
     Creates the DonutsScreen and necessary buttons when called.
     @param savedInstanceState data that was previously contained in other screens.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donuts);
        flavorView = findViewById(R.id.donutFlavorRecycle);
        donutOrders = findViewById(R.id.allOrderListView);
        backBtn = findViewById(R.id.donutBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            /**
             Goes back to the main
             @param v
             */
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        flavorList = new ArrayList<>();
        addFlavors();
        setFlavorList();
        ListAdapter listAdapter = new ListAdapter(getApplicationContext(),MainActivity.currentOrder.getItemList(0), 0);
        donutOrders.setAdapter(listAdapter);
        donutOrders.setClickable(true);
    }

    /**
     Goes back one screen, back to the main menu.
     */
    private void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     Creates and sets up the flavor list and creates a new view upon having a flavor selected.
     Refer to FlavorActivity.java for more information about the flavor view.
     */
    private void setFlavorList(){
        setOnClickListener();
        RecyclerAdaptor adaptor = new RecyclerAdaptor(flavorList,listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        flavorView.setLayoutManager(layoutManager);
        flavorView.setItemAnimator(new DefaultItemAnimator());
        flavorView.setAdapter(adaptor);

    }

    /**
     Creates a recycler adaptor for the list of flavors to be clicked on.
     */
    private void setOnClickListener() {
        listener = new RecyclerAdaptor.RecyclerViewClickListener() {
            /**
             Gives the name and position of the flavor clicked on the recycler list.
             @param view The name of the flavor clicked on.
                    position The position this flavor is on the list of flavors.
             */
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(),FlavorActivity.class);
                intent.putExtra(keyForFlavor,flavorList.get(position).getFlavorType());
                startActivity(intent);
            }
        };

    }

    /**
     Add a list of flavors into the flavor list.
     */
    private void addFlavors(){
        flavorList.add(new Flavor("Chocolate Frosted Doughnut"));
        flavorList.add(new Flavor("Cinnamon Twist Doughnut"));
        flavorList.add(new Flavor("Cruller"));
        flavorList.add(new Flavor("Strawberry Frosted Doughnut"));
        flavorList.add(new Flavor("Old-fashioned Doughnut"));
        flavorList.add(new Flavor("Jelly Doughnut"));
        flavorList.add(new Flavor("Blueberry Doughnut"));
        flavorList.add(new Flavor("Glazed Doughnut."));
        flavorList.add(new Flavor("Sprinkles"));
        flavorList.add(new Flavor("Chocolate Cake"));
        flavorList.add(new Flavor("Cookies and Cream"));
        flavorList.add(new Flavor("Vanilla Red Velvet"));
    }

}