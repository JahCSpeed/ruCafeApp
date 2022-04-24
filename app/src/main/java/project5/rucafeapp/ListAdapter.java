package project5.rucafeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

import coffee.Coffee;
import donut.Donut;
import mainMenu.MenuItem;
import orders.Order;

public class ListAdapter extends BaseAdapter {
    Context context;
    ArrayList<MenuItem> donutArrayList;
    LayoutInflater inflater;
    private final String optionRemove = "Remove";
    private final String optionEdit = "Edit";
    private final String keyForFlavor = "Flavors";
    private final int DONUTVIEW = 1, COFFEEVIEW = 2, ALLORDERS = 0;
    private int orderType;
    public ListAdapter(Context context, ArrayList<MenuItem> arrayList, int orderType) {
        this.context = context;
        this.donutArrayList = arrayList;
        this.inflater = LayoutInflater.from(context);
        this.orderType = orderType;
    }

    @Override
    public int getCount() {
        if(orderType == DONUTVIEW){
            int counter = 0;
            for(MenuItem item: donutArrayList){
                if(item instanceof Donut){
                    counter++;
                }
            }
            return counter;
        }else if(orderType == COFFEEVIEW){
            int counter = 0;
            for(MenuItem item: donutArrayList){
                if(item instanceof Coffee){
                    counter++;
                }
            }
            return counter;
        }
        return donutArrayList.size();

    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MenuItem orderItem = donutArrayList.get(position);
        convertView = inflater.inflate(R.layout.listview_item,null);
        TextView menuItem = convertView.findViewById(R.id.menuItemType);
        TextView menuDetails = convertView.findViewById(R.id.menuItemDetails);
        Button removeBtn = convertView.findViewById(R.id.removeOrder);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(orderItem);

            }
        });
        if(orderType == DONUTVIEW){
            if(orderItem instanceof Donut) {
                menuItem.setText(orderItem.getType());
                menuDetails.setText("Order Details:" + ((Donut) orderItem).toString());
                return convertView;
            }
        }

        if(orderType == COFFEEVIEW){
            if(orderItem instanceof  Coffee) {
                menuItem.setText(orderItem.getType());
                menuDetails.setText("Order Details:" + ((Coffee) orderItem).toString());
                return convertView;
            }
        }
        if(orderType == ALLORDERS){
            menuItem.setText(orderItem.getType());
            menuDetails.setText("Order Details:" + orderItem.toString());
            return convertView;
        }
        return convertView;
    }
    public void removeItem(MenuItem item) {
       if(MainActivity.currentOrder.remove(item)){
           Toast.makeText(context.getApplicationContext(), "Item Removed From Cart", Toast.LENGTH_LONG).show();
           notifyDataSetChanged();
       }else{
           Toast.makeText(context.getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
       }

    }


}
