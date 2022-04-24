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

public class DonutsScreen extends AppCompatActivity {
    private ArrayList<Flavor> flavorList;
    private RecyclerView flavorView;
    private RecyclerAdaptor.RecyclerViewClickListener listener;
    private ListView donutOrders;
    private Button backBtn;
    private final String keyForFlavor = "Flavors";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donuts);
        flavorView = findViewById(R.id.donutFlavorRecycle);
        donutOrders = findViewById(R.id.allOrderListView);
        backBtn = findViewById(R.id.donutBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        flavorList = new ArrayList<>();
        addFlavors();
        setFlavorList();
        ListAdapter listAdapter = new ListAdapter(getApplicationContext(),MainActivity.currentOrder.orderList, 1);
        donutOrders.setAdapter(listAdapter);
        donutOrders.setClickable(true);
    }

    private void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setFlavorList(){
        setOnClickListener();
        RecyclerAdaptor adaptor = new RecyclerAdaptor(flavorList,listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        flavorView.setLayoutManager(layoutManager);
        flavorView.setItemAnimator(new DefaultItemAnimator());
        flavorView.setAdapter(adaptor);

    }

    private void setOnClickListener() {
        listener = new RecyclerAdaptor.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(),FlavorActivity.class);
                intent.putExtra(keyForFlavor,flavorList.get(position).getFlavorType());
                startActivity(intent);
            }
        };

    }

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